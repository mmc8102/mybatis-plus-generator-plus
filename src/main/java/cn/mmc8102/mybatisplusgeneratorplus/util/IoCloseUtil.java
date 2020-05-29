package cn.mmc8102.mybatisplusgeneratorplus.util;

import org.apache.commons.lang3.ObjectUtils;

import java.io.Closeable;

/**
 * 流关闭工具类
 *
 * @author wangli
 */
public class IoCloseUtil {
    /**
     * 关闭
     *
     * @param ios 各种流
     */
    public static void closeAll(Closeable... ios) {
        for (Closeable io : ios) {
            if (ObjectUtils.isNotEmpty(io)) {
                try {
                    io.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}