package henu.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import henu.bean.User;
import henu.bean.UserInformation;
import henu.factory.DaoFactory;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     * response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // TODO Auto-generated method stub
        action(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     * response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // TODO Auto-generated method stub
        action(request, response);
    }

    /**
     * action
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void action(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // TODO Auto-generated method stub
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json; charset=utf-8");
        String method = request.getParameter("method");
        if ("login".equals(method)) {
            login(request, response);
        } else if ("add".equals(method)) {
            add(request, response);
        } else if ("exit".equals(method)) {
            exit(request, response);
        } else if ("loginAdmin".equals(method)) {
            loginAdmin(request, response);
        } else if ("isLogin".equals(method)) {
            isLogin(request, response);
        } else if ("queryByUserID".equals(method)) {
            queryByUserID(request, response);
        } else if ("updateByUserID".equals(method)) {
            updateByUserID(request, response);
        } else if ("delete".equals(method)) {
            delete(request, response);
        }
    }

    /**
     * 用户登录 method=login json:{ "result":"true[登陆成功]/false[登陆失败]",
     * "username":"[用户名]","password":"[密码]", "userType":"[用户类型（1：买家；2：卖家；3：管理员）]" }
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void login(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        JSONObject json = new JSONObject();
        PrintWriter writer = response.getWriter();
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json; charset=utf-8");
        User user = new User(request.getParameter("username"), request.getParameter("password"), 0);
        if (DaoFactory.getUserDaoImpl().login(user) == -1) {// 登陆失败
            json.put("result", false);
        } else {// 登陆成功
            user = new User(user.getUserID(), user.getUserPassword(), DaoFactory.getUserDaoImpl().login(user));
            System.out.println(user.getUserID());
            json.put("result", true);
            json.put("username", user.getUserID());
            json.put("password", user.getUserPassword());
            json.put("userType", user.getUserType());
        }
        writer.print(json);

    }

    /**
     * 用户注册 method=add 输出json{" result":"true[注册成功]/false[注册失败（用户名已经被使用）]" }
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter writer = response.getWriter();
        JSONObject json = new JSONObject();
        User user = new User(request.getParameter("regist_username"), request.getParameter("regist_password"), 1);
        UserInformation userInformation = new UserInformation();
        userInformation.setUserID(user.getUserID());
        userInformation.setUserAddress(request.getParameter("address"));
        userInformation.setUserSex(Integer.parseInt(request.getParameter("gender")));
        if (DaoFactory.getUserDaoImpl().queryByUserID(user.getUserID()) == null) {// 用户名未被注册
            DaoFactory.getUserDaoImpl().add(user);
            DaoFactory.getUserInformationDaoImpl().add(userInformation);
            json.put("result", true);
        } else {// 用户名已被注册
            json.put("result", false);
        }
        writer.print(json);
    }

    /**
     * 退出登录 method=exit json{ "result":"true" }
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void exit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        PrintWriter writer = response.getWriter();
        JSONObject json = new JSONObject();
        session.setAttribute("isLogin", 0);
        json.put("result", true);
        writer.print(json);
    }

    /**
     * 管理员登录 输出json{"result":"true/false"}
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void loginAdmin(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        User loginUser = new User(request.getParameter("username"), request.getParameter("password"), 0);
        int user = DaoFactory.getUserDaoImpl().login(loginUser);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式
        JSONObject json = new JSONObject();
        if (user != -1) {
            if (user == 8) {
                if (request.getParameter("remember") != null) {
                    Cookie userID = new Cookie("userID", loginUser.getUserID());
                    userID.setMaxAge(60 * 60);// cookie保存时间（秒）
                    response.addCookie(userID);
                    Cookie userPassword = new Cookie("userPassword", loginUser.getUserPassword());
                    userPassword.setMaxAge(60 * 60);
                    response.addCookie(userPassword);
                }

                System.out.println(request.getParameter("username") + "登录管理员后台 " + df.format(new Date()));
                json.put("result", true);
            } else {
                System.out.println(request.getParameter("username") + "尝试登录管理员后台 " + df.format(new Date()));
                json.put("result", false);
            }
        } else {
            json.put("result", false);
        }
    }

    /**
     * 判断是否登录 method=isLogin 输出json数据{"isLogin":"true[已经登录]/false[未登录]"}
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void isLogin(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        PrintWriter writer = response.getWriter();
        JSONObject json = new JSONObject();
        if (session.getAttribute("isLogin") == null) {
            session.setAttribute("isLogin", 0);
            json.put("isLogin", false);
        } else {
            if (Integer.parseInt(session.getAttribute("isLogin").toString()) == 0) {
                json.put("isLogin", false);
            } else {
                json.put("isLogin", true);
            }
        }
        writer.print(json);
    }

    /**
     * 根据用户id查询用户信息(传入用户id，未传入输出json:{"result":"false"}) json:{ "userID":"[用户id]",
     * "userPassword":"[密码]", "userType":"[用户类型]", "userSex":"[用户性别]",
     * "userAddress":"[地址]" }
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void queryByUserID(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter writer = response.getWriter();
        JSONObject json = new JSONObject();
        if (request.getParameter("userID") != null) {
            String userID = request.getParameter("userID");
            Object[] object = (Object[]) DaoFactory.getUserInformationDaoImpl().queryOne(userID);
            json.put("userID", object[0]);
            json.put("userPassword", object[1]);
            json.put("userType", object[2]);
            json.put("userSex", object[5]);
            json.put("userAddress", object[6]);
            writer.print(json);
        } else {
            json.put("result", false);
            writer.print(json);
        }
    }

    /**
     * 根据用户id修改用户信息（传入用户id，未传入输出json:{"result":"false"}） 成功修改输出json{"result":"true"}
     * 可修改信息：
     * 密码userPassword
     * 用户类型（管理员权限）userType
     * 用户性别userSex
     * 用户地址userAddress
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void updateByUserID(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter writer = response.getWriter();
        JSONObject json = new JSONObject();
        if (request.getParameter("userID") != null) {
            User user = DaoFactory.getUserDaoImpl().queryByUserID(request.getParameter("userID"));// 获取用户id
            UserInformation userInformation = DaoFactory.getUserInformationDaoImpl().queryByUserID(user.getUserID());
            if (request.getParameter("userPassword") != null && request.getParameter("userPassword") != "") {// 更新密码
                user.setUserPassword(request.getParameter("userPassword"));
            }
            if (request.getParameter("userType") != null && request.getParameter("userType") != "") {// 更新用户类型
                user.setUserType(Integer.parseInt(request.getParameter("userType")));
            }
            if (request.getParameter("userSex") != null && request.getParameter("userSex") != "") {// 更新性别
                userInformation.setUserSex(Integer.parseInt(request.getParameter("userSex")));
            }
            if (request.getParameter("userAddress") != null && request.getParameter("userAddress") != "") {// 更新地址
                userInformation.setUserAddress(request.getParameter("userAddress"));
            }
            DaoFactory.getUserDaoImpl().update(user);
            DaoFactory.getUserInformationDaoImpl().update(userInformation);
            json.put("result", true);
        } else {
            json.put("result", false);
        }
        writer.print(json);
    }

    /**
     * 删除用户(用户id)
     * json{"result":"true/false"}
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void delete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter writer = response.getWriter();
        JSONObject json = new JSONObject();
        if (request.getParameter("userID") == null) {
            json.put("result", false);
        } else {
            String userID = request.getParameter("userID");
            DaoFactory.getUserDaoImpl().delete(userID);
            json.put("result", true);
        }
        writer.print(json);
    }
}
