package com.java.low.level.design;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LeakyBucket {
    private int capacity;
    private int rate;
    private int tokens;
    private long lastRefillTimeStamp;
    private Lock lock;

    public LeakyBucket(int capacity, int rate) {
        this.capacity = capacity;
        this.rate = rate;
        this.tokens = 0;
        this.lastRefillTimeStamp = System.currentTimeMillis();
        this.lock = new ReentrantLock();
    }

    public boolean allowRequest(int tokensRequested) {
        try {
            lock.lock();
            refill();
            if(tokens > tokensRequested) {
                tokens -= tokensRequested;
                return true;
            }
            return false;
        } finally {
            lock.unlock();
        }
    }

    private void refill() {
        long currentTime = System.currentTimeMillis();
        long elapsedTime = currentTime - lastRefillTimeStamp;
        int tokensToAdd = (int)elapsedTime*rate/1000;
        tokens = Math.min(capacity, tokens + tokensToAdd);
        lastRefillTimeStamp = currentTime;
    }
}
