package com.itlike.service.impl;

import com.itlike.domain.Permission;
import com.itlike.mapper.PermissionMapper;
import com.itlike.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
@Transactional
@Service
public class PermissionServiceImpl implements PermissionService {
    @Autowired
     private PermissionMapper permissionMapper;
    @Override
    public List<Permission> getAllPermission() {
        List<Permission> permissions = permissionMapper.selectAll();
        return permissions;
    }

    @Override
    public List<Permission> getPermissionByid(long rid) {

        List<Permission> permissions = permissionMapper.getPrimaryByrid(rid);
        return permissions;
    }
}
