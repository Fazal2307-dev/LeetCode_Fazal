/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        helper(result,root,0);
        return result;
        
    }
    public void helper(List<List<Integer>> result,TreeNode root,int level){
        if(root == null) return ;
        if(level == result.size()){
            List<Integer> ls = new ArrayList<>();
            ls.add(root.val);
            result.add(ls);
        }else{
            result.get(level).add(root.val);
        }
        helper(result,root.left,level+1);
        helper(result,root.right,level+1);
    }
}