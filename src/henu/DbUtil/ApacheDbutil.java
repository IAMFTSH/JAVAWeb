package henu.DbUtil;

import henu.bean.*;
import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayListHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

public class ApacheDbutil {
    static BasicDataSource dataSource = DbcpPool.getDataSource();
    private static QueryRunner queryRunner = new QueryRunner(dataSource);
//单表查询

    /**
     * 单表多行查询之多条件查询
     *
     * @param tableType 查询的表
     * @param keys      查询的关键字
     * @param params    关键字的值
     * @return
     */
    public static List<? extends Object> query(String tableType, String[] keys, Object[] params) {
        List<? extends Object> list = null;
        String sql = "select * from " + tableType + " where ";   //问号中，如果是字符串会加单引号，数字不会
        for (int i = 0; i < keys.length; i++) {
            if (i == keys.length - 1)
                sql += keys[i] + "=?";
            else
                sql += keys[i] + "=? and ";
        }
        if (tableType == "orders") {
            try {
                list = queryRunner.query(sql, new BeanListHandler<>(Order.class), params);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else if (tableType == "users") {
            try {
                list = queryRunner.query(sql, new BeanListHandler<>(User.class), params);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else if (tableType == "shops") {
            try {
                list = queryRunner.query(sql, new BeanListHandler<>(Shop.class), params);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else if (tableType == "commodity") {
            try {
                list = queryRunner.query(sql, new BeanListHandler<>(Commodity.class), params);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    /**
     * 单表多行查询之无条件查找
     *
     * @param tableType 查询的表
     * @return
     */
    public static List<? extends Object> query(String tableType) {
        String sql = "select * from " + tableType;
        List<? extends Object> list = null;
        if (tableType == "orders") {
            try {
                list = queryRunner.query(sql, new BeanListHandler<>(Order.class));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else if (tableType == "users") {
            try {
                list = queryRunner.query(sql, new BeanListHandler<>(User.class));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else if (tableType == "shops") {
            try {
                list = queryRunner.query(sql, new BeanListHandler<>(Shop.class));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else if (tableType == "commodity") {
            try {
                list = queryRunner.query(sql, new BeanListHandler<>(Commodity.class));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    /**
     * 单表单行查询之多条件查询
     *
     * @param tableType 查询的表
     * @param keys      查询的关键字
     * @param params    关键字的值
     * @return
     */
    public static Object querySingle(String tableType, String[] keys, Object[] params) {
        Object result = null;
        String sql = "select * from " + tableType + " where ";   //问号中，如果是字符串会加单引号，数字不会
        for (int i = 0; i < keys.length; i++) {
            if (i == keys.length - 1)
                sql += keys[i] + "=?";
            else
                sql += keys[i] + "=? and";
        }
        if (tableType == "orders") {
            try {
                result = queryRunner.query(sql, new BeanHandler<>(Order.class), params);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else if (tableType == "users") {
            try {
                result = queryRunner.query(sql, new BeanHandler<>(User.class), params);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else if (tableType == "shops") {
            try {
                result = queryRunner.query(sql, new BeanHandler<>(Shop.class), params);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else if (tableType == "commodity") {
            try {
                result = queryRunner.query(sql, new BeanHandler<>(Commodity.class), params);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    //多表查询
    public static List<Object[]> Query(String sql, Object[] params) {
        List<Object[]> list = null;
        try {
            list = queryRunner.query(sql, new ArrayListHandler(), params);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 单表单行行查询之无条件
     *
     * @param tableType 查询的表
     * @return
     */
    public static Object querySingle(String tableType) {
        Object result = null;
        String sql = "select * from " + tableType;   //问号中，如果是字符串会加单引号，数字不会
        if (tableType == "orders") {
            try {
                result = queryRunner.query(sql, new BeanHandler<>(Order.class));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else if (tableType == "users") {
            try {
                result = queryRunner.query(sql, new BeanHandler<>(User.class));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else if (tableType == "shops") {
            try {
                result = queryRunner.query(sql, new BeanHandler<>(Shop.class));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else if (tableType == "commodity") {
            try {
                result = queryRunner.query(sql, new BeanHandler<>(Commodity.class));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }
//插入

    /**
     * 插入
     *
     * @param tableType 插入什么表
     * @param object    插入的对象
     * @return 目前0是失败，1是成功，考虑后续增加其他返回值应对报错。
     */
    public static int insert(String tableType, Object object) {
        Object[] parmas = null;
        String sql = null;
        if (tableType == "users") {
            User user = (User) object;
            sql = "insert into " + tableType + " values(?,?,?)";
            parmas = new Object[]{user.getUserID(), user.getUserPassword(), user.getUserType()};
            try {
                return queryRunner.update(sql, parmas);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else if (tableType == "orders") {
            Order order = (Order) object;
            sql = "insert into " + tableType + " values(?,?,sysdate())";
            parmas = new Object[]{order.getOrderID(), order.getUserID()};
            try {
                return queryRunner.update(sql, parmas);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else if (tableType == "shops") {
            Shop shop = (Shop) object;
            sql = "insert into " + tableType + " values(?,?,?)";
            parmas = new Object[]{shop.getShopID(), shop.getShopName(), shop.getShopManager()};
            try {
                return queryRunner.update(sql, parmas);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else if (tableType == "commodity") {
            Commodity commodity = (Commodity) object;
            sql = "insert into " + tableType + " values(?,?,?,?)";
            parmas = new Object[]{commodity.getCommodityID(), commodity.getShopID(), commodity.getCommodityName(), commodity.getCommodityNumber(), commodity.getCommodityPrice()};
            try {
                return queryRunner.update(sql, parmas);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else if (tableType == "user_information") {
            UserInformation userInformation = (UserInformation) object;
            sql = "insert into " + tableType + " values(?,?,?,?)";
            parmas = new Object[]{userInformation.getUserID(), userInformation.getUserName(), userInformation.getUserSex(), userInformation.getUserAddress()};
            try {
                return queryRunner.update(sql, parmas);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else if (tableType == "shopping_car") {
            ShoppingCar shoppingCar = (ShoppingCar) object;
            sql = "insert into " + tableType + " values(?,?)";
            parmas = new Object[]{shoppingCar.getUserID(), shoppingCar.getCommodityID()};
            try {
                return queryRunner.update(sql, parmas);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else if (tableType == "order_commodity") {
            OrderCommodity orderCommodity = (OrderCommodity) object;
            sql = "insert into " + tableType + " values(?,?,?)";
            parmas = new Object[]{orderCommodity.getOrderID(), orderCommodity.getCommodityID(), orderCommodity.getNumber()};
            try {
                return queryRunner.update(sql, parmas);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return 0;
    }
//修改

    /**
     * 可以自由的修改
     *
     * @param tableType
     * @param updateKeys 需要修改什么列
     * @param keys       where的限制条件
     * @param params     上面两个的值
     * @return
     */
    public static int update(String tableType, Object[] updateKeys, Object[] keys, Object[] params) {
        String sql = "update " + tableType + " set ";
        for (int i = 0; i < updateKeys.length; i++) {
            if (i == updateKeys.length - 1)
                sql += updateKeys[i] + "=? where ";
            else
                sql += updateKeys[i] + "=?,";
        }
        for (int i = 0; i < keys.length; i++) {
            if (i == keys.length - 1)
                sql += keys[i] + "=?";
            else
                sql += keys[i] + "=? and ";
        }
        try {
            return queryRunner.update(sql, params);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 你提供带问号的sql语句的修改
     *
     * @param sql    带问号的
     * @param params 值
     * @return
     */
    public static int update(String sql, Object[] params) {
        try {
            return queryRunner.update(sql, params);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 整行修改（一次只能修改一行）
     *
     * @param tableType 修改什么表（修改只有一个主键的表）
     * @param object    表的对象    对象的主键作为where条件
     * @return
     */
    public static int update(String tableType, Object object) {
        String sql = "update " + tableType + " set ";
        Object[] params = null;
        if (tableType == "users") {
            sql += "userPassword=?,userType=? where userID=?";
            User user = (User) object;
            params = new Object[]{user.getUserPassword(), user.getUserType(), user.getUserID()};
        } else if (tableType == "shops") {
            sql += "shopName=?,shopManager=? where shopID=?";
            Shop shop = (Shop) object;
            params = new Object[]{shop.getShopName(), shop.getShopManager(), shop.getShopID()};
        } else if (tableType == "orders") {
            sql += "userID=? where orderID=?";
            Order order = (Order) object;
            params = new Object[]{order.getUserID()};
        } else if (tableType == "commodity") {
            sql += "shopID=?,commodityName=?,commodityPrice=?,commodityNumber=? where commodityID=?";
            Commodity commodity = (Commodity) object;
            params = new Object[]{commodity.getShopID(), commodity.getCommodityName(), commodity.getCommodityPrice(), commodity.getCommodityNumber(), commodity.getCommodityID()};
        } else if (tableType == "user_information") {
            sql += "userName=?,userSex=?,userAddress=? where userID=?";
            UserInformation userInformation = (UserInformation) object;
            params = new Object[]{userInformation.getUserName(), userInformation.getUserSex(), userInformation.getUserAddress(), userInformation.getUserID()};
        }
        try {
            return queryRunner.update(sql, params);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 修改购物车表和订单商品关系表
     *
     * @param tableType
     * @param params    商品要五个，订单商品要四个
     * @return
     */
    public static int updatePrimaryS(String tableType, Object[] params) {
        String sql = "update " + tableType + " set ";
        if (tableType == "order_commodity") {
            sql += "commodityID=?,orderID=?,number=? where commodityID=? and orderID=? ";
        } else if (tableType == "shopping_car") {
            sql += "userID=?,commodityID=? where commodityID=? and userID=?";
        }
        try {
            return queryRunner.update(sql, params);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 删除
     *
     * @param tableType
     * @param key       传入关键字
     * @param params    传入需要删除的值的数组
     * @return
     */
    public static int delete(String tableType, String key, Object[] params) {
        String sql = "delete from " + tableType + " where " + key + " in(";
        for (int i = 0; i < params.length; i++) {
            if (i == (params.length - 1)) {
                sql += "?)";
            } else
                sql += "?,";
        }
        try {
            return queryRunner.update(sql, params);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
