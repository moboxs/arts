package com.arts.DataStructure;

public class BinaryTree {
    private TreeNode root;

    public BinaryTree(int value){
        root = new TreeNode(value);
        root.leftNode = null;
        root.rightNode = null;
    }

    //二叉树最大深度
    public int maxDepth(TreeNode node){
        if (node == null) {
            return 0;
        }
        int left = maxDepth(node.leftNode);
        int right = maxDepth(node.rightNode);
        return Math.max(left, right) + 1;
    }

    //二叉树最小深度
    public int findMinDepth(TreeNode node) {
        if (node == null) {
            return 0;
        }
        return minDepth(node);
    }
    public int minDepth(TreeNode node) {

    }

    //查找元素
    public TreeNode find(int value) {
        TreeNode current = root;
        while (true) {
            if (value == current.value) {
                return current;
            } else if (value < current.value) {
                current = current.leftNode;
            } else if (value > current.value) {
                current = current.rightNode;
            }
            if (current == null) {
                return null;
            }
        }
    }

    public void insert(int value) {
        TreeNode node = new TreeNode(value);
        if (root == null) {
            root = node;
            root.leftNode = null;
            root.rightNode = null;
        } else {
            TreeNode current = root;
            TreeNode parent = null;
            while (true) {
                if (value < current.value) {
                    parent = current;
                    current = current.leftNode;
                    if (current == null) {
                        parent.leftNode = node;
                        break;
                    }
                } else if (value > current.value) {
                    parent = current;
                    current = current.rightNode;
                    if (current == null) {
                        parent.rightNode = node;
                        break;
                    }
                } else {
                    break;
                }
            }
        }
    }

    public void inOrderTraverse(TreeNode node) {
        if (node == null) {
            return;
        }
        inOrderTraverse(node.leftNode);
        node.display();
        inOrderTraverse(node.rightNode);
    }


    public void inOrderByStack() {

    }
    
    public static void main(String[] args){
        BinaryTree bt = new BinaryTree(5);
        bt.insert(2);
        bt.insert(3);
        bt.insert(4);
        bt.insert(0);
        bt.insert(1);
        bt.insert(7);
        bt.insert(8);
        System.out.println("Insert Done");

        System.out.println("-------二叉树最大深度------");
        System.out.println(bt.maxDepth(bt.root));



        System.out.println("------递归中序遍历------");
        bt.inOrderTraverse(bt.root);

    }
}
