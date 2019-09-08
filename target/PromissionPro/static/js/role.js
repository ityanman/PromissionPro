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
        toolbar: '#tb',
        striped:true,
        singleSelect:true,
    });

    //添加/编辑对话框
    $("#dialog").dialog({
        width:600,
        height:500,
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

    })
})