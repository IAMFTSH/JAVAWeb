package henu.dao;

import java.util.List;

import henu.bean.Order;
import henu.bean.OrderCommodity;

public interface OrderDao {

    /**
     * findAll
     *
     * @return
     */
    public List<Object[]> findAll();

    /**
     * 根据用户id查询订单
     * 连接orders表、order_commoidty表和commodity表
     *
     * @return List<Object [ ]>
     */
    public List<Object[]> queryByUser(String userID);

    /**
     * 根据订单号查询订单
     * 连接orders表、order_commodity表、commodity表
     *
     * @param orderID
     * @return List<Object [ ]>
     */
    public List<Object[]> queryByOrderId(int orderID);

    public Order queryByOrderID(int orderID);

    /**
     * 根据商品id查询订单
     *
     * @param commodity
     * @return
     */
    public List<Object[]> queryByCommodityID(int commodityID);

    /**
     * 添加订单
     *
     * @param order
     * @return int 影响行数，成功返回1，失败返回0
     */
    public int addOrder(Order order);

    /**
     * 订单内添加商品
     *
     * @param orderCommodity
     * @return int 影响行数，成功返回1，失败返回0
     */
    public int addOrderCommodity(OrderCommodity orderCommodity);

    /**
     * 修改订单状态
     *
     * @param order
     * @return int  影响行数，成功返回1，失败返回0
     */
    public int update(int orderID, int orderState);

    /**
     * 查询目前最大id
     *
     * @return
     */
    public int queryMaxID();

    /**
     * 删除订单
     *
     * @return
     */
    public int delete(int orderID);
}
