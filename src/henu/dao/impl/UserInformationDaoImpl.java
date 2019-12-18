package henu.dao.impl;

import henu.DbUtil.ApacheDbutil;
import henu.bean.UserInformation;
import henu.dao.UserInformationDao;

import java.util.List;

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
        return ApacheDbutil.update("user_information", userInformation) != 0;
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
        Object[] params = {userID};// ������ֵ
        UserInformation userInformation = new UserInformation();
        String sql = "select * from user_information where userID=? ;";
        List<Object[]> list = ApacheDbutil.Query(sql, params);
        if (list.size() > 0) {
            Object[] object = list.get(0);
            userInformation.setUserID(object[0].toString());
            userInformation.setUserSex(Integer.parseInt(object[2].toString()));
            userInformation.setUserAddress(object[3].toString());
        }
        return userInformation;
    }
}
