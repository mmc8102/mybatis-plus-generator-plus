## 比mybatis-plus代码生成器更好用的生成器 mybatis-plus-generator-plus 
### 优势
- #### 可以自动生成代码注释从数据库中取得
- #### 使用freemarker来自定义模板使其更加方便
- #### 灵活选择模板，可以自行配置多套，并且灵活切换
- #### 已经给定了预留的模板，开箱即用
- #### 无需修改代码，仅修改GenerateConfig.xml中的配置即可，更加清晰 

### 使用方式 
 - ##### 1.下载该项目到本地并install到本地仓库,在项目中引用依赖
 - ##### 2.拷贝src\main\resources 下的 GenerateConfig.xml文件 并对文件进行修改
```
    <?xml version="1.0" encoding="UTF-8"?>
    <root>
        <!--配置编码的作者 即 @author 后的名称-->
        <author>wangli</author>
        <!--oracle数据库连接-->
        <!--<jdbc type="oracle" database="">
            <param name="driverClassName">oracle.jdbc.driver.OracleDriver</param>
            <param name="url">jdbc:oracle:thin:@172.16.30.100:1521:orcl</param>
            <param name="username">eims</param>
            <param name="password">eims</param>
        </jdbc>-->
        <!--mysql数据库连接 type 数据库类型  dataspace 所需要生成的数据库的名字-->
        <jdbc type="mysql" database="test">
            <param name="driverClassName">com.mysql.cj.jdbc.Driver</param>
            <param name="jdbcUrl">jdbc:mysql://localhost:3306/test?serverTimezone=GMT%2B8</param>
            <param name="username">root</param>
            <param name="password">admin</param>
        </jdbc>
        <!--生成类的基包-->
        <!--生成的文件路径跟基类包有关-->
        <!--当前生成文件的路径为/src/main/java/-->
        <basePackage>cn.mmc8102.mybatisplusgeneratorplus</basePackage>
    
        <!--需要生成的实体类和表之间对应关系,可以配置多个-->
        <table>
            <mapping tableName="employee" modelName="Employee"/>
        </table>
    
    
        <!-- 模板配置 模板文件路径 生成的包名以及文件名 * 代表className 如 Student-->
        <!-- path里面选择配置不同的模板 如model1 都在ftl目录下-->
        <ftl path="/ftl/model-mybatis-plus">
            <param name="model" basePackageName="domain">*.java</param>
            <param name="dao" basePackageName="mapper">*Mapper.java</param>
            <param name="mapper" basePackageName="mapper">*Mapper.xml</param>
            <param name="service" basePackageName="service">I*Service.java</param>
            <param name="serviceImpl" basePackageName="service.impl">*ServiceImpl.java</param>
            <param name="controller" basePackageName="web.controller">*Controller.java</param>
        </ftl>
    </root>
    
```
#### 3.默认使用jar包中的模板,如果觉得模板生成的文件不是很满意，可以拷贝ftl文件夹放到你项目的根路径下,自行进行修改
#### 4.拷贝打开CodeGenerator到你的项目中(建议放到test文件夹下)  这个类不需要做任何修改 直接运行

```
public class CodeGenerator {

    public static void main(String[] args) throws DocumentException, IOException, TemplateException {

        //找到配置文件的路径
        String path = System.getProperty("user.dir") + "/src/main/resources/" + "GenerateConfig.xml";
        final Config config = ConfigParser.getConfig(path);
        System.out.println(config);
        //获取得到的表的集合
        List<EntityVO> entityList = InitDb.getInstence(config).initTables();
        for (EntityVO entity : entityList) {
            GenerateUtil.generateTemplate(entity, "model");
            GenerateUtil.generateTemplate(entity, "dao");
            GenerateUtil.generateTemplate(entity, "mapper");
            GenerateUtil.generateTemplate(entity, "service");
            GenerateUtil.generateTemplate(entity, "serviceImpl");
            GenerateUtil.generateTemplate(entity, "controller");
            System.out.println(entity);
        }
        System.out.println("------------生成完毕!-------------");
    }


```
### 模板文件
- #### 对应的模板文件地址 \ftl\model-mybatis-plus 
- #### 如果觉得模板生成的文件不是很满意，可以自行进行修改

- #### freemarker 取值格式为：${属性名!}，对freemarker取值不熟的同学可以看下面这个链接
- #### <a href="https://blog.csdn.net/xyu1234/article/details/78593893" target="_blank">freemarker取值教程</a>

### 生成文件预览 以Student为例
- #### model文件
```
    package cn.mmc8102.mybatisplusgeneratorplus.domain;
    
    import lombok.Data;
    
    /**
    * 学生表
    * @author wangli
    * @date 2020-05-29
    **/
    @Data
    public class Student {
    
        /**
         * 
         */
        private Integer id;
        /**
         * 学生姓名
         */
        private String name;
        /**
         * 学号
         */
        private String num;
        /**
         * 创建时间
         */
        private LocalDateTime createTime;
        /**
         * 更新时间
         */
        private LocalDateTime updateTime;
    
    }
```
- #### dao文件
```
package cn.mmc8102.mybatisplusgeneratorplus.mapper;


/**
* @author wangli
* @date 2020-05-29
**/
public interface StudentMapper extends BaseMapper<Student>{


}

```
- #### Mapper文件
```
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd" >
<mapper namespace="cn.mmc8102.mybatisplusgeneratorplus.mapper.StudentMapper" >

    <resultMap id="BaseResultMap" type="cn.mmc8102.mybatisplusgeneratorplus.domain.Student" >
        <id column="id" property="id" jdbcType="INTEGER" />
        <result column="name" property="name" jdbcType="VARCHAR" />
        <result column="num" property="num" jdbcType="VARCHAR" />
        <result column="create_time" property="createTime" jdbcType="TIMESTAMP" />
        <result column="update_time" property="updateTime" jdbcType="TIMESTAMP" />
    </resultMap>

    <sql id="Base_Column_List" >
        id,name,num,create_time,update_time
    </sql>

</mapper>
```
- #### Service文件
```
package cn.mmc8102.mybatisplusgeneratorplus.service;

import cn.mmc8102.mybatisplusgeneratorplus.domain.Student;

/**
* @author wangli
* @date 2020-05-29
**/
public interface IStudentService {
    

}

```
- #### ServiceImpl文件
```
package cn.mmc8102.mybatisplusgeneratorplus.service.impl;


/**
* @author wangli
* @date 2020-05-29
**/
@Service
public class StudentServiceImpl implements StudentService{
    @Autowired
    private StudentMapper studentMapper;

}

```
- #### Controller文件
```
package cn.mmc8102.mybatisplusgeneratorplus.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
* @author wangli
* @date 2020-05-29
**/
@Controller
@RequestMapping("student")
public class StudentController {
    @Autowired
    private IStudentService studentService;

}
```

#### 有问题就留言吧！！
