package henu.dao;


import henu.bean.UserInformation;

public interface UserInformationDao {

    /**
     * 查询一个用户的全部信息
     * 连接users \ user_information
     *
     * @return List<Object [ ]>
     */
    public Object[] queryOne(String userID);

    /**
     * 修改信息
     *
     * @return 修改成功返回true，失败返回false
     */
    public boolean update(UserInformation userInformation);

    /**
     * 删除用户个人信息，只在删除用户账号时调用，以免发生不可预计的混乱
     *
     * @param userID
     */
    public void delete(String userID);

    /**
     * 创建用户个人信息
     *
     * @param userID
     * @return
     */
    public int add(UserInformation userInformation);

    /**
     * 查询一个用户个人信息
     *
     * @return
     */
    public UserInformation queryByUserID(String userID);
}
