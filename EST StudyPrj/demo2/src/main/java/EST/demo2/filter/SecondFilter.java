package EST.demo2.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;

public class SecondFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("SecondFilter init()");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {


        System.out.println("SecondFilter doFilter() request");

        // client가 요청한 http method(GET,POST,...) 로깅
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        String httpMethod = httpRequest.getMethod();
        System.out.println("SecondFilter: " + httpMethod);

        filterChain.doFilter(servletRequest, servletResponse);

        System.out.println("SecondFilter doFilter() response");
    }

    @Override
    public void destroy() {
        System.out.println("SecondFilter destroy()");
    }
}
