package com.yh.math.easy;

import java.util.HashSet;
import java.util.Set;

public class Easy217 {

    class Solution {
        public boolean containsDuplicate(int[] nums) {
            if (nums == null || nums.length == 0) {
                return false;
            }
            Set<Integer> set = new HashSet<>();
            for (int i : nums) {
                set.add(i);
            }
            return nums.length != set.size();
        }
    }
}
