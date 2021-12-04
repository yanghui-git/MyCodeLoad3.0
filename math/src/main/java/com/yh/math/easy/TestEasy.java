package com.yh.math.easy;


class Solution {
    public int maxSubArray(int[] nums) {
        int max = nums[0];
        for (int i = 1; i <= nums.length; i++) {
            max = Math.max(max, getMax(nums, i));
        }
        return max;
    }

    /**
     * 滑动窗口
     */
    public static int getMax(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k <= 0) {
            return 0;
        }
        //记录第一个窗口值
        int first = 0;
        for (int i = 0; i < k; i++) {
            first += nums[i];
        }
        //  System.out.println("第一个窗口: " + first);
        int max = first;
        //后续窗口值 = 窗口值+新值- 旧值
        for (int j = 0; j < nums.length - k; j++) {
            //最新窗口值
            int second = first + nums[j + k] - nums[j];
            //  System.out.println("第二个窗口" + second);
            max = Math.max(first, second);
            //调整下一个区间
            first = second;
        }
        return max;
    }

}
