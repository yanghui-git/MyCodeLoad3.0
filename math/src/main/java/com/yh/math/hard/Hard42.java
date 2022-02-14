package com.yh.math.hard;

public class Hard42 {

    public static void main(String[] args) {
        Integer[] height = new Integer[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        System.out.println(trap(height));
    }

    /**
     * 暴力破解
     *
     * @param height
     * @return
     */
    public static int trap(Integer[] height) {
        int result = 0;
        if (height.length == 0) {
            return result;
        }
        // 遍历数组 从 1 开始 无论什么首尾情况 第一桶 肯定是 0
        for (int i = 1; i < height.length - 1; i++) {
            int left = 0;
            int right = 0;
            // 计算左边最高桶
            for (int j = 0; j < i; j++) {
                left = Math.max(height[j], left);
            }
            // 计算右边最高桶
            for (int k = i; k < height.length; k++) {
                right = Math.max(height[k], right);
            }
            // 当前位置可以接水高度
            int temp = Math.min(left, right) - height[i];
            result = result + (temp > 0 ? temp : 0);
        }
        return result;
    }
}
