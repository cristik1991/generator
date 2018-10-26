<%@page contentType="text/html;charset=UTF-8" %>
<%@include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<title>${table}详情</title>
<head>
    <jsp:include page="../../include/resource.jsp"/>
    <script type="text/javascript">
        var currentMenu = clothingMenu;
    </script>
</head>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">
    <jsp:include page="../../include/header.jsp"/>
    <jsp:include page="../../include/navigation.jsp"/>
    <div class="content-wrapper">
        <section class="content-header">
            <h1>${table}详情</h1>
            <ol class="breadcrumb">
                <li><a href="#"><i class="fa fa-dashboard"></i>主菜单</a></li>
                <li><a href="${r'${base}'}/${pathName}/list"><i class="fa fa-dashboard"></i>${table}列表</a></li>
                <li class="active">${table}详情</li>
            </ol>
        </section>
        <section class="content">
            <div class="row">
                <div class="col-md-12">
                    <div class="box box-primary ">
                        <form class="form-horizontal" autocomplete="off">
                            <div class="box-header with-border">
                                <div class="col-md-2">
                                    <button type="button" class="btn topBackButton pull-left" onclick="window.location.href = '${r'${base}'}/${pathName}/list'">返回</button>
                                </div>
                            </div>
                            <c:choose>
                                <c:when test="<#noparse>${</#noparse>${table}!=null<#noparse>}</#noparse>">
                                    <div class="box-body">
                                        <div class="col-md-8 col-md-offset-1">
                                        <#list fields as field>
                                            <div class="form-group">
                                                <label class="col-md-4 control-label">${field.comment}</label>
                                                <div class="col-md-8">
                                                    <input type="text" value="${table}.${field.field}" class="form-control" disabled="disabled" />
                                                </div>
                                            </div>
                                        </#list>
                                        </div>
                                    </div>
                                </c:when>
                                <c:otherwise>
                                    <div class="box-body">
                                        <div class="callout callout-info">
                                            <p>无数据</p>
                                        </div>
                                    </div>
                                </c:otherwise>
                            </c:choose>
                        </form>
                        <div class="box-footer"></div>
                    </div>
                </div>
            </div>
        </section>
    </div>
    <jsp:include page="../../include/footer.jsp"/>
</div>
<script src="${r'${base}'}/static/plugins/adminlte-2.3.0/js/demo.js"></script>
<script src="${r'${base}'}/static/plugins/adminlte-2.3.0/js/app.min.js"></script>
</body>
</html>

