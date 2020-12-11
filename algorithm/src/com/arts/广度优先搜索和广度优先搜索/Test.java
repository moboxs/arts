package com.arts.广度优先搜索和广度优先搜索;

import com.arts.DataStructure.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

public class Test {

    public static void main(String[] args) {

    }

    public void BroadFirstSearch(TreeNode head) {
        if (head == null) {
            return;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(head);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();

            if (node.leftNode != null) {
                queue.add(node.leftNode);
            }
            if (node.rightNode != null) {
                queue.add(node.rightNode);
            }
        }
    }
}
