package com.xzit.rental.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;
@Data
public class RouteVO {
    private String path;
    private String component;
    private String name;
    private Boolean alwaysShow;
    private Meta meta;
    private List<RouteVO> children;//子菜单
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public class Meta{
        private String title;
        private String icon;
        private Object[] roles;
    }
}

