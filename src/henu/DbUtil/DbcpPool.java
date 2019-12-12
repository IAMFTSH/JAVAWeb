package henu.DbUtil;


import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.commons.dbcp2.BasicDataSourceFactory;

import java.sql.*;
import java.util.Properties;

public class DbcpPool {
    protected static Statement statement = null;
    protected static ResultSet rs = null;
    protected static Connection conn = null;
    private static BasicDataSource dataSource = null;
    private static PreparedStatement ps = null;

    public static void init() {
        if (dataSource != null) {
            try {
                dataSource.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            dataSource = null;
        }
        try {
            Properties p = new Properties();
            p.setProperty("driverClassName", "com.mysql.cj.jdbc.Driver");
            p.setProperty("url", "jdbc:mysql://localhost:3306/shopping mall?useUnicode=true&characterEncoding=utf-8&useSSL=false&serverTimezone = GMT");//这里写数据库名字
            p.setProperty("username", "root");//数据库账号
            p.setProperty("password", "123");
            p.setProperty("maxActive", "30");   //活动状态最大连接数
            p.setProperty("maxIdle", "10");      //休闲最大连接数
            p.setProperty("maxWait", "1000");    //没有空闲的连接，请求连接的请求最长连接时间（ms）  -1无限
            p.setProperty("removeAbandoned", "false");   //是否自动回收超时连接
            p.setProperty("removeAbandonedTimeout", "120"); //#超时时间(以秒数为单位)
            p.setProperty("testOnBorrow", "true");
            p.setProperty("logAbandoned", "true");   //是否在自动回收超时连接的时候打印连接的超时错误
            dataSource = (BasicDataSource) BasicDataSourceFactory.createDataSource(p);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static BasicDataSource getDataSource() {
        if (dataSource == null)
            init();
        return dataSource;
    }
}
