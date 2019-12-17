package henu.dao;


import java.util.List;

import henu.bean.Shop;

public interface ShopDao {

	/**
	 * �����̵�
	 *
	 * @param shop
	 * @return
	 */
	public int addShop(Shop shop);

	/**
	 * ɾ���̵�
	 *
	 * @param shopID
	 * @return
	 */
	public int delete(int shopID);

	/**
	 * �޸��̵�
	 *
	 * @param shop
	 * @return
	 */
	public int update(Shop shop);

	/**
	 * �̵�id���ҵ�����Ʒ��Ϣ
	 *
	 * @param shopID
	 * @return List<Object [ ]>
	 */
	public List<Object[]> queryByShopID(int shopID);

	/**
	 * ����id���ҵ�����Ʒ��Ϣ
	 *
	 * @param userID
	 * @return List<Object [ ]>
	 */
	public List<Object[]> queryByShopManager(String userID);

	/**
	 * ��ѯ�����̵���Ϣ
	 *
	 * @return
	 */
	public List<Shop> findAll();

	/**
	 * ��ѯĿǰ���id
	 *
	 * @return
	 */
	public int queryMaxID();

	/**
	 * �̵�id���ҵ�����Ϣ
	 *
	 * @param shopID
	 * @return Shop
	 */
	public Shop queryByShopId(int shopID);

	/**
	 * ����id���ҵ�����Ϣ
	 *
	 * @param userID
	 * @return Shop
	 */
	public Shop queryByShopmanager(String userID);
}
