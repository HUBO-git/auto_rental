package com.xzit.rental.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
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
@TableName("auto_brand")
@ApiModel(value = "AutoBrand对象", description = "")
public class AutoBrand implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("品牌id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("厂商id")
    private Integer mid;

    @ApiModelProperty("品牌名称")
    private String brandName;

    @ApiModelProperty("是否删除")
    private Boolean deleted;
}
