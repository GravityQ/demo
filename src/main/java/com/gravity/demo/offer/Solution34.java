package com.gravity.demo.offer;


/**
 * 在一个字符串(0<=字符串长度<=10000，全部由字母组成)中
 * 找到第一个只出现一次的字符,并返回它的位置, 如果没有则返回 -1（需要区分大小写）.（从0开始计数）
 */
public class Solution34 {
    public static void main(String[] args) {
        String str = "ofjowijefonknfduinfdnfan";
        int i = new Solution34().FirstNotRepeatingChar(str);
        System.out.println(i);

    }

    public int FirstNotRepeatingChar(String str) {

        if (str == null || str.length() == 0) {
            return -1;
        }
        int length = str.length();
        int[] index = new int[58];
        for (int i = 0; i < length; i++) {
            int c = str.charAt(i)-'A';
            index[c]++;
        }
        for (int i = 0; i < length; i++) {
            if (index[str.charAt(i)-'A']==1) {
                return i;
            }
        }
        return -1;
    }

}