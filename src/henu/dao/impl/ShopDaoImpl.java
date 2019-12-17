package henu.dao.impl;

import java.util.List;

import henu.DbUtil.ApacheDbutil;
import henu.bean.Shop;
import henu.dao.ShopDao;

public class ShopDaoImpl implements ShopDao {

    // �����̵�
    public int addShop(Shop shop) {
        int result = ApacheDbutil.insert("shops", shop);
        return result;
    }

    // ɾ���̵�
    public int delete(int shopID) {
        String key = "shopID";
        Object[] params = {shopID};
        int result = ApacheDbutil.delete("shops", key, params);
        return result;
    }

    // �޸��̵�
    public int update(Shop shop) {
        int result = ApacheDbutil.update("shops", shop);
        return result;
    }

    // �̵�id���ҵ���
    public List<Object[]> queryByShopID(int shopID) {
        String sql = "select * from shops,commodity where shops.shopID=commodity.shopID and shops.shopID=? ;";
        Object[] p = {shopID};
        List<Object[]> lists = ApacheDbutil.Query(sql, p);
        return lists;
    }

    // ���ݵ���id��ѯ����
    public List<Object[]> queryByShopManager(String userID) {
        String sql = "select * from shops,commodity where shops.shopID=commodity.shopID and shops.shopManager=? ;";
        Object[] p = {userID};
        List<Object[]> lists = ApacheDbutil.Query(sql, p);
        return lists;
    }

    // findall
    public List<Shop> findAll() {
        List<Shop> list = (List<Shop>) ApacheDbutil.query("shops");
        return list;
    }

    // ��ѯĿǰ���id
    public int queryMaxID() {
        String sql = "select MAX(shopID) from shops;";
        Object[] p = {};
        List<Object[]> list = ApacheDbutil.Query(sql, p);
        int result = 0;
        for (Object[] ob : list) {
            result = Integer.parseInt(ob[0].toString());
        }
        return result;

    }

    //�̵�id���ҵ�����Ϣ
    public Shop queryByShopId(int shopID) {
        String[] keys1 = {"shopID"};//��ѯ����
        Object[] params1 = {shopID};//������ֵ
        Shop shop = (Shop) ApacheDbutil.querySingle("shops", keys1, params1);
        return shop;
    }

    //����id���ҵ�����Ϣ
    public Shop queryByShopmanager(String userID) {
        String[] keys1 = {"shopManager"};//��ѯ����
        Object[] params1 = {userID};//������ֵ
        Shop shop = (Shop) ApacheDbutil.querySingle("shops", keys1, params1);
        return shop;
    }
}
