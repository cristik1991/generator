package ${mapperPackage};

import com.cristik.common.message.Pagination;
<#list mapperImportList as import>
import ${import};
</#list>
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @author cristik
 */
public interface ${mapperClassName} extends Mapper<${entityClassName}> {

    /**
     * 分页查询列表
     * @param pagination
     * @return
     */
    List<${entityClassName}> queryPaginationData(@Param("pagination") Pagination pagination);

    /**
     * 查询分页记录总条数
     * @param pagination
     * @return
     */
    Integer queryPaginationCount(@Param("pagination") Pagination pagination);

    /**
     * 批量删除
     * @param ids
     * @return
     */
    Integer delete${entityClassName}sByIds(@Param("ids") List<Integer> ids);

}
