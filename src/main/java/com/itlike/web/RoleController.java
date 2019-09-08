package com.itlike.web;

import com.itlike.domain.PageListRes;
import com.itlike.domain.QueryVo;
import com.itlike.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class RoleController {
    @Autowired
    private RoleService roleService;
    @RequestMapping("/role")
    public String role(){
        return "role";
    }

    //查询所有角色
    @RequestMapping("/roleList")
    @ResponseBody
    public PageListRes roleList(QueryVo vo){
        //调用服务层
        PageListRes ListRole = roleService.getAllRole(vo);
       return ListRole;
    }
}
