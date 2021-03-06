/**
 * 25 Aug 2016
 */
package cn.mmc8102.mybatisplusgeneratorplus.pojo.bo;

import lombok.Data;

/**
 * @author wangli 数据库表结构对象
 */
@Data
public class DbColumn {

    /**
     * 表名
     */
    private String tableName;
    /**
     * 列名
     */
    private String columnName;
    /**
     * 列的数据类型
     */
    private String columnDataType;
    /**
     * 注释
     */
    private String comments;
    /**
     * 是否是主键
     */
    private String isPrimaryKey;


}
