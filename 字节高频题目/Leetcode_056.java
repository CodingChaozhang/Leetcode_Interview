class Solution {
    public int[][] merge(int[][] intervals) {
        // 对其自定义排序
        Arrays.sort(intervals,(a,b)->(a[0]-b[0]));
        // 用List来存储 
        List<int[]> list = new  ArrayList<>();
        //对其遍历
        for(int i=0;i<intervals.length;i++){
            // 如果为空
            if(list.isEmpty()){
                list.add(new int[]{intervals[i][0],intervals[i][1]});
            }else{
                // 不为空
                if(list.get(list.size()-1)[1]>=intervals[i][0]){
                    // 可以整合
                    // list.get(list.size()-1)[0] = Math.min(list.get(list.size()-1)[0],intervals[i][0]);
                    list.get(list.size()-1)[1] = Math.max(list.get(list.size()-1)[1],intervals[i][1]);
                }else{
                    // 不可以整合
                    list.add(new int[]{intervals[i][0],intervals[i][1]});
                }
            }
        }

        return list.toArray(new int[list.size()][]);
    }
}