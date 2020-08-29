package com.gravity.demo.offer;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * 输入n个整数，找出其中最小的K个数。例如输入4,5,1,6,2,7,3,8这8个数字，则最小的4个数字是1,2,3,4。
 */
public class Solution29 {
    public static void main(String[] args) {
        int[] input = {4, 5, 1, 6, 2, 8, 3, 7};
        System.out.println(GetLeastNumbers(input, 4));
    }

    public static ArrayList<Integer> GetLeastNumbers(int[] input, int k) {
        if (input == null || k == 0 || k > input.length) {
            return new ArrayList<>();
        }
        if (k == input.length) {
            return (ArrayList) Arrays.asList(input);
        }
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>((o1, o2) -> o2 - o1);
        for (int i = 0; i < input.length; i++) {
            priorityQueue.add(input[i]);
            if (priorityQueue.size() > k) {
                priorityQueue.poll();
            }
        }
        return new ArrayList<>(priorityQueue);
    }
}