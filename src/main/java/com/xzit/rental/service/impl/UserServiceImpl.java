package com.xzit.rental.service.impl;

import com.xzit.rental.entity.User;
import com.xzit.rental.mapper.UserMapper;
import com.xzit.rental.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author coder_hu
 * @since 2025-03-02
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
