package henu.dao;

import java.util.List;

import henu.bean.Commodity;

public interface CommodityDao {

    /**
     * 查询所有商品
     *
     * @return List<Commodity>
     */
    public List<Commodity> findAll();

    /**
     * 添加商品
     *
     * @param commodity
     * @return 添加成功返回影响行数，添加失败返回0
     */
    public int addCommodity(Commodity commodity);

    /**
     * 根据商品id删除商品
     *
     * @param commodity
     * @return 删除成功返回影响行数。添加失败返回0
     */
    public int deleteByID(int commodityID);

    /**
     * 修改商品
     *
     * @param commodity
     * @return 修改成功返回影响行数，修改失败返回0
     */
    public int update(Commodity commodity);

    /**
     * 商品id寻找商品
     *
     * @param commodityID
     * @return Commodity 一个商品实例
     */
    public Commodity queryByCommodityID(int commodityID);

    /**
     * 商店id查询商品
     *
     * @param shopID
     * @return List<Commodity>
     */
    public List<Commodity> queryByShopID(int shopID);

    /**
     * 商店名称查询商品（模糊查找）
     * 连接shops 表和commodity表
     *
     * @param shopName
     * @return List<Object [ ]>
     */
    public List<Object[]> queryByShopName(String shopName);

    /**
     * 商品名称查询商品（模糊查询）
     *
     * @param name
     * @return List<Commodity>
     */
    public List<Commodity> queryByCommodityName(String name);

    /**
     * 商品价格查询商品
     *
     * @return List<Commodity>
     */
    public List<Object[]> queryByPriceMin(int minPrice);

    public List<Object[]> queryByPriceMax(int maxPrice);

    public List<Object[]> queryByPriceMinToMax(int minPrice, int maxPrice);

    /**
     * 商品数量减少
     *
     * @param commodity
     * @param reduce
     * @return int 影响行数，成功返回1，失败返回0
     */
    public int reduce(Commodity commodity, int reduce);

    /**
     * 商品数量增加
     *
     * @param commodity
     * @param add
     * @return int 影响行数，成功返回1，失败返回0
     */
    public int add(Commodity commodity, int add);

    /**
     * 修改商品图片路径
     *
     * @param commodity
     * @return
     */
    public int updateImage(Commodity commodity);

    /**
     * 查询目前最大id
     *
     * @return
     */
    public int queryMaxID();
}
