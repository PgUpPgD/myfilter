package com.zh.myfilter.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.StringUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

//@WebFilter(filterName = "MyFilter")
public class MyFilter implements Filter {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest)req;
        HttpServletResponse response = (HttpServletResponse)resp;
        HttpServletResponseWrapper wrapper = new HttpServletResponseWrapper((HttpServletResponse) resp);
        if(request.getRequestURI().indexOf("/index") != -1 ||
                request.getRequestURI().indexOf("/online") != -1 ||
                request.getRequestURI().indexOf("/img") != -1 ||
                request.getRequestURI().indexOf("/layui") != -1 ||
                request.getRequestURI().indexOf("/login") != -1) {
            chain.doFilter(req, resp);
        }else{
            String token = request.getHeader("token");
            String s = stringRedisTemplate.opsForValue().get(token);
            if (StringUtils.isEmpty(s)){
                //wrapper.sendRedirect("login");
                returnJson(response);
                return;
            }
            chain.doFilter(req, resp);
        }
    }

    public void init(FilterConfig config) throws ServletException {

    }

    private void returnJson(HttpServletResponse response){
        PrintWriter writer = null;
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        try {
            writer = response.getWriter();
            Map<String, Object> result = PackageReturnResult.returnJson(400, "用户令牌token无效");
            result.put("data", null);
            writer.print(result);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(writer != null){
                writer.close();
            }
        }
    }

}
