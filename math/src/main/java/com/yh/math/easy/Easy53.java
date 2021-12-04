package com.yh.math.easy;

public class Easy53 {

    public static void main(String[] args) {
        int[] num = new int[]
                {-2, 1, -3, 4, -1, 2, 1, -5, 4};

        // System.out.println(getMax(num, 2));
        System.out.println("-----");
        System.out.println("最大值:" + maxSubArray(num));
        System.out.println("动态规划思想:" + getMax(num));
    }

    public static int maxSubArray(int[] nums) {
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
        System.out.println("第一个窗口: " + first);
        int max = first;
        //后续窗口值 = 窗口值+新值- 旧值
        for (int j = 0; j < nums.length - k; j++) {
            //最新窗口值
            int second = first + nums[j + k] - nums[j];
            System.out.println("第二个窗口" + second);
            max = Math.max(max, second);
            //调整下一个区间
            first = second;
        }
        return max;
    }


    /**
     * 动态规划思想
     */
    public static int getMax(int[] nums) {
        //遍历数组,求出以当前下标 i 结尾的 最大值
        //dp[i]=Max(dp[i-1]+num[i],num[i])
        int result = nums[0];
        int pre = 0;
        for (int i : nums) {
            //当前下标结尾下 最大值
            pre = Math.max(pre + i, i);
            System.out.println("当前下标" + i + "下,结尾最大值:" + pre);
            //最终结果
            result = Math.max(result, pre);
        }
        return result;
    }
}
