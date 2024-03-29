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
 * Servlet implementation class UsersUpdateServlet
 */
@WebServlet("/users/update")
public class UsersUpdateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UsersUpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        response.getWriter().append("Served at: ").append(request.getContextPath());
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
           String _token = request.getParameter("_token");
            if(_token != null && _token.equals(request.getSession().getId())) {
                EntityManager em = DBUtil.createEntityManager();

                Users u = em.find(Users.class, (Integer)(request.getSession().getAttribute("users_id")));

                // 現在の値と異なるメールアドレスが入力されていたら
                // 重複チェックを行う指定をする
                Boolean emailDuplicateCheckFlag = true;
                if(u.getEmail().equals(request.getParameter("email"))) {
                    emailDuplicateCheckFlag = false;
                } else {
                    u.setEmail(request.getParameter("email"));
                }

                // パスワード欄に入力があったら
                // パスワードの入力値チェックを行う指定をする
                Boolean passwordCheckFlag = true;
                String password = request.getParameter("password");
                if(password == null || password.equals("")) {
                    passwordCheckFlag = false;
                } else {
                    u.setPassword(
                            EncryptUtil.getPasswordEncrypt(
                                    password,
                                    (String)this.getServletContext().getAttribute("pepper")
                                    )
                            );
                }

                u.setUsername(request.getParameter("username"));
                u.setAdmin_flag(0);
                u.setDelete_flag(0);

                List<String> errors = UsersValidator.validate(u, emailDuplicateCheckFlag, passwordCheckFlag);
                if(errors.size() > 0) {
                    em.close();

                    request.setAttribute("_token", request.getSession().getId());
                    request.setAttribute("users", u);
                    request.setAttribute("errors", errors);

                    RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/users/edit.jsp");
                    rd.forward(request, response);
                } else {
                    em.getTransaction().begin();
                    em.getTransaction().commit();
                    em.close();

                    request.getSession().setAttribute("flush", "更新が完了しました。");

                    request.getSession().removeAttribute("users_id");

                    response.sendRedirect(request.getContextPath() + "/users/index");
                }
            }
        }

    }

