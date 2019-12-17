package henu.dao.impl;

import java.util.List;

import henu.DbUtil.ApacheDbutil;
import henu.bean.UserInformation;
import henu.dao.UserInformationDao;

public class UserInformationDaoImpl implements UserInformationDao {

	// ��ѯһ���û���ȫ����Ϣ
	public Object[] queryOne(String userID) {
		String sql = "select users.*, user_information.* from users,user_information where users.userID=? and users.userID=user_information.userID";
		Object[] p = {userID};
		Object[] object = null;
		List<Object[]> list = ApacheDbutil.Query(sql, p);
		if (list.size() > 0)
			object = list.get(0);
		return object;
	}

	// �޸���Ϣ
	public boolean update(UserInformation userInformation) {
		if (ApacheDbutil.update("user_information", userInformation) != 0)
			return true;
		return false;
	}

	// ɾ���û�������Ϣ
	public void delete(String userID) {
		String key = "userID";
		Object[] params = {userID};
		ApacheDbutil.delete("user_information", key, params);
	}

	// �����û�������Ϣ
	public int add(UserInformation userInformation) {
		int result = ApacheDbutil.insert("user_information", userInformation);
		return result;
	}

	// ��ѯ������Ϣ
	public UserInformation queryByUserID(String userID) {
		String[] keys1 = {"userID"};// ��ѯ����
		Object[] params1 = {userID};// ������ֵ
		UserInformation userInformation = (UserInformation) ApacheDbutil.querySingle("user_information", keys1,
				params1);
		return userInformation;
	}
}
