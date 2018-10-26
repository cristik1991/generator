<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd" >
<mapper namespace="${namespace}" >
    <resultMap id="BaseResultMap" type="${type}" >
    <#list columns as field>
        <#if field.key>
        <id column="${field.column}" property="${field.field}" javaFullType="${field.javaFullType}" jdbcType="${field.mybatisType}"/>
        <#else>
        <result column="${field.column}" property="${field.field}" javaFullType="${field.javaFullType}" jdbcType="${field.mybatisType}"/>
        </#if>
    </#list>
    </resultMap>
</mapper>