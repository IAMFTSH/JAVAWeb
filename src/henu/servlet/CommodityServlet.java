package henu.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import henu.bean.Commodity;
import henu.factory.DaoFactory;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class CommodityServlet
 */
@WebServlet("/CommodityServlet")
public class CommodityServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public CommodityServlet() {
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
        if ("list".equals(method)) {
            list(request, response);
        } else if ("add".equals(method)) {
            add(request, response);
        } else if ("update".equals(method)) {
            update(request, response);
        } else if ("delete".equals(method)) {
            delete(request, response);
        } else if ("queryByID".equals(method)) {
            queryByID(request, response);
        }
    }

    /**
     * ��ѯ������Ʒ ���json JSONArray���� jsonObject{ "commodityID":"[��Ʒid]",
     * "commodityName":"[��Ʒ����]", "commodityNumber":"[��Ʒʣ��]",
     * "commodityImage":"[��ƷͼƬ·��]", "commodityIntroduce":"[��Ʒ���]",
     * "commodityPrice":"[��Ʒ����]", "shopID":"[�̵�id]" }
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Commodity> list = DaoFactory.getCommodityDaoImpl().findAll();
        JSONObject jsonObject = new JSONObject();
        PrintWriter writer = response.getWriter();
        JSONArray jsonArray = new JSONArray();
        for (Commodity commodity : list) {
            jsonObject.put("commodityID", commodity.getCommodityID());
            jsonObject.put("commodityName", commodity.getCommodityName());
            jsonObject.put("commodityNumber", commodity.getCommodityNumber());
            jsonObject.put("commodityImage", commodity.getCommodityImage());
            jsonObject.put("shopID", commodity.getShopID());
            jsonObject.put("commodityIntroduce", commodity.getCommodityIntroduce());
            jsonObject.put("commodityPrice", commodity.getCommodityPrice());
            jsonArray.add(jsonObject);
        }
        writer.print(jsonArray);
    }

    /**
     * �����Ʒ ���json{"result":"true"}
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Commodity commodity = new Commodity();
        commodity.setCommodityID(DaoFactory.getCommodityDaoImpl().queryMaxID() + 1);
        commodity.setCommodityImage(request.getParameter("commodityImage"));// ��ȡ��ƷͼƬ·��
        commodity.setCommodityIntroduce(request.getParameter("commodityIntroduce"));// ��ȡ��Ʒ���
        commodity.setCommodityName(request.getParameter("commodityName"));// ��ȡ��Ʒ����
        commodity.setShopID(Integer.parseInt(request.getParameter("shopID")));// ��ȡ�̵�id
        commodity.setCommodityNumber(Integer.parseInt(request.getParameter("commodityNumber")));// ��ȡ��Ʒ����
        commodity.setCommodityPrice(Integer.parseInt(request.getParameter("commodityPrice")));// ��ȡ��Ʒ����
        DaoFactory.getCommodityDaoImpl().addCommodity(commodity);
        PrintWriter writer = response.getWriter();
        JSONObject json = new JSONObject();
        json.put("result", true);
        writer.print(json);
    }

    /**
     * ɾ����Ʒ ���json:{"result":"true/false"}
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void delete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int commodityID = Integer.parseInt(request.getParameter("commodityID"));// ��ȡ��Ʒid
        int result = DaoFactory.getCommodityDaoImpl().deleteByID(commodityID);
        PrintWriter writer = response.getWriter();
        JSONObject json = new JSONObject();
        if (result == 1) {
            json.put("result", true);

        } else {
            json.put("result", false);
        }
        writer.print(json);
    }

    /**
     * ������Ʒ ���json{"result":"true"}
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void update(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int commodityID = Integer.parseInt(request.getParameter("commodityID"));// ��ȡ��Ʒid
        Commodity commodity = DaoFactory.getCommodityDaoImpl().queryByCommodityID(commodityID);
        JSONObject json = new JSONObject();
        if (request.getParameter("commodityName") != null && request.getParameter("commodityName") != "") {// ������Ʒ����
            commodity.setCommodityName(request.getParameter("commodityName"));
        }
        if (request.getParameter("commodityImage") != null && request.getParameter("commodityImage") != "") {// ������ƷͼƬ·��
            commodity.setCommodityImage(request.getParameter("commodityImage"));
        }
        if (request.getParameter("commodityIntroduce") != null && request.getParameter("commodityIntroduce") != "") {// ������Ʒ���
            commodity.setCommodityIntroduce(request.getParameter("commodityIntroduce"));
        }
        if (request.getParameter("commodityPrice") != null && request.getParameter("commodityPrice") != "") {// ������Ʒ����
            commodity.setCommodityPrice(Integer.parseInt(request.getParameter("commodityPrice")));
        }
        if (request.getParameter("commodityNumber") != null && request.getParameter("commodityNumber") != "") {// ������Ʒ����
            commodity.setCommodityNumber(Integer.parseInt(request.getParameter("commodityNumber")));
        }
        DaoFactory.getCommodityDaoImpl().update(commodity);
        PrintWriter writer = response.getWriter();
        json.put("result", true);
        writer.print(json);
    }

    /**
     * ������Ʒid��ѯ��Ʒ jsonObject{ "commodityID":"[��Ʒid]", "commodityName":"[��Ʒ����]",
     * "commodityNumber":"[��Ʒʣ��]", "commodityImage":"[��ƷͼƬ·��]",
     * "commodityIntroduce":"[��Ʒ���]", "commodityPrice":"[��Ʒ����]", "shopID":"[�̵�id]" }
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void queryByID(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int commodityID = 0;
        if (request.getParameter("commodityID") != null) {
            commodityID = Integer.parseInt(request.getParameter("commodityID"));
        }
        Commodity commodity = DaoFactory.getCommodityDaoImpl().queryByCommodityID(commodityID);
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json; charset=utf-8");
        JSONObject jsonObject = new JSONObject();
        PrintWriter writer = response.getWriter();
        jsonObject.put("commodityID", commodity.getCommodityID());
        jsonObject.put("commodityName", commodity.getCommodityName());
        jsonObject.put("commodityNumber", commodity.getCommodityNumber());
        jsonObject.put("commodityImage", commodity.getCommodityImage());
        jsonObject.put("shopID", commodity.getShopID());
        jsonObject.put("commodityIntroduce", commodity.getCommodityIntroduce());
        jsonObject.put("commodityPrice", commodity.getCommodityPrice());
        writer.print(jsonObject);
    }
}
