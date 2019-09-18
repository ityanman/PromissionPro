package com.itlike.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itlike.domain.PageListRes;
import com.itlike.domain.Permission;
import com.itlike.domain.QueryVo;
import com.itlike.domain.Role;
import com.itlike.mapper.RoleMapper;
import com.itlike.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Transactional
@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleMapper roleMapper;
    @Override
    //查询所有角色
    public PageListRes getAllRole(QueryVo vo) {
        Page<Object> page = PageHelper.startPage(vo.getPage(), vo.getRows());
         //调用mapper
        List<Role> roles = roleMapper.selectAll();
        PageListRes pageListRes = new PageListRes();
        pageListRes.setTotal(page.getTotal());
        pageListRes.setRows(roles);
        return pageListRes;
    }

    //保存角色
    @Override
    public void saveRole(Role role) {
        roleMapper.insert(role);
        //保存角色相关联的权限
    List<Permission> permissions = role.getPermissions();
        for (Permission permission : permissions) {
        roleMapper.inserRolePermission(role.getRid(),permission.getPid());
    }
}

     //更新角色
    @Override
    public void updateRole(Role role) {
        roleMapper.updateByPrimaryKey(role);
        //保存角色相关联的权限
        //先删除已存在的
        roleMapper.deleteRolePermission(role.getRid());
        //在增加编辑后的权限
        List<Permission> permissions = role.getPermissions();
        for (Permission permission : permissions) {
            roleMapper.inserRolePermission(role.getRid(),permission.getPid());
        }
    }

    //删除角色和对应的权限
    @Override
    public void deleteRole(long rid) {
        //删除角色
        roleMapper.deleteByPrimaryKey(rid);
        //删除权限
        roleMapper.deleteRolePermission(rid);
    }

    @Override
    public List<Role> getRoleList() {
        List<Role> roles = roleMapper.selectAll();
        return roles;
    }

    //根据用户id得到角色id
    @Override
    public List<Long> getRole(Long id) {
        return roleMapper.getRoleByid(id);
    }


}
