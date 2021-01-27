// 数组的相对排序
import java.util.*;
public class Leetcode1122 {
	class Solution {
	    // 解题思路 在里面按照索引顺序，要不就按照大小排序 自定义排序
		public int[] relativeSortArray(int[] arr1, int[] arr2) {
	    	// HashSet
	    	HashMap<Integer,Integer> hashMap = new HashMap<>();
	    	for(int i=0;i<arr2.length;i++) {
	    		hashMap.put(arr2[i], i);
	    	}
	    	// 自定义排序
	    	List<Integer> list = new ArrayList<>();
	    	for(int i=0;i<arr1.length;i++) {
	    		list.add(arr1[i]);
	    	}
	    	Collections.sort(list,(a,b)->{
	    		if(hashMap.containsKey(a) || hashMap.containsKey(b)) {
	    			return hashMap.getOrDefault(a, 1001)-hashMap.getOrDefault(b, 1001);
	    		}
	    		// 大小排序
	    		return a-b;
	    	});
	    	int[] res = new int[arr1.length];
	    	// 重新赋值
	    	for(int i=0;i<arr1.length;i++) {
	    		res[i] = list.get(i);
	    	}
	    	return res;
	    }
		// 计数排序
		public int[] relativeSortArray_2(int[] arr1, int[] arr2) {
			// 数据范围为1000 考虑计数排序
			// 先从arr1中找到最大值
			int max = arr1[0];
			for(int i=1;i<arr1.length;i++) {
				max = Math.max(max, arr1[i]);
			}
			// 开辟空间
			int[] bucket = new int[max+1];
			// 存取出现频率
			for(int num:arr1) {
				bucket[num]++;
			}
			// 结果
			int[] res = new int[arr1.length];
			int index = 0;
			// 遍历arr2
			for(int num:arr2) {
				// 肯定有
				for(int i=0;i<bucket[num];i++) {
					res[index++] = num;
				}
				bucket[num] = 0;
			}
			// 遍历剩余的
			for(int i=0;i<bucket.length;i++) {
				if(bucket[i]!=0) {
					// 可能存在多个
					for(int j=0;j<bucket[i];j++) {
						res[index++] = i;
					}
				}
			}
			return res;
		}

			
	}
}
