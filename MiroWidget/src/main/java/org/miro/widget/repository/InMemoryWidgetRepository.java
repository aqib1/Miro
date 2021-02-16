package org.miro.widget.repository;

import org.miro.widget.domain.MiroWidget;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

@Repository
public class InMemoryWidgetRepository {
    private ReadWriteLock lock = new ReentrantReadWriteLock();
    private Queue<MiroWidget> minHeap;

    @Autowired
    public InMemoryWidgetRepository() {
        // min heap logic on z index
        minHeap = new PriorityQueue<>(
                Comparator.comparingInt(p -> p.getCoordinate().getZ())
        );
    }

    public boolean save(MiroWidget miroWidget) {
        Lock writeLock = lock.writeLock();
        writeLock.lock();
        try {
            return minHeap.offer(miroWidget);
        } finally {
            writeLock.unlock();
        }
    }
}
