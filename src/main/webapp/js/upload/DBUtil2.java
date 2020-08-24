package com.util;

import com.alibaba.druid.pool.DruidDataSourceDruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * 工具类
 */
public class DBUtil2 {


    static DataSource dataSource;

    static {

        try {
            InputStream inputStream = DBUtil2.class.getClassLoader().getResourceAsStream("db2.properties");
            Properties properties = new Properties();
            properties.load(inputStream);
            dataSource = DruidDataSourceFactory.createDataSource(properties);

            //Class.forName(driver);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    /**
     * 获取连接对像
     *
     * @return
     */
    public static Connection getConnection() {
        try {
            Connection connection = dataSource.getConnection();
            return connection;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 关闭资源
     *
     * @param set
     * @param statement
     * @param connection
     */
    public static void close(ResultSet set, Statement statement, Connection connection) {
        try {
            if (set != null) {
                set.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 增删改操作
     *
     * @param sql
     * @param params
     * @return
     */
    public static int executeUpdate(String sql, Object... params) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = getConnection();
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(sql);
            for (int i = 0; i < params.length; i++) {
                statement.setObject(i + 1, params[i]);
            }
            int i = statement.executeUpdate();
            connection.commit();
            connection.setAutoCommit(true);
            return i;
        } catch (Exception e) {
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        } finally {
            close(null, statement, connection);
        }
        return 0;
    }

    /**
     * @param sql    只支持单表查询，列名和对像的属性名一致
     * @param tClass
     * @param params
     * @param <T>
     * @return
     */
    public static <T> List<T> executeQuery(String sql, Class<T> tClass, Object... params) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<T> list = new ArrayList<>();
        try {
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            if(params!=null){
                for (int i = 0; i < params.length; i++) {
                    statement.setObject(i + 1, params[i]);
                }
            }
            resultSet = statement.executeQuery();
            //反射封装
            Field[] fileds = tClass.getDeclaredFields();
            while (resultSet.next()) {
                T t = tClass.newInstance();
                //单个对像封装
                for (Field field : fileds) {
                    field.setAccessible(true);
                    String name = field.getName();
                    field.set(t,resultSet.getObject(name));
                }
                System.out.println(t);
                list.add(t);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(resultSet, statement, connection);
        }
        return null;
    }
}
