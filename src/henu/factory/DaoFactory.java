package henu.factory;

import henu.dao.CommodityDao;
import henu.dao.OrderDao;
import henu.dao.ShopDao;
import henu.dao.ShoppingCarDao;
import henu.dao.UserDao;
import henu.dao.UserInformationDao;
import henu.dao.impl.CommodityDaoImpl;
import henu.dao.impl.OrderDaoImpl;
import henu.dao.impl.ShopDaoImpl;
import henu.dao.impl.ShoppingCarDaoImpl;
import henu.dao.impl.UserDaoImpl;
import henu.dao.impl.UserInformationDaoImpl;

public class DaoFactory {
	/**
	 * UserDao
	 * @return
	 */
	public static UserDao getUserDaoImpl() {
		return new UserDaoImpl();
	}

    /**
	 * CommodityDao
	 * @return
	 */
	public static CommodityDao getCommodityDaoImpl() {
		return new CommodityDaoImpl();
	}

    /**
	 * UserInformationDao
	 * @return
	 */
	public static UserInformationDao getUserInformationDaoImpl() {
		return new UserInformationDaoImpl();
	}

    /**
	 * ShoppingCarDao
	 * @return
	 */
	public static ShoppingCarDao getShoppingCarDaoImpl() {
		return new ShoppingCarDaoImpl();
	}

    /**
	 * OrderDao
	 * @return
	 */
	public static OrderDao getOrderDaoImpl() {
		return new OrderDaoImpl();
	}

    public static ShopDao getShopDaoImpl() {
		return new ShopDaoImpl();
	}
}
