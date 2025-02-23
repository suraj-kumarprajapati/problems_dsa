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
    int[] preorder;
    int[] postorder;
    Map<Integer, Integer> postMap;
    public TreeNode constructFromPrePost(int[] preorder, int[] postorder) {
        int n = preorder.length;

        this.preorder = preorder;
        this.postorder = postorder;
        this.postMap = new HashMap<>();

        for(int i=0; i<n; i++) {
            postMap.put(postorder[i], i);
        }

        return buildTree(0, n-1, 0, n-1);
    }

    public TreeNode buildTree(int preStart, int preEnd, int postStart, int postEnd) {
        // base cases
        if(preStart > preEnd || postStart > postEnd)
            return null;

        if(preStart == preEnd || postStart == postEnd)
            return new TreeNode(preorder[preStart]);

        int currValue = preorder[preStart];
        int postMapInd = postMap.get(preorder[preStart + 1]);
        int dist = postMapInd - postStart + 1;

        TreeNode currNode = new TreeNode(currValue);
        currNode.left = buildTree(preStart + 1, preStart + dist, postStart, postMapInd);
        currNode.right = buildTree(preStart + dist + 1, preEnd, postMapInd + 1, postEnd - 1);
        return currNode;        
    }
}