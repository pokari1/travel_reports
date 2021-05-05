package controllers.users;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Users;
import utils.DBUtil;

/**
 * Servlet implementation class NewServlet
 */
@WebServlet("/users/new")
public class NewServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public NewServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        EntityManager em = DBUtil.createEntityManager();
        Users u = new Users();

        String username ="test";
        u.setUsername(username);

        String email ="test";
        u.setEmail(email);

        String password ="test";
        u.setPassword(password);

        int admin_flag =1;
        u.setAdmin_flag(admin_flag);

        int delete_flag =0;
        u.setDelete_flag(delete_flag);

        em.getTransaction().begin();
        em.persist(u);
        em.getTransaction().commit();

        // 自動採番されたIDの値を表示
        response.getWriter().append(Integer.valueOf(u.getId()).toString());

        em.close();
     }

}
