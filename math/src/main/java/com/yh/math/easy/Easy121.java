package com.yh.math.easy;

public class Easy121 {

    public static void main(String[] args) {
        int[] prices = new int[]{7, 1, 5, 3, 6, 4};
        maxProfit4(prices);
        // maxProfit(prices);
        // maxProfit2(prices);
        prices = new int[]{7, 6, 4, 3, 1};
        //  maxProfit(prices);
        maxProfit4(prices);
        //  maxProfit2(prices);
        //
        prices = new int[]{2, 6, 1, 3};
        //  maxProfit2(prices);
        //  maxProfit3(prices);
        //  maxProfit4(prices);
        maxProfit4(prices);
    }

    /**
     * 暴力破解
     *
     * @param prices
     * @return
     */
    //[7,1,5,3,6,4]
    public static int maxProfit(int[] prices) {
        int result = 0;
        if (prices == null || prices.length == 0) {
            return result;
        }
        //依次从第一个元素开始买进,然后计算其最大值
        for (int start = 0; start < prices.length - 1; start++) {
            //依次计算 此后每天卖出的值
            for (int end = start + 1; end < prices.length; end++) {
                result = Math.max(result, prices[end] - prices[start]);

            }
        }
        System.out.println("最大值是 :" + result);
        return result;
    }

    /**
     * 另类暴力破解
     *
     * @param prices
     * @return
     */
    public static int maxProfit2(int[] prices) {
        int result = 0;
        if (prices == null || prices.length == 0) {
            return result;
        }
        // 假设我从第 i 天卖出 ，那么最大利润即是[0,i-1]区间的最小值
        for (int end = 1; end < prices.length; end++) {
            //求出[0,i-1]区间的最小值
            int min = prices[end];
            for (int start = 0; start < end; start++) {
                min = Math.min(min, prices[start]);
            }
            result = Math.max(prices[end] - min, result);
        }
        ;
        System.out.println("最大值是 :" + result);
        return result;
    }

    /**
     * 一次线性
     *
     * @param prices
     * @return
     */
    public static int maxProfit3(int[] prices) {
        int minprice = Integer.MAX_VALUE;
        int maxprofit = 0;
        for (int i = 0; i < prices.length; i++) {
            if (prices[i] < minprice) {
                minprice = prices[i];
            } else if (prices[i] - minprice > maxprofit) {
                maxprofit = prices[i] - minprice;
            }
        }
        return maxprofit;
    }

    /**
     * 历史最大最小值
     *
     * @param prices
     * @return
     */
    public static int maxProfit4(int[] prices) {
        //历史最大值
        int result = 0;
        if (prices == null || prices.length == 0) {
            return result;
        }
        //记录历史最小值
        int low = prices[0];

        for (int i = 0; i < prices.length; i++) {
            low = Math.min(low, prices[i]);
            result = Math.max(prices[i] - low, result);
        }
        System.out.println("最大值是 :" + result);
        return result;
    }

}
