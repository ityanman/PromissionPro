$(function () {
    /*员式数据列表*/
    $("#dg").datagrid({
        url:"/employeeList",
        columns:[[
            {field:'username',title:'姓名',width:100,align:'center'},
            {field:'inputtime',title:'入职时间',width:100,align:'center'},
            {field:'tel',title:'电话',width:100,align:'center'},
            {field:'email',title:'邮箱',width:100,align:'center'},
            {field:'department',title:'部门',width:100,align:'center',formatter: function(value,row,index){
                    if (value){
                        return value.name;
                    }
                }},
            {field:'state',title:'状态',width:100,align:'center',formatter: function(value,row,index){
                    if(row.state){
                        return "在职";
                    }else {
                        return "<font style='color: red'>离职</font>"
                    }
                }},
            {field:'admin',title:'管理员',width:100,align:'center',formatter: function(value,row,index){
                    if(row.admin){
                        return "是";
                    }else {
                        return "否"
                    }
                }},
        ]],
        fit:true,
        fitColumns:true,
        rownumbers:true,
        pagination:true,
        toolbar: '#tb',
        striped:true,
        singleSelect:true,
        //修改离职按钮
        onClickRow:function (rowIndex,rowData) {
            if (!rowData.state){
                $("#delete").linkbutton("disable")
            }else {
                $("#delete").linkbutton("enable")
            }
        }
    });
    /*对话框*/
   $("#dialog").dialog({
    width:350,
    height:350,
    closed: true,
       //添加保存和关闭按钮
       buttons:[{
           text:'保存',
           handler:function(){
               //判断所点击的是添加还是修改
               var url;
               var id = $("[name = 'id']").val()
               if (id){
                   url="editEmployee"
               }else {
                   url="saveEmployee"
               }
               //提交表单
               $("#employeeForm").form("submit",{
                   url:url,
                   success:function (data) {
                       data = $.parseJSON(data)
                      if (data.success){
                           $.messager.alert("温馨提示",data.msg)
                          //关闭对话框
                          $("#dialog").dialog(close())
                          //重新加载列表数据
                          $("#dg").datagrid("reload")
                       }else {
                          $.messager.alert("温馨提示",data.msg)
                      }
                   }
               })
           }
           },{
           text:'关闭',
           handler:function(){
               $("#dialog").dialog(close())
           }
       }]
   })
    /*监听添加按钮*/
    $("#add").click(function () {
        //设置标题
        $("#dialog").dialog("setTitle","添加员工");
        //清空对话框中的数据
        $("#employeeForm").form("clear");
        //打开对话框
        $("#dialog").dialog("open");
        /*显示密码*/
        $("#password").hide();
    })

    /*监听编辑按钮*/
    $("#edit").click(function () {
        //设置标题
        $("#dialog").dialog("setTitle","编辑员工");
        //判断是否选中一行
        var rowdata = $("#dg").datagrid("getSelected");
        if (!rowdata){
            $.messager.alert("请选择一行数据")
            return;
        }
        //弹出对话框
        $("#dialog").dialog("open");
        //取消密码验证
        $("#password").hide();
        //回显部门
        rowdata["department.id"] = rowdata["department"].id;
        //回显管理员
        rowdata["admin"] = rowdata["admin"]+""
        //数据回显
        $("#employeeForm").form("load",rowdata);

    })
    /*监听离职按钮*/
    $("#delete").click(function () {
        //判断是否选中一行
        rowdata = $("#dg").datagrid("getSelected");
        if (!rowdata){
            $.messager.alert("提示","请选择一行数据");
            return;
        }
        //提醒用户是否做离职操作
        $.messager.confirm("确认","是否做离职操作",function (res) {
            if (res){
                //做更新操作
                $.get("/updateState?id="+rowdata.id,function (data) {

                     if (data.success){
                         //如果更新成功
                         $.messager.alert("温馨提示",data.msg)
                         //重新加载数据表格
                         $("#dg").datagrid("reload");
                     }else {
                         $.messager.alert("温馨提示",data.msg)
                     }
                })

            }
        })
    })
     /*监听搜索框*/
    $("#searchBtn").searchbox({
        searcher:function (value,name) {
            var keyword = value;
            /*重新加载列表  把参数keyword传过去*/
            $("#dg").datagrid("load",{keyword:keyword});
        }
    })
    /*监听刷新按钮*/
       $("#reload").click(function () {
        //搜索框内容清空
          $("#searchBtn").searchbox('setValue',null);
        //重新加载数据
        $("#dg").datagrid("load",{})


    });
    /*部门下拉列表*/
    $("#department").combobox({
        url:'departList',
        width:150,
        panelHeight:'auto',
        editable:false,
        valueField:"id",
        textField:"name",

        onLoadSuccess:function () {
            $("#department").each(function(i){
                var span = $(this).siblings("span")[i];
                var targetInput = $(span).find("input:first");
                if(targetInput){
                    $(targetInput).attr("placeholder", $(this).attr("placeholder"));
                }
            });
        }
    })
    /*管理员下拉列表*/
    $("#admin").combobox({
        width:150,
        panelHeight:'auto',
        editable:false,
        valueField: 'value',
        textField: 'label',
        data: [{
            label: '是',
            value: 'true'
        },{
            label: '否',
            value: 'false'
        }],
        onLoadSuccess:function () {
            $("#admin").each(function(i){
                var span = $(this).siblings("span")[i];
                var targetInput = $(span).find("input:first");
                if(targetInput){
                    $(targetInput).attr("placeholder", $(this).attr("placeholder"));
                }
            });
        }
    })
});