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
     * �û���¼ method=login json:{ "result":"true[��½�ɹ�]/false[��½ʧ��]",
     * "username":"[�û���]","password":"[����]", "userType":"[�û����ͣ�1����ң�2�����ң�3������Ա��]" }
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
        if (DaoFactory.getUserDaoImpl().login(user) == -1) {// ��½ʧ��
            json.put("result", false);
        } else {// ��½�ɹ�
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
     * �û�ע�� method=add ���json{" result":"true[ע��ɹ�]/false[ע��ʧ�ܣ��û����Ѿ���ʹ�ã�]" }
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
        if (DaoFactory.getUserDaoImpl().queryByUserID(user.getUserID()) == null) {// �û���δ��ע��
            DaoFactory.getUserDaoImpl().add(user);
            DaoFactory.getUserInformationDaoImpl().add(userInformation);
            json.put("result", true);
        } else {// �û����ѱ�ע��
            json.put("result", false);
        }
        writer.print(json);
    }

    /**
     * �˳���¼ method=exit json{ "result":"true" }
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
     * ����Ա��¼ ���json{"result":"true/false"}
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
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// �������ڸ�ʽ
        JSONObject json = new JSONObject();
        if (user != -1) {
            if (user == 8) {
                if (request.getParameter("remember") != null) {
                    Cookie userID = new Cookie("userID", loginUser.getUserID());
                    userID.setMaxAge(60 * 60);// cookie����ʱ�䣨�룩
                    response.addCookie(userID);
                    Cookie userPassword = new Cookie("userPassword", loginUser.getUserPassword());
                    userPassword.setMaxAge(60 * 60);
                    response.addCookie(userPassword);
                }

                System.out.println(request.getParameter("username") + "��¼����Ա��̨ " + df.format(new Date()));
                json.put("result", true);
            } else {
                System.out.println(request.getParameter("username") + "���Ե�¼����Ա��̨ " + df.format(new Date()));
                json.put("result", false);
            }
        } else {
            json.put("result", false);
        }
    }

    /**
     * �ж��Ƿ��¼ method=isLogin ���json����{"isLogin":"true[�Ѿ���¼]/false[δ��¼]"}
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
     * �����û�id��ѯ�û���Ϣ(�����û�id��δ�������json:{"result":"false"}) json:{ "userID":"[�û�id]",
     * "userPassword":"[����]", "userType":"[�û�����]", "userSex":"[�û��Ա�]",
     * "userAddress":"[��ַ]" }
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
     * �����û�id�޸��û���Ϣ�������û�id��δ�������json:{"result":"false"}�� �ɹ��޸����json{"result":"true"}
     * ���޸���Ϣ��
     * ����userPassword
     * �û����ͣ�����ԱȨ�ޣ�userType
     * �û��Ա�userSex
     * �û���ַuserAddress
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
            User user = DaoFactory.getUserDaoImpl().queryByUserID(request.getParameter("userID"));// ��ȡ�û�id
            UserInformation userInformation = DaoFactory.getUserInformationDaoImpl().queryByUserID(user.getUserID());
            if (request.getParameter("userPassword") != null && request.getParameter("userPassword") != "") {// ��������
                user.setUserPassword(request.getParameter("userPassword"));
            }
            if (request.getParameter("userType") != null && request.getParameter("userType") != "") {// �����û�����
                user.setUserType(Integer.parseInt(request.getParameter("userType")));
            }
            if (request.getParameter("userSex") != null && request.getParameter("userSex") != "") {// �����Ա�
                userInformation.setUserSex(Integer.parseInt(request.getParameter("userSex")));
            }
            if (request.getParameter("userAddress") != null && request.getParameter("userAddress") != "") {// ���µ�ַ
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
     * ɾ���û�(�û�id)
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
