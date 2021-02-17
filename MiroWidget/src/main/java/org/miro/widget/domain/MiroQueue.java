package org.miro.widget.domain;


import java.util.*;

public class MiroQueue {
    private final Map<String, Integer> zIndexesRef = new HashMap<>();
    private Queue<MiroWidget> minHeap;

    private MiroQueue() {
        // min heap logic on z index
        minHeap = getMinHeapOnZIndex();
    }

    public boolean offer(MiroWidget miroWidget) {
        boolean offer = minHeap.offer(miroWidget);
        evaluateZIndexesForOffer(miroWidget.getUuid(), miroWidget.getCoordinate().getZ());
        return offer;
    }

    public List<MiroWidget> getAsList() {
        return new ArrayList<>(minHeap);
    }

    public MiroWidget getById(String uuid) {
        if (!zIndexesRef.containsKey(uuid))
            return null;
        // widget will never null as we already checked tracked keys above
        return minHeap.stream()
                .filter(x -> x.getUuid().equals(uuid))
                .findFirst()
                .get();
    }

    public MiroWidget update(MiroWidget widget) {
        if (!zIndexesRef.containsKey(widget.getUuid()))
            return null;
        // widget will never null as we already checked tracked keys above
        MiroWidget old = minHeap.stream()
                .filter(x -> x.getUuid().equals(widget.getUuid()))
                .findFirst()
                .get();
        zIndexesRef.remove(old.getUuid());
        minHeap.remove(old);
        minHeap.offer(widget);
        evaluateZIndexesForOffer(widget.getUuid(), widget.getCoordinate().getZ());
        return widget;
    }

    public boolean delete(String uuid) {
        if (!zIndexesRef.containsKey(uuid))
            return false;
        // widget will never null as we already checked tracked keys above
        MiroWidget old = minHeap.stream()
                .filter(x -> x.getUuid().equals(uuid))
                .findFirst()
                .get();

        zIndexesRef.remove(uuid);
        return minHeap.remove(old);
    }

    public int deleteAll() {
        if (zIndexesRef.isEmpty())
            return 0;
        int size = zIndexesRef.size();
        zIndexesRef.clear();
        minHeap.clear();
        return size;
    }

    private void evaluateZIndexesForOffer(String uuid, int z) {
        if (zIndexesRef.containsValue(z)) {
            zIndexesRef.put(uuid, updateHeap());
        } else {
            zIndexesRef.put(uuid, z);
        }
    }

    private int updateHeap() {
        int newlyAddedIndex = 0;
        Queue<MiroWidget> temp = getMinHeapOnZIndex();
        MiroWidget top = minHeap.poll();
        temp.offer(top);
        while (!minHeap.isEmpty()) {
            if (top.getCoordinate().getZ().equals(minHeap.peek().getCoordinate().getZ())) {
                MiroWidget current = minHeap.poll();
                current.getCoordinate().setZ(top.getCoordinate().getZ() + 1);
                newlyAddedIndex = current.getCoordinate().getZ();
                minHeap.offer(current);
            } else {
                top = minHeap.poll();
                temp.offer(top);
            }
        }
        minHeap = temp;
        return newlyAddedIndex;
    }

    private Queue<MiroWidget> getMinHeapOnZIndex() {
        return new PriorityQueue<>(
                Comparator.comparingInt(p -> p.getCoordinate().getZ())
        );
    }

    /**
     * Initialization on demand pattern
     * this pattern is alternative of double check locking pattern
     * which not even support lazy loading but also safe to use in
     * multi-processor distributed instances
     */

    private static class InstanceHolder {
        private static final MiroQueue INSTANCE = new MiroQueue();

        private InstanceHolder() {

        }
    }

    public static MiroQueue getInstance() {
        return InstanceHolder.INSTANCE;
    }

}
