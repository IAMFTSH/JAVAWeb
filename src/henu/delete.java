package henu;

import henu.DbUtil.ApacheDbutil;

public class delete {
    public static void main(String[] args) {
        System.out.println(ApacheDbutil.delete("users", "userID", new Object[]{"asd"}));
    }
}
