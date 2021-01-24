package org.tylor.tutorial.algorithm.binary_tree;

/**
 * Tylor 2020/12/15
 */
import java.util.ArrayList;
import java.util.List;


public class ListAllOrder {

    public List<List<Integer>> permute(int[] nums) {
        int len = nums.length;
        // 使用一个动态数组保存所有可能的全排列
        List<List<Integer>> res = new ArrayList<>();
        if (len == 0) {
            return res;
        }

        boolean[] used = new boolean[len];
        List<Integer> path = new ArrayList<>();

        dfs(nums, len, 0, path, used, res);
        return res;
    }

    /**
     *
     * @param nums 数组
     * @param len 长度
     * @param depth 深度
     * @param path 当前进度
     * @param used 状态数组，标记索引是否已遍历
     * @param res 结果
     */
    private void dfs(int[] nums, int len, int depth,
                     List<Integer> path, boolean[] used,
                     List<List<Integer>> res) {
        if (depth == len) {
            List list = new ArrayList<>();
            list.addAll(path);
            res.add(list);
            return;
        }

        // 在非叶子结点处，产生不同的分支，这一操作的语义是：在还未选择的数中依次选择一个元素作为下一个位置的元素，这显然得通过一个循环实现。
        for (int i = 0; i < len; i++) {
            if (!used[i]) {
                path.add(nums[i]);
                used[i] = true;

                dfs(nums, len, depth + 1, path, used, res);
                // 注意：下面这两行代码发生 「回溯」，回溯发生在从 深层结点 回到 浅层结点 的过程，代码在形式上和递归之前是对称的
                used[i] = false;
                path.remove(path.size() - 1);
            }
        }
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        ListAllOrder solution = new ListAllOrder();
        List<List<Integer>> lists = solution.permute(nums);
        System.out.println(lists);
    }
}