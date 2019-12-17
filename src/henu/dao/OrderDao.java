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
	 * �����û�id��ѯ����
	 * ����orders��order_commoidty���commodity��
	 *
	 * @return List<Object [ ]>
	 */
	public List<Object[]> queryByUser(String userID);

	/**
	 * ���ݶ����Ų�ѯ����
	 * ����orders��order_commodity��commodity��
	 *
	 * @param orderID
	 * @return List<Object [ ]>
	 */
	public List<Object[]> queryByOrderId(int orderID);

	public Order queryByOrderID(int orderID);

	/**
	 * ������Ʒid��ѯ����
	 *
	 * @param commodity
	 * @return
	 */
	public List<Object[]> queryByCommodityID(int commodityID);

	/**
	 * ��Ӷ���
	 *
	 * @param order
	 * @return int Ӱ���������ɹ�����1��ʧ�ܷ���0
	 */
	public int addOrder(Order order);

	/**
	 * �����������Ʒ
	 *
	 * @param orderCommodity
	 * @return int Ӱ���������ɹ�����1��ʧ�ܷ���0
	 */
	public int addOrderCommodity(OrderCommodity orderCommodity);

	/**
	 * �޸Ķ���״̬
	 *
	 * @param order
	 * @return int  Ӱ���������ɹ�����1��ʧ�ܷ���0
	 */
	public int update(int orderID, int orderState);

	/**
	 * ��ѯĿǰ���id
	 *
	 * @return
	 */
	public int queryMaxID();

	/**
	 * ɾ������
	 *
	 * @return
	 */
	public int delete(int orderID);
}
