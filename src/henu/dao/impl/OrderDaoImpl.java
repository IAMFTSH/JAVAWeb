package henu.dao.impl;

import java.util.List;

import henu.DbUtil.ApacheDbutil;
import henu.bean.Order;
import henu.bean.OrderCommodity;
import henu.dao.OrderDao;

public class OrderDaoImpl implements OrderDao {
    //findAll
    public List<Object[]> findAll() {
        String sql = "select * from orders,order_commodity,commodity where orders.orderID=order_commodity.orderID and order_commodity.commodityID=commodity.commodityID;";
        Object[] p = {""};
        List<Object[]> list = ApacheDbutil.Query(sql, p);
        return list;
    }

    // �����û�id��ѯ����
    public List<Object[]> queryByUser(String userID) {
        String sql = "select * from orders,order_commodity,commodity where orders.orderID=order_commodity.orderID and order_commodity.commodityID=commodity.commodityID and orders.userID=? ;";
        Object[] p = {userID};
        List<Object[]> list = ApacheDbutil.Query(sql, p);
        return list;
    }

    // ���ݶ����Ų�ѯ����
    public List<Object[]> queryByOrderId(int orderID) {
        String sql = "select * from orders,order_commodity,commodity where orders.orderID=order_commodity.orderID and order_commodity.commodityID=commodity.commodityID and orders.orderID=? ;";
        Object[] p = {orderID};
        List<Object[]> list = ApacheDbutil.Query(sql, p);
        return list;
    }

    public Order queryByOrderID(int orderID) {
        String[] keys1 = {"orderID"};// ��ѯ����
        Object[] params1 = {orderID};// ������ֵ
        Order order = (Order) ApacheDbutil.querySingle("orders", keys1, params1);
        return order;
    }

    // ������ƷID��ѯ����
    public List<Object[]> queryByCommodityID(int commodityID) {
        String sql = "select * from orders,order_commodity,commodity where orders.orderID=order_commodity.orderID and order_commodity.commodityID=commodity.commodityID and commodity.commodityID=? ;";
        Object[] p = {commodityID};
        List<Object[]> list = ApacheDbutil.Query(sql, p);
        return list;
    }

    // ��Ӷ���
    public int addOrder(Order order) {
        int result = ApacheDbutil.insert("orders", order);
        return result;
    }

    // �����������Ʒ
    public int addOrderCommodity(OrderCommodity orderCommodity) {
        int result = ApacheDbutil.insert("order_commodity", orderCommodity);
        return result;
    }

    // �޸Ķ���״̬
    public int update(int orderID, int orderState) {
        Order order = queryByOrderID(orderID);
        order.setOrderState(orderState);
        int result = ApacheDbutil.update("orders", order);
        return result;
    }

    //��ѯĿǰ���id
    public int queryMaxID() {
        String sql = "select MAX(orderID) from orders;";
        Object[] p = {};
        List<Object[]> list = ApacheDbutil.Query(sql, p);
        int result = 0;
        for (Object[] ob : list) {
            result = Integer.parseInt(ob[0].toString());
        }
        return result;

    }

    public int delete(int orderID) {
        Object[] params = {orderID};
        int result = ApacheDbutil.delete("orders", "orderID", params);
        return result;
    }
}
