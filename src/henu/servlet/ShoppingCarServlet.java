package henu.servlet;

import henu.factory.DaoFactory;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * Servlet implementation class ShoppingCarServlet
 */
@WebServlet("/ShoppingCarServlet")
public class ShoppingCarServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ShoppingCarServlet() {
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
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json; charset=utf-8");
        String method = request.getParameter("method");
        if ("queryByUserID".equals(method)) {
            queryByUserID(request, response);
        } else if ("addCommodity".equals(method)) {
            addCommodity(request, response);
        } else if ("deleteCommodity".equals(method)) {
            deleteCommodity(request, response);
        } else if ("updateNum".equals(method)) {
            updateNum(request,response);
		}
	}

	/**
	 * 根据用户id查询购物车信息(传入uesrID，未传入输出json{"result":"false"})
	 * json数组[{
	 * "userID":"[用户id]",
	 * "commodityID":"[商品id]",
	 * "num":"[购物车中的数量]",
	 * "shopManager":"[店主id]",
	 * "shopID":"[商店id]",
	 * "commodityName":"[商品名称]",
	 * "commodityPrice":"[商品单价]",
	 * "commodityNumber":"[商品剩余]",
	 * "commodityIntroduce":"[商品简介]",
	 * "commodityImage":"[商品图片路径]",
	 * "shopName":"[商店名称]",
	 * }]
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void queryByUserID(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
        PrintWriter writer = response.getWriter();
        if (request.getParameter("userID") != null) {
            String userID = request.getParameter("userID");
            JSONObject jsonObject = new JSONObject();
            JSONArray jsonArray = new JSONArray();
            List<Object[]> list = DaoFactory.getShoppingCarDaoImpl().queryMyShoppingCar(userID);
            for (Object[] object : list) {
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

                jsonArray.add(jsonObject);
            }
            writer.print(jsonArray);
        } else {
            JSONObject json = new JSONObject();
            json.put("result", false);
            writer.print(json);
		}
	}
	
	/**
	 * 添加商品，如果购物车中有相同商品则数量相加（传入商品id，商品数量，用户id，未传入数据输出json:{"result":"false"}）
	 * 添加成功输出json{"result":"true"}
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void addCommodity(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
        PrintWriter writer = response.getWriter();
        JSONObject json = new JSONObject();
        if (request.getParameter("commodityID") == null || request.getParameter("num") == null || request.getParameter("userID") == null) {
            json.put("result", false);
        } else {
            String userID = request.getParameter("userID");//获取用户id
            int commodityID = Integer.parseInt(request.getParameter("commodityID"));//获取商品id
            int num = Integer.parseInt(request.getParameter("num"));//获取数量
            if (DaoFactory.getShoppingCarDaoImpl().addCommodity(userID, commodityID, num) != 0) {
                json.put("result", true);
            } else {
                json.put("result", false);
			}
		}
		writer.print(json);
    }

    /**
     * 购物车删除商品(传用户id清空购物车，传用户id+商品id只删除该商品)
     * json{"result":"true/false"}
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
	 */
	protected void deleteCommodity(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
        PrintWriter writer = response.getWriter();
        JSONObject json = new JSONObject();
        if (request.getParameter("userID") == "") {
            json.put("result", false);
        } else {
            String userID = request.getParameter("userID");//获取用户id
            if (request.getParameter("commodityID") == "") {//清空购物车
                if (DaoFactory.getShoppingCarDaoImpl().deleteByUserID(userID) != 0)
                    json.put("result", true);
                else
                    json.put("result", false);
            } else {//删除购物车中某商品
                int commodityID = Integer.parseInt(request.getParameter("commodityID"));//获取商品id
                if (DaoFactory.getShoppingCarDaoImpl().deleteByUserIDCommodityID(userID, commodityID) != 0)
                    json.put("result", true);
                else
                    json.put("result", false);
            }
        }
        writer.print(json);
    }

    /**
     * 修改购物车中商品数量(userID, commodityID, updateNum, updateType)
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void updateNum(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter writer = response.getWriter();
        JSONObject json = new JSONObject();
        if (request.getParameter("userID") == "" || request.getParameter("commodityID") == "" || request.getParameter("updateNum") == "" || request.getParameter("updateType") == "") {
            json.put("result", false);
        } else {
            String userID = request.getParameter("userID");//获取用户id
            int commodityID = Integer.parseInt(request.getParameter("commodityID"));//获取商品id
            String updateType = request.getParameter("updateType");//获取修改类型
            int updateNum = Integer.parseInt(request.getParameter("updateNum"));//获取修改数量
            if ("add".equals(updateType)) {//添加
                if (DaoFactory.getShoppingCarDaoImpl().add(userID, commodityID, updateNum) != 0) {
                    json.put("result", true);
                } else {
                    json.put("result", false);
                }
            } else if ("reduce".equals(updateType)) {//减少
                if (DaoFactory.getShoppingCarDaoImpl().reduce(userID, commodityID, updateNum) != 0) {
                    json.put("result", true);
                } else {
                    json.put("result", false);
                }
            } else if ("updateTo".equals(updateType)) {//修改到
                if (DaoFactory.getShoppingCarDaoImpl().updateNum(userID, commodityID, updateNum) != 0) {
                    json.put("result", true);
                } else {
                    json.put("result", false);
                }
            } else {
                json.put("result", false);
            }
        }
        writer.print(json);
	}}
