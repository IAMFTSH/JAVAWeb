package henu.dao.impl;

import henu.DbUtil.ApacheDbutil;
import henu.bean.Commodity;
import henu.dao.CommodityDao;

import java.util.List;

public class CommodityDaoImpl implements CommodityDao {

    //��ѯ����
    public List<Commodity> findAll() {
        List<Commodity> list = (List<Commodity>) ApacheDbutil.query("commodity");
        return list;
    }

    //�����Ʒ
    public int addCommodity(Commodity commodity) {
        int result = ApacheDbutil.insert("commodity", commodity);
		return result;
	}

    //ɾ����Ʒ
	public int deleteByID(int commodityID) {
        String key = "commodityID";
        Object[] params = {commodityID};
        int result = ApacheDbutil.delete("commodity", key, params);
        return result;
    }

    //�޸���Ʒ
	public int update(Commodity commodity) {
		int result = ApacheDbutil.update("commodity", commodity);
		return result;
	}

    //��ƷidѰ����Ʒ
	public Commodity queryByCommodityID(int commodityID) {
        String[] keys = {"commodityID"};
        Object[] params = {commodityID};
        Commodity commodity = (Commodity) ApacheDbutil.querySingle("commodity", keys, params);
        return commodity;
    }

    //�̵�id��ѯ��Ʒ
    public List<Commodity> queryByShopID(int shopID) {
        String[] keys = {"shopID"};
        Object[] params = {shopID};
        List<Commodity> list = (List<Commodity>) ApacheDbutil.query("commodity", keys, params);
        return list;
    }

    //�̵����Ʋ�ѯ��Ʒ��ģ�����ң�
    public List<Object[]> queryByShopName(String shopName) {
        String sql = "select * from shops,commodity where shops.shopID=commodity.shopID and shopName=? ;";
        Object[] params = {"%" + shopName + "%"};
        List<Object[]> list = ApacheDbutil.Query(sql, params);
        return list;
    }

    //��Ʒ���Ʋ�ѯ��Ʒ��ģ����ѯ��
    public List<Commodity> queryByCommodityName(String name) {
        String[] keys = {"commodityName"};
        Object[] params = {"%" + name + "%"};
        List<Commodity> list = (List<Commodity>) ApacheDbutil.query("commodity", keys, params);
        return list;
    }

    //�۸��ѯ��Ʒ
    public List<Object[]> queryByPriceMin(int minPrice) {
        Object[] params = {minPrice};
        String sql = "select * from commodity where commodityPrice>=? ;";
        List<Object[]> list = ApacheDbutil.Query(sql, params);
        return list;
    }

    public List<Object[]> queryByPriceMax(int maxPrice) {
        Object[] params = {maxPrice};
        String sql = "select * from commodity where commodityPrice<=? ;";
        List<Object[]> list = ApacheDbutil.Query(sql, params);
        return list;
    }

    public List<Object[]> queryByPriceMinToMax(int minPrice, int maxPrice) {
        Object[] params = {minPrice, maxPrice};
        String sql = "select * from commodity where commodityPrice>=?  and commodityPrice<=?;";
        List<Object[]> list = ApacheDbutil.Query(sql, params);
        return list;
    }

    //��Ʒ��������
    public int reduce(Commodity commodity, int reduce) {
        commodity = queryByCommodityID(commodity.getCommodityID());
        if (commodity.getCommodityNumber() < reduce) {
            System.out.print(commodity.getCommodityName() + "��治�㣬��ƷID��" + commodity.getCommodityID());
            return 0;
        } else {
            commodity.setCommodityNumber(commodity.getCommodityNumber() - reduce);
            int result = update(commodity);
            return result;
        }
    }

    //��Ʒ��������
	public int add(Commodity commodity, int add) {
        commodity = queryByCommodityID(commodity.getCommodityID());
        commodity.setCommodityNumber(commodity.getCommodityNumber() + add);
        int result = update(commodity);
        return result;
    }

    //�޸���ƷͼƬ·��
	public int updateImage(Commodity commodity) {
		int result = ApacheDbutil.update("commodity", commodity);
		return result;
	}

    //��ѯĿǰ���id
	public int queryMaxID() {
        String sql = "select MAX(commodityID) from commodity;";
        Object[] p = {};
        List<Object[]> list = ApacheDbutil.Query(sql, p);
        int result = 0;
        for (Object[] ob : list) {
            result = Integer.parseInt(ob[0].toString());
        }
        return result;

    }
}
