package henu.dao;

import henu.bean.User;

public interface UserDao {

    /**
     * 登录
     *
     * @return
     */
    public int login(User user);

    /**
     * 添加用户
     *
     * @param user
     * @return 返回影响行数，即成功注册人数
     */
    public int add(User user);

    /**
     * 查询一个用户
     *
     * @return 成功返回user, 失败返回null
     */
    public User queryByUserID(String userID);

    /**
     * 修改用户
     *
     * @param user
     * @return int 成功返回1，失败返回0
     */
    public int update(User user);

    /**
     * 删除用户
     *
     * @param userID
     * @return
     */
    public int delete(String userID);

}
