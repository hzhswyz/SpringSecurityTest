package com.hzh.config.securityconfig;

import org.apache.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class MyLogoutHandler implements LogoutHandler {
    Logger logger = Logger.getLogger(this.getClass());
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        logger.debug("用户退出登陆"+authentication.getPrincipal());
    }
}
