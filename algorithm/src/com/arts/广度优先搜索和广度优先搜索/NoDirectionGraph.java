package com.arts.广度优先搜索和广度优先搜索;

public class NoDirectionGraph {

    //图中包含的最大顶点数
    private int mMaxSize;
    //顶点数组
    private GraphVertex[] vertexList;
    //指示顶点之间的联通关系的邻接矩阵
    private int[][] indicatorMat;
    //之前实际保存的顶点数目
    private int nVertex;

    public NoDirectionGraph(int maxSize) {
        mMaxSize = maxSize;
        vertexList = new GraphVertex[mMaxSize];
        indicatorMat = new int[mMaxSize][mMaxSize];

        //初始化邻接矩阵元素数为0
        nVertex = 0;
        for (int j=0; j<mMaxSize; j++) {
            for (int k=0; k<mMaxSize; k++) {
                indicatorMat[j][k] = 0;
            }
        }
    }

    public void addVertex(GraphVertex v) {
        if (nVertex < mMaxSize) {
            vertexList[nVertex++] = v;
        } else {
            System.out.println("顶点数量已达上限");
        }
    }

    public void print() {
        for (int[] line : indicatorMat) {
            for (int i : line) {
                System.out.print(i + " ");
            }
            System.out.println();
        }
    }

    /**
     * 深度优先搜索
     * @param vertexIndex 表示要搜索的起点，即图的邻接矩阵中的行数
     */
    public void DFS(int vertexIndex) {
        ArrayStack stack = new ArrayStack();
        vertexList[vertexIndex].setVisited(true);
        stack.push(vertexIndex);
        int nextVertexIndex = getNextVertexIndex(vertexIndex);
        while (!stack.isEmpty()) {
            //不断压栈、出栈，直到栈为空
            if (nextVertexIndex != -1) {
                vertexList[nextVertexIndex].setVisited(true);
                stack.push(nextVertexIndex);
                stack.print();
            } else {
                stack.pop();
            }
            //检索当前栈顶元素是否包含其他未遍历过的节点
            if (!stack.isEmpty()) {
                nextVertexIndex = getNextVertexIndex(stack.peek());
            }
        }
    }

    /**
     * 得到当前顶点的下一个顶点所在行
     * @param column
     * @return
     */
    public int getNextVertexIndex(int column) {
        for (int i=0; i < indicatorMat[column].length; i++) {
            if (indicatorMat[column][i] == 1 && !vertexList[i].visited) {
                return i;
            }
        }
        return -1;
    }

    public void BFS(int vertexIndex) {
        ChainQueue queue = new ChainQueue();
        vertexList[vertexIndex].setVisited(true);
        queue.insert(new QueueNode(vertexIndex));
        int nextVertexIndex = getNextVertexIndex(vertexIndex);
        while (!queue.isEmpty()) {
            if (nextVertexIndex != -1) {
                vertexList[nextVertexIndex].setVisited(true);
                queue.insert(new QueueNode(nextVertexIndex));
            } else {
                queue.remove();
            }
            if (!queue.isEmpty()) {
                nextVertexIndex = getNextVertexIndex(queue.peek().data);
                queue.print();
            }
        }
    }
}
