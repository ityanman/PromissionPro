package com.itlike.web.realm;

import com.itlike.domain.Employee;
import com.itlike.service.EmployeeService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.SimpleAutowireCandidateResolver;

import java.util.ArrayList;
import java.util.List;

public class EmployeeRealm extends AuthorizingRealm {

    @Autowired
    EmployeeService employeeService;

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken Token) throws AuthenticationException {
        System.out.println("来到了认证");
        //获取身份信息
        String username = (String) Token.getPrincipal();
        System.out.println(username);
        //根据用户名到数据库中查询有没有该用户
        Employee employee = employeeService.getEmplByUsername(username);
        System.out.println(employee);
        //判断是否有值
        if (employee==null){
           return null;
        }
        //认证
        /*参数: 主体,正确的密码,盐,当前realm名称*/
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(employee,
                                                                     employee.getPassword(),
                                                                      ByteSource.Util.bytes(employee.getUsername()),
                                                                     this.getName());
        return info;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("来到了授权");
        //获取客户主体
        Employee employee = (Employee)principalCollection.getPrimaryPrincipal();
        //声明角色集合
        List<String> roles = new ArrayList<>();
        //声明权限集合
        List<String> permissions = new ArrayList<>();
        //判断是不是管理员
        if (employee.getAdmin()){
            //如果是，就给所有权限
            permissions.add("*:*");
        }else {
            //根据员工id查询对应的角色编号
            roles = employeeService.getRolesByEid(employee.getId());
            System.out.println("角色有----"+roles);
            //根据员工id查询对应的权限
            permissions = employeeService.getPermissionByEid(employee.getId());
            System.out.println("权限有---"+permissions);
              }

        //给授权信息
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        //添加角色
        info.addRoles(roles);
        //添加权限
        info.addStringPermissions(permissions);
        return info;
    }

}
