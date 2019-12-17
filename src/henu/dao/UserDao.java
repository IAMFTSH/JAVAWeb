package henu.dao;

import henu.bean.User;

public interface UserDao {

	/**
	 * ��¼
	 *
	 * @return
	 */
	public int login(User user);

	/**
	 * ����û�
	 *
	 * @param user
	 * @return ����Ӱ�����������ɹ�ע������
	 */
	public int add(User user);

	/**
	 * ��ѯһ���û�
	 *
	 * @return �ɹ�����user,ʧ�ܷ���null
	 */
	public User queryByUserID(String userID);

	/**
	 * �޸��û�
	 *
	 * @param user
	 * @return int �ɹ�����1��ʧ�ܷ���0
	 */
	public int update(User user);

	/**
	 * ɾ���û�
	 *
	 * @param userID
	 * @return
	 */
	public int delete(String userID);

}
