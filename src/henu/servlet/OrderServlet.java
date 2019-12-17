package henu.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import henu.bean.Order;
import henu.bean.OrderCommodity;
import henu.factory.DaoFactory;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class OrderServlet
 */
@WebServlet("/OrderServlet")
public class OrderServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public OrderServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        action(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
    protected void action(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String method = request.getParameter("method");
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json; charset=utf-8");
        if ("list".equals(method)) {
            list(request, response);
        } else if ("queryByUserID".equals(method)) {
            queryByUserID(request, response);
        } else if ("add".equals(method)) {
            add(request, response);
        } else if ("delete".equals(method)) {
            delete(request, response);
        } else if ("update".equals(method)) {
            update(request, response);
        }
    }

    /**
     * ���ж���������Ϣ
     * json����[{
     * "orderID":"[����id]",
     * "userID":"[�û�id]",
     * "orderDate":"[������������]",
     * "orderState":"[����״̬]",
     * "commodityID":"[��Ʒid]",
     * "number":"[��������]",
     * "shopID":"[�̵�id]",
     * "commodityName":"[��Ʒ����]",
     * "commodityPrice":"[��Ʒ����]",
     * "commodityNumber":"[��Ʒʣ��]",
     * "commodityIntroduce":"[��Ʒ���]",
     * "commodityImage":"[��ƷͼƬ·��]"
     * }]
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Object[]> list = DaoFactory.getOrderDaoImpl().findAll();
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json; charset=utf-8");
        JSONObject jsonObject = new JSONObject();
        PrintWriter writer = response.getWriter();
        JSONArray jsonArray = new JSONArray();
        for (Object[] object : list) {
            jsonObject.put("orderID", object[0]);//����id
            jsonObject.put("userID", object[1]);//�û�id
            jsonObject.put("orderDate", object[2]);//������������
            jsonObject.put("orderState", object[3]);//����״̬
            jsonObject.put("commodityID", object[5]);//��Ʒid
            jsonObject.put("number", object[6]);//��������
            jsonObject.put("shopID", object[8]);//�̵�id
            jsonObject.put("commodityName", object[9]);//��Ʒ����
            jsonObject.put("commodityPrice", object[10]);//��Ʒ����
            jsonObject.put("commodityNumber", object[11]);//��Ʒʣ��
            jsonObject.put("commodityIntroduce", object[12]);//��Ʒ���
            jsonObject.put("commodityImage", object[13]);//��ƷͼƬ·��
            jsonArray.add(jsonObject);
        }
        writer.print(jsonArray);

    }

    /**
     * �����û�id��ѯ����������Ϣ������userID��δ�����򷵻�json{"result":"false"}��
     * ���json����[{
     * "orderID":"[����id]",
     * "userID":"[�û�id]",
     * "orderDate":"[������������]",
     * "orderState":"[����״̬]",
     * "commodityID":"[��Ʒid]",
     * "number":"[��������]",
     * "shopID":"[�̵�id]",
     * "commodityName":"[��Ʒ����]",
     * "commodityPrice":"[��Ʒ����]",
     * "commodityNumber":"[��Ʒʣ��]",
     * "commodityIntroduce":"[��Ʒ���]",
     * "commodityImage":"[��ƷͼƬ·��]"
     * }]
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void queryByUserID(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userID = "";
        PrintWriter writer = response.getWriter();
        if (request.getParameter("userID") != null) {
            userID = request.getParameter("userID");//��ȡ�û�id
            List<Object[]> list = DaoFactory.getOrderDaoImpl().queryByUser(userID);
            response.setCharacterEncoding("utf-8");
            response.setContentType("application/json; charset=utf-8");
            JSONObject jsonObject = new JSONObject();
            JSONArray jsonArray = new JSONArray();
            for (Object[] object : list) {
                jsonObject.put("orderID", object[0]);//����id
                jsonObject.put("userID", object[1]);//�û�id
                jsonObject.put("orderDate", object[2]);//������������
                jsonObject.put("orderState", object[3]);//����״̬
                jsonObject.put("commodityID", object[5]);//��Ʒid
                jsonObject.put("number", object[6]);//��������
                jsonObject.put("shopID", object[8]);//�̵�id
                jsonObject.put("commodityName", object[9]);//��Ʒ����
                jsonObject.put("commodityPrice", object[10]);//��Ʒ����
                jsonObject.put("commodityNumber", object[11]);//��Ʒʣ��
                jsonObject.put("commodityIntroduce", object[12]);//��Ʒ���
                jsonObject.put("commodityImage", object[13]);//��ƷͼƬ·��
                jsonArray.add(jsonObject);
            }
            writer.print(jsonArray);
        } else {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("result", false);
            writer.print(jsonObject);
        }
    }

    /**
     * ���ݵ�ǰ���ﳵ��Ϣ ��������(�����û�id)
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter writer = response.getWriter();
        JSONObject json = new JSONObject();
        if (request.getParameter("userID") == null) {
            json.put("result", false);
        } else {
            String userID = request.getParameter("userID");
            List<Object[]> list = DaoFactory.getShoppingCarDaoImpl().queryMyShoppingCar(userID);
            for (Object[] object : list) {
				/*
				jsonObject.put("userID", object[0]);
				jsonObject.put("commodityID", object[1]);
				jsonObject.put("num", object[2]);
				jsonObject.put("shopManager", object[12]);
				jsonObject.put("shopID", object[4]);
				jsonObject.put("commodityName", object[5]);
				jsonObject.put("commodityPrice", object[6]);
				jsonObject.put("commodityNumber", object[7]);
				jsonObject.put("commodityIntroduce", object[8]);
				jsonObject.put("commodityImage", object[9]);
				jsonObject.put("shopName", object[11]);
				*/
                Order order = new Order();
                order.setOrderID(DaoFactory.getOrderDaoImpl().queryMaxID() + 1);
                order.setOrderDate(new Date());
                order.setOrderState(0);
                order.setUserID(userID);
                OrderCommodity orderCommodity = new OrderCommodity();
                orderCommodity.setCommodityID((Integer) object[1]);
                orderCommodity.setNumber((Integer) object[2]);
                orderCommodity.setOrderID(order.getOrderID());
                DaoFactory.getOrderDaoImpl().addOrder(order);
                DaoFactory.getOrderDaoImpl().addOrderCommodity(orderCommodity);
            }
            json.put("result", true);
        }
        writer.print(json);
    }

    /**
     * ɾ������(����id)
     * json:{"result":"true/false"}
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter writer = response.getWriter();
        JSONObject json = new JSONObject();
        if (request.getParameter("orderID") == null) {
            json.put("result", false);
        } else {
            int orderID = Integer.parseInt(request.getParameter("orderID"));
            DaoFactory.getOrderDaoImpl().delete(orderID);
            json.put("result", true);
        }
        writer.print(json);
    }

    /**
     * �޸Ķ���(����id,Ҫ�ĳɵĶ���״̬orderState[0δ����][1�Ѹ���][2�ѷ���][3��ȷ���ջ�][4�����˻�][5���������˻�][6���˻�])
     * json:{"result":"true/false"}
     * ���޸ģ�����״̬
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter writer = response.getWriter();
        JSONObject json = new JSONObject();
        if (request.getParameter("orderID") == null || request.getParameter("orderState") == null) {
            json.put("result", false);
        } else {
            int orderID = Integer.parseInt(request.getParameter("orderID"));
            int orderState = Integer.parseInt(request.getParameter("orderState"));
            DaoFactory.getOrderDaoImpl().update(orderID, orderState);
            json.put("result", true);
        }
        writer.print(json);
    }
}
