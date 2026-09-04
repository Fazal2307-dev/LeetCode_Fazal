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
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        if(root==null)return new ArrayList<>();
        Queue<TreeNode> q=new ArrayDeque<>();
        List<List<Integer>> ans=new ArrayList<>();

        q.offer(root);

        while(!q.isEmpty()){
            int size=q.size();
            List<Integer> list=new ArrayList<>();

            while(size>0){
                TreeNode temp=q.poll();
                list.add(temp.val);
                if(temp.left!=null)q.offer(temp.left);
                if(temp.right!=null)q.offer(temp.right);
                size--;
            }
            ans.add(list);
        }

        Collections.reverse(ans);
        return ans;
    }
}