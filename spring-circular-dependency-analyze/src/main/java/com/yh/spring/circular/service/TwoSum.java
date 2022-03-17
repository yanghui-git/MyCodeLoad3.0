package com.yh.spring.circular.service;


import java.util.HashMap;
import java.util.Map;

/**
 * 输入：nums = [2,7,11,15], target = 9
 * 输出：[0,1]
 * 解释：因为 nums[0] + nums[1] == 9 ，返回 [0, 1] 。
 * 示例 2：
 * <p>
 * 输入：nums = [3,2,4], target = 6
 * 输出：[1,2]
 */
public class TwoSum {

    public static void main(String[] args) {
        int[] result = twoSum(new int[]{2, 6, 10}, 12);
        System.out.println(result);
    }

    public static int[] twoSum(int[] nums, int target) {
        // 结果缓存
        Map<Integer, Integer> cache = new HashMap();
        for (int i = 0; i < nums.length; i++) {
            // 目标值
            int other = target - nums[i];
            if (cache.containsKey(other)) {
                return new int[]{cache.get(other), i};
            }
            //放入缓存
            cache.put(nums[i], i);
        }
        return new int[]{};
    }
}
