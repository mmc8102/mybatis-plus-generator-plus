package cn.mmc8102.mybatisplusgeneratorplus.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * JSON的驼峰和下划线互转帮助类
 *
 * @author wangli
 */
public class FieldNameUtil {

    public static void main(String[] args) {
        underLineToUpperCase("name_aa");
    }

    private static Pattern linePattern = Pattern.compile("_(\\w)");

    /**
     * 下划线转驼峰
     *
     * @param str 要转的字符串
     * @return 转换后的字符串
     */
    static String underLineToUpperCase(String str) {
        str = str.toLowerCase();
        Matcher matcher = linePattern.matcher(str);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, matcher.group(1).toUpperCase());
        }
        matcher.appendTail(sb);
        return sb.toString();
    }


}