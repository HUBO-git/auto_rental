package com.xzit.rental.mapper;

import com.xzit.rental.entity.Permission;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author coder_hu
 * @since 2025-03-02
 */
public interface PermissionMapper extends BaseMapper<Permission> {
    List<Permission> selectPermissionListByUserId(Integer userId);
}
