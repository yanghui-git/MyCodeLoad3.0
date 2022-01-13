package com.yh.springeasy.util;

import net.sourceforge.pinyin4j.PinyinHelper;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * 拼音-中文 转换工具类
 */
public class PingYingUtil {

    /**
     * 获取中文拼音首字母，其他字符不变
     *
     * @param str
     * @return String
     */
    public static String getShortPinyin(String str) {
        return getShortPinyin(str, true);
    }

    /**
     * 获取中文拼音首字母
     *
     * @param str
     * @param retain 为true保留其他字符
     * @return String
     */
    public static String getShortPinyin(String str, boolean retain) {
        return getPinyin(str, true, retain);
    }

    /**
     * 获取中文拼音，其他字符不变
     *
     * @param str
     * @return String
     */
    public static String getFullPinyin(String str) {
        return getFullPinyin(str, true);
    }

    /**
     * 获取中文拼音
     *
     * @param str
     * @param retain 为true保留其他字符
     * @return String
     */
    public static String getFullPinyin(String str, boolean retain) {
        return getPinyin(str, false, retain);
    }

    /**
     * 获取中文拼音
     *
     * @param str
     * @param shortPinyin 为true获取中文拼音首字母
     * @param retain      为true保留其他字符
     * @return String
     */
    private static String getPinyin(String str, boolean shortPinyin, boolean retain) {
        if (StringUtils.isBlank(str)) {
            return "";
        }
        StringBuffer pinyinBuffer = new StringBuffer();
        char[] arr = str.toCharArray();
        for (char c : arr) {
            String[] temp = PinyinHelper.toHanyuPinyinStringArray(c);
            if (ArrayUtils.isNotEmpty(temp)) {
                if (StringUtils.isNotBlank(temp[0])) {
                    if (shortPinyin) {
                        pinyinBuffer.append(temp[0].charAt(0));
                    } else {
                        pinyinBuffer.append(temp[0].replaceAll("\\d", ""));
                    }
                }
            } else {
                if (retain) {
                    pinyinBuffer.append(c);
                }
            }
        }
        return pinyinBuffer.toString();
    }

    public static void main(String[] args) {
       // System.out.println(getFullPinyin("拼音测试数据!!!123"));
      //  System.out.println(getFullPinyin("拼音测试数据!!!123", false));
      //  System.out.println(getShortPinyin("拼音测试数据!!!123"));
        //System.out.println(getShortPinyin("拼音测试数据!!!123", false));
      //  System.out.println(getShortPinyin("杭州市西湖区"));
     //   System.out.println(getShortPinyin("银行"));
        System.out.println(getShortPinyin("df",false));
    }
}
