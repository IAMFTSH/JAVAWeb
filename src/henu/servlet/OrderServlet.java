package henu.servlet;

import henu.bean.Order;
import henu.bean.OrderCommodity;
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
import java.util.Date;
import java.util.List;

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
	 * 所有订单及其信息
	 * json数组[{
	 * "orderID":"[订单id]",
	 * "userID":"[用户id]",
	 * "orderDate":"[订单创建日期]",
	 * "orderState":"[订单状态]",
	 * "commodityID":"[商品id]",
	 * "number":"[购买数量]",
	 * "shopID":"[商店id]",
	 * "commodityName":"[商品名称]",
	 * "commodityPrice":"[商品单价]",
	 * "commodityNumber":"[商品剩余]",
	 * "commodityIntroduce":"[商品简介]",
	 * "commodityImage":"[商品图片路径]"
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
			jsonObject.put("orderID", object[0]);//订单id
			jsonObject.put("userID", object[1]);//用户id
			jsonObject.put("orderDate", object[2]);//订单创建日期
			jsonObject.put("orderState", object[3]);//订单状态
			jsonObject.put("commodityID", object[5]);//商品id
			jsonObject.put("number", object[6]);//购买数量
			jsonObject.put("shopID", object[8]);//商店id
			jsonObject.put("commodityName", object[9]);//商品名称
			jsonObject.put("commodityPrice", object[10]);//商品单价
			jsonObject.put("commodityNumber", object[11]);//商品剩余
			jsonObject.put("commodityIntroduce", object[12]);//商品简介
			jsonObject.put("commodityImage", object[13]);//商品图片路径
			jsonArray.add(jsonObject);
		}
		writer.print(jsonArray);

	}

	/**
	 * 根据用户id查询订单及其信息（传入userID，未传入则返回json{"result":"false"}）
	 * 输出json数组[{
	 * "orderID":"[订单id]",
	 * "userID":"[用户id]",
	 * "orderDate":"[订单创建日期]",
	 * "orderState":"[订单状态]",
	 * "commodityID":"[商品id]",
	 * "number":"[购买数量]",
	 * "shopID":"[商店id]",
	 * "commodityName":"[商品名称]",
	 * "commodityPrice":"[商品单价]",
	 * "commodityNumber":"[商品剩余]",
	 * "commodityIntroduce":"[商品简介]",
	 * "commodityImage":"[商品图片路径]"
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
			userID = request.getParameter("userID");//获取用户id
			List<Object[]> list = DaoFactory.getOrderDaoImpl().queryByUser(userID);
			response.setCharacterEncoding("utf-8");
			response.setContentType("application/json; charset=utf-8");
			JSONObject jsonObject = new JSONObject();
			JSONArray jsonArray = new JSONArray();
			for (Object[] object : list) {
				jsonObject.put("orderID", object[0]);//订单id
				jsonObject.put("userID", object[1]);//用户id
				jsonObject.put("orderDate", object[2]);//订单创建日期
				jsonObject.put("orderState", object[3]);//订单状态
				jsonObject.put("commodityID", object[5]);//商品id
				jsonObject.put("number", object[6]);//购买数量
				jsonObject.put("shopID", object[8]);//商店id
				jsonObject.put("commodityName", object[9]);//商品名称
				jsonObject.put("commodityPrice", object[10]);//商品单价
				jsonObject.put("commodityNumber", object[11]);//商品剩余
				jsonObject.put("commodityIntroduce", object[12]);//商品简介
				jsonObject.put("commodityImage", object[13]);//商品图片路径
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
	 * 根据当前购物车信息 创建订单(传入用户id)
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
	 * 删除订单(订单id)
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
	 * 修改订单(订单id,要改成的订单状态orderState[0未付款][1已付款][2已发货][3已确认收货][4请求退货][5正在受理退货][6已退货])
	 * json:{"result":"true/false"}
	 * 可修改：订单状态
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
