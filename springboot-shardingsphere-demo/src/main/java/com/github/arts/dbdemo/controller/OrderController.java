package com.github.arts.dbdemo.controller;

import org.apache.shardingsphere.transaction.core.TransactionType;
import org.apache.shardingsphere.transaction.core.TransactionTypeHolder;
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
        TransactionTypeHolder.set(TransactionType.XA);
        try (Connection connection = shardingDataSource.getConnection()) {
            connection.setAutoCommit(false);
            String sql = "select * from t_order where user_id = ? and order_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, userId);
            preparedStatement.setInt(2, orderId);
            ResultSet resultSet = preparedStatement.executeQuery();
            connection.commit();
            while (resultSet.next()) {
                System.out.println(resultSet.getString(1));
                System.out.println(resultSet.getString(2));
                System.out.println(resultSet.getString(3));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;

    }
}
