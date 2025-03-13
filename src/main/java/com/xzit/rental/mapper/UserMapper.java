package com.xzit.rental.mapper;

import com.xzit.rental.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author coder_hu
 * @since 2025-03-02
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
    List<String> selectRoleName(int id);
}
