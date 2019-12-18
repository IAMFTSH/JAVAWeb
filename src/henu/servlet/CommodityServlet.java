package henu.servlet;

import henu.bean.Commodity;
import henu.bean.Shop;
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
	 * 查询所有商品 输出json JSONArray数组 jsonObject{ "commodityID":"[商品id]",
	 * "commodityName":"[商品名称]", "commodityNumber":"[商品剩余]",
	 * "commodityImage":"[商品图片路径]", "commodityIntroduce":"[商品简介]",
	 * "commodityPrice":"[商品单价]", "shopID":"[商店id]" }
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
			Shop shop = DaoFactory.getShopDaoImpl().queryByShopId(commodity.getShopID());
			jsonObject.put("shopName", shop.getShopName());
			jsonObject.put("shopManager", shop.getShopManager());
			jsonObject.put("commodityIntroduce", commodity.getCommodityIntroduce());
			jsonObject.put("commodityPrice", commodity.getCommodityPrice());
			jsonArray.add(jsonObject);
		}
		writer.print(jsonArray);
	}

	/**
	 * 添加商品 输出json{"result":"true"}
	 *
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		JSONObject json = new JSONObject();
		Commodity commodity = new Commodity();
		commodity.setCommodityID(DaoFactory.getCommodityDaoImpl().queryMaxID() + 1);
		commodity.setCommodityImage(request.getParameter("commodityImage"));// 获取商品图片路径
		commodity.setCommodityIntroduce(request.getParameter("commodityIntroduce"));// 获取商品简介
		commodity.setCommodityName(request.getParameter("commodityName"));// 获取商品名称
		commodity.setShopID(Integer.parseInt(request.getParameter("shopID")));// 获取商店id
		commodity.setCommodityNumber(Integer.parseInt(request.getParameter("commodityNumber")));// 获取商品数量
		commodity.setCommodityPrice(Integer.parseInt(request.getParameter("commodityPrice")));// 获取商品单价
		if (DaoFactory.getCommodityDaoImpl().addCommodity(commodity) != 1) {
			json.put("result", true);
		} else {
			json.put("result", false);
		}
		PrintWriter writer = response.getWriter();
		writer.print(json);
	}

	/**
	 * 删除商品 输出json:{"result":"true/false"}
	 *
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void delete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int commodityID = Integer.parseInt(request.getParameter("commodityID"));// 获取商品id
		int result = DaoFactory.getCommodityDaoImpl().deleteByID(commodityID);
		PrintWriter writer = response.getWriter();
		JSONObject json = new JSONObject();
		if (result != 0) {
			json.put("result", true);
		} else {
			json.put("result", false);
		}
		writer.print(json);
	}

	/**
	 * 更新商品 输出json{"result":"true"}
	 *
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void update(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		JSONObject json = new JSONObject();
		int commodityID = Integer.parseInt(request.getParameter("commodityID"));// 获取商品id
		if (DaoFactory.getCommodityDaoImpl().queryByCommodityID(commodityID) != null) {
			Commodity commodity = DaoFactory.getCommodityDaoImpl().queryByCommodityID(commodityID);
			if (request.getParameter("commodityName") != null && request.getParameter("commodityName") != "") {// 更新商品名称
				commodity.setCommodityName(request.getParameter("commodityName"));
			}
			if (request.getParameter("commodityImage") != null && request.getParameter("commodityImage") != "") {// 更新商品图片路径
				commodity.setCommodityImage(request.getParameter("commodityImage"));
			}
			if (request.getParameter("commodityIntroduce") != null
					&& request.getParameter("commodityIntroduce") != "") {// 更新商品简介
				commodity.setCommodityIntroduce(request.getParameter("commodityIntroduce"));
			}
			if (request.getParameter("commodityPrice") != null && request.getParameter("commodityPrice") != "") {// 更新商品单价
				commodity.setCommodityPrice(Integer.parseInt(request.getParameter("commodityPrice")));
			}
			if (request.getParameter("commodityNumber") != null && request.getParameter("commodityNumber") != "") {// 更新商品数量
				commodity.setCommodityNumber(Integer.parseInt(request.getParameter("commodityNumber")));
			}
			if (DaoFactory.getCommodityDaoImpl().update(commodity) != 0) {
				json.put("result", true);
			} else {
				json.put("result", false);
			}
		} else {
			json.put("result", false);
		}
		PrintWriter writer = response.getWriter();
		writer.print(json);
	}

	/**
	 * 根据商品id查询商品 jsonObject{ "commodityID":"[商品id]", "commodityName":"[商品名称]",
	 * "commodityNumber":"[商品剩余]", "commodityImage":"[商品图片路径]",
	 * "commodityIntroduce":"[商品简介]", "commodityPrice":"[商品单价]", "shopID":"[商店id]" }
	 *
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void queryByID(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int commodityID = 0;
		JSONObject jsonObject = new JSONObject();
		if (request.getParameter("commodityID") != "") {
			commodityID = Integer.parseInt(request.getParameter("commodityID"));
			if (DaoFactory.getCommodityDaoImpl().queryByCommodityID(commodityID) != null) {
				Commodity commodity = DaoFactory.getCommodityDaoImpl().queryByCommodityID(commodityID);
				jsonObject.put("commodityID", commodity.getCommodityID());
				jsonObject.put("commodityName", commodity.getCommodityName());
				jsonObject.put("commodityNumber", commodity.getCommodityNumber());
				jsonObject.put("commodityImage", commodity.getCommodityImage());
				jsonObject.put("shopID", commodity.getShopID());
				jsonObject.put("commodityIntroduce", commodity.getCommodityIntroduce());
				jsonObject.put("commodityPrice", commodity.getCommodityPrice());
				jsonObject.put("result", true);
			} else {
				jsonObject.put("result", false);
			}
		} else {
			jsonObject.put("result", false);
		}

		PrintWriter writer = response.getWriter();
		writer.print(jsonObject);
	}
}
