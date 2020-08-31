package com.gravity.demo.offer;

/**
 * 在数组中的两个数字，如果前面一个数字大于后面的数字，则这两个数字组成一个逆序对。
 * 输入一个数组,求出这个数组中的逆序对的总数P。
 * 并将P对1000000007取模的结果输出。 即输出P%1000000007
 *
 * 题目保证输入的数组中没有的相同的数字
 *
 * 数据范围：
 *
 * 	对于%50的数据,size<=10^4
 *
 * 	对于%75的数据,size<=10^5
 *
 * 	对于%100的数据,size<=2*10^5
 *
 * 	1,2,3,4,5,6,7,0
 * 	7
 */
public class Solution35 {
    // TODO: 2020/8/31 未作出
    public static void main(String[] args) {
        int[] array = {1, 2, 3, 4, 5, 6, 7, 0};
        System.out.println(new Solution35().InversePairs(array));
    }
    public int InversePairs(int [] array) {
        if (array==null||array.length==0) {
            return 0;
        }
        int p=0;
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < i; j++) {
                if (array[i] < array[j]) {
                   p++;
                }
            }
        }
        return p%1000000007;
    }
}