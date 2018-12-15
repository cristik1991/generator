<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd" >
<mapper namespace="${namespace}" >
    <resultMap id="BaseResultMap" type="${type}" >
    <#list columns as field>
        <#if field.key>
        <id column="${field.column}" property="${field.field}" javaType="${field.javaFullType}" jdbcType="${field.mybatisType}"/>
        <#else>
        <result column="${field.column}" property="${field.field}" javaType="${field.javaFullType}" jdbcType="${field.mybatisType}"/>
        </#if>
    </#list>
    </resultMap>

    <!-- 批量删除 -->
    <delete id="deleteConfigsByIds">
        delete from ${tableName}
        <where>
            ${primaryKey.column} in
            <foreach collection="ids" separator="," open="(" close=")" item="id">
                ${r'#{id,jdbcType=INTEGER}'}
            </foreach>
        </where>
    </delete>
    <!-- 分页查询数据 -->
    <select id="queryPaginationData" resultMap="BaseResultMap">
        select * from ${tableName}
        <where>
            <include refid="queryConfigCaluseSql"/>
        </where>
    </select>
    <!-- 分页查询记录条数 -->
    <select id="queryPaginationCount" resultMap="BaseResultMap">
        select count(1) from ${tableName}
        <where>
            <include refid="queryConfigCaluseSql"/>
        </where>
    </select>

    <sql id="queryConfigCaluseSql">
        <if test="pagination.query!=null">

        </if>
    </sql>
</mapper>