package com.itlike.web;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.itlike.domain.AjaxRes;
import com.itlike.domain.Employee;
import com.itlike.domain.PageListRes;
import com.itlike.domain.QueryVo;
import com.itlike.service.EmployeeService;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;
    @RequestMapping("/employeeList")
    @ResponseBody
    public PageListRes employeeList(QueryVo vo){
        //调用服务层查询所有员工
        PageListRes pageListRes = employeeService.getAllEmployee(vo);
        System.out.println(vo);
        return pageListRes;
    }

    @RequestMapping("/employee")
    @RequiresPermissions("employee:index")
    public String employee(){ return "employee"; }

    //保存员工
    @RequestMapping("/saveEmployee")
    @ResponseBody
    @RequiresPermissions("employee:add")
    public AjaxRes employeeForm(Employee employee){
        System.out.println("------------------------------");
        System.out.println(employee.getRoles());
        System.out.println(employee);
        employee.setState(true);
        AjaxRes ajaxRes = new AjaxRes();
        try {
            //调用服务层
            employeeService.savaEmployee(employee);
            ajaxRes.setSuccess(true);
            ajaxRes.setMsg("保存成功");
        }catch (Exception e){
            ajaxRes.setSuccess(false);
            ajaxRes.setMsg("保存失败");
        }
        return ajaxRes;
    }
    //编辑员工
    @RequestMapping("/editEmployee")
    @ResponseBody
    @RequiresPermissions("employee:edit")
    public AjaxRes EditEmployee(Employee employee){
        AjaxRes ajaxRes = new AjaxRes();
        try {
            //调用服务层更新员工
            employeeService.updateEmployee(employee);
            ajaxRes.setSuccess(true);
            ajaxRes.setMsg("编辑成功！");
        }catch (Exception e){
            ajaxRes.setSuccess(false);
            ajaxRes.setMsg("编辑失败");
        }
         return ajaxRes;
    }
    //更新员工离职状态
    @RequestMapping("/updateState")
    @ResponseBody
    @RequiresPermissions("employee:delete")
    public AjaxRes updateState(Long id){
        AjaxRes ajaxRes = new AjaxRes();
        try {
            //调用服务层更新员工
            employeeService.updateState(id);
            ajaxRes.setSuccess(true);
            ajaxRes.setMsg("编辑成功！");
        }catch (Exception e){
            ajaxRes.setSuccess(false);
            ajaxRes.setMsg("编辑失败");
        }
        return ajaxRes;
    }
    @ExceptionHandler(AuthorizationException.class)
    public void handleShiroException(HandlerMethod method,HttpServletResponse response) throws Exception {
        /*跳转到一个界面，界面提示没有权限
        * 判断当前请求是不是json请求，如果是 返回json给浏览器 让浏览器自己做跳转
        * 获取方法上的注解*/
        ResponseBody methodAnnotation = method.getMethodAnnotation(ResponseBody.class);
        if (methodAnnotation!=null){
            //设置字符集编码
            response.setCharacterEncoding("utf-8");
            AjaxRes ajaxRes = new AjaxRes();
            ajaxRes.setSuccess(false);
            ajaxRes.setMsg("你没有权限操作！");
            String s = new ObjectMapper().writeValueAsString(ajaxRes);
            response.getWriter().print(s);
        }else {
            response.sendRedirect("noPermission.jsp");
        }

    }
}
