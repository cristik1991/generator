package ${mapperPackage};

<#list mapperImportList as import>
import ${import};
</#list>
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @author cristik on ${.now}.
 */
public interface ${mapperClassName} extends Mapper<${entityClassName}> {

    List<${entityClassName}> queryPage(Map map);

    Integer queryCount(Map map);

}