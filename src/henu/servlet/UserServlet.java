package henu.servlet;

import henu.bean.User;
import henu.bean.UserInformation;
import henu.factory.DaoFactory;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

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
     *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		action(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
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
        } else if ("getSession".equals(method)) {
            getSession(request, response);
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
        User user = new User(request.getParameter("userID"), request.getParameter("userPassword"), 0);
        if (DaoFactory.getUserDaoImpl().login(user) == -1) {// 登陆失败
            json.put("result", false);
        } else {// 登陆成功
            HttpSession session = request.getSession();
            user = new User(user.getUserID(), user.getUserPassword(), DaoFactory.getUserDaoImpl().login(user));
            System.out.println(user.getUserID() + "登陆成功");
            session.setAttribute("isLogin", 1);
            session.setAttribute("userID", user.getUserID());
            session.setAttribute("userType", user.getUserType());
            json.put("result", true);
            json.put("userID", user.getUserID());
            json.put("userPassword", user.getUserPassword());
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
        JSONObject json = new JSONObject();
        session.setAttribute("isLogin", 0);
        session.setAttribute("userID", "null");
        json.put("result", true);
        PrintWriter writer = response.getWriter();
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
        User loginUser = new User(request.getParameter("userID"), request.getParameter("userPassword"), 0);// 获取账号密码
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
                System.out.println(request.getParameter("userID") + "登录管理员后台 " + df.format(new Date()));
                json.put("result", true);
            } else {
                System.out.println(request.getParameter("userID") + "尝试登录管理员后台 " + df.format(new Date()));
                json.put("result", false);
            }
        } else {
            json.put("result", false);
        }
        PrintWriter writer = response.getWriter();
        writer.print(json);
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
                String userID = session.getAttribute("userID").toString();
                User user = DaoFactory.getUserDaoImpl().queryByUserID(userID);
                json.put("isLogin", true);
                json.put("userID", user.getUserID());
                json.put("userPassword", user.getUserPassword());
                json.put("userType", user.getUserType());
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
        if (request.getParameter("userID") != "") {
            String userID = request.getParameter("userID");
            if (DaoFactory.getUserInformationDaoImpl().queryOne(userID) != null) {
                Object[] object = DaoFactory.getUserInformationDaoImpl().queryOne(userID);
                json.put("userID", object[0]);
                json.put("userPassword", object[1]);
                json.put("userType", object[2]);
                json.put("userSex", object[5]);
                json.put("userAddress", object[6]);
                json.put("result", true);
			} else {
				json.put("result", false);
			}
		} else {
			json.put("result", false);
		}
		writer.print(json);
    }

    /**
     * 根据用户id修改用户信息（传入用户id，未传入输出json:{"result":"false"}） 成功修改输出json{"result":"true"}
     * 可修改信息： 密码userPassword 用户类型（管理员权限）userType 用户性别userSex 用户地址userAddress
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
        if (request.getParameter("userID") != "") {
            if (DaoFactory.getUserDaoImpl().queryByUserID(request.getParameter("userID")) != null) {
                User user = DaoFactory.getUserDaoImpl().queryByUserID(request.getParameter("userID"));// 获取用户id
                UserInformation userInformation = DaoFactory.getUserInformationDaoImpl()
                        .queryByUserID(user.getUserID());
                if (request.getParameter("userPassword") != "") {// 更新密码
                    user.setUserPassword(request.getParameter("userPassword"));
                }
                if (request.getParameter("userType") != "") {// 更新用户类型
                    user.setUserType(Integer.parseInt(request.getParameter("userType")));
                }
                if (request.getParameter("userSex") != "") {// 更新性别
                    userInformation.setUserSex(Integer.parseInt(request.getParameter("userSex")));
                }
                if (request.getParameter("userAddress") != "") {// 更新地址
                    userInformation.setUserAddress(request.getParameter("userAddress"));
                }
                if (DaoFactory.getUserDaoImpl().update(user) != 0
                        && DaoFactory.getUserInformationDaoImpl().update(userInformation)) {
                    json.put("result", true);
                } else {
                    json.put("result", false);
                }
            } else {
                json.put("result", false);
            }
        } else {
            json.put("result", false);
        }
        writer.print(json);
    }

    /**
     * 删除用户(用户id) json{"result":"true/false"}
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
        if (request.getParameter("userID") == "") {
            json.put("result", false);
        } else {
            String userID = request.getParameter("userID");
            if (DaoFactory.getUserDaoImpl().delete(userID) != 0) {
                json.put("result", true);
            } else {
                json.put("result", false);
            }
        }
        writer.print(json);
    }

    /**
     * 获取当前session
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void getSession(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        PrintWriter writer = response.getWriter();
        JSONObject json = new JSONObject();
        json.put("result", true);
        if (session.getAttribute("userID") != null) {
            json.put("userID", session.getAttribute("userID"));
        }
        if (session.getAttribute("isLogin") != null) {
            json.put("isLogin", session.getAttribute("isLogin"));
        }
        if (session.getAttribute("userType") != null) {
            json.put("userType", session.getAttribute("userType"));
        }
        writer.print(json);
	}
}