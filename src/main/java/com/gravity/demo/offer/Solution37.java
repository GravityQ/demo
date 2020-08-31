package com.gravity.demo.offer;

import sun.security.util.Length;

/**
 * 统计一个数字在升序数组中出现的次数。
 */
public class Solution37 {
    public static void main(String[] args) {
//        int[] array = {1, 2, 3, 4, 5, 6, 7, 8};
        int[] array = {1, 2, 3, 3, 3, 3};
        System.out.println(new Solution37().GetNumberOfK(array, 3));
    }

    public int GetNumberOfK(int[] array, int k) {
        if (array == null || array.length == 0 || array[0] > k || array[array.length - 1] < k) {
            return 0;
        }
        int m = 0;
        int index = array.length / 2;
        while (array[index] > k) {
            index = index / 2;
        }
        while (array[array.length / 2] < k) {
            index = array.length / 2 + index / 2;
        }
        int i = index;
        while (i < array.length && array[i] <= k) {
            if (array[i] == k) {
                m++;
            }
            i++;
        }
        i = index - 1;
        while (i >= 0 && array[i] >= k) {
            if (array[i] == k) {
                m++;
            }
            i--;
        }
        return m;
    }
}