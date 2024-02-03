package com.java.low.level.design;

public class Main {
    public static void main(String[] args) {
        LeakyBucket leakyBucket = new LeakyBucket(10,2);

        for(int i=0; i<15; i++) {
            boolean allowed = leakyBucket.allowRequest(1);
            if (allowed) {
                System.out.println("Request " + (i + 1) + " is allowed.");
            } else {
                System.out.println("Request " + (i + 1) + " is denied.");
            }
            try {
                Thread.sleep(1000); // Sleep for 1 second
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}