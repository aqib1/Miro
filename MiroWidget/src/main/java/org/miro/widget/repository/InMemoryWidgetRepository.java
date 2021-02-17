package org.miro.widget.repository;

import org.miro.widget.domain.MiroQueue;
import org.miro.widget.domain.MiroWidget;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

@Repository
public class InMemoryWidgetRepository {
    private ReadWriteLock lock = new ReentrantReadWriteLock();
    private Lock writeLock = lock.writeLock();
    private Lock readLock = lock.readLock();
    private MiroQueue miroQueue = MiroQueue.getInstance();

    public boolean save(MiroWidget miroWidget) {
        writeLock.lock();
        try {
            return miroQueue.offer(miroWidget);
        } finally {
            writeLock.unlock();
        }
    }

    public List<MiroWidget> getAll() {
        readLock.lock();
        try {
            return miroQueue.getAll();
        } finally {
            readLock.unlock();
        }
    }

    public MiroWidget getById(String uuid) {
        readLock.lock();
        try {
            return miroQueue.getById(uuid);
        } finally {
            readLock.unlock();
        }
    }

    public MiroWidget update(MiroWidget widget) {
        writeLock.lock();
        try {
            return miroQueue.update(widget);
        } finally {
            writeLock.unlock();
        }
    }

    public boolean delete(String uuid) {
        writeLock.lock();
        try {
            return miroQueue.delete(uuid);
        } finally {
            writeLock.unlock();
        }
    }

    public int deleteAll() {
        writeLock.lock();
        try {
            return miroQueue.deleteAll();
        } finally {
            writeLock.unlock();
        }
    }
}
