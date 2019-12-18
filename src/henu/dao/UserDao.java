package henu.dao;

import henu.bean.User;

public interface UserDao {

    /**
     * ��¼
     *
     * @return
     */
    int login(User user);

    /**
     * ����û�
     *
     * @param user
     * @return ����Ӱ�����������ɹ�ע������
     */
    int add(User user);

    /**
     * ��ѯһ���û�
     *
     * @return �ɹ�����user,ʧ�ܷ���null
     */
    User queryByUserID(String userID);

    /**
     * �޸��û�
     *
     * @param user
     * @return int �ɹ�����1��ʧ�ܷ���0
     */
    int update(User user);

    /**
     * ɾ���û�
     *
     * @param userID
     * @return
     */
    int delete(String userID);

}
