package com.arts.DataStructure;

public class TreeNode {
    TreeNode leftNode;
    TreeNode rightNode;
    int value;

    public TreeNode(int value) {
        this.value = value;
    }

    public TreeNode(TreeNode leftNode, TreeNode rightNode, int value) {
        this.leftNode = leftNode;
        this.rightNode = rightNode;
        this.value = value;
    }

    public void display() {
        System.out.println(value);
    }
}
