package henu.dao;

import henu.bean.Order;
import henu.bean.OrderCommodity;

import java.util.List;

public interface OrderDao {

    /**
     * findAll
     *
     * @return
     */
    List<Object[]> findAll();

    /**
     * �����û�id��ѯ����
     * ����orders��order_commoidty���commodity��
     *
     * @return List<Object [ ]>
     */
    List<Object[]> queryByUser(String userID);

    /**
     * ���ݶ����Ų�ѯ����
     * ����orders��order_commodity��commodity��
     *
     * @param orderID
     * @return List<Object [ ]>
     */
    List<Object[]> queryByOrderId(int orderID);

    Order queryByOrderID(int orderID);

    /**
     * ������Ʒid��ѯ����
     *
     * @param commodity
     * @return
     */
    List<Object[]> queryByCommodityID(int commodityID);

    /**
     * ��Ӷ���
     *
     * @param order
     * @return int Ӱ���������ɹ�����1��ʧ�ܷ���0
     */
    int addOrder(Order order);

    /**
     * �����������Ʒ
     *
     * @param orderCommodity
     * @return int Ӱ���������ɹ�����1��ʧ�ܷ���0
     */
    int addOrderCommodity(OrderCommodity orderCommodity);

    /**
     * �޸Ķ���״̬
     *
     * @param order
     * @return int  Ӱ���������ɹ�����1��ʧ�ܷ���0
     */
    int update(int orderID, int orderState);

    /**
     * ��ѯĿǰ���id
     *
     * @return
     */
    int queryMaxID();

    /**
     * ɾ������
     *
     * @return
     */
    int delete(int orderID);
}
