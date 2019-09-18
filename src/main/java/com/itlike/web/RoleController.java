package com.itlike.web;

import com.itlike.domain.AjaxRes;
import com.itlike.domain.PageListRes;
import com.itlike.domain.QueryVo;
import com.itlike.domain.Role;
import com.itlike.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

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
    //接收保存角色请求
    @RequestMapping("/saveRole")
    @ResponseBody
    public AjaxRes saveRole(Role role){
        System.out.println("保存来了"+role);
        System.out.println(role);
        AjaxRes ajaxRes = new AjaxRes();
        try {
            roleService.saveRole(role);
            ajaxRes.setSuccess(true);
            ajaxRes.setMsg("保存成功");
        }catch (Exception e) {
            ajaxRes.setSuccess(false);
            ajaxRes.setMsg("保存失败");
            e.printStackTrace();
        }
        return ajaxRes;
    }
    //接受编辑角色请求
    @RequestMapping("/updateRole")
    @ResponseBody
    public AjaxRes updateRole(Role role){
        System.out.println("更新来了"+role);
        AjaxRes ajaxRes = new AjaxRes();
        //调用服务层处理
        try {
            roleService.updateRole(role);
            ajaxRes.setSuccess(true);
            ajaxRes.setMsg("编辑成功");
        }catch (Exception e){
            ajaxRes.setSuccess(false);
            ajaxRes.setMsg("编辑失败");
            e.printStackTrace();
        }
        return ajaxRes;
    }

    //删除角色与所对应的权限
    @RequestMapping("/deleteRole")
    @ResponseBody
    public AjaxRes deleteRole(long rid){
        AjaxRes ajaxRes = new AjaxRes();
        try {
            roleService.deleteRole(rid);
             ajaxRes.setSuccess(true);
             ajaxRes.setMsg("删除成功");
        }catch (Exception e){
            ajaxRes.setSuccess(false);
            ajaxRes.setMsg("删除失败");
            e.printStackTrace();
        }
        return ajaxRes;
    }

    //角色下拉列表
    @RequestMapping("/selectRole")
    @ResponseBody
    public List<Role> roleList(){
        List<Role> roleList = roleService.getRoleList();
        return roleList;
    }
    //根据用户id得到角色id
    @RequestMapping("/getRoleByid")
    @ResponseBody
    public List<Long> getRoleByid(Long id){

            List<Long> role = roleService.getRole(id);
            return role;
    }
}
