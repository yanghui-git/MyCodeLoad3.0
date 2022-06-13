package com.yh.springeasy.other;

import org.springframework.util.StringUtils;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 短信对账匹配
 */
public class FileReader {

    /**
     * 读取文件数据
     */
    public static List<String> getFileList(String filePath) throws Exception {
        List<String> result = new ArrayList<>();
        //(文件完整路径),编码格式
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), "utf-8"));//GBK
        String line = null;
        while ((line = reader.readLine()) != null) {
            result.add(line);
        }
        return result;
    }

    public static void main(String[] args) throws Exception {
        //匹配数据keys
        List<String> keys = getFileList("/Users/hui.yang/Desktop/1.txt");
        //匹配数据values
        List<String> values = getFileList("/Users/hui.yang/Desktop/2.txt");
        // 放到内存map 匹配数据
        Map yhMap = new HashMap();
        for (int i = 0; i < 2288; i++) {
            yhMap.put(keys.get(i), StringUtils.isEmpty(values.get(i)) ? "#NA" : values.get(i));
        }
        // 要统计的数据
        List<String> resultData = getFileList("/Users/hui.yang/Desktop/统计的数据.txt");
        resultData.stream().forEach(result -> System.out.println(StringUtils.isEmpty(yhMap.get(result)) ? "#NA" : yhMap.get(result)));
    }

}
