package com.itlike.service;

import com.itlike.domain.Employee;
import com.itlike.domain.PageListRes;
import com.itlike.domain.QueryVo;

import java.util.List;

public interface EmployeeService {
    //查询所有员工
    public PageListRes getAllEmployee(QueryVo vo);
    //保存员工
    public void  savaEmployee(Employee employee);
     //更新员工
    public void updateEmployee(Employee employee);
       //更新员工离职状态
    void updateState(Long id);
       //根据用户名查询用户
    Employee getEmplByUsername(String username);
        //根据员工id查询对应的角色名称
    List<String> getRolesByEid(Long id);
    //根据员工id查询对应的权限
    List<String> getPermissionByEid(Long id);
}
