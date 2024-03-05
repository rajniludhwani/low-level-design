package com.java.low.level.design;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

class Node {
    private final int value;
    private final String server;

    public Node(int value, String server) {
        this.value = value;
        this.server = server;
    }

    public int getValue() {
        return value;
    }

    public String getServer() {
        return server;
    }
}
public class ConsistentHashing2 {
    private final int vNodeCount;
    private final MessageDigest md;
    private final Map<String, Integer> vNodes;
    private final List<String> servers;
    private final List<Node> ring;
    private final int replicas;

    public ConsistentHashing2(List<String> servers, String algorithm, Map<String, Integer> options) throws NoSuchAlgorithmException {
        this.vNodeCount = options.containsKey("vnode-count") ? options.get("vnode-count") : 2;
        this.md = MessageDigest.getInstance(algorithm);
        this.vNodes = new HashMap<>();
        this.servers = servers;
        this.ring = new ArrayList<>();
        this.replicas = 2;
        parse();
        generateHashRing();
    }

    private void parse() {
        for (String server : servers) {
            vNodes.put(server, 1);
        }
    }

    public int[] digest(String key) {
        String hashedValue = md5(key);
        char[] chars = hashedValue.toCharArray();
        int[] digest = new int[chars.length];
        for (int i = 0; i < chars.length; i++) {
            digest[i] = (int) chars[i];
        }
        return digest;
    }

    private String md5(String key) {
        md.update(key.getBytes());
        byte[] digestBytes = md.digest();
        StringBuilder sb = new StringBuilder();
        for (byte b : digestBytes) {
            sb.append(String.format("%02x", b & 0xff));
        }
        return sb.toString();
    }

    public int convertTo32Bit(int a, int b, int c, int d) {
        return ((a << 24) | (b << 16) | (c << 8) | d) >>> 0;
    }

    private int hashValue(String key) {
        int[] x = digest(key);
        return convertTo32Bit(x[3], x[2], x[1], x[0]);
    }

    public String get(String key) {
        int hashedValue = hashValue(key);
        int index = find(hashedValue);
        return ring.get(index).getServer();
    }

    private int find(int hashedValue) {
        int high = ring.size();
        int low = 0;
        int prev;
        while (low <= high) {
            int mid = (low + high) / 2;
            int middle = ring.get(mid).getValue();
            prev = mid == 0 ? 0 : ring.get(mid - 1).getValue();
            if (hashedValue <= middle && hashedValue > prev) return mid;
            if (middle < hashedValue) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return 0;
    }

    private void generateHashRing() {
        for (String server : servers) {
            int totalWeight = 0;
            for(int weight : vNodes.values()) {
                totalWeight += weight;
            }
            double percentage = (double) totalWeight / servers.size();
            int vNodeCount = vNodes.containsKey(server) ? vNodes.get(server) : this.vNodeCount;
            int length = (int) Math.floor(percentage * vNodeCount * servers.size());

            for (int i = 0; i < length; i++) {
                int[] x = digest(server + "-" + i);
                for (int j = 0; j < replicas; j++) {
                    int key = convertTo32Bit(x[3 + j * 4], x[2 + j * 4], x[1 + j * 4], x[j * 4]);
                    ring.add(new Node(key, server));
                }
            }
        }
        ring.sort(Comparator.comparingInt(Node::getValue));
        }

    public static void main(String[] args) throws NoSuchAlgorithmException {
        List<String> servers = Arrays.asList("127.0.0.1:11211", "127.0.0.2:11211", "127.0.0.3:11211");
        Map<String, Integer> options = new HashMap<>();
        ConsistentHashing2 ch = new ConsistentHashing2(servers, "MD5", options);

        System.out.println("Omkar : " + ch.get("omkar"));
        System.out.println("Zebra : " + ch.get("zebra"));
    }
}