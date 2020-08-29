package com.gravity.demo.offer;


import java.util.Arrays;

import static java.lang.Math.min;

/**
 * 把只包含质因子2、3和5的数称作丑数（Ugly Number）。
 * 例如6、8都是丑数，但14不是，因为它包含质因子7。
 * 习惯上我们把1当做是第一个丑数。求按从小到大的顺序的第N个丑数。
 * <p>
 * 丑数能够分解成2^x3^y5^z,
 * 所以只需要把得到的丑数不断地乘以2、3、5之后并放入他们应该放置的位置即可
 */
public class Solution33 {
    public static void main(String[] args) {
        int i = new Solution33().GetUglyNumber_Solution(100);
        System.out.println(i);

    }

    public int GetUglyNumber_Solution(int index) {
        int[] result = new int[index];
        result[0] = 1;
        int p2 = 0, p3 = 0, p5 = 0;
        for (int i = 1; i < index; i++) {
            result[i] = min(2 * result[p2], min(3 * result[p3], 5 * result[p5]));
            if (result[i] == 2 * result[p2]) {
                p2++;
            }
            if (result[i] == 3 * result[p3]) {
                p3++;
            }
            if (result[i] == 5 * result[p5]) {
                p5++;
            }
        }
        Arrays.stream(result).forEach(System.out::println);
        return result[index - 1];
    }

}