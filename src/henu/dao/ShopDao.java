package henu.dao;


import henu.bean.Shop;

import java.util.List;

public interface ShopDao {

    /**
     * �����̵�
     *
     * @param shop
     * @return
     */
    int addShop(Shop shop);

    /**
     * ɾ���̵�
     *
     * @param shopID
     * @return
     */
    int delete(int shopID);

    /**
     * �޸��̵�
     *
     * @param shop
     * @return
     */
    int update(Shop shop);

    /**
     * �̵�id���ҵ�����Ʒ��Ϣ
     *
     * @param shopID
     * @return List<Object [ ]>
     */
    List<Object[]> queryByShopID(int shopID);

    /**
     * ����id���ҵ�����Ʒ��Ϣ
     *
     * @param userID
     * @return List<Object [ ]>
     */
    List<Object[]> queryByShopManager(String userID);

    /**
     * ��ѯ�����̵���Ϣ
     *
     * @return
     */
    List<Shop> findAll();

    /**
     * ��ѯĿǰ���id
     *
     * @return
     */
    int queryMaxID();

    /**
     * �̵�id���ҵ�����Ϣ
     *
     * @param shopID
     * @return Shop
     */
    Shop queryByShopId(int shopID);

    /**
     * ����id���ҵ�����Ϣ
     *
     * @param userID
     * @return Shop
     */
    Shop queryByShopmanager(String userID);
}
