## 二叉堆

> 二叉堆的逻辑结构就是一颗完全二叉树，所以也叫完全二叉堆。

添加-上滤操作

![image-20210307170020215](D:/000/Leetcode刷题/Leetcode_Interview/二叉树/imgs_tree/2.png)

删除-下滤操作

![image-20210307170101558](D:/000/Leetcode刷题/Leetcode_Interview/二叉树/imgs_tree/3.png)

替换操作

```java
/**
 * 删除元素并替换
 */
@Override
public E replace(E element) {
	elementNotNullCheck(element);
	
	E root = null;
	if(size==0) {
		elements[0] = element;
		size++;
	}else {
		root = elements[0];
		elements[0] = element;
		siftDown(0);
	}
	
	return root;
}
```

建堆-自上而下的上滤

```java
for(int i=1;i<size;i++){
    siftUp(i);
}
```

建堆--自下而上的下滤

```java
for(int i=(size>>1)-1;i>=0;i--){
    siftDown(i);
}
```

## 二叉堆的应用-topK问题(优先级队列)

`PriorityQueue`