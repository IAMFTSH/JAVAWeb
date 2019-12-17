package henu.dao.impl;

import java.util.List;

import henu.DbUtil.ApacheDbutil;
import henu.bean.ShoppingCar;
import henu.dao.ShoppingCarDao;

public class ShoppingCarDaoImpl implements ShoppingCarDao {

	//查询我的购物车
	public List<Object[]> queryMyShoppingCar(String userID) {
		String sql = "select * from shopping_car,commodity,shops where shopping_car.commodityID=commodity.commodityID and shopping_car.userID=? and shops.shopID=commodity.shopID;";
		Object[] p = {userID};
		List<Object[]> list = ApacheDbutil.Query(sql, p);
		return list;
	}

	//商品加入购物车
	public void addCommodity(String userID, int commodityID, int num) {
		//判断购物车中是否有相同的商品
		String sql = "select * from shopping_car where userID=? and commodityID=? ;";
		Object[] p = {userID, commodityID};
		if (ApacheDbutil.Query(sql, p) != null) {
			add(userID, commodityID, num);
		} else {
			ShoppingCar car = new ShoppingCar();
			car.setUserID(userID);
			car.setCommodityID(commodityID);
			car.setNum(num);
			ApacheDbutil.insert("shopping_car", car);
		}
	}

	//购物车中商品加减
	public void add(String userID, int commodityID, int add) {
		ShoppingCar car = new ShoppingCar();
		car.setUserID(userID);
		car.setCommodityID(commodityID);
		car.setNum(car.getNum() + add);
		ApacheDbutil.update("shopping_car", car);
	}

	public void reduce(String userID, int commodityID, int reduce) {
		ShoppingCar car = new ShoppingCar();
		car.setUserID(userID);
		car.setCommodityID(commodityID);
		car.setNum(car.getNum() - reduce);
		if (car.getNum() < 0) {
			car.setNum(0);
		}
		ApacheDbutil.update("shopping_car", car);
	}

	//删除某样商品
	public int deleteByCommodityID(int commodityID) {
		int result = 0;
		String key = "commodityID";//查询条件
		Object[] params = {commodityID};//条件的值
		result = ApacheDbutil.delete("shopping_car", key, params);
		return result;
	}

	//清空购物车
	public int deleteByUserID(String userID) {
		int result = 0;
		String key = "userID";//查询条件
		Object[] params = {userID};//条件的值
		result = ApacheDbutil.delete("shopping_car", key, params);
		return result;
	}
}
