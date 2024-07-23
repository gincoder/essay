package com.panther.algo;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

/**
 * scheme
 *
 * @author panther
 * @version 1.0: test.java, 2024/7/11 17:06 $
 */
public class TreePrintFromTopToBottom {

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    public List<List<Integer>> printFromTopToBottom(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        ArrayDeque<TreeNode> path = new ArrayDeque<>();
        boolean flag = false;
        path.offer(root);
        while(true){
            if(path.size() == 0) break;
            int size = path.size();
            ArrayList<Integer> level = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                if(flag){
                    TreeNode temp = path.pollLast();
                    level.add(temp.val);
                    path.offerLast(temp.right);
                    path.offerLast(temp.left);
                }else{
                    TreeNode temp = path.pollFirst();
                    level.add(temp.val);
                    path.offerLast(temp.left);
                    path.offerLast(temp.right);
                }
            }
            ans.add(level);
            flag =!flag;
        }
        return ans;
    }
}

