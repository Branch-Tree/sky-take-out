package com.sky.mapper;

import com.github.pagehelper.Page;
import com.sky.dto.OrdersPageQueryDTO;
import com.sky.entity.Orders;
import com.sky.vo.OrderStatisticsVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface OrderMapper {
    /**
     * 向订单中插入一条数据
     * @param order
     */
    void insert(Orders order);

    /**
     * 分页条件查询并按照下单时间排序
     * @param ordersPageQueryDTO
     * @return
     */
    Page<Orders> pageQuery(OrdersPageQueryDTO ordersPageQueryDTO);

    /**
     * 根据订单号和用户id查询订单
     * @param orderNumber
     * @param userId
     */
    @Select("select * from orders where number = #{orderNumber} and user_id= #{userId}")
    Orders getByNumberAndUserId(String orderNumber, Long userId);

    /**
     * 修改订单信息
     * @param orders
     */
    void update(Orders orders);

    /**
     * 通过订单id获取信息
     * @param id
     * @return
     */
    @Select("select *from orders where id =#{id}")
    Orders getById(Long id);

    /**
     * 各个状态的订单数量统计
     * @return
     */
    @Select("select count(id) from orders where status=#{status}")
    Integer countStatus(Integer status);

    /**
     * 根据状态和下单时间查询订单
     * @param status
     * @param time
     * @return
     */
    @Select("select * from orders where status=#{status} and order_time<#{time}")
    List<Orders> getByStatusAndOrderTimeLT(Integer status, LocalDateTime time);
}
