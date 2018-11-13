package code;

import org.junit.Test;

import java.util.Stack;

/**
 * Created by lanjing on 2018/11/13.
 */

/**
 * Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.
 * <p>
 * push(x) -- Push element x onto stack.
 * pop() -- Removes the element on top of the stack.
 * top() -- Get the top element.
 * getMin() -- Retrieve the minimum element in the stack.
 * Example:
 * <p>
 * MinStack minStack = new MinStack();
 * minStack.push(-2);
 * minStack.push(0);
 * minStack.push(-3);
 * minStack.getMin();   --> Returns -3.
 * minStack.pop();
 * minStack.top();      --> Returns 0.
 * minStack.getMin();   --> Returns -2.
 * <p>
 * 要求：设计一个能随时获取栈中最小值的栈
 * <p>
 * 思路：两个栈，一个保存栈数据，一个保存比上一个入栈还小的数据，在入栈和出栈的时候判断
 */
public class _155MinStack {

    private Stack<Integer> s1 = new Stack<>();

    private Stack<Integer> s2 = new Stack<>();

    /**
     * initialize your data structure here.
     */
    public _155MinStack() {
    }

    public void push(int x) {
        s1.push(x);
        if (s2.isEmpty() || s2.peek() >= x) {
            s2.push(x);
        }
    }

    public void pop() {
        int x = s1.pop();
        if (s2.peek() == x) {
            s2.pop();
        }
    }

    public int top() {
        return s1.peek();
    }

    public int getMin() {
        return s2.peek();
    }

    public static void main(String[] args) {
        _155MinStack stack = new _155MinStack();
        stack.push(-2);
        stack.push(0);
        stack.push(-3);
        System.out.println(stack.getMin());
        stack.pop();
        System.out.println(stack.top());
        System.out.println(stack.getMin());
    }
}
