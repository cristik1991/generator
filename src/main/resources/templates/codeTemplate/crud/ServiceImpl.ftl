package ${serviceImplPackage};

import com.cristik.common.message.Pagination;
<#list serviceImplImportList as import>
import ${import};
</#list>
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author cristik
 */

@Service
@Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,rollbackFor = Exception.class)
public class ${serviceImplClassName} implements ${serviceClassName} {

    @Autowired
    ${mapperClassName} ${mapperObjectName};

    @Override
    public void insert${entityClassName}Selective(${entityClassName} ${entityObjectName}) {
        //null属性不保存使用默认值
        ${mapperObjectName}.insertSelective(${entityObjectName});
    }

    @Override
    public void delete${entityClassName}ById(Integer id) {
        ${mapperObjectName}.deleteByPrimaryKey(id);
    }

    @Override
    public void delete${entityClassName}sByIds(List<Integer> ids) {
        ${mapperObjectName}.delete${entityClassName}sByIds(ids);
    }

    @Override
    public void deleteBy${entityClassName}(${entityClassName} ${entityObjectName}) {
        ${mapperObjectName}.delete(${entityObjectName});
    }

    @Override
    public void update${entityClassName}(${entityClassName} ${entityObjectName}) {
        //跟新包括null值
        ${mapperObjectName}.updateByPrimaryKey(${entityObjectName});
    }

    @Override
    public void update${entityClassName}Selective(${entityClassName} ${entityObjectName}) {
        //跟新不为null的值
        ${mapperObjectName}.updateByPrimaryKeySelective(${entityObjectName});
    }

    @Override
    public ${entityClassName} query${entityClassName}ById(Integer id) {
        return ${mapperObjectName}.selectByPrimaryKey(id);
    }

    @Override
    public List<${entityClassName}> queryBy${entityClassName}(${entityClassName} ${entityObjectName}) {
        return ${mapperObjectName}.select(${entityObjectName});
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
    public Pagination queryPagination(Pagination<${entityClassName}, ${entityClassName}> pagination) {
        pagination.setData(${mapperObjectName}.queryPaginationData(pagination));
        pagination.setTotal(${mapperObjectName}.queryPaginationCount(pagination));
        return pagination;
    }
}
