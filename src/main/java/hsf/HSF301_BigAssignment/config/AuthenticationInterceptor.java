package hsf.HSF301_BigAssignment.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;

@Configuration
public class AuthenticationInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestURI = request.getRequestURI();
        HttpSession session = request.getSession();

        if (requestURI.startsWith("/admin")) {
            if (session.getAttribute("admin") == null) {
                response.sendRedirect("/login");
                return false;
            }
        } else if (requestURI.startsWith("/customer")) {
            if (session.getAttribute("customer") == null) {
                response.sendRedirect("/login");
                return false;
            }
        }

        return true;
    }
}
