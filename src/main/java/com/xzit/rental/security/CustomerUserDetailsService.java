package com.xzit.rental.security;

import com.xzit.rental.entity.Permission;
import com.xzit.rental.entity.User;
import com.xzit.rental.service.IPermissionService;
import com.xzit.rental.service.IUserService;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import
        org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Objects;


import java.util.List;
import java.util.Objects;

@Component
public class CustomerUserDetailsService implements UserDetailsService {
    @Autowired
    private IUserService userService;
    @Resource
    private IPermissionService permissionService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user=userService.selectByUserName(username);
        if(user==null){
            throw new UsernameNotFoundException("用户不存在");
        }
        //查询用户权限列表
        List<Permission> permissions =
                permissionService.selectPermissionListByUserId(user.getId());
        user.setPermissionList(permissions);
//通过stream流处理，将权限对象转换为权限字符串列表
        List<String> list = permissions.stream().filter(Objects::nonNull)
                .map(Permission::getPermissionCode)
                .filter(Objects::nonNull)
                .toList();
        String[] array = list.toArray(new String[list.size()]);
        List<GrantedAuthority> authorityList=
                AuthorityUtils.createAuthorityList(array);
        user.setAuthorities(authorityList);
        System.out.println(user);
        return user;
    }
}
