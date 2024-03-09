package com.java.low.level.design;

public class Main {
    public static void main(String[] args) {
        Splitwise splitwise = new Splitwise();
        try {
            splitwise.demo();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}