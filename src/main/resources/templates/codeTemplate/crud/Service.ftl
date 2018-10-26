package ${servicePackage};

import com.cristik.common.base.PageInfo;
<#list serviceImportList as import>
import ${import};
</#list>

import java.util.List;

/**
 * @author cristik on ${.now}.
 */

public interface ${serviceClassName} {

    boolean insert(${entityClassName} ${entityObjectName});

    boolean insertSelective(${entityClassName} ${entityObjectName});

    boolean deleteById(Integer id);

    boolean delete(${entityClassName} ${entityObjectName});

    boolean update(${entityClassName} ${entityObjectName});

    ${entityClassName} queryById(Integer id);

    List<${entityClassName}> query(${entityClassName} ${entityObjectName});

    List<${entityClassName}> selectAll();

    PageInfo queryPage(PageInfo pageInfo, ${entityClassName} ${entityObjectName});

}