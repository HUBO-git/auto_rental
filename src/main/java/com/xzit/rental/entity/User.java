package com.xzit.rental.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author coder_hu
 * @since 2025-03-02
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("sys_user")
@ApiModel(value = "User对象", description = "")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("用户id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("用户账户")
    private String username;

    @ApiModelProperty("用户密码")
    private String password;

    @ApiModelProperty("账户是否过期")
    private Boolean isAccountNonExpired;

    @ApiModelProperty("账户是否被锁定")
    private Boolean isAccountNonLocked;

    @ApiModelProperty("密码是否过期")
    private Boolean isCredentialsNonExpired;

    @ApiModelProperty("账户是否可用")
    private Boolean isEnabled;

    @ApiModelProperty("用户真实姓名")
    private String realname;

    @ApiModelProperty("用户昵称")
    private String nickname;

    @ApiModelProperty("所属部门id")
    private Integer deptId;

    @ApiModelProperty("所属部门名称")
    private String deptName;

    @ApiModelProperty("性别")
    private Boolean gender;

    @ApiModelProperty("联系电话")
    private String phone;

    @ApiModelProperty("邮箱")
    private String email;

    @ApiModelProperty("头像")
    private String avatar;

    @ApiModelProperty("是否管理员")
    private Boolean isAdmin;

    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty("修改时间")
    private LocalDateTime updateTime;

    @ApiModelProperty("是否删除")
    private Boolean deleted;
}
