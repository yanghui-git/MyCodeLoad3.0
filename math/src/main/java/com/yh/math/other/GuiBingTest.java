package com.yh.math.other;

import com.alibaba.fastjson.JSON;

/**
 * 归并算法 递归/合并
 * https://blog.csdn.net/weixin_54107527/article/details/117753128
 */
public class GuiBingTest {

    public static void main(String[] args) {
        int arr[] = new int[]{80, 30, 60, 40, 20, 10, 50, 70};
        int[] tempArr = new int[arr.length];
        mergeSort(arr, tempArr, 0, arr.length - 1);
        System.out.println(JSON.toJSONString(tempArr));
    }


    /**
     * 归并排序
     *
     * @param arr        排序数组
     * @param temp       结果数组
     * @param leftIndex  数组左标
     * @param rightIndex 数组右标
     */
    public static void mergeSort(int[] arr, int[] temp, int leftIndex, int rightIndex) {
        //如果只有一个元素,不用排序
        if (arr == null || arr.length == 0 || leftIndex >= rightIndex) {
            return;
        }
        //记录中间值
        int middleIndex = (leftIndex + rightIndex) / 2;
        //左边的数不断进行拆分
        mergeSort(arr, temp, leftIndex, middleIndex);
        //右边的数不断进行拆分
        mergeSort(arr, temp, middleIndex + 1, rightIndex);
        //合并
        merge(arr, temp, leftIndex, rightIndex, middleIndex);
    }

    /**
     * 合并
     */
    public static void merge(int[] arr, int[] temp, int leftIndex, int rightIndex, int middleIndex) {
        //复制要合并的数据
        for (int s = leftIndex; s <= rightIndex; s++) {
            temp[s] = arr[s];
        }
        int left = leftIndex;//左边首位下标
        int right = middleIndex + 1;//右边首位下标
        for (int k = leftIndex; k <= rightIndex; k++) {
            if (left > middleIndex) {
                //如果左边的首位下标大于中部下标，证明左边的数据已经排完了。
                arr[k] = temp[right++];
            } else if (right > rightIndex) {
                //如果右边的首位下标大于了数组长度，证明右边的数据已经排完了。
                arr[k] = temp[left++];
            } else if (temp[right] < temp[left]) {
                arr[k] = temp[right++];//将右边的首位排入，然后右边的下标指针+1。
            } else {
                arr[k] = temp[left++];//将左边的首位排入，然后左边的下标指针+1。
            }
        }
    }

}
