package com.itlike.service;

import com.itlike.domain.PageListRes;
import com.itlike.domain.QueryVo;
import com.itlike.domain.Role;

import java.util.List;

public interface RoleService {
    //查询所有角色
    public PageListRes getAllRole(QueryVo vo);

    void saveRole(Role role);

    void updateRole(Role role);

    void deleteRole(long rid);

    List<Role> getRoleList();
    //根据用户id得到角色id
    List<Long> getRole(Long id);
}
