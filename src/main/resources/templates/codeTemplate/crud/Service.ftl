package ${servicePackage};

import com.cristik.common.message.Pagination;
<#list serviceImportList as import>
import ${import};
</#list>

import java.util.List;

/**
 * @author cristik
 */
public interface ${serviceClassName} {

    /**
     * 新增
     *
     * @param ${entityObjectName}
     * @return
     */
    void insert${entityClassName}Selective(${entityClassName} ${entityObjectName});

    /**
     * 根据主键删除
     *
     * @param id
     * @return
     */
    void delete${entityClassName}ById(Integer id);

    /**
     * 根据主键查找角色
     *
     * @param ids
     * @return
     */
    void delete${entityClassName}sByIds(List<Integer> ids);

    /**
     * 根据条件删除
     *
     * @param ${entityObjectName}
     * @return
     */
    void deleteBy${entityClassName}(${entityClassName} ${entityObjectName});

    /**
     * 根据主键修改
     * 更新Null属性
     *
     * @param ${entityObjectName}
     * @return
     */
    void update${entityClassName}(${entityClassName} ${entityObjectName});

    /**
     * 根据主键修改
     * 不更新Null属性
     * @param ${entityObjectName}
     * @return
     */
    void update${entityClassName}Selective(${entityClassName} ${entityObjectName});

    /**
     * 根据主键查找
     *
     * @param id
     * @return
     */
    ${entityClassName} query${entityClassName}ById(Integer id);

    /**
     * 按条件查找
     *
     * @param ${entityObjectName}
     * @return
     */
    List<${entityClassName}> queryBy${entityClassName}(${entityClassName} ${entityObjectName});

    /**
     * 分页查询
     *
     * @param pagination
     * @return
     */
    Pagination queryPagination(Pagination<${entityClassName}, ${entityClassName}> pagination);
}
