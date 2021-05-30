package controllers.reports;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import models.Report;
import models.Users;
import utils.DBUtil;

/**
 * Servlet implementation class ReportsCreateServlet
 */
@WebServlet("/reports/create")
@MultipartConfig(location="C:\\tmp", maxFileSize=15606000)
public class ReportsCreateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReportsCreateServlet() {
        super();
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Part imgPart = request.getPart("img_file");
        String _token = request.getParameter("_token");
        String title = request.getParameter("title");


        if (_token != null && _token.equals(request.getSession().getId())) {
            EntityManager em = DBUtil.createEntityManager();


            try {
                Report r = new Report();


                //ユーザー情報セット
                r.setUsers((Users)request.getSession().getAttribute("login_users"));
                // タイトルをセット
                r.setTitle(title);

                r.setContent(request.getParameter("content"));
                r.setAddress(request.getParameter("address"));
                r.setReport_time(request.getParameter("report_time"));
                r.setPrefecture(request.getParameter("prefecture"));



                // byte[]に変換
                byte[] byteArray = getByteArray(imgPart.getInputStream());
                String base64String = Base64.getEncoder().encodeToString(byteArray);
                // 画像をセット
                r.setImage(base64String);

                em.getTransaction().begin();
                em.persist(r);
                em.getTransaction().commit();

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                em.close();
            }


        }
        // 一覧画面にリダイレクト
        response.sendRedirect(request.getContextPath() + "/mypage/index");




        }
        /**
        *
        * 画像データをbyte[]に変換
        *
        * @param is
        * @return
        * @throws Exception
        */
       private static byte[] getByteArray(InputStream is) throws Exception {
           ByteArrayOutputStream b = new ByteArrayOutputStream();
           BufferedOutputStream os = new BufferedOutputStream(b);
           while (true) {
               int i = is.read();
               if (i == -1)
                   break;
               os.write(i);
           }
           os.flush();
           os.close();
           return b.toByteArray();
       }



    }

