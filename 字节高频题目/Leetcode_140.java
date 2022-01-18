class Solution {
    // 结果
    List<String> res = new ArrayList<>();
    public List<String> wordBreak(String s, List<String> wordDict) {
        // 临时值
        ArrayList<String> list = new ArrayList<>();
        // 将其转换为数组
        char[] arr = s.toCharArray();
        // 回溯
        dfs(arr,0,wordDict,list);
        return res; 
    }
    // 开始
    public void dfs(char[] arr,int index,List<String> wordDict,ArrayList<String> list){
        if(index==arr.length){
            // 符合条件
            StringBuilder temp = new StringBuilder();
            // 对其遍历
            for(String word:list){
                temp.append(word);
                temp.append(" ");
            }
            String temp_s = temp.toString();
            res.add(temp_s.substring(0,temp_s.length()-1));
        }
        if(index>arr.length){
            // 提前截止
            return;
        }
        for(String word:wordDict){
            int len = word.length();
            // 符合条件
            if(index+len<=arr.length&&new String(arr,index,len).equals(word)){
                list.add(new String(arr,index,len));
                dfs(arr,index+len,wordDict,list);
                list.remove(list.size()-1);
            }

        }

    }
}