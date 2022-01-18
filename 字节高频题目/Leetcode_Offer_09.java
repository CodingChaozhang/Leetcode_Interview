class CQueue {
    // 两个栈模拟队列
    // 入队：直接进in_stack
    // 出队：为空和不为空 为空则将in_stack全部进output，弹出来一个；不为空则直接弹出来
    Stack<Integer> in_stack;
    Stack<Integer> output_stack;
    public CQueue() {
        in_stack = new Stack<>();
        output_stack = new Stack<>();
    }
    // 入队
    public void appendTail(int value) {
        in_stack.push(value);
    }
    // 出队
    public int deleteHead() {
        if(output_stack.isEmpty()){
            if(in_stack.isEmpty()){
                return -1;
            }
            while(!in_stack.isEmpty()){
                output_stack.push(in_stack.pop());
            }
            return output_stack.pop();
        }else{
            return output_stack.pop();
        }
    }
}

/**
 * Your CQueue object will be instantiated and called as such:
 * CQueue obj = new CQueue();
 * obj.appendTail(value);
 * int param_2 = obj.deleteHead();
 */