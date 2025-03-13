package com.xzit.rental.service;

import com.xzit.rental.entity.Permission;
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
public interface IPermissionService extends IService<Permission> {
    List<Permission> selectPermissionListByUserId(Integer userId);
}
