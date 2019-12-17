package henu.dao;


import java.util.List;

import henu.bean.Shop;

public interface ShopDao {

    /**
     * 创建商店
     *
     * @param shop
     * @return
     */
    public int addShop(Shop shop);

    /**
     * 删除商店
     *
     * @param shopID
     * @return
     */
    public int delete(int shopID);

    /**
     * 修改商店
     *
     * @param shop
     * @return
     */
    public int update(Shop shop);

    /**
     * 商店id查找店铺商品信息
     *
     * @param shopID
     * @return List<Object [ ]>
     */
    public List<Object[]> queryByShopID(int shopID);

    /**
     * 店主id查找店铺商品信息
     *
     * @param userID
     * @return List<Object [ ]>
     */
    public List<Object[]> queryByShopManager(String userID);

    /**
     * 查询所有商店信息
     *
     * @return
     */
    public List<Shop> findAll();

    /**
     * 查询目前最大id
     *
     * @return
     */
    public int queryMaxID();

    /**
     * 商店id查找店铺信息
     *
     * @param shopID
     * @return Shop
     */
    public Shop queryByShopId(int shopID);

    /**
     * 店主id查找店铺信息
     *
     * @param userID
     * @return Shop
     */
    public Shop queryByShopmanager(String userID);
}
