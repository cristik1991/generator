<%@page contentType="text/html;charset=UTF-8" %>
<%@include file="/WEB-INF/views/include/taglib.jsp" %>
<html>
<title>${table}</title>
<head>
    <jsp:include page="../../include/resource.jsp"/>
    <script type="text/javascript">
        var currentMenu = taskMenu;
    </script>
</head>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">
    <%--头部--%>
    <jsp:include page="../../include/header.jsp"/>
    <%--导航栏--%>
    <jsp:include page="../../include/navigation.jsp"/>
    <%--主体部分--%>
    <div class="content-wrapper">
        <section class="content-header">
            <h1>
                ${table}列表
            </h1>
            <ol class="breadcrumb">
                <li><a href="#"><i class="fa fa-dashboard"></i>主菜单</a></li>
                <li class="active">${table}列表</li>
            </ol>
        </section>
        <section class="content-header">
            <form id="search-form" class="form-horizontal" autocomplete="off">
        <#if (fields)??>
            <#assign index =0/>
            <#list fields as field>
                <#if index%4 == 0>
                <div class="form-group">
                </#if>
                <#assign index=index+1/>
                    <label class="col-md-1 control-label">${field.comment}</label>
                    <div class="col-md-2">
                        <input type="text" name="${field.field}" class="form-control"/>
                    </div>
                    <#if !field_has_next && index%4 !=0>
                    <div class="col-md-1 pull-right">
                        <button type="button" id="search" class="btn btn-primary pull-right">查询</button>
                    </div>
                </div>
                    </#if>
                <#if index%4 == 0 && !field_has_next>
                </div>
                <div class="form-group">
                    <div class="col-md-1 pull-right">
                        <button type="button" id="search" class="btn btn-primary pull-right">查询</button>
                    </div>
                </div>
                </#if>
            </#list>
        </#if>
            </form>
        </section>
        <section class="content">
            <div class="row">
                <div class="col-md-12">
                    <div class="box">
                        <div class="box-header">
                        </div>
                        <div class="box-body">
                            <button id="insert" type="button" class="btn btn-sm btn-twitter pull-right">新增${table}
                            </button>
                            <table id="dtable" class="table table-bordered table-hover">
                                <thead>
                                    <tr>
                                        <#list fields as field>
                                        <th>${field.comment}</th>
                                        </#list>
                                        <th>操作</th>
                                    </tr>
                                </thead>
                                <tbody>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </section>
    </div>

    <#--新增弹窗-->
    <div id="insert-div" style="display: none; width: 565px; padding-left: 15px;">
        <form id="insert-form" class="form-horizontal" autocomplete="off">
            <div class="box-header"></div>
            <#list fields as field>
                <div class="form-group">
                    <label class="col-sm-3 control-label">${field.comment}</label>
                    <div class="col-sm-8">
                        <input type="text" name="${field.field}" class="form-control" id="insert-${field.field}" required placeholder="${field.comment}">
                    </div>
                </div>
            </#list>

        </form>
    </div>
    <#--修改弹窗-->
    <div id="update-div" style="display: none; width: 565px; padding-left: 15px;">
        <form id="modify-form" class="form-horizontal" autocomplete="off">
            <input type="hidden" id="change-id" name="id">
            <div class="box-header"></div>
            <#list fields as field>
            <div class="form-group">
                <label class="col-sm-3 control-label">${field.comment}</label>
                <div class="col-sm-8">
                    <input type="text" name="${field.field}" class="form-control" id="update-${field.field}" required placeholder="${field.comment}">
                </div>
            </div>
            </#list>
        </form>
    </div>
    <jsp:include page="../../include/footer.jsp"/>
</div>
<script src="${r'${base}'}/static/plugins/adminlte-2.3.0/js/demo.js"></script>
<script src="${r'${base}'}/static/plugins/adminlte-2.3.0/js/app.min.js"></script>
<script src="${r'${base}'}/static/views/sys/monitorurl/list.js"></script>
</body>
</html>

