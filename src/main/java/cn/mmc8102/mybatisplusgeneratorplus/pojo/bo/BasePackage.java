package cn.mmc8102.mybatisplusgeneratorplus.pojo.bo;

import lombok.Data;

/**
 * @author wangli
 **/
@Data
public class BasePackage implements Cloneable {

    /**
     * 基类包名称
     */
    private String basePackageName;
    /**
     * 对应的模板命成分
     */
    private String ftlName;
    /**
     * 生成的文件名
     */
    private String outputFileName;
    /**
     * 生成的文件路径
     */
    private String outputFilePath;
    /**
     * 完整的包名
     */
    private String packageName;

    /**
     * 文件名对应的类名
     */
    private String className;


    @Override
    public BasePackage clone() {
        BasePackage basePackage = null;
        try {
            basePackage = (BasePackage) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return basePackage;
    }

}
