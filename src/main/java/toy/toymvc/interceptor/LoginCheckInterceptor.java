package toy.toymvc.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import toy.toymvc.controller.WebController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginCheckInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute(WebController.LOGIN_MEMBER) == null) {
            response.sendRedirect("/login?redirectURI=" + request.getRequestURI());
            return false;
        }
        return true;
    }
}
