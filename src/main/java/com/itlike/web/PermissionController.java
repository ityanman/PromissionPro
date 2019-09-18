package com.itlike.web;

import com.itlike.domain.Permission;
import com.itlike.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class PermissionController {
    @Autowired
    private PermissionService permissionService;
    @RequestMapping("/permissionList")
    @ResponseBody
    public List<Permission> permissionList(){
        List<Permission> PermissionList = permissionService.getAllPermission();
        return PermissionList;
    }
    //接收 "根据角色id获取权限" 的请求
    @RequestMapping("/getPermissionByRoleid")
    @ResponseBody
    public List<Permission> getPermissionByRoleid(long rid){
        System.out.println("ddddd"+rid);
        //调用service层
        List<Permission> permissions = permissionService.getPermissionByid(rid);
        return permissions;
    }
}
