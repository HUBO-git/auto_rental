package com.xzit.rental.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;

/**
 * MyMetaObjectHandler 类用于实现 MyBatis-Plus 的自动填充功能。
 * 当执行数据库的插入和更新操作时，会自动为实体类的特定字段填充相应的值。
 * 此类实现了 MetaObjectHandler 接口，并重写了插入和更新时的填充方法。
 * 通过 @Component 注解将该类纳入 Spring 容器管理，以便 MyBatis-Plus 能够自动发现并使用它。
 */
@Component
class MyMetaObjectHandler implements MetaObjectHandler {

    /**
     * 该方法在执行插入操作时被调用，用于自动填充插入时需要的字段。
     * 在插入数据时，会自动为实体类中的 createTime 和 updateTime 字段赋予当前时间。
     *
     * @param metaObject 包含了当前操作的实体对象的元数据信息，通过它可以对实体对象的属性进行操作。
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        // 严格模式下，为实体类的 createTime 字段填充当前时间
        // 起始版本 3.3.3 开始推荐使用此方式，确保只有当 createTime 字段为空时才进行填充
        // LocalDateTime::now 是 Java 8 引入的方法引用，用于获取当前的日期和时间
        this.strictInsertFill(metaObject, "createTime", LocalDateTime::now, LocalDateTime.class);

        // 严格模式下，为实体类的 updateTime 字段填充当前时间
        // 插入数据时，更新时间也初始化为当前时间
        this.strictInsertFill(metaObject, "updateTime", LocalDateTime::now, LocalDateTime.class);
    }

    /**
     * 该方法在执行更新操作时被调用，用于自动填充更新时需要的字段。
     * 在更新数据时，会自动为实体类中的 updateTime 字段更新为当前时间。
     *
     * @param metaObject 包含了当前操作的实体对象的元数据信息，通过它可以对实体对象的属性进行操作。
     */
    @Override
    public void updateFill(MetaObject metaObject) {
        // 严格模式下，为实体类的 updateTime 字段更新为当前时间
        // 确保只有当 updateTime 字段参与更新且为空时才进行填充
        this.strictUpdateFill(metaObject, "updateTime", LocalDateTime::now, LocalDateTime.class);
    }
}
