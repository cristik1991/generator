package ${controllerPackage};

import com.cristik.common.message.Pagination;
import com.cristik.common.utils.FormatUtil;
import com.cristik.common.utils.MessageUtil;
<#list controllerImportList as import>
import ${import};
</#list>
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author cristik
 */

@Api(value = "", description = "", tags = "")
@RestController
@RequestMapping("/")
public class ${controllerClassName} {

    @Autowired
    ${serviceClassName} ${serviceImplObjectName};

    @ApiOperation(value = "新增")
    @PostMapping(value = "/${controllerPath}", produces = MediaType.APPLICATION_JSON_VALUE)
    public String create(${entityClassName} ${entityObjectName}) {
        ${serviceImplObjectName}.insert${entityClassName}Selective(${entityObjectName});
        return MessageUtil.success();
    }

    @ApiOperation(value = "删除单条")
    @DeleteMapping(value = "/${controllerPath}/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public String delete(@PathVariable("id") Integer id) {
        ${serviceImplObjectName}.delete${entityClassName}ById(id);
        return MessageUtil.success();
    }

    @ApiOperation(value = "批量删除")
    @DeleteMapping(value = "/${controllerPath}s", produces = MediaType.APPLICATION_JSON_VALUE)
    public String delete(@RequestBody List<Integer> ids) {
        ${serviceImplObjectName}.delete${entityClassName}sByIds(ids);
        return MessageUtil.success();
    }

    @ApiOperation(value = "修改")
    @PutMapping(value = "/${controllerPath}s", produces = MediaType.APPLICATION_JSON_VALUE)
    public String update(@RequestBody ${entityClassName} ${entityObjectName}) {
        ${serviceImplObjectName}.update${entityClassName}Selective(${entityObjectName});
        return MessageUtil.success();
    }

    @ApiOperation(value = "分页查询")
    @GetMapping(value = "/${controllerPath}s", produces = MediaType.APPLICATION_JSON_VALUE)
    public String queryData(${entityClassName} ${entityObjectName}, Integer currentPage, Integer pageSize) {
        Pagination<${entityClassName}, ${entityClassName}> pagination = new Pagination(currentPage, pageSize, ${entityObjectName});
        pagination = ${serviceImplObjectName}.queryPagination(pagination);
        return MessageUtil.success(FormatUtil.format(pagination));
    }

}
