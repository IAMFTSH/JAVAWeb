package henu.dao.impl;

import henu.DbUtil.ApacheDbutil;
import henu.bean.ShoppingCar;
import henu.dao.ShoppingCarDao;

import java.util.List;

public class ShoppingCarDaoImpl implements ShoppingCarDao {

    //��ѯ�ҵĹ��ﳵ
    public List<Object[]> queryMyShoppingCar(String userID) {
        String sql = "select * from shopping_car,commodity,shops where shopping_car.commodityID=commodity.commodityID and shopping_car.userID=? and shops.shopID=commodity.shopID;";
        Object[] p = {userID};
        List<Object[]> list = ApacheDbutil.Query(sql, p);
        return list;
    }

    //
    public List<Object[]> queryMyShoppingCar(String userID, int commodityID) {
        String sql = "select * from shopping_car,commodity,shops where shopping_car.commodityID=commodity.commodityID and shopping_car.userID=? and commodity.commodityID=? and shops.shopID=commodity.shopID;";
        Object[] p = {userID, commodityID};
        List<Object[]> list = ApacheDbutil.Query(sql, p);
        return list;
    }

    //��Ʒ���빺�ﳵ
    public int addCommodity(String userID, int commodityID, int num) {
        //�жϹ��ﳵ���Ƿ�����ͬ����Ʒ
        int result = 0;
        String sql = "select * from shopping_car where userID=? and commodityID=? ;";
        Object[] p = {userID, commodityID};
        List<Object[]> list = ApacheDbutil.Query(sql, p);
        if (list.size() > 0) {
            result = add(userID, commodityID, num);
        } else {
            ShoppingCar car = new ShoppingCar();
            car.setUserID(userID);
            car.setCommodityID(commodityID);
            car.setNum(num);
            result = ApacheDbutil.insert("shopping_car", car);
        }
        return result;
    }

    //���ﳵ����Ʒ��
    public int add(String userID, int commodityID, int add) {
        int result = 0;
        ShoppingCar car = new ShoppingCar();
        car.setUserID(userID);
        car.setCommodityID(commodityID);
        String sql1 = "select * from shopping_car where userID=? and commodityID=? ;";
        Object[] params1 = {userID, commodityID};
        List<Object[]> list = ApacheDbutil.Query(sql1, params1);
        if (list.size() > 0) {
            car.setNum(Integer.parseInt(list.get(0)[2].toString()));
        }
        car.setNum(car.getNum() + add);
        String sql = "update shopping_car set num=? where userID=? and commodityID=?;";
        Object[] params = {car.getNum(), car.getUserID(), car.getCommodityID()};
        result = ApacheDbutil.update(sql, params);
        return result;
    }

    //���ﳵ����Ʒ��
    public int reduce(String userID, int commodityID, int reduce) {
        int result = 0;
        ShoppingCar car = new ShoppingCar();
        car.setUserID(userID);
        car.setCommodityID(commodityID);
        String sql1 = "select * from shopping_car where userID=? and commodityID=? ;";
        Object[] params1 = {userID, commodityID};
        List<Object[]> list = ApacheDbutil.Query(sql1, params1);
        if (list.size() > 0) {
            car.setNum(Integer.parseInt(list.get(0)[2].toString()));
        }
        car.setNum(car.getNum() - reduce);
        if (car.getNum() < 0) {
            car.setNum(0);
        }
        result = ApacheDbutil.update("shopping_car", car);
        return result;
    }

    //�޸�����
    public int updateNum(String userID, int commodityID, int num) {
        int result = 0;
        ShoppingCar car = new ShoppingCar();
        car.setUserID(userID);
        car.setCommodityID(commodityID);
        car.setNum(num);
        result = ApacheDbutil.update("shopping_car", car);
        return result;
    }

    //ɾ��ĳ����Ʒ
    public int deleteByUserIDCommodityID(String userID, int commodityID) {
        int result = 0;
        String[] key = {"userID", "commodityID"};
        Object[] params = {userID, commodityID};
        result = ApacheDbutil.delete1("shopping_car", key, params);
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
