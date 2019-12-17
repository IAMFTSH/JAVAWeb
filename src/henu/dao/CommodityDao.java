package henu.dao;

import java.util.List;

import henu.bean.Commodity;

public interface CommodityDao {

	/**
	 * ��ѯ������Ʒ
	 *
	 * @return List<Commodity>
	 */
	public List<Commodity> findAll();

	/**
	 * �����Ʒ
	 *
	 * @param commodity
	 * @return ��ӳɹ�����Ӱ�����������ʧ�ܷ���0
	 */
	public int addCommodity(Commodity commodity);

	/**
	 * ������Ʒidɾ����Ʒ
	 *
	 * @param commodity
	 * @return ɾ���ɹ�����Ӱ�����������ʧ�ܷ���0
	 */
	public int deleteByID(int commodityID);

	/**
	 * �޸���Ʒ
	 *
	 * @param commodity
	 * @return �޸ĳɹ�����Ӱ���������޸�ʧ�ܷ���0
	 */
	public int update(Commodity commodity);

	/**
	 * ��ƷidѰ����Ʒ
	 *
	 * @param commodityID
	 * @return Commodity һ����Ʒʵ��
	 */
	public Commodity queryByCommodityID(int commodityID);

	/**
	 * �̵�id��ѯ��Ʒ
	 *
	 * @param shopID
	 * @return List<Commodity>
	 */
	public List<Commodity> queryByShopID(int shopID);

	/**
	 * �̵����Ʋ�ѯ��Ʒ��ģ�����ң�
	 * ����shops ���commodity��
	 *
	 * @param shopName
	 * @return List<Object [ ]>
	 */
	public List<Object[]> queryByShopName(String shopName);

	/**
	 * ��Ʒ���Ʋ�ѯ��Ʒ��ģ����ѯ��
	 *
	 * @param name
	 * @return List<Commodity>
	 */
	public List<Commodity> queryByCommodityName(String name);

	/**
	 * ��Ʒ�۸��ѯ��Ʒ
	 *
	 * @return List<Commodity>
	 */
	public List<Object[]> queryByPriceMin(int minPrice);

	public List<Object[]> queryByPriceMax(int maxPrice);

	public List<Object[]> queryByPriceMinToMax(int minPrice, int maxPrice);

	/**
	 * ��Ʒ��������
	 *
	 * @param commodity
	 * @param reduce
	 * @return int Ӱ���������ɹ�����1��ʧ�ܷ���0
	 */
	public int reduce(Commodity commodity, int reduce);

	/**
	 * ��Ʒ��������
	 *
	 * @param commodity
	 * @param add
	 * @return int Ӱ���������ɹ�����1��ʧ�ܷ���0
	 */
	public int add(Commodity commodity, int add);

	/**
	 * �޸���ƷͼƬ·��
	 *
	 * @param commodity
	 * @return
	 */
	public int updateImage(Commodity commodity);

	/**
	 * ��ѯĿǰ���id
	 *
	 * @return
	 */
	public int queryMaxID();
}
