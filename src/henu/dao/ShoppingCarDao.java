package henu.dao;

import java.util.List;

public interface ShoppingCarDao {

	/**
	 * ��ѯ�ҵĹ��ﳵ
	 * ����shopping_car��shops��commodity��
	 *
	 * @return List<Object [ ]>
	 */
	public List<Object[]> queryMyShoppingCar(String userID);
	
	/**
	 * ��Ʒ���빺�ﳵ
	 * @param commodity
	 */
	public void addCommodity(String userID, int commodityID, int num);
	
	/**
	 * ���ﳵ����Ʒ�Ӽ�
	 * @param user
	 * @param commodity
	 * @param add
	 */
	public void add(String userID, int commodityID, int add);
	public void reduce(String userID, int commodityID, int reduce);
	
	/**
	 * ɾ��ĳ����Ʒ
	 * @param commodityID
	 * @return Ӱ��������ɾ���ɹ�����1��ʧ�ܷ���0
	 */
	public int deleteByCommodityID(int commodityID);
	
	/**
	 * ��չ��ﳵ
	 * @param user
	 * @return Ӱ��������ɾ���ɹ�����1��ʧ�ܷ���0
	 */
	public int deleteByUserID(String userID);
}
