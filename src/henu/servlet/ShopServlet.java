package henu.servlet;

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
 * Servlet implementation class ShopServlet
 */
@WebServlet("/ShopServlet")
public class ShopServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ShopServlet() {
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
		if ("list".equals(method)) {
			list(request, response);
		} else if ("queryByID".equals(method)) {
			queryByID(request, response);
		} else if ("add".equals(method)) {
			add(request, response);
		} else if ("update".equals(method)) {
			update(request, response);
		} else if ("delete".equals(method)) {
			delete(request, response);
        }

    }

    /**
     * 查询所有商店 输出json:[{ "shopID":"[商店id]", "shopManager":"[店主id]",
     * "shopName":"[商店名称]" }]
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
	 */
	protected void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Shop> list = DaoFactory.getShopDaoImpl().findAll();
		JSONObject jsonObject = new JSONObject();
		PrintWriter writer = response.getWriter();
		JSONArray jsonArray = new JSONArray();
		for (Shop shop : list) {
			jsonObject.put("shopID", shop.getShopID());// 商店id
			jsonObject.put("shopManager", shop.getShopManager());// 店主id
			jsonObject.put("shopName", shop.getShopName());// 商店名称
			jsonArray.add(jsonObject);
		}
		writer.print(jsonArray);
    }

    /**
     * 根据店铺id或者店主id查询店铺（未传入userID和shopID输出json:{"result":"false"}） 输出json:[{
     * "shopID":"[商店id]", "shopName":"[商店名称]", "shopManager":"[店主id]",
     * "commodityID":"[商品id]", "commodityName":"[商品名称]", "commodityPrice":"[商品单价]",
     * "commodityNumber":"[商品剩余]", "commodityIntroduce":"[商品简介]",
     * "commodityImage":"[商品图片路径]" }]
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
	 */
	protected void queryByID(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
        PrintWriter writer = response.getWriter();
        JSONObject jsonObject = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        if (request.getParameter("userID") != "") {// 传入店主id
            String userID = request.getParameter("userID");
            List<Object[]> list = DaoFactory.getShopDaoImpl().queryByShopManager(userID);
            for (Object[] object : list) {
                jsonObject.put("shopID", object[0]);
                jsonObject.put("shopName", object[1]);
                jsonObject.put("shopManager", object[2]);
                jsonObject.put("commodityID", object[3]);
                jsonObject.put("commodityName", object[5]);
                jsonObject.put("commodityPrice", object[6]);
                jsonObject.put("commodityNumber", object[7]);
                jsonObject.put("commodityIntroduce", object[8]);
                jsonObject.put("commodityImage", object[9]);
                jsonArray.add(jsonObject);
            }
            writer.print(jsonArray);
        } else if (request.getParameter("shopID") != "") {// 传入商店id
            int shopID = Integer.parseInt(request.getParameter("shopID"));
            List<Object[]> list = DaoFactory.getShopDaoImpl().queryByShopID(shopID);
            for (Object[] object : list) {
                jsonObject.put("shopID", object[0]);
                jsonObject.put("shopName", object[1]);
                jsonObject.put("shopManager", object[2]);
                jsonObject.put("commodityID", object[3]);
                jsonObject.put("commodityName", object[5]);
                jsonObject.put("commodityPrice", object[6]);
                jsonObject.put("commodityNumber", object[7]);
				jsonObject.put("commodityIntroduce", object[8]);
				jsonObject.put("commodityImage", object[9]);
				jsonArray.add(jsonObject);
			}
			writer.print(jsonArray);
		} else {
			jsonObject.put("result", false);
			writer.print(jsonObject);
        }
    }

    /**
     * 添加商店(传入用户id,未传入用户id输出json{"result":"false"})
     * 添加成功/失败输出json{"result":"true/false"}
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
	 */
	protected void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter writer = response.getWriter();
        JSONObject json = new JSONObject();
        if (request.getParameter("userID") == "") {
            json.put("result", false);
        } else {
            Shop shop = new Shop();
            shop.setShopID(DaoFactory.getShopDaoImpl().queryMaxID() + 1);
            shop.setShopManager(request.getParameter("userID"));// 获取用户id
            if (request.getParameter("shopName") != "") {
                shop.setShopName(request.getParameter("shopName"));
            } else {
                shop.setShopName("未命名商店");
            }
            if (DaoFactory.getShopDaoImpl().addShop(shop) != 0) {
                json.put("result", true);
                json.put("shopID", shop.getShopID());
                json.put("shopManager", shop.getShopManager());
                json.put("shopName", shop.getShopName());
            } else {
                json.put("result", false);
			}

		}
		writer.print(json);
    }

    /**
     * 修改商店名称(传入用户id或者商店id) json{"result":"true/false"}
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
	 */
	protected void update(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
        PrintWriter writer = response.getWriter();
        JSONObject json = new JSONObject();
        if ((request.getParameter("userID") == "" && request.getParameter("shopID") == "")
                || request.getParameter("shopName") == "") {
            json.put("result", false);
        } else {
            if (request.getParameter("userID") != "") {
                String userID = request.getParameter("userID");
                Shop shop = DaoFactory.getShopDaoImpl().queryByShopmanager(userID);

                shop.setShopName(request.getParameter("shopName"));
                if (DaoFactory.getShopDaoImpl().update(shop) != 0) {
                    json.put("result", true);
                    json.put("shopID", shop.getShopID());
                    json.put("shopName", shop.getShopName());
                    json.put("shopManager", shop.getShopManager());
                } else {
                    json.put("result", false);
                }

            } else {
                int shopID = Integer.parseInt(request.getParameter("shopID"));
                Shop shop = DaoFactory.getShopDaoImpl().queryByShopId(shopID);
                shop.setShopName(request.getParameter("shopName"));
                if (DaoFactory.getShopDaoImpl().update(shop) != 0) {
                    json.put("result", true);
                    json.put("shopID", shop.getShopID());
                    json.put("shopName", shop.getShopName());
                    json.put("shopManager", shop.getShopManager());
                } else {
                    json.put("result", false);
				}
			}
		}
		writer.print(json);
    }

    /**
     * 删除商店(商店id) json{"result":"true/false"}
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
        if (request.getParameter("shopID") == "") {
            json.put("result", false);
        } else {
            int shopID = Integer.parseInt(request.getParameter("shopID"));// 获取商店id
            if (DaoFactory.getShopDaoImpl().delete(shopID) != 0) {
                json.put("result", true);
            } else {
                json.put("result", false);
            }
        }
        writer.print(json);
	}
}
