package filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import models.Users;

/**
 * Servlet Filter implementation class LoginFilter
 */
@WebFilter(urlPatterns={"/users/*","/reports/*"})
public class LoginFilter implements Filter {

    /**
     * Default constructor.
     */
    public LoginFilter() {
    }

    /**
     * @see Filter#destroy()
     */
    public void destroy() {
    }

    /**
     * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
     */
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        String context_path = ((HttpServletRequest)request).getContextPath();
        String servlet_path = ((HttpServletRequest)request).getServletPath();

        // CSSフォルダ内は認証処理から除外する
        if(!servlet_path.matches("/css.*")) {
            HttpSession session = ((HttpServletRequest)request).getSession();

         // セッションスコープに保存されたログインユーザ情報を取得
            Users u = (Users)session.getAttribute("login_users");


            // ログイン画面以外について
            // ログアウトしている状態であれば
            // ログイン画面にリダイレクト
                if(!servlet_path.equals("/login")) {
                    if(!servlet_path.equals("/new")) {
                        if(!servlet_path.equals("/mypage/*")) {
                if(u == null) {
                    ((HttpServletResponse)response).sendRedirect(context_path + "/login");
                    return;
                }


            }
        }
        }}
        chain.doFilter(request, response);

                        }

    /**
     * @see Filter#init(FilterConfig)
     */
    public void init(FilterConfig fConfig) throws ServletException {
    }

}
