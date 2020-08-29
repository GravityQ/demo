package com.gravity.demo.offer;

import java.util.ArrayList;
import java.util.HashSet;

public class Solution27 {
    public static void main(String[] args) {
        String str = "abcd";
        String str1 = "abac";
        System.out.println(Permutation(str));
        System.out.println(Permutation(str1));
    }

    /**
     * 输入一个字符串,按字典序打印出该字符串中字符的所有排列。
     * 例如输入字符串abc,则按字典序打印出由字符a,b,c所能排列出来的所有字符串abc,acb,bac,bca,cab和cba。
     *
     * @param str
     * @return
     */
    //输入一个字符串,长度不超过9(可能有字符重复),字符只包括大小写字母。
    public static ArrayList<String> Permutation(String str) {
        if (str == null || str.length() == 0) {
            return new ArrayList<>();
        }
        char[] chars = str.toCharArray();
        HashSet<String> set = new HashSet<>();
        perm(0, chars, set);
        return new ArrayList<>(set);

    }

    private static void perm(int index, char[] str, HashSet set) {
        if (index + 1 == str.length) {
            set.add(String.valueOf(str));
            return;
        }
        for (int i = index; i < str.length; i++) {
            swap(str,index, i);
            perm(index + 1, str, set);
            swap(str, index, i);
        }
    }

    private static void swap(char[] str, int a,int b) {
        char tem = str[a];
        str[a] = str[b];
        str[b] = tem;
    }
}