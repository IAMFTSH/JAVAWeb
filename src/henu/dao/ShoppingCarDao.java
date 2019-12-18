package henu.dao;

import java.util.List;

public interface ShoppingCarDao {

    /**
     * ��ѯ�ҵĹ��ﳵ
     * ����shopping_car��shops��commodity��
     *
     * @return List<Object [ ]>
     */
    List<Object[]> queryMyShoppingCar(String userID);

    /**
     * ��ѯ�ҵĹ��ﳵ
     * ����shopping_car��shops��commodity��
     *
     * @return List<Object [ ]>
     */
    List<Object[]> queryMyShoppingCar(String userID, int commodityID);

    /**
     * ��Ʒ���빺�ﳵ
     *
     * @param commodity
     */
    int addCommodity(String userID, int commodityID, int num);

    /**
     * ���ﳵ����Ʒ�Ӽ�
     *
     * @param user
     * @param commodity
     * @param add
     */
    int add(String userID, int commodityID, int add);

    int reduce(String userID, int commodityID, int reduce);

    /**
     * ɾ��ĳ����Ʒ
     *
     * @param commodityID
     * @return Ӱ��������ɾ���ɹ�����1��ʧ�ܷ���0
     */
    int deleteByUserIDCommodityID(String userID, int commodityID);

    /**
     * ��չ��ﳵ
     *
     * @param user
     * @return Ӱ��������ɾ���ɹ�����1��ʧ�ܷ���0
     */
    int deleteByUserID(String userID);

    /**
     * �޸�����
     *
     * @param userID
     * @param commodityID
     * @param num
     * @return
     */
    int updateNum(String userID, int commodityID, int num);
}
