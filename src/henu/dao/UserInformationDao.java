package henu.dao;


import henu.bean.UserInformation;

public interface UserInformationDao {

    /**
     * ��ѯһ���û���ȫ����Ϣ
     * ����users \ user_information
     *
     * @return List<Object [ ]>
     */
    Object[] queryOne(String userID);

    /**
     * �޸���Ϣ
     *
     * @return �޸ĳɹ�����true��ʧ�ܷ���false
     */
    boolean update(UserInformation userInformation);

    /**
     * ɾ���û�������Ϣ��ֻ��ɾ���û��˺�ʱ���ã����ⷢ������Ԥ�ƵĻ���
     *
     * @param userID
     */
    void delete(String userID);

    /**
     * �����û�������Ϣ
     *
     * @param userID
     * @return
     */
    int add(UserInformation userInformation);

    /**
     * ��ѯһ���û�������Ϣ
     *
     * @return
     */
    UserInformation queryByUserID(String userID);
}
