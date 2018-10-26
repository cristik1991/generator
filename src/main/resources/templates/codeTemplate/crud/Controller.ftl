package ${controllerPackage};

import com.cristik.common.base.BaseController;
import com.cristik.common.base.PageInfo;
import com.cristik.common.exception.ResponseInfo;
import com.cristik.common.utils.RequestUtil;
<#list controllerImportList as import>
import ${import};
</#list>

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author cristik on ${.now}.
 */
@Controller
@RequestMapping("/${controllerPath}")
public class ${controllerClassName} extends BaseController{

    @Autowired
    ${serviceClassName} ${serviceImplObjectName};

    /**
     *跳转到新增页面
     */
    @RequestMapping(value="/insert",method = RequestMethod.GET)
    public String insertView(){
        return "/sys/${controllerPath}/createnew";
    }

    /**
     *新增记录
     */
    @ResponseBody
    @RequestMapping(value="/insert",method = RequestMethod.POST)
    public String create(${entityClassName} ${entityObjectName}){
        if(${serviceImplObjectName}.insertSelective(${entityObjectName})){
            return success(RequestUtil.getRequestId(), ResponseInfo.SUCCESS);
        }else{
            return error(RequestUtil.getRequestId(), ResponseInfo.ERROR);
        }
    }

    /**
     *根据ID删除
     */
    @ResponseBody
    @RequestMapping(value="/deleteById")
    public String deleteById(Integer id){
        if(${serviceImplObjectName}.deleteById(id)){
            return success(RequestUtil.getRequestId(), ResponseInfo.SUCCESS);
        }else{
            return error(RequestUtil.getRequestId(), ResponseInfo.ERROR);
        }
    }

    /**
     *根据条件删除
     */
    @ResponseBody
    @RequestMapping(value="delete")
    public String delete(${entityClassName} ${entityObjectName}){
        if(${serviceImplObjectName}.delete(${entityObjectName})){
            return success(RequestUtil.getRequestId(), ResponseInfo.SUCCESS);
        }else{
            return error(RequestUtil.getRequestId(), ResponseInfo.ERROR);
        }
    }

    /**
     *跳转到修改页面
     */
    @RequestMapping(value="/update",method = RequestMethod.GET)
    public String updateView(ModelMap modelMap,Integer id){
        ${entityClassName} ${entityObjectName} = ${serviceImplObjectName}.queryById(id);
        modelMap.put("${entityObjectName}",${entityObjectName});
        return "/sys/${controllerPath}/update";
    }

    /**
     *根据主键修改
     */
    @ResponseBody
    @RequestMapping(value="/update",method = RequestMethod.POST)
    public String update(${entityClassName} ${entityObjectName}){
        if(${serviceImplObjectName}.update(${entityObjectName})){
            return success(RequestUtil.getRequestId(), ResponseInfo.SUCCESS);
        }else{
            return error(RequestUtil.getRequestId(), ResponseInfo.ERROR);
        }
    }

    /**
     *跳转到列表页
     */
    @RequestMapping(value = "list",method = RequestMethod.GET)
    public String queryPage(){
        return "/sys/${controllerPath}/list";
    }

    /**
     * 异步分页查询列表信息
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "querydata",method = RequestMethod.POST)
    public String queryData(PageInfo pageInfo,${entityClassName} ${entityObjectName}){
        pageInfo = ${serviceImplObjectName}.queryPage(pageInfo,${entityObjectName});
        return pageInfo.toJSONString();
    }

    /**
     *根据条件查询所有记录不分页
     */
    @RequestMapping(value="/query")
    public String query(ModelMap model,${entityClassName} ${entityObjectName}){
        List<${entityObjectName}> list = ${serviceImplObjectName}.query(${entityObjectName});
        model.put("list",list);
        return "/sys/${controllerPath}/queryall";
    }


    /**
     * 查看详情
     */
    @RequestMapping(value="/view")
    public String view(ModelMap modelMap , Integer id){
        ${entityClassName} ${entityObjectName} = ${serviceImplObjectName}.queryById(id);
        modelMap.put("${entityObjectName}",${entityObjectName});
        return "/sys/${controllerPath}/view";
    }
}

