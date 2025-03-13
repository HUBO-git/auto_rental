package com.xzit.rental.vo;

import com.aliyun.oss.model.CreateBucketCnameTokenResult;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class TokenVo {
    private String token;
    private Long expireTime;

}
