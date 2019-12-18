package henu.dao;

import henu.bean.Commodity;

import java.util.List;

public interface CommodityDao {

    /**
     * ��ѯ������Ʒ
     *
     * @return List<Commodity>
     */
    List<Commodity> findAll();

    /**
     * �����Ʒ
     *
     * @param commodity
     * @return ��ӳɹ�����Ӱ�����������ʧ�ܷ���0
     */
    int addCommodity(Commodity commodity);

    /**
     * ������Ʒidɾ����Ʒ
     *
     * @param commodity
     * @return ɾ���ɹ�����Ӱ�����������ʧ�ܷ���0
     */
    int deleteByID(int commodityID);

    /**
     * �޸���Ʒ
     *
     * @param commodity
     * @return �޸ĳɹ�����Ӱ���������޸�ʧ�ܷ���0
     */
    int update(Commodity commodity);

    /**
     * ��ƷidѰ����Ʒ
     *
     * @param commodityID
     * @return Commodity һ����Ʒʵ��
     */
    Commodity queryByCommodityID(int commodityID);

    /**
     * �̵�id��ѯ��Ʒ
     *
     * @param shopID
     * @return List<Commodity>
     */
    List<Commodity> queryByShopID(int shopID);

    /**
     * �̵����Ʋ�ѯ��Ʒ��ģ�����ң�
     * ����shops ���commodity��
     *
     * @param shopName
     * @return List<Object [ ]>
     */
    List<Object[]> queryByShopName(String shopName);

    /**
     * ��Ʒ���Ʋ�ѯ��Ʒ��ģ����ѯ��
     *
     * @param name
     * @return List<Commodity>
     */
    List<Commodity> queryByCommodityName(String name);

    /**
     * ��Ʒ�۸��ѯ��Ʒ
     *
     * @return List<Commodity>
     */
    List<Object[]> queryByPriceMin(int minPrice);

    List<Object[]> queryByPriceMax(int maxPrice);

    List<Object[]> queryByPriceMinToMax(int minPrice, int maxPrice);

    /**
     * ��Ʒ��������
     *
     * @param commodity
     * @param reduce
     * @return int Ӱ���������ɹ�����1��ʧ�ܷ���0
     */
    int reduce(Commodity commodity, int reduce);

    /**
     * ��Ʒ��������
     *
     * @param commodity
     * @param add
     * @return int Ӱ���������ɹ�����1��ʧ�ܷ���0
     */
    int add(Commodity commodity, int add);

    /**
     * �޸���ƷͼƬ·��
     *
     * @param commodity
     * @return
     */
    int updateImage(Commodity commodity);

    /**
     * ��ѯĿǰ���id
     *
     * @return
     */
    int queryMaxID();
}
