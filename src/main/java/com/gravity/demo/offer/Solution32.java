package com.gravity.demo.offer;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 输入一个正整数数组，把数组里所有数字拼接起来排成一个数，打印能拼接出的所有数字中最小的一个。
 * 例如输入数组{3，32，321}，则打印出这三个数字能排成的最小数字为321323。
 */
public class Solution32 {
    public static void main(String[] args) {
//        int[] param = {3, 32, 321};
        int[] param = {321};
        System.out.println(new Solution32().PrintMinNumber(param));

    }


    public String PrintMinNumber(int[] numbers) {
        if (numbers == null || numbers.length == 0) {
            return "";
        }
        List<Integer> list = Arrays.stream(numbers).boxed().collect(Collectors.toList());
        list.sort((o1, o2) -> {
            String s1 = String.valueOf(o1) + o2;
            String s2 = String.valueOf(o2) + o1;
            if (Long.parseLong(s1) >= Long.parseLong(s2)) {
                return 1;
            }
            return -1;
        });
        StringBuilder res = new StringBuilder();
        list.forEach(res::append);
        return res.toString();
    }
}