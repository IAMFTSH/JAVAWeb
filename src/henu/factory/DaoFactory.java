package henu.factory;

import henu.dao.*;
import henu.dao.impl.*;

public class DaoFactory {
    /**
     * UserDao
     *
     * @return
     */
    public static UserDao getUserDaoImpl() {
        return new UserDaoImpl();
    }

    /**
     * CommodityDao
     *
     * @return
     */
    public static CommodityDao getCommodityDaoImpl() {
        return new CommodityDaoImpl();
    }

    /**
     * UserInformationDao
     *
     * @return
     */
    public static UserInformationDao getUserInformationDaoImpl() {
        return new UserInformationDaoImpl();
    }

    /**
     * ShoppingCarDao
     *
     * @return
     */
    public static ShoppingCarDao getShoppingCarDaoImpl() {
        return new ShoppingCarDaoImpl();
    }

    /**
     * OrderDao
     *
     * @return
     */
    public static OrderDao getOrderDaoImpl() {
        return new OrderDaoImpl();
    }

    public static ShopDao getShopDaoImpl() {
        return new ShopDaoImpl();
    }
}
