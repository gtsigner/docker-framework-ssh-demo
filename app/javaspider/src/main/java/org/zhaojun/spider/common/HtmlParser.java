package org.zhaojun.spider.common;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by zhaojunlike on 6/4/2017.
 */
public class HtmlParser {
    public static void parseTags(String html) {
        //采集Tags分页
        try {
            String regx = "<dl(.*?)class=\"tags\">([\\s\\S]*?)<\\/dl>";
            Pattern p = Pattern.compile(regx, Pattern.COMMENTS);
            Matcher matcher = p.matcher(regx);
            System.out.println("解析Tag：" + p.pattern());
            while (matcher.find()) {
                System.out.println("Res：" + matcher.group());
            }
        } catch (Exception ex) {
            System.err.println("Err:" + ex.getMessage());
        }

    }
}
