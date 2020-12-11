package com.arts.DataStructure;

public class TreeNode {
    public TreeNode leftNode;
    public TreeNode rightNode;
    public int value;

    public TreeNode(int value) {
        this.value = value;
    }

    public TreeNode(TreeNode leftNode, TreeNode rightNode, int value) {
        this.leftNode = leftNode;
        this.rightNode = rightNode;
        this.value = value;
    }

    public void display() {
        System.out.print(value + " ");
    }

    @Override
    public String toString() {
        return "TreeNode{" +
                "leftNode=" + leftNode +
                ", rightNode=" + rightNode +
                ", value=" + value +
                '}';
    }
}
