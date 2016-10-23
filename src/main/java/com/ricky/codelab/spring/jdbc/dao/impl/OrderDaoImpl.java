package com.ricky.codelab.spring.jdbc.dao.impl;

import com.ricky.codelab.spring.domain.Order;
import com.ricky.codelab.spring.jdbc.dao.IOrderDao;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import javax.annotation.Resource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * ${DESCRIPTION}
 *
 * @author Ricky Fung
 * @create 2016-10-18 23:06
 */
@Repository("orderDao")
public class OrderDaoImpl implements IOrderDao {

    @Resource(name = "jdbcTemplate")
    private JdbcTemplate jdbcTemplate;

    @Override
    public long insert(Order order) {
        String sql = "INSERT INTO tb_order(customer_name,total_price,amount,address) VALUES (?,?,?,?)";
        return jdbcTemplate.update(sql, order.getCustomerName(),
                order.getTotalPrice(), order.getAmount(), order.getAddress());
    }

    @Override
    public List<Order> queryOrders(){

        return jdbcTemplate.query("SELECT * FROM tb_order", new RowMapper<Order>() {
            @Override
            public Order mapRow(ResultSet rs, int i) throws SQLException {
                Order order = new Order();
                order.setId(rs.getLong("id"));
                order.setCustomerName(rs.getString("customer_name"));
                order.setTotalPrice(rs.getDouble("total_price"));
                order.setAmount(rs.getInt("amount"));
                order.setAddress(rs.getString("address"));
                return order;
            }
        });
    }
}
