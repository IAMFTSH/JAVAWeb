package henu.dao;


import henu.bean.UserInformation;

public interface UserInformationDao {

	/**
	 * ��ѯһ���û���ȫ����Ϣ
	 * ����users \ user_information
	 *
	 * @return List<Object [ ]>
	 */
	public Object[] queryOne(String userID);

	/**
	 * �޸���Ϣ
	 *
	 * @return �޸ĳɹ�����true��ʧ�ܷ���false
	 */
	public boolean update(UserInformation userInformation);

	/**
	 * ɾ���û�������Ϣ��ֻ��ɾ���û��˺�ʱ���ã����ⷢ������Ԥ�ƵĻ���
	 *
	 * @param userID
	 */
	public void delete(String userID);

	/**
	 * �����û�������Ϣ
	 *
	 * @param userID
	 * @return
	 */
	public int add(UserInformation userInformation);

	/**
	 * ��ѯһ���û�������Ϣ
	 *
	 * @return
	 */
	public UserInformation queryByUserID(String userID);
}
