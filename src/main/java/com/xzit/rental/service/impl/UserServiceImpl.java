package com.xzit.rental.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xzit.rental.entity.User;
import com.xzit.rental.mapper.UserMapper;
import com.xzit.rental.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author coder_hu
 * @since 2025-03-02
 */
@Service
@Transactional
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Override
    public User selectByUserName(String name) {
        QueryWrapper<User> wrapper=new QueryWrapper<>();
        wrapper.eq("username",name);
        return baseMapper.selectOne(wrapper);
    }

    @Override
    public List<String> selectRoleName(Integer id) {
        return baseMapper.selectRoleName(id);
    }
}
