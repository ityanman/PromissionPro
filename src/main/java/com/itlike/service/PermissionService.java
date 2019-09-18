package com.itlike.service;

import com.itlike.domain.Permission;

import java.util.List;

public interface PermissionService {
    //查询所有权限
    public List<Permission> getAllPermission();

   public List<Permission>  getPermissionByid(long rid);
}
