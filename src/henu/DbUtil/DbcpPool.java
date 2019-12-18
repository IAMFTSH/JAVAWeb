package henu.DbUtil;


import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.commons.dbcp2.BasicDataSourceFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
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
            p.setProperty("url", "jdbc:mysql://localhost:3306/shopping mall?useUnicode=true&characterEncoding=utf-8&useSSL=false&serverTimezone = GMT");//����д���ݿ�����
            p.setProperty("username", "root");//���ݿ��˺�
            p.setProperty("password", "123");
            p.setProperty("maxActive", "30");   //�״̬���������
            p.setProperty("maxIdle", "10");      //�������������
            p.setProperty("maxWait", "1000");    //û�п��е����ӣ��������ӵ����������ʱ�䣨ms��  -1����
            p.setProperty("removeAbandoned", "false");   //�Ƿ��Զ����ճ�ʱ����
            p.setProperty("removeAbandonedTimeout", "120"); //#��ʱʱ��(������Ϊ��λ)
            p.setProperty("testOnBorrow", "true");
            p.setProperty("logAbandoned", "true");   //�Ƿ����Զ����ճ�ʱ���ӵ�ʱ���ӡ���ӵĳ�ʱ����
            dataSource = BasicDataSourceFactory.createDataSource(p);
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
