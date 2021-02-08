// 设计前中后队列
import java.util.*;
public class Leetcode1670 {
	class FrontMiddleBackQueue {
		List<Integer> list;
	    public FrontMiddleBackQueue() {
	    	list = new ArrayList<>();
	    }
	    
	    public void pushFront(int val) {
	    	if(list.size()==0) {
	    		list.add(val);
	    	}else {
	    		list.add(0,val);
	    	}
	    }
	    
	    public void pushMiddle(int val) {
	    	if(list.size()==0) {
	    		list.add(val);
	    	}else {
	    		int middle = list.size()/2;
	    		list.add(middle,val);
	    	}
	    }
	    
	    public void pushBack(int val) {
	    	if(list.size()==0) {
	    		list.add(val);;
	    	}else {
	    		list.add(val);
	    	}
	    }
	    
	    public int popFront() {
	    	if(list.size()==0) {
	    		return -1;
	    	}else {
	    		return list.remove(0);
	    	}
	    }
	    
	    public int popMiddle() {
	    	if(list.size()==0) {
	    		return -1;
	    	}
	    	int mid = list.size()/2;
	    	if(list.size()%2==0) {
	    		return list.remove(mid-1);
	    	}else{
	    		return list.remove(mid);
	    	}
	    }
	    
	    public int popBack() {
	    	if(list.size()==0) {
	    		return -1;
	    	}else{
	    		return list.remove(list.size()-1);
	    	}
	    }
	}

	/**
	 * Your FrontMiddleBackQueue object will be instantiated and called as such:
	 * FrontMiddleBackQueue obj = new FrontMiddleBackQueue();
	 * obj.pushFront(val);
	 * obj.pushMiddle(val);
	 * obj.pushBack(val);
	 * int param_4 = obj.popFront();
	 * int param_5 = obj.popMiddle();
	 * int param_6 = obj.popBack();
	 */
}
