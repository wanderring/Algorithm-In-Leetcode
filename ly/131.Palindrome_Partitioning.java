/*
Given a string s, partition s such that every substring of the partition is a palindrome.

Return all possible palindrome partitioning of s.

Example:

Input: "aab"
Output:
[
  ["aa","b"],
  ["a","a","b"]
]

使用dfs不断添加新的组合方式，一开始的实现运行需要4ms，beat 26%，对比高票答案发现实现原理完全相同，
但高票只需要2ms，分析后发现多消耗的时间在两个地方，一个是高票解决方式使用ArrayList，我用的LinkedList，
之前一直以为LinkedList会比较快。。。另一处是判断是否为回文字符串，我用substring截取了字符串判断，
事实证明这么做很慢。

修改后的代码:
*/
class Solution {
    public List<List<String>> partition(String s) {
        List<List<String>> result = new ArrayList<>();
        ArrayList<String> temp = new ArrayList<>();
        partitionCore(result,temp,s,0);
        return result;
    }
    public void partitionCore(List<List<String>> result, ArrayList<String> temp, String s, int pos) {
        if (pos==s.length()) {
            result.add(new ArrayList<>(temp));
            return;
        }
        for (int i=pos+1; i<=s.length(); ++i) {
            if (isPal(s,pos,i-1)) {
                temp.add(s.substring(pos,i));
                partitionCore(result,temp,s,i);
                temp.remove(temp.size()-1);
            }
        }
    }
    public boolean isPal(String s, int low, int high){
        while(low<high) if(s.charAt(low++)!=s.charAt(high--)) return false;
        return true;
    }
}
