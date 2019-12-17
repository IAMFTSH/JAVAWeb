package henu.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import henu.factory.DaoFactory;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


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
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json; charset=utf-8");
		String method = request.getParameter("method");
		if ("queryByUserID".equals(method)) {
			queryByUserID(request, response);
		} else if ("addCommodity".equals(method)) {
			addCommodity(request, response);
		} else if ("deleteCommodity".equals(method)) {
			deleteCommodity(request, response);
		}
	}

	/**
	 * �����û�id��ѯ���ﳵ��Ϣ(����uesrID��δ�������json{"result":"false"})
	 * json����[{
	 * "userID":"[�û�id]",
	 * "commodityID":"[��Ʒid]",
	 * "num":"[���ﳵ�е�����]",
	 * "shopManager":"[����id]",
	 * "shopID":"[�̵�id]",
	 * "commodityName":"[��Ʒ����]",
	 * "commodityPrice":"[��Ʒ����]",
	 * "commodityNumber":"[��Ʒʣ��]",
	 * "commodityIntroduce":"[��Ʒ���]",
	 * "commodityImage":"[��ƷͼƬ·��]",
	 * "shopName":"[�̵�����]",
	 * }]
	 *
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
	 * �����Ʒ��������ﳵ������ͬ��Ʒ��������ӣ�������Ʒid����Ʒ�������û�id��δ�����������json:{"result":"false"}��
	 * ��ӳɹ����json{"result":"true"}
	 *
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
			String userID = request.getParameter("userID");//��ȡ�û�id
			int commodityID = Integer.parseInt(request.getParameter("commodityID"));//��ȡ��Ʒid
			int num = Integer.parseInt(request.getParameter("num"));//��ȡ����
			DaoFactory.getShoppingCarDaoImpl().addCommodity(userID, commodityID, num);
			json.put("result", true);
		}
		writer.print(json);
	}

	/**
	 * ���ﳵɾ����Ʒ(���û�id��չ��ﳵ������Ʒidֻɾ������Ʒ��ǰ������)
	 * json{"result":"true/false"}
	 *
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void deleteCommodity(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter writer = response.getWriter();
		JSONObject json = new JSONObject();
		if (request.getParameter("commodityID") == null && request.getParameter("userID") == null) {
			json.put("result", false);
		} else {
			if (request.getParameter("userID") != null) {
				String userID = request.getParameter("userID");//��ȡ�û�id
				DaoFactory.getShoppingCarDaoImpl().deleteByUserID(userID);
				json.put("result", true);
			} else {
				int commodityID = Integer.parseInt(request.getParameter("commodityID"));//��ȡ��Ʒid
				DaoFactory.getShoppingCarDaoImpl().deleteByCommodityID(commodityID);
				json.put("result", true);
			}
		}
		writer.print(json);
	}


}
