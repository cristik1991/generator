package ${entityPackage};

import javax.persistence.*;
<#list entityImportList  as import>
import ${import};
</#list>

@Table(name="${tableName}")
public class ${entityClassName} {

    <#list fields as field>
    <#if field.key>
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    <#else>
    //${field.comment}
    @Column
    </#if>
    private ${field.javaType} ${field.field};
    </#list>

    public ${entityClassName}() {
    }
    <#list fields as field>

    public void ${field.setMethod}(${field.javaType} ${field.field}){
        this.${field.field} = ${field.field};
    }

    public ${field.javaType} ${field.getMethod}(){
        return ${field.field};
    }
    </#list>
}