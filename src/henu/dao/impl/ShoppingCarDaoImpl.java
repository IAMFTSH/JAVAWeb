package henu.dao.impl;

import java.util.List;

import henu.DbUtil.ApacheDbutil;
import henu.bean.ShoppingCar;
import henu.dao.ShoppingCarDao;

public class ShoppingCarDaoImpl implements ShoppingCarDao {

	//��ѯ�ҵĹ��ﳵ
	public List<Object[]> queryMyShoppingCar(String userID) {
		String sql = "select * from shopping_car,commodity,shops where shopping_car.commodityID=commodity.commodityID and shopping_car.userID=? and shops.shopID=commodity.shopID;";
		Object[] p = {userID};
		List<Object[]> list = ApacheDbutil.Query(sql, p);
		return list;
	}

	//��Ʒ���빺�ﳵ
	public void addCommodity(String userID, int commodityID, int num) {
		//�жϹ��ﳵ���Ƿ�����ͬ����Ʒ
		String sql = "select * from shopping_car where userID=? and commodityID=? ;";
		Object[] p = {userID, commodityID};
		List<Object[]> list = ApacheDbutil.Query(sql, p);
		if (list.size() > 0) {
			add(userID, commodityID, num);
		} else {
			ShoppingCar car = new ShoppingCar();
			car.setUserID(userID);
			car.setCommodityID(commodityID);
			car.setNum(num);
			ApacheDbutil.insert("shopping_car", car);
		}
	}

	//���ﳵ����Ʒ�Ӽ�
	public void add(String userID, int commodityID, int add) {
		ShoppingCar car = new ShoppingCar();
		car.setUserID(userID);
		car.setCommodityID(commodityID);
		String sql1 = "select * from shopping_car where userID=? and commodityID=? ;";
		Object[] params1 = {userID, commodityID};
		List<Object[]> list = (List<Object[]>) ApacheDbutil.Query(sql1, params1);
		if (list.size() > 0) {
			car.setNum(Integer.parseInt(list.get(0)[2].toString()));
		}
		car.setNum(car.getNum() + add);
		String sql = "update shopping_car set num=? where userID=? and commodityID=?;";
		Object[] params = {car.getNum(), car.getUserID(), car.getCommodityID()};
		ApacheDbutil.update(sql, params);
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

	//ɾ��ĳ����Ʒ
	public int deleteByCommodityID(int commodityID) {
		int result = 0;
		String key = "commodityID";//��ѯ����
		Object[] params = {commodityID};//������ֵ
		result = ApacheDbutil.delete("shopping_car", key, params);
		return result;
	}

	//��չ��ﳵ
	public int deleteByUserID(String userID) {
		int result = 0;
		String key = "userID";//��ѯ����
		Object[] params = {userID};//������ֵ
		result = ApacheDbutil.delete("shopping_car", key, params);
		return result;
	}
}
