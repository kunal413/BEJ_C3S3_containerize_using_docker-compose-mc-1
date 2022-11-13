package com.niit.athometicDemo.JwtFilter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtFilter extends GenericFilterBean {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
        ServletOutputStream pw = httpServletResponse.getOutputStream();
        String authHeader = httpServletRequest.getHeader("authorization");
        if (authHeader == null||!authHeader.startsWith("Bearer")){
            httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            pw.println("Missing or invalid Token");
            pw.close();
        }else {
            String jwtToken = authHeader.substring(7);

            String token = authHeader.substring(7); // removes 'Bearer ' from token value
            Claims claims = Jwts.parser().setSigningKey("mykey").parseClaimsJws(token).getBody();
            System.out.println("\nclaims : " + claims);

            httpServletRequest.setAttribute("claims", claims);
            filterChain.doFilter(httpServletRequest, httpServletResponse);
        }
    }
}
