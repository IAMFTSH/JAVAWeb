package henu.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Base64;

/**
 * @author FTSH
 * @date 2019/12/18 - 21:30
 */
@WebServlet("/imgServlet")
public class imgServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        String imgBase = request.getParameter("imgBase");
        String imgdata = imgBase.substring(23);
        System.out.println(imgdata);
        System.out.println(imgBase);
        try {
            Base64.Decoder decoder = Base64.getMimeDecoder();
            byte[] b = decoder.decode(imgdata);
            // 处理数据
            for (int i = 0; i < b.length; ++i) {
                if (b[i] < 0) {
                    b[i] += 256;
                }
            }
            OutputStream out = new FileOutputStream("A://aa.jpg");
            out.write(b);
            out.flush();
            out.close();
        } catch (IOException e) {
        }
    }
}
