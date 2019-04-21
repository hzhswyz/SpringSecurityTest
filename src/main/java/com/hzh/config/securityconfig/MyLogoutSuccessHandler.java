package com.hzh.config.securityconfig;

import org.apache.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class MyLogoutSuccessHandler implements LogoutSuccessHandler {

    Logger logger = Logger.getLogger(this.getClass());
    private SimpleUrlLogoutSuccessHandler simpleUrlLogoutSuccessHandler = new SimpleUrlLogoutSuccessHandler();
    private HttpStatusReturningLogoutSuccessHandler httpStatusReturningLogoutSuccessHandler = new HttpStatusReturningLogoutSuccessHandler();

    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        String mark = request.getParameter("restful");
        if (mark != null && mark.equals("true")){
            logger.debug("restful API");
            response.getWriter().print("{\"code\":1}");
            response.setHeader("Content-Type","application/json");
            httpStatusReturningLogoutSuccessHandler.onLogoutSuccess(request,response,authentication);
        }
        else {
            logger.debug("非restful API");
            simpleUrlLogoutSuccessHandler.onLogoutSuccess(request,response,authentication);
        }
    }
}
