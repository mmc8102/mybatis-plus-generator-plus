/**
 * 24 Aug 2016
 */
package cn.mmc8102.mybatisplusgeneratorplus.util;

import cn.mmc8102.mybatisplusgeneratorplus.pojo.bo.BasePackage;
import cn.mmc8102.mybatisplusgeneratorplus.pojo.vo.EntityVO;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.IOException;

/**
 * @author wangli
 * 自动生成的工具类
 */
public class GenerateUtil {


    /**
     * 根据Entity自动生成model
     *
     * @param entity 数据库对象
     * @param type   生成什么文件  1 model 2 dao 3 daoImpl 4 service 5 serviceImpl 6 controller
     * @throws IOException
     * @throws TemplateException
     */
    public static void generateTemplate(EntityVO entity, String type) throws IOException, TemplateException {
        Configuration cfg = new Configuration();
        //类名
        String className = entity.getClassName();
        //获取要生成的basePackage
        BasePackage basePackage = makeBasePackage(entity.getBasePackageMap().get(type), className);
        String path = null;
        Template template = null;
        //模板的的路径
        String ftlPath = entity.getFtlPath();
        //如果项目根路径下用户没有自定义模板,就是用jar包中默认的模板
        File file = new File(System.getProperty("user.dir") + "/ftl");
        if (file.exists() && file.isDirectory()) { //用来测试此路径名表示的文件或目录是否存在
            //模板在类路径下的地址
            path = System.getProperty("user.dir") + ftlPath;
            //创建配置对象
            cfg.setDirectoryForTemplateLoading(new File(path));
            //得到模板对象
            template = cfg.getTemplate(basePackage.getFtlName(), "utf-8");
        }else{
            cfg.setClassForTemplateLoading(GenerateUtil.class, ftlPath);
            template = cfg.getTemplate(basePackage.getFtlName(), "utf-8");
        }
        //新生成的文件的路径
        String newPath = System.getProperty("user.dir") + basePackage.getOutputFilePath();
        //判断生成路径是否存在  不存在就创建
        PathUtil.checkDirAndCreate(newPath);
        PathUtil.printFileByObject(entity, template, newPath, basePackage.getOutputFileName());
    }

    /**
     * 根据类名初始化basePackage
     *
     * @param basePackage
     * @param className
     * @return
     */
    private static BasePackage makeBasePackage(BasePackage basePackage, String className) {
        String fileName = basePackage.getOutputFileName().replace("*", className);
        String substring = StringUtils.substringBeforeLast(fileName, ".");
        basePackage.setOutputFileName(fileName);
        basePackage.setClassName(substring);
        return basePackage;
    }

    public static void main(String[] args) {
        String clazzName = "StudentRjkl.java";
        String substring = clazzName.substring(0, clazzName.indexOf("."));
        System.out.println(substring);
    }
}
