package henu;

import henu.DbUtil.ApacheDbutil;
import henu.bean.Commodity;
import henu.bean.User;

import java.util.Arrays;
import java.util.List;


public class queryText {


    public static void main(String[] args) {//userType不能使用0，因为int默认值是0；

        //单表查询
        String[] keys1 = {"userID"};//查询条件
        Object[] params1 = {"lvhao"};//条件的值

        List<User> users = (List<User>) ApacheDbutil.query("users", keys1, params1);  //多行多条件查询测试
        System.out.println("多行多条件查询测试：用户" + Arrays.toString(users.toArray()));

        users = (List<User>) ApacheDbutil.query("users");   //多行无条件查询测试
        System.out.println("多行无条件查询测试：用户" + Arrays.toString(users.toArray()));

        List<Commodity> com = (List<Commodity>) ApacheDbutil.query("commodity");   //多行无条件查询测试
        System.out.println("多行无条件查询测试：用户" + Arrays.toString(com.toArray()));

        User user = (User) ApacheDbutil.querySingle("users", keys1, params1);  //单行多条件查询测试
        System.out.println("单行多条件查询测试" + user);

        user = (User) ApacheDbutil.querySingle("users");  //单行无条件查询测试
        System.out.println("单行无条件查询测试" + user);

        //多表查询
        //查出来的是链表数组
        String sql = "select u.*,ui.* from users u,user_information ui where u.userID=ui.userID and u.userID=?";
        Object[] p = {"123"};
        List<Object[]> lists = ApacheDbutil.Query(sql, p);
        for (Object[] list : lists) {
            for (int i = 0; i < list.length; i++) {
                System.out.print(list[i] + ",");
            }
        }
    }
}
