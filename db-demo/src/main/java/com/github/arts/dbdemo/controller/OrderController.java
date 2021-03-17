package com.github.arts.dbdemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@RestController
@RequestMapping("/")
public class OrderController {

    @Autowired
    DataSource shardingDataSource;

    @RequestMapping(value = "order", method = RequestMethod.GET)
    public Object getOrder(@RequestParam Integer userId, @RequestParam Integer orderId) {
        try {
            Connection connection = shardingDataSource.getConnection();
            String sql = "select * from t_order where user_id = ? and order_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, userId);
            preparedStatement.setInt(2, orderId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                //
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;

    }
}
