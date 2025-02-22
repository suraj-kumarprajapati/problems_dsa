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
    int lastDigitInd = -1;
    int lastDashInd = -1;

    public TreeNode recoverFromPreorder(String traversal) {
        // // using recursion 
        // return dfs(traversal, 0);


        // iterative approach using stack
        return recoverUsingStack(traversal);
    }


    public TreeNode recoverUsingStack(String traversal) {
        int n = traversal.length();

        Stack<TreeNode> stack = new Stack<>();
        int ind = 0;

        while(ind < n) {
            // count the depth by counting the number of dashes
            int depth = 0;
            while(ind < n && traversal.charAt(ind) == '-') {
                ind += 1;
                depth += 1;
            }


            // generate the node value
            int value = 0;
            while(ind < n && traversal.charAt(ind) != '-') {
                value = value*10 + (traversal.charAt(ind) - '0');
                ind += 1;
            }

            // create a new node
            TreeNode node = new TreeNode(value);

            while(stack.size() > depth) {
                stack.pop();
            }

            if(!stack.isEmpty()) {
                if(stack.peek().left == null) {
                    stack.peek().left = node;
                }
                else {
                    stack.peek().right = node;
                }
            }

            stack.push(node); 
        }

        while(stack.size() > 1) {
            stack.pop();
        }

        return stack.pop();
    }

    public TreeNode dfs(String traversal, int level) {
        int n = traversal.length();
        int ind = lastDashInd + 1;

        if(ind >= n) 
            return null;

        TreeNode node = new TreeNode();
        int[] res = findValue(traversal, ind);
        node.val = res[0];
        ind = res[1];

        lastDigitInd = ind - 1;

        int levelCount = 0;
        res = findLevelCount(traversal, lastDigitInd + 1);
        levelCount = res[0];
        ind = res[1];
        lastDashInd = ind - 1;

        if(levelCount == level + 1) {
            node.left = dfs(traversal, level + 1);
        }

        levelCount = 0;
        res = findLevelCount(traversal, lastDigitInd + 1);
        levelCount = res[0];
        ind = res[1];
        lastDashInd = ind - 1;

        if(levelCount == level + 1) {
            node.right = dfs(traversal, level + 1);
        }

        return node;
    }


    public int[] findValue(String traversal, int ind) {
        int n = traversal.length();
        StringBuilder str = new StringBuilder();
        while(ind < n && traversal.charAt(ind) != '-') {
            str.append(traversal.charAt(ind));
            ind += 1;
        }

        int val = Integer.parseInt(str.toString());
        return new int[] {val, ind};
    }


    public int[] findLevelCount(String traversal, int ind) {
        int n = traversal.length();
        int count = 0;
        while(ind < n && traversal.charAt(ind) == '-') {
            count += 1;
            ind += 1;
        }

        return new int[] {count, ind};
    }
}