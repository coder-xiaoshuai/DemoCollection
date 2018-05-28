package com.zhangs.javabasicuse;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class StringTest {

    public static void countString(String str) {
        if (str!=null&&str.length()>0) {
            //不为空进行计数
            int len = str.length();
            Map<String, Integer> map = new HashMap<String, Integer>();//用于储存已经计数的字符
            List<Integer> list = new ArrayList<>();
            for (int i = 0; i < len; i++) {
                if (map.containsKey(String.valueOf(str.charAt(i)))) {
                    continue;
                } else {
                    //遍历计数 并添加到map中
                    int count = 0;
                    for (int j = 0; j < len; j++) {
                        if (String.valueOf(str.charAt(j)).equals(String.valueOf(str.charAt(i)))) {
                            count++;
                        }
                    }
                    map.put(String.valueOf(str.charAt(i)), count);
                    list.add(count);
                }
            }
            //遍历map输出结果
            Iterator entries = map.entrySet().iterator();
            Collections.sort(list);
            int maxCount=list.get(list.size()-1);
            while (entries.hasNext()) {
                Map.Entry entry = (Map.Entry) entries.next();
                String key =(String)entry.getKey();
                int value = (Integer) entry.getValue();

                if(value==maxCount){
                    System.out.println("出现最多的字符是" + key + ", 出现的次数是" + value+"次");
                }
            }
        }
    }

    public static void main(String[] args){
        countString("abdeasdeasdfaaaaaaaaaaaa");
    }
}
