package com.arts.DataStructure;

import apple.laf.JRSUIUtils;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BinaryTree {
    private TreeNode root;

    BinaryTree(int value){
        root = new TreeNode(value);
        root.leftNode = null;
        root.rightNode = null;
    }

    //二叉树最大深度
    int maxDepth(TreeNode node){
        if (node == null) {
            return 0;
        }
        int left = maxDepth(node.leftNode);
        int right = maxDepth(node.rightNode);
        return Math.max(left, right) + 1;
    }

    //二叉树最小深度
    int findMinDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return minDepth(root);
    }
    int minDepth(TreeNode root) {
        if (root == null) {
            return Integer.MAX_VALUE;
        }
        if (root.leftNode == null && root.rightNode == null) {
            return 1;
        }
        return Math.min(minDepth(root.leftNode), minDepth(root.rightNode)) + 1;
    }

    //计算二叉树中节点个数
    int numOfTreeNode(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = numOfTreeNode(root.leftNode);
        int right = numOfTreeNode(root.rightNode);
        return left + right + 1;
    }

    //求二叉树叶子节点个数
    int numOfNoChildNode(TreeNode root) {
        if (root == null) {
            return 0;
        }
        if (root.leftNode == null && root.rightNode == null) {
            return 1;
        }
        return numOfNoChildNode(root.leftNode) + numOfNoChildNode(root.rightNode);
    }

    //求二叉树第k层节点的个数
    int numOfKLevelNode(TreeNode root, int k) {
        if (root == null || k<1) {
            return 0;
        }
        if (k == 1) {
            return 1;
        }
        int numOfLeft = numOfKLevelNode(root.leftNode, k-1);
        int numOfRight = numOfKLevelNode(root.rightNode, k-1);
        return numOfLeft + numOfRight;
    }

    //判断二叉树是否是平衡二叉树
    //又称AVL树，它是一棵空树，或者它的左右两个子树的高度差的绝对值不超过1，并且左右两个子树都是平衡二叉树。
    boolean isBalanced(TreeNode root) {
        return maxDepth2(root) != -1;
    }
    int maxDepth2(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int left = maxDepth2(root.leftNode);
        int right = maxDepth2(root.rightNode);
        if (left == -1 || right == -1 || Math.abs(left-right) > 1) {
            return -1;
        }
        return Math.max(left, right) + 1;
    }

    //判断二叉树是否是完全二叉树
    //若设二叉树的深度为k,除第k层外，其他各层（1-(k-1)）层的节点数都达到最大，且第k层所有的节点都连续集中在最左边。
    boolean isCompleteTreeNode(TreeNode node) {
        if (node == null) {
            return false;
        }
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.add(node);
        boolean result = true;
        boolean hasNoChild = false;
        while (!queue.isEmpty()) {
            TreeNode current = queue.remove();
            if (hasNoChild) {
                if (current.leftNode != null || current.rightNode != null) {
                    result = false;
                    break;
                }
            } else {
                if (current.leftNode != null && current.rightNode != null) {
                    queue.add(current.leftNode);
                    queue.add(current.rightNode);
                } else if (current.leftNode != null && current.rightNode == null) {
                    queue.add(current.leftNode);
                    hasNoChild = true;
                } else if (current.leftNode == null && current.rightNode != null) {
                    result = false;
                    break;
                } else {
                    hasNoChild = true;
                }
            }
        }
        return result;
    }

    //判断二叉树是否是满二叉树
    //一个二叉树，如果每一层的节点数都达到最大值，那么这个二叉树就是满二叉树
    boolean isFullTreeNode() {

        return false;
    }

    //判断两个二叉树是否相同
    boolean isSameTreeNode(TreeNode node1, TreeNode node2) {
        if (node1 == null && node2 == null) {
            return true;
        } else if (node1 == null || node2 == null) {
            return false;
        }
        if (node1.value != node2.value) {
            return false;
        }
        boolean left = isSameTreeNode(node1.leftNode, node2.leftNode);
        boolean right = isSameTreeNode(node1.rightNode, node2.rightNode);
        return left && right;
    }

    //判断两个二叉树是否互为镜像
    boolean isMirrorTreeNode(TreeNode node1, TreeNode node2) {
        if (node1 == null && node2 == null) {
            return true;
        } else if (node1 == null || node2 == null) {
            return false;
        }
        if (node1.value != node2.value) {
            return false;
        }
        boolean left = isMirrorTreeNode(node1.leftNode, node2.rightNode);
        boolean right = isMirrorTreeNode(node1.rightNode, node2.leftNode);
        return left && right;
    }

    //翻转二叉树
    TreeNode mirrorTreeNode(TreeNode root) {
        if (root == null) {
            return null;
        }
        TreeNode left = mirrorTreeNode(root.leftNode);
        TreeNode right = mirrorTreeNode(root.rightNode);
        root.leftNode = right;
        root.rightNode = left;
        return root;
    }

    //求两个二叉树的最低公共祖先节点
//    TreeNode getLastCommonParent(TreeNode root1, TreeNode root2) {
//
//    }


    //查找node是否在当前二叉树中
    boolean findNode(TreeNode root, TreeNode node) {
        if (root == null || node == null) {
            return false;
        }
        if (root == node) {
            return true;
        }
        boolean found = findNode(root.leftNode, node);
        if (!found) {
            found = findNode(root.rightNode, node);
        }
        return found;
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

    public void preOrder(TreeNode node) {
        if (node == null) {
            return;
        }
        node.display();
        preOrder(node.leftNode);
        preOrder(node.rightNode);
    }

    //二叉树层次遍历
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> list = new ArrayList<>();
        if (root == null) {
            return list;
        }

        Queue<TreeNode> q = new LinkedList<>();
        ((LinkedList<TreeNode>) q).add(root);
        while (!q.isEmpty()) {
            int levelSize = q.size();
            List<Integer> currLevel = new ArrayList<>();

            for (int i=0; i<levelSize; i++) {
                TreeNode curNode = q.poll();
                currLevel.add(curNode.value);
                if (curNode.leftNode != null) {
                    ((LinkedList<TreeNode>) q).add(curNode.leftNode);
                }
                if (curNode.rightNode != null) {
                    ((LinkedList<TreeNode>) q).add(curNode.rightNode);
                }
            }
            list.add(currLevel);
        }
        return list;
    }



    
    public static void main(String[] args){
        BinaryTree bt = new BinaryTree(5);
        bt.insert(2);
        bt.insert(3);
        bt.insert(0);
        bt.insert(7);
        bt.insert(6);

        BinaryTree bt2 = new BinaryTree(5);
        bt2.insert(2);
        bt2.insert(3);
        bt2.insert(0);
        bt2.insert(7);
        bt2.insert(6);

        BinaryTree bt5 = new BinaryTree(5);
        BinaryTree bt7 = new BinaryTree(7);
        BinaryTree bt6 = new BinaryTree(6);
        BinaryTree bt22 = new BinaryTree(2);
        BinaryTree bt3 = new BinaryTree(3);
        BinaryTree bt0 = new BinaryTree(0);
        bt5.root.leftNode = bt7.root;
        bt7.root.rightNode = bt6.root;
        bt5.root.rightNode = bt22.root;
        bt22.root.leftNode = bt3.root;
        bt22.root.rightNode = bt0.root;


        System.out.println("Insert Done\n");

        System.out.println("\n-------查找元素------");
        System.out.println(bt.find(7));

        System.out.println("\n-------二叉树最大深度------\n");
        System.out.println(bt.maxDepth(bt.root));

        System.out.println("\n-----二叉树最小深度-------\n");
        System.out.println(bt.findMinDepth(bt.root));

        System.out.println("\n-----二叉树节点个数-------\n");
        System.out.println(bt.numOfTreeNode(bt.root));

        System.out.println("\n-----二叉树叶子节点个数-------\n");
        System.out.println(bt.numOfNoChildNode(bt.root));

        System.out.println("\n-----二叉树第k层节点个数-------\n");
        System.out.println(bt.numOfKLevelNode(bt.root, 1));

        System.out.println("\n-----判断二叉树是否是平衡二叉树-------\n");
        System.out.println(bt.isBalanced(bt.root));

        System.out.println("\n-----判断二叉树是否是完全二叉树-------\n");
        System.out.println(bt.isCompleteTreeNode(bt.root));

        System.out.println("\n-----判断两个二叉树是否相等-------\n");
        System.out.println(bt.isSameTreeNode(bt.root, bt2.root));

        System.out.println("\n-----判断两个二叉树是否互为镜像-------\n");
        System.out.println(bt.isMirrorTreeNode(bt.root, bt5.root));

        //System.out.println("\n-----翻转二叉树-------\n");
        //TreeNode mirrorTreeNode = bt.mirrorTreeNode(bt.root);

        System.out.println("\n------递归中序遍历------\n");
        bt.inOrderTraverse(bt.root);

        System.out.println("\n------递归前序遍历------\n");
        bt.preOrder(bt.root);

        System.out.println("\n------层次遍历------\n");
        bt.levelOrder(bt.root);

    }
}
