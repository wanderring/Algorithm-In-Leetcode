/*
Given a string s, partition s such that every substring of the partition is a palindrome.

Return the minimum cuts needed for a palindrome partitioning of s.

Example:

Input: "aab"
Output: 1
Explanation: The palindrome partitioning ["aa","b"] could be produced using 1 cut.

dfs写了半天，还尝试用hashMap保存已经计算过的结果来加速，然而还是超时，看了答案以后
还是用炫酷的动态规划来解决了。核心思路为找到所有回文片段起止位置，若从0开始，则终
止位置需要分割0次，否则遍历所有以该位置为终止，前面所有形成回文的位置分割的分割次数。
*/
class Solution {
    /*
    HashMap<String,Integer> step = new HashMap<>();
    public int minCut(String s) {
        return minCutCore(s, 0, s.length());
    }
    public int minCutCore(String s, int start, int end) {
        String key = start+"|"+end;
        if (step.containsKey(key)) {
            return step.get(key);
        }
        if (start==end || isPal(s,start,end-1)) return 0;
        int min = Integer.MAX_VALUE;
        for (int i=start; i<end; ++i) {
            int temp = Integer.MAX_VALUE;
            if (isPal(s,i,end-1)) {
                temp = minCutCore(s,start,i) + 1;
            } else if (isPal(s,start,i)) {
                temp = minCutCore(s,i+1,end) + 1;
            } else {
                continue;
            }
            if (min > temp) {
                min = temp;
            }
        }
        //System.out.println(start + "\t" + end + "\t" + min);
        step.put(key,min);
        return min;
    }
    public boolean isPal(String s, int low, int high){
        while(low<high) if(s.charAt(low++)!=s.charAt(high--)) return false;
        return true;
    }
    */
    public int minCut(String s) {
        char[] sChar = s.toCharArray();
        int n = sChar.length;
        boolean[][] pal = new boolean[n][n];
        int[] cut = new int[n];
        for (int i = 0; i < n; ++i) {
            cut[i] = Integer.MAX_VALUE;
            for (int j = i; j >= 0; --j) {
                if (sChar[j]==sChar[i] && (i-j<=1 || pal[i-1][j+1])) {
                    pal[i][j] = true;
                    cut[i] = j==0?0:Math.min(cut[i], cut[j-1] + 1);
                }
            }
        }
        return cut[n-1];
    }
}
