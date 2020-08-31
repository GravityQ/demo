package com.gravity.demo.offer;

import com.gravity.demo.offer.tool.TreeNode;

/**
 * 输入一棵二叉树，判断该二叉树是否是平衡二叉树。
 * 在这里，我们只需要考虑其平衡性，不需要考虑其是不是排序二叉树
 */
public class Solution39 {
    // TODO: 2020/8/31 未作出 
    public static void main(String[] args) {
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);
        TreeNode node6 = new TreeNode(6);
        TreeNode node7 = new TreeNode(7);
        TreeNode node8 = new TreeNode(8);
        TreeNode node9 = new TreeNode(9);
        node1.left = node2;
        node2.left = node4;
        node2.right = node5;
        node5.left = node7;
        node1.right = node3;
        node3.right = node6;
        node4.left = node5;
        System.out.println(new Solution39().IsBalanced_Solution(node1));
    }

    public boolean IsBalanced_Solution(TreeNode root) {
        if (root == null) {
            return true;
        }
        if (root.left == null && root.right != null) {
            if (root.right.left == null && root.right.right == null) {
                return true;
            }
        }
        if (root.right == null && root.left != null) {
            if (root.left.left == null && root.left.right == null) {
                return true;
            }
        }
        return IsBalanced_Solution(root.left) && IsBalanced_Solution(root.right);
    }
}