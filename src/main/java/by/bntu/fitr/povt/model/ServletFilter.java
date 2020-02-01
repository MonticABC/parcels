package by.bntu.fitr.povt.model;

import by.bntu.fitr.povt.dao.entities.User;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class ServletFilter implements Filter {
    private static final Logger LOG = Logger.getLogger(ServletFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse res = (HttpServletResponse) servletResponse;
        String uri = req.getRequestURI();
        HttpSession session = req.getSession();
        if(uri.contains("/administrator")) {
            if(session!=null){
                User user =(User)session.getAttribute("user");
                if(user!=null) {
                    if(user.getRole()!=2) {
                        LOG.info("user("+user.getId()+") try  go to admin page");
                        req.getRequestDispatcher("/WEB-INF/views/security.jsp").forward(req, res);
                    }
                } else {
                    LOG.info("not autorization user try  go to admin page");
                    req.getRequestDispatcher("/WEB-INF/views/security.jsp").forward(req, res);
                }
            }
        }
        else if(uri.equals("/final-project/parcel") || uri.equals("/final-project/parcel/Parcels")) {
            filterChain.doFilter(req, res);
            return;
        }
        else if(uri.contains("/parcel") || uri.contains("/order")) {
            if(session!=null){
                User user =(User)session.getAttribute("user");
                if(user!=null) {
                    if(uri.contains("/ordersinformation"))
                    {
                        filterChain.doFilter(req, res);
                        return;
                    }
                    if(uri.equals("/parcelupdatePassword") && user.getRole()==2)
                    {
                        filterChain.doFilter(req, res);
                        return;
                    }
                    if(user.getRole()!=1) {
                        req.getRequestDispatcher("/WEB-INF/views/security.jsp").forward(req, res);
                        return;
                    }
                } else {
                    LOG.info("not autorization user try  go to user pages");
                    req.getRequestDispatcher("/WEB-INF/views/security.jsp").forward(req, res);
                    return;
                }
            }
        }
        filterChain.doFilter(req, res);
    }

    @Override
    public void destroy() {
    }
}
