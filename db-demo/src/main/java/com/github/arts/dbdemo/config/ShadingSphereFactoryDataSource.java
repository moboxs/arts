package com.github.arts.dbdemo.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.shardingsphere.driver.api.ShardingSphereDataSourceFactory;
import org.apache.shardingsphere.driver.jdbc.core.datasource.ShardingSphereDataSource;
import org.apache.shardingsphere.infra.config.algorithm.ShardingSphereAlgorithmConfiguration;
import org.apache.shardingsphere.sharding.api.config.ShardingRuleConfiguration;
import org.apache.shardingsphere.sharding.api.config.rule.ShardingTableRuleConfiguration;
import org.apache.shardingsphere.sharding.api.config.strategy.sharding.StandardShardingStrategyConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@Configuration
public class ShadingSphereFactoryDataSource {

    /**
     * 基于用户 user_id 分库、订单 order_id 分表的场景
     * @return
     */

    @Bean
    public DataSource buildShadingSphereFactoryDataSource() throws SQLException {

        Map<String, DataSource> dataSourceMap = new HashMap<>();

        DruidDataSource dataSource1 = new DruidDataSource();
        dataSource1.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource1.setUrl("jdbc:mysql://localhost:3306/ds0?serverTimezone=UTC");
        dataSource1.setUsername("root");
        dataSource1.setPassword("123456");
        dataSourceMap.put("ds0", dataSource1);

        DruidDataSource dataSource2 = new DruidDataSource();
        dataSource2.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource2.setUrl("jdbc:mysql://localhost:3306/ds1?serverTimezone=UTC");
        dataSource2.setUsername("root");
        dataSource2.setPassword("123456");

        dataSourceMap.put("ds1", dataSource2);
        //配置表规则
        ShardingTableRuleConfiguration tableRuleConfiguration =
                new ShardingTableRuleConfiguration("t_order", "ds${0..1}.t_order${0..1}");

        //配置分库策略
        tableRuleConfiguration.setDatabaseShardingStrategy(
                new StandardShardingStrategyConfiguration("user_id", "dbShardingAlgorithm"));

        //配置分表策略
        tableRuleConfiguration.setTableShardingStrategy(
                new StandardShardingStrategyConfiguration("order_id", "tableShardingAlgorithm"));

        //配置分片规则
        ShardingRuleConfiguration shardingRuleConfig = new ShardingRuleConfiguration();
        shardingRuleConfig.getTables().add(tableRuleConfiguration);

        // 配置分库算法
        Properties dbShardingAlgorithmProperties = new Properties();
        dbShardingAlgorithmProperties.setProperty("algorithm-expression", "ds${user_id % 2}");
        shardingRuleConfig.getShardingAlgorithms().put("dbShardingAlgorithm", new ShardingSphereAlgorithmConfiguration("INLINE", dbShardingAlgorithmProperties));

        //配置分表算法
        Properties tableShardingAlgorithmProps = new Properties();
        tableShardingAlgorithmProps.setProperty("algorithm-expression", "t_order${order_id % 2}");
        shardingRuleConfig.getShardingAlgorithms().put("tableShardingAlgorithm", new ShardingSphereAlgorithmConfiguration("INLINE", tableShardingAlgorithmProps));

        // 创建 ShardingSphereDatasource

        return ShardingSphereDataSourceFactory.createDataSource(dataSourceMap, Collections.singleton(shardingRuleConfig), new Properties());
    }




}
