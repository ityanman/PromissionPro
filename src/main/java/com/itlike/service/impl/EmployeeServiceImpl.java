package com.itlike.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itlike.domain.*;
import com.itlike.mapper.EmployeeMapper;
import com.itlike.service.EmployeeService;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Transactional
@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeMapper employeeMapper;

    @Override
    public PageListRes getAllEmployee(QueryVo vo) {
        //调用mapper查询员工
        Page<Object> page = PageHelper.startPage(vo.getPage(), vo.getRows());
        List<Employee> employees = employeeMapper.selectAll(vo.getKeyword());
        /*封装pageList*/
        PageListRes pageListRes = new PageListRes();
        pageListRes.setTotal(page.getTotal());
        pageListRes.setRows(employees);
        return pageListRes;
    }

    //保存员工
    @Override
    public void savaEmployee(Employee employee) {
        //密码加密操作
        String password = employee.getPassword();
        //把用户名作为盐，加密两次
        employee.setPassword(new Md5Hash(password,employee.getUsername(),2).toString());
        /*保存员工*/
        employeeMapper.insert(employee);


        /*保存员工和 角色 关系*/
        for (Role role : employee.getRoles()) {
            employeeMapper.insertEmployeeAndRoleRel(employee.getId(),role.getRid());
        }
    }
    //更新员工
    @Override
    public void updateEmployee(Employee employee) {
        employeeMapper.updateByPrimaryKey(employee);
        //更新员工对应的关系
        //先删除再插入
        employeeMapper.deleteEmployeeAndRoleRel(employee.getId());
        for (Role role : employee.getRoles()) {
            employeeMapper.insertEmployeeAndRoleRel(employee.getId(),role.getRid());
        }
    }

    //更新员工离职状态
    @Override
    public void updateState(Long id) {
        employeeMapper.updateState(id);
    }

    @Override
    public Employee getEmplByUsername(String username) {
        Employee employee = employeeMapper.selectByUsername(username);
        return employee;
    }

    //根据员工id查询对应的角色编号
    @Override
    public List<String> getRolesByEid(Long id) {
        return employeeMapper.getRolesByEid(id);
    }

    //根据员工id查询对应的权限
    @Override
    public List<String> getPermissionByEid(Long id) {

        return employeeMapper.getPermissions(id);
    }
}
