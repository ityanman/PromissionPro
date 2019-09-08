package com.itlike.service.impl;

import com.itlike.domain.Department;
import com.itlike.mapper.DepartmentMapper;
import com.itlike.service.DepartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class DepartServiceImpl implements DepartService {
    //注入mapper
    @Autowired
    private DepartmentMapper departmentMapper;

    @Override
    public List<Department> getAllDepartment() {
        //调用mapper并返回
        return departmentMapper.selectAll();
    }
}
