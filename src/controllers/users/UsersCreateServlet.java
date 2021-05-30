package controllers.users;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Users;
import models.validators.UsersValidator;
import utils.DBUtil;
import utils.EncryptUtil;

/**
 * Servlet implementation class UsersCreteServlet
 */
@WebServlet("/create")
public class UsersCreateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UsersCreateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        response.getWriter().append("Served at: ").append(request.getContextPath());
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
           String _token = request.getParameter("_token");
            if(_token != null && _token.equals(request.getSession().getId())) {
                EntityManager em = DBUtil.createEntityManager();

                Users u = new Users();

                u.setUsername(request.getParameter("username"));
                u.setEmail(request.getParameter("email"));
                u.setPassword(
                    EncryptUtil.getPasswordEncrypt(
                        request.getParameter("password"),
                            (String)this.getServletContext().getAttribute("pepper")
                        )
                    );
                u.setAdmin_flag(0);

                u.setDelete_flag(0);

                List<String> errors = UsersValidator.validate(u, true, true);
                if(errors.size() > 0) {
                    em.close();

                    request.setAttribute("_token", request.getSession().getId());
                    request.setAttribute("users", u);
                    request.setAttribute("errors", errors);

                    RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/users/new.jsp");
                    rd.forward(request, response);
                } else {
                    em.getTransaction().begin();
                    em.persist(u);
                    em.getTransaction().commit();
                    request.getSession().setAttribute("flush", "登録が完了しました。");
                    em.close();

                    response.sendRedirect(request.getContextPath() + "/mypage/index");
                }
            }
        }

}
