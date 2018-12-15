package ${entityPackage};

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.io.Serializable;
<#list entityImportList  as import>
    import ${import};
</#list>

/**
 * @author cristik
 */
@Data
@Accessors(chain = true)
@Table(name="${tableName}")
public class ${entityClassName} implements Serializable{

<#list fields as field>
    <#if field.key>
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    <#else>
    /**
     * ${field.comment}
     */
     @Column
    </#if>
    private ${field.javaType} ${field.field};
</#list>
}