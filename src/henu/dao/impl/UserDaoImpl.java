package henu.dao.impl;

import henu.DbUtil.ApacheDbutil;
import henu.bean.User;
import henu.dao.UserDao;

public class UserDaoImpl implements UserDao {

	//
	public int login(User user) {
        String[] keys1 = {"userID", "userPassword"};
        Object[] params1 = {user.getUserID(), user.getUserPassword()};
        User user1 = (User) ApacheDbutil.querySingle("users", keys1, params1);
        if (user1 != null) {
            return user1.getUserType();
        } else
            return -1;
    }

	//
	public int add(User user) {
		int result = 0;
		result = ApacheDbutil.insert("users", user);
		if (result == 1) {
			System.out.println("���ӹ˿��û�" + user.getUserID());// ע���û�
		}
		return result;

	}

	//
	public User queryByUserID(String userID) {
        String[] keys1 = {"userID"};// ��ѯ����
        Object[] params1 = {userID};// ������ֵ
        User user1 = (User) ApacheDbutil.querySingle("users", keys1, params1);
        return user1;
    }

	//
	public int update(User user) {
		int result = ApacheDbutil.update("users", user);
		return result;
	}

    //ɾ���û�
	public int delete(String userID) {
		Object[] params = {userID};
		int result = ApacheDbutil.delete("users", "userID", params);
		return result;
	}
}
