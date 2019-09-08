package com.itlike.service;

import com.itlike.domain.PageListRes;
import com.itlike.domain.QueryVo;

public interface RoleService {
    //查询所有角色
    public PageListRes getAllRole(QueryVo vo);
}
