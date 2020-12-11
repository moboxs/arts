package com.arts.广度优先搜索和广度优先搜索;

public class ArrayStack {
    private int[] tArray;
    private int topIndex = -1;

    private int CAPACITY_STEP = 12;

    public ArrayStack() {
        tArray = new int[CAPACITY_STEP];
    }

    public int pop() {
        if (isEmpty()) {
            System.out.println("栈中元素为空");
            return -1;
        } else {
            int i = tArray[topIndex];
            tArray[topIndex--] = -1;
            return i;
        }
    }

    public void push(int t) {
        //检查栈是否为满
        if (topIndex == tArray.length-1) {
            //扩展容量
            int[] tempArray = new int[tArray.length + CAPACITY_STEP];
            for (int i=0; i<tArray.length; i++) {
                tempArray[i] = tArray[i];
            }
            tArray = tempArray;
            tempArray = null;
        } else {
            topIndex++;
            tArray[topIndex] = t;
        }
    }

    public int peek() {
        if (isEmpty()) {
            System.out.println("栈中元素为空");
            return -1;
        } else {
            return tArray[topIndex];
        }
    }

    public Boolean isEmpty() {
        return topIndex < 0;
    }

    public void print() {
        for (int i=0; i<=topIndex; i++) {
            System.out.print(tArray[i] + " ");
        }
        System.out.println();
    }
}
