package ${serviceImplPackage};

import com.cristik.common.base.PageInfo;
import com.cristik.common.exception.BusinessException;
<#list serviceImplImportList as import>
import ${import};
</#list>
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author cristik on ${.now}.
 */
@Service
@Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,rollbackFor = Exception.class)
public class ${serviceImplClassName} implements ${serviceClassName} {

    @Autowired
    ${mapperClassName} ${mapperObjectName};

    /**
     * insert 不忽略null
     */
    @Override
    public boolean insert(${entityClassName} ${entityObjectName}) {
        // TODO: 2016/7/18 设置新增时间,默认状态
        return ${mapperObjectName}.insert(${entityObjectName}) == 1;
    }

    /**
     * insert 忽略null值
     */
    @Override
    public boolean insertSelective(${entityClassName} ${entityObjectName}){
        // TODO: 2016/7/18 设置新增时间,默认状态
        return ${mapperObjectName}.insertSelective(${entityObjectName}) == 1;
    }

    /**
     *根据ID删除记录
     */
    @Override
    public boolean deleteById(Integer id) {
        return ${mapperObjectName}.deleteByPrimaryKey(id) == 1;
    }

    /**
     *根据条件删除记录
     */
    @Override
    public boolean delete(${entityClassName} ${entityObjectName}){
        int num = ${mapperObjectName}.delete(${entityObjectName});
        return num > 0;
    }

    @Override
    public boolean update(${entityClassName} ${entityObjectName}) {
        // TODO: 2016/7/18 设置修改时间
        return ${mapperObjectName}.updateByPrimaryKeySelective(${entityObjectName}) == 1;
    }

    @Override
    @Transactional(readOnly = true)
    public ${entityClassName} queryById(Integer id) {
        ${entityClassName} ${entityObjectName} = new ${entityClassName}();
        ${entityObjectName}.set{bean.keyMethod}(id);
        return ${mapperObjectName}.selectOne(${entityObjectName});
    }

    @Override
    @Transactional(readOnly = true)
    public List<${entityClassName}> query(${entityClassName} ${entityObjectName}){
        return ${mapperObjectName}.select(${entityObjectName});
    }

    @Override
    @Transactional(readOnly = true)
    public List<${entityClassName}> selectAll(){
        return ${mapperObjectName}.selectAll();
    }

    /**
     * 分页查询
     */
    @Override
    @Transactional(readOnly = true)
    public PageInfo queryPage(PageInfo pageInfo,${entityClassName} ${entityObjectName}) {
        Map map = new HashMap<String,Object>();
        map.put("${entityObjectName}",${entityObjectName});
        map.put("start", pageInfo.getStart());
        map.put("length", pageInfo.getLength());
        List<${entityClassName}> list =  ${mapperObjectName}.queryPage(map);
        Integer count = ${mapperObjectName}.queryCount(map);
        pageInfo.setData(list);
        pageInfo.setRecordsTotal(count);
        pageInfo.setRecordsFiltered(count);
        return pageInfo;
    }

}
