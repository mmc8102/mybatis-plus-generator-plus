package cn.mmc8102.mybatisplusgeneratorplus.util;


import cn.mmc8102.mybatisplusgeneratorplus.pojo.bo.Config;
import cn.mmc8102.mybatisplusgeneratorplus.pojo.bo.DataBaseInfo;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * @author wangli 数据库连接驱动
 */
public class JdbcUtil {

    private Connection conn = null;

    JdbcUtil(Config config) {
        DataBaseInfo dataBaseInfo = config.getDataBaseInfo();
        try {
            Class.forName(dataBaseInfo.getDriverClassName());
            this.conn = DriverManager.getConnection(dataBaseInfo.getJdbcUrl(), dataBaseInfo.getUsername(),
                    dataBaseInfo.getPassword());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 创建连接实例
     *
     * @return
     */
    Connection getConnection() {

        return this.conn;
    }

    /**
     * 关闭连接
     */
    public void close() {
        try {
            this.conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
