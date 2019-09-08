package com.itlike.service;

import com.itlike.domain.Employee;
import com.itlike.domain.PageListRes;
import com.itlike.domain.QueryVo;

public interface EmployeeService {
    //查询所有员工
    public PageListRes getAllEmployee(QueryVo vo);
    //保存员工
    public void  savaEmployee(Employee employee);
     //更新员工
    public void updateEmployee(Employee employee);
       //更新员工离职状态
    void updateState(Long id);
}
