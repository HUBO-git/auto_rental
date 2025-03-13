package com.xzit.rental.service;

import com.xzit.rental.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author coder_hu
 * @since 2025-03-02
 */
public interface IUserService extends IService<User> {
    User selectByUserName(String name);

    List<String> selectRoleName(Integer id);
}
