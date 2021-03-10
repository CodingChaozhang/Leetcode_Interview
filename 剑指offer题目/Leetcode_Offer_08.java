class CQueue {
    // 用两个栈实现队列
    Stack<Integer> s1;
    Stack<Integer> s2;
    public CQueue() {
        // 初始化
        s1 = new Stack<>();
        s2 = new Stack<>();
    }
    // 入队列
    public void appendTail(int value) {
        // 直接入栈1
        s1.push(value);
    }
    // 出队列
    public int deleteHead() {
        // 看栈2是否有值
        if(s2.isEmpty()){
            //栈2为空，则从栈1借
            while(!s1.isEmpty()){
                s2.push(s1.pop());
            }
        }
        return s2.isEmpty()?-1:s2.pop();
    }
}

/**
 * Your CQueue object will be instantiated and called as such:
 * CQueue obj = new CQueue();
 * obj.appendTail(value);
 * int param_2 = obj.deleteHead();
 */