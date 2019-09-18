package com.itlike.mapper;

import com.itlike.domain.Employee;
import com.itlike.domain.QueryVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EmployeeMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Employee record);

    Employee selectByPrimaryKey(Long id);

    List<Employee> selectAll(@Param(value="keyword") String keyword);

    int updateByPrimaryKey(Employee record);
     //更新员工状态
    void updateState(Long id);

    //保存员工的角色信息
    void insertEmployeeAndRoleRel(@Param("id") Long id, @Param("rid") Long rid);

    void deleteEmployeeAndRoleRel(Long id);
    //根据员工用户名查询员工
    Employee selectByUsername(String username);

    //根据员工id查询对应的角色编号
    List<String> getRolesByEid(Long id);

    //根据员工id查询对应的权限
    List<String> getPermissions(Long id);
}