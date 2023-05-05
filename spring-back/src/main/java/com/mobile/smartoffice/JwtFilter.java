package com.mobile.smartoffice;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;
import org.springframework.util.StringUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class JwtFilter extends BasicHttpAuthenticationFilter {
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String authorization = httpServletRequest.getHeader("Authorization");

        String requestURI = httpServletRequest.getRequestURI();
        if (requestURI.startsWith("/api/user/")) {   // 忽略/user/路径下的鉴权
            return true;
        }
        if (StringUtils.isEmpty(authorization)) {
            return false;
        }

        String token = StringUtils.replace(authorization, "Bearer ", "");
        String username = JWT.decode(token).getClaim("username").asString();
        String password = JWT.decode(token).getClaim("password").asString();
        return !StringUtils.isEmpty(username) && !StringUtils.isEmpty(password);
    }
}
