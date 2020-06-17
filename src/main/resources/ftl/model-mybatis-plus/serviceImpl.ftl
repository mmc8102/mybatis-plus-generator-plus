package ${basePackageMap['serviceImpl'].packageName};


/**
* @author ${author!}
* @date ${.now?string("yyyy-MM-dd HH:mm:ss")}
**/
@Service
public class ${basePackageMap['serviceImpl'].className} implements ${basePackageMap['service'].className}{
    @Autowired
    private ${basePackageMap['dao'].className} ${basePackageMap['dao'].className ? uncap_first};

}