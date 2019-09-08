package com.itlike.web;

import com.itlike.domain.AjaxRes;
import com.itlike.domain.Employee;
import com.itlike.domain.PageListRes;
import com.itlike.domain.QueryVo;
import com.itlike.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
    public String employee(){ return "employee"; }

    //保存员工
    @RequestMapping("/saveEmployee")
    @ResponseBody
    public AjaxRes employeeForm(Employee employee){
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
}
