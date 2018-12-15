#Generator使用


##1.配置generator.xml文件

[generator.xml](src/resources/generator.xml)

1.配置properties

    <properties resource="/generator.properties"/>

2.配置数据库连接  

    <connection>
        <property name="driverName" value="${mysql.driverName}"/>
        <property name="url" value="${mysql.connectionURL}"/>
        <property name="username" value="${mysql.user}"/>
        <property name="password" value="${mysql.password}"/>
        <property name="schema" value="${mysql.schema}"/>
    </connection>

>${value}自动从环境变量和加载的properties中替换值
    
3.JavaModel配置

    <javaModels directory="src/test/java" templatePath="src/main/resources/templates/codeTemplate/crud">
        <javaModel override="true">
            <property name="package" value="com.gen.modules.sys.controller"/>
            <property name="templateName" value="Controller.ftl"/>
            <property name="layer" value="controller"/>
        </javaModel>
    </javaModels>
    
>directory: 相对workspace位置  
templatePath: 模板文件相对directory位置  
package: Java的包路径  
override: 是否覆盖默认为false  
templateName：模板名称  
layer:定义模板类型(controller,service,serviceImpl,dao,entity)  

4.MapperModel配置
    
    <mapperModels directory="src/main/resources" templatePath="src/main/resources/templates/codeTemplate/mybatis">
        <mapperModel override="true">
            <property name="targetDirectory" value="/mapping/sys"/>
            <property name="templateName" value="Mapper.ftl"/>
        </mapperModel>
    </mapperModels>
    
>directory: 相对workspace位置  
templatePath: 模板文件相对directory位置  
targetDirectory: 文件输出目录  
override: 是否覆盖默认为false  
templateName：模板名称  

5.HtmlModel配置  
    略。。。
    
6.Table配置
    
    <tables>
        <table prefix="test_">xxx</table>
    </tables>
    
>prefix: 去除表名前缀如test_person对应生成类为Person如不指定前缀生成为TestPerson
    
##2.依赖关系

    //entity ->
    //dao -> entity
    //service -> entity
    //serviceImpl -> entity service dao
    //controller -> entity service
    //Mapper.xml -> dao entity    
    
生成指定组件需指明其依赖项配置


##3.自定义配置

自定义配置
    
    GeneratorFactory.setGeneratorSetting("/path/to/generator_name.xml");

自定义名称策略  
1.定义名称类

    public class TestNameStrategy implements NameStrategy {
        @Override
        public String formatName(Layer layer, String className) {
            switch (layer) {
                case Entity:
                    return className;
                case Dao:
                    return className + "Dao";
                case ServiceImpl:
                    return className + "ServiceImpl";
                case Service:
                    return "I" + className + "Service";
                case Controller:
                    return className + "Controller";
                case MapperXml:
                    return className + "Mapper";
                default:
                    throw new RuntimeException("No supported layer");
            }
        }
    }
2.设置名称策略

    GeneratorFactory.setNameStrategy(new DefaultNameStrategy());

###生成指定文件

    //生成所有组件
    GeneratorFactory.generate();
    //生成Entity
    GeneratorFactory.genEntity();
    //生成Service
    GeneratorFactory.genService();
    //生成ServiceImpl
    GeneratorFactory.genServiceImpl();
    //生成Controller
    GeneratorFactory.genController();
    //生成Dao
    GeneratorFactory.genDao();
    //生成Mapper.xml
    GeneratorFactory.genMapper();
    
##4.编辑模板文件
模板文件基于Freemarker
eg. table = test_pserson prefix = test_
<table>
    <tr><td>Key</td><td>demo</td></tr>
    <tr><td>${tableName}</td><td>test_person</td></tr>
    <tr><td>${entityPackage}</td><td>package xxx.xxx</td></tr>
    <tr><td>${entityImportList}</td><td>List<String></td></tr>
    <tr><td>${entityClassName}</td><td>Person</td></tr>
    <tr><td>${entityObjectName}</td><td>person</td></tr>
    <tr><td>${primaryKey}</td><td>${field}</td></tr>
    <tr><td>${fields}</td><td>List<${field}></td></tr>
    <tr><td>${controllerPath}</td><td>person</td></tr>
    <tr><td>${controllerPackage}</td><td>package xxx.xxx</td></tr>
    <tr><td>${controllerImportList}</td><td>List<String></td></tr>
    <tr><td>${controllerClassName}</td><td>PersonController</td></tr>
    <tr><td>${controllerObjectName}</td><td>personController</td></tr>
    <tr><td>${servicePackage}</td><td>package xxx.xxx</td></tr>
    <tr><td>${serviceImportList}</td><td>List<String></td></tr>
    <tr><td>${serviceClassName}</td><td>IPersonService</td></tr>
    <tr><td>${serviceObjectName}</td><td>personService</td></tr>
    <tr><td>${serviceImplPackage}</td><td>package xxx.xxx</td></tr>
    <tr><td>${serviceImplImportList}</td><td>List<String></td></tr>
    <tr><td>${serviceImplClassName}</td><td>PersonServiceImpl</td></tr>
    <tr><td>${serviceImplObjectName}</td><td>personServiceImpl</td></tr>
    <tr><td>${mapperPackage}</td><td>package xxx.xxx</td></tr>
    <tr><td>${mapperImportList}</td><td>List<String></td></tr>
    <tr><td>${mapperClassName}</td><td>PersonMapper</td></tr>
    <tr><td>${mapperObjectName}</td><td>personMapper</td></tr>
    <tr><td>${namespace}</td><td>com.xxx.xxx.PersonMapper</td></tr>
    <tr><td>${type}</td><td>com.xxx.xxx.Person</td></tr>
    <tr><td>${columns}</td><td>List<${field}></td></tr>
</table>

Field 属性 如数据库字段 varchar user_name comment 用户名
<table>
    <tr><td>${field.javaFullType}</td><td>java.lang.String</td></tr>
    <tr><td>${field.javaType}</td><td>String</td></tr>
    <tr><td>${field.mybatisType}</td><td>VARCHAR</td></tr>
    <tr><td>${field.isDate}</td><td>false</td></tr>
    <tr><td>${field.isBoolean}</td><td>false</td></tr>
    <tr><td>${field.methodName}</td><td>UserName</td></tr>
    <tr><td>${field.setMethod}</td><td>setUserName</td></tr>
    <tr><td>${field.getMethod}</td><td>getUserName</td></tr>
    <tr><td>${field.field}</td><td>userName</td></tr>
    <tr><td>${field.fieldId}</td><td>user-name</td></tr>
    <tr><td>${field.key}</td><td>false</td></tr>
    <tr><td>${field.table}</td><td>test_person</td></tr>
    <tr><td>${field.column}</td><td>USER_NAME</td></tr>
    <tr><td>${field.comment}</td><td>用户名</td></tr>
    <tr><td>${field.dataType}</td><td>varchar</td></tr>
</table> 
    
    
    