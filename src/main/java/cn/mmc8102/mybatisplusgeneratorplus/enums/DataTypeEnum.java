package cn.mmc8102.mybatisplusgeneratorplus.enums;

import lombok.AllArgsConstructor;

/**
 * @author wangli
 */
@AllArgsConstructor
public enum DataTypeEnum {

    /**
     * NUMBER类型
     */
    VARCHAR("VARCHAR", "String", "VARCHAR"),
    CHAR("CHAR", "String", "CHAR"),
    BLOB("BLOB", "byte[]", "BLOB"),
    TEXT("TEXT", "String", "VARCHAR"),
    INTEGER("INTEGER", "Integer", "INTEGER"),
    TINYINT("TINYINT", "Integer", "TINYINT"),
    SMALLINT("SMALLINT", "Integer", "SMALLINT"),
    BIGINT("BIGINT", "Long", "BIGINT"),
    DECIMAL("DECIMAL", "BigDecimal", "DECIMAL"),
    NUMBER("NUMBER", "BigDecimal", "DECIMAL"),
    TIME("TIME", "LocalDateTime", "TIMESTAMP"),
    DATETIME("DATETIME", "LocalDateTime", "TIMESTAMP"),
    DATE("DATE", "Date", "TIMESTAMP"),
    INT("INT", "Integer", "INTEGER");

    /**
     * 数据库列的类型
     */
    private String dbColumnType;
    /**
     * 对应的java代码的类型
     */
    private String javaType;
    /**
     * mapper中对应的类型
     */
    private String mapperJdbcType;


    public String getDbColumnType() {
        return dbColumnType;
    }

    public void setDbColumnType(String dbColumnType) {
        this.dbColumnType = dbColumnType;
    }

    public String getJavaType() {
        return javaType;
    }

    public void setJavaType(String javaType) {
        this.javaType = javaType;
    }

    public String getMapperJdbcType() {
        return mapperJdbcType;
    }

    public void setMapperJdbcType(String mapperJdbcType) {
        this.mapperJdbcType = mapperJdbcType;
    }

}
