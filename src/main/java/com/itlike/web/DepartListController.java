package com.itlike.web;

import com.itlike.domain.Department;
import com.itlike.service.DepartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class DepartListController {

    /*注入业务*/
    @Autowired
    private DepartService departmentService;

    /*查询部门*/
    @RequestMapping("/departList")
    @ResponseBody
    public List<Department> departList(){
        List<Department> departmentList = departmentService.getAllDepartment();
        return departmentList;
    }

}
