package common.util;

import user.entitiy.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

// 该过滤器拦截所有的请求
@WebFilter("/ReserveSys_war_exploded/*")
public class LoginFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest =(HttpServletRequest) servletRequest;
        String uri = httpServletRequest.getRequestURI();
        HttpSession session =httpServletRequest.getSession();
        User user = (User) session.getAttribute("User");
        System.out.println(uri);
        //判断请求页面是否为登陆界面
        if (uri.contains("/index.jsp") || uri.contains("/map_center_servlet_action")|| uri.contains("/assets/")||uri.contains("/lib/")|| uri.contains("/frame/")|| uri.contains("/main/")||uri.contains("/user_center_servlet_action")){
            filterChain.doFilter(servletRequest,servletResponse);
        }else {
            //判断用户是否登陆,如果登陆就继续执行
            if (user!=null){
                //判断是否为商家或是管理员才能访问界面
                if (uri.contains("/guesthouse/")&&user.getRole_name().equals("user")){
                    //筛掉普通用户通过网址访问隐私资料
                    servletRequest.setAttribute("error","权限不足,无法访问!");
                    servletRequest.getRequestDispatcher("../../home/main/login.jsp").forward(servletRequest,servletResponse);
                }
                filterChain.doFilter(servletRequest,servletResponse);
            }else {
                //若登陆超时,则跳转登陆界面
                servletRequest.setAttribute("error","尚未登入或登入超时,请重新登录!");
                servletRequest.getRequestDispatcher("../../home/main/login.jsp").forward(servletRequest,servletResponse);
            }
        }

    }

    @Override
    public void destroy() {

    }

    public  LoginFilter(){

    }
}
