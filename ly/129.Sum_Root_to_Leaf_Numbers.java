/**
Given a binary tree containing digits from 0-9 only, each root-to-leaf path could represent a number.

An example is the root-to-leaf path 1->2->3 which represents the number 123.

Find the total sum of all root-to-leaf numbers.

Note: A leaf is a node with no children.

Example:

Input: [1,2,3]
    1
   / \
  2   3
Output: 25
Explanation:
The root-to-leaf path 1->2 represents the number 12.
The root-to-leaf path 1->3 represents the number 13.
Therefore, sum = 12 + 13 = 25.
Example 2:

Input: [4,9,0,5,1]
    4
   / \
  9   0
 / \
5   1
Output: 1026
Explanation:
The root-to-leaf path 4->9->5 represents the number 495.
The root-to-leaf path 4->9->1 represents the number 491.
The root-to-leaf path 4->0 represents the number 40.
Therefore, sum = 495 + 491 + 40 = 1026.

 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    /*
    public int sumNumbers(TreeNode root) {//两个链表迭代实现
        LinkedList<TreeNode> leaf1 = new LinkedList<>();
        LinkedList<TreeNode> leaf2 = new LinkedList<>();
        int sum = 0;
        if (root != null) {
            leaf1.add(root);
        }
        while (leaf1.size()>0) {
            TreeNode temp = leaf1.getFirst();
            leaf1.removeFirst();
            if (temp.left!=null) {
                temp.left.val += temp.val*10;
                leaf2.add(temp.left);
            }
            if (temp.right!=null) {
                temp.right.val += temp.val*10;
                leaf2.add(temp.right);
            }
            if (temp.left==null && temp.right==null) {
                sum += temp.val;
            }
            if (leaf1.size()==0) {
                leaf1 = leaf2;
                leaf2 = new LinkedList<>();
            }
        }
        return sum;
    }
    */
    public int sumNumbers(TreeNode root) {
        return sumNumbersCore(root,0);
    }
    public int sumNumbersCore(TreeNode root, int s) {//递归实现
        if (root==null) {
            return 0;
        } else if (root.left==null && root.right==null) {
            return s*10 + root.val;
        }
        int leftsum = sumNumbersCore(root.left, s*10+root.val);
        int rightsum = sumNumbersCore(root.right, s*10+root.val);
        return leftsum + rightsum;
    }
}
