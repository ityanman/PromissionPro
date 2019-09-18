<%--
  Created by IntelliJ IDEA.
  User: 10458
  Date: 2019/9/4
  Time: 15:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <%@include file="/static/common/common.jsp"%>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/employee.js"></script>
</head>
<body>
<%--工具栏按钮--%>
<div id="tb">
    <shiro:hasPermission name="employee:add">
        <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" id="add">添加</a>
    </shiro:hasPermission>

    <shiro:hasPermission name="employee:edit">
        <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" id="edit">编辑</a>
    </shiro:hasPermission>

    <shiro:hasPermission name="employee:delete">
        <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" id="delete">离职</a>
    </shiro:hasPermission>

    <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-reload',plain:true" id="reload">刷新</a>
    <input name="searchBox" id="searchBtn" class="easyui-searchbox" style="width:200px ;height:30px;padding-left: 5px" placeholder="搜索..."/>
    <%--<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true" id="search">搜索</a>--%>
</div>
<%--添加对话框弹出--%>
<div id="dialog">
    <form id="employeeForm">
        <table align="center" style="border-spacing: 0px 10px">
            <%--添加一个隐藏域---编辑--%>
            <input type="hidden" name="id"/>
            <tr>
                <td>用户名:</td>
                <td><input type="text"name="username" class="easyui-validatebox"></td>
            </tr>

            <tr id="password">
                <td>密码:</td>
                <td><input type="text" name="password" class="easyui-validatebox" ></td>
            </tr>

            <tr>
                <td>手机:</td>
                <td><input type="text" name="tel" class="easyui-validatebox" ></td>
            </tr>
            <tr>
                <td>邮箱:</td>
                <td><input type="text" name="email" class="easyui-validatebox" ></td>
            </tr>
            <tr>
                <td>入职日期:</td>
                <td><input type="text" name="inputtime" class="easyui-datebox"></td>
            </tr>
            <tr>
                <td>部门:</td>
                <td><input id="department" name="department.id" placeholder="请选择部门"/></td>
            </tr>
            <tr>
                <td>是否管理员:</td>
                <td><input id="admin" name="admin" placeholder="是否为管理员"/></td>
            </tr>
            <tr>
                    <td>选择角色:</td>
                    <td><input id="role" name="role" placeholder="选择角色"/></td>
             </tr>
        </table>
    </form>

</div>
<%--数据列表--%>
<table id="dg"></table>
</body>
</html>
