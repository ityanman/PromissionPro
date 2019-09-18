$(function () {
    //角色列表展示
    $("#role_dg").datagrid({
        url:"/roleList",
        columns:[[
            {field:'rnum',title:'角色编号',width:100,align:'center'},
            {field:'rname',title:'角色名称',width:100,align:'center'},
        ]],
        fit:true,
        fitColumns:true,
        rownumbers:true,
        pagination:true,
        toolbar: '#toolbar',
        striped:true,
        singleSelect:true,
    });

    //添加or编辑对话框
    $("#dialog").dialog({
        closed:true,
        width:600,
        height:500,
        //保存\关闭按钮
        //添加保存和关闭按钮
        buttons:[{
            text:'保存',
            handler:function(){
                //判断所点击是编辑还是删除
                var url;
                var rid = $("[name = 'rid']").val();
                if (rid){//判断rid是否为空
                    url = "updateRole"
                }else {
                    url = "saveRole"
                }

                //提交表单
                $("#myform").form("submit",{
                   onSubmit:function(param){//添加额外参数
                       //获取已选择的权限
                       var allRow = $("#role_data2").datagrid("getRows")
                       //遍历出每个已选择的权限
                       for(var i=0;i<allRow.length;i++){
                           //取出每个权限
                           var row = allRow[i];
                           //封装到permission集合中
                           param["permissions["+i+"].pid"] = row.pid;
                       }
                   },
                    url:url,
                    success:function (data) {
                        data = $.parseJSON(data)
                        if (data.success){
                            $.messager.alert("温馨提示",data.msg)
                            //关闭对话框
                            $("#dialog").dialog(close())
                            //重新加载列表数据
                            $("#role_dg").datagrid("reload")
                        }else {
                            $.messager.alert("温馨提示",data.msg)
                        }
                    }
                })
            }
        },{
            text:'关闭',
            handler:function(){
             $("#dialog").dialog("close")
            }
        }]
    })
    /*权限列表*/
    $("#role_data1").datagrid({
        title:"所有权限",
        columns:[[
            {field:'pname',title:'权限名称',width:100,align:'center'},
        ]],
        url:'permissionList',
        width:250,
        height:400,
        fitColumns:true,
        singleSelect:true,
        onClickRow:function (rowIndex,rowData) {
            //判断是否已存在该权限
            var allRows = $("#role_data2").datagrid("getRows");
            for (var i = 0; i<allRows.length;i++){
                //取出每一行
               var row=  allRows[i];
               if (row.pid==rowData.pid){//已经存在该权限
                   //获取已经存在权限的角标
                   var index = $("#role_data2").datagrid("getRowIndex",row);
                   //让该权限成为选中状态
                   $("#role_data2").datagrid("selectRow",index)
                   return;
               }
            }
        //把当前选中的添加到已选权限
            $("#role_data2").datagrid("appendRow",rowData);
        }
    })
    $("#role_data2").datagrid({
        title:"已选权限",
        columns:[[
            {field:'pname',title:'权限名称',width:100,align:'center'},
        ]],
        width:250,
        height:400,
        fitColumns:true,
        singleSelect:true,
        onClickRow:function (rowIndex,rowData) {
            //删除已选中的权限
            $("#role_data2").datagrid("deleteRow",rowIndex)
        }
    })
    //添加角色
    $("#add").click(function () {
        //设置标题
        $("#dialog").dialog("setTitle","添加角色");
        $("#dialog").dialog("open")
        //清空表单中的数据
        $("#myform").form("clear")
        //删除上次缓存的已选择的权限
        $('#role_data2').datagrid('loadData', { total: 0, rows: [] });
    })
    //编辑角色
    $("#edit").click(function () {
        //设置标题
        $("#dialog").dialog("setTitle","编辑角色");
        //判断是否选中一行
        var rowdata = $("#role_dg").datagrid("getSelected");
        if (!rowdata){
            $.messager.alert("温馨提示","请选中一行");
            return;
        }
        //弹出对话框
        $("#dialog").dialog("open")
        //回显已选择的权限
        var options = $("#role_data2").datagrid("options")//加载当前角色下的权限
        options.url = "/getPermissionByRoleid?rid="+rowdata.rid;
        $("#role_data2").datagrid("load");
        //回显数据
        $("#myform").form("load",rowdata);


    })
    //删除角色
    $("#remove").click(function () {
        //判断是否选中一行
        var rowdata = $("#role_dg").datagrid("getSelected");
        if (!rowdata){
            $.messager.alert("温馨提示","请选中一行");
            return;
        }
        //弹出窗口提醒用户是否确认删除角色
        $.messager.confirm("确认","确认删除？",function (res) {
            if (res){//如果点击确认
                //做删除操作
                $.get("/deleteRole?rid="+rowdata.rid,function (data) {
                 if (data.success){
                     $.messager.alert("温馨提示",data.msg)
                     //重新加载页面
                     $("#role_dg").datagrid("load")
                 }else {
                     $.messager.alert("温馨提示",data.msg)
                 }
             })

            }
        })
    })
})