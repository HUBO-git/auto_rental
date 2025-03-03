package com.xzit.rental.service.impl;

import com.xzit.rental.entity.Order;
import com.xzit.rental.mapper.OrderMapper;
import com.xzit.rental.service.IOrderService;
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
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements IOrderService {

}
