<%@page contentType="text/html;charset=UTF-8" %>
<%@include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<title>${table}修改</title>
<head>
    <jsp:include page="../../include/resource.jsp"/>
    <script type="text/javascript">
        var currentMenu = clothingMenu;
    </script>
    <script>

    </script>
</head>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">
    <jsp:include page="../../include/header.jsp"/>
    <jsp:include page="../../include/navigation.jsp"/>
    <div class="content-wrapper">
        <!-- Content Header (Page header) -->
        <section class="content-header">
            <h1>${table}修改</h1>
            <ol class="breadcrumb">
                <li><a href="#"><i class="fa fa-dashboard"></i>主菜单</a></li>
                <li class="active">${table}修改</li>
            </ol>
        </section>
        <section class="content">
            <div class="row">
                <div class="col-md-12">
                    <div class="box box-primary ">
                        <form id="form" class="form-horizontal" autocomplete="off">
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
                                                    <input type="text" name="${field.field}" value="<#noparse>${</#noparse>${table}.${field.field}<#noparse>}</#noparse>" class="form-control" required maxlength="15"/>
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
                            <div class="box-footer">
                                <div class="col-md-2 col-md-offset-10">
                                    <button type="submit" class="btn btn-info pull-right">保存</button>
                                </div>
                            </div>
                        </form>
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

