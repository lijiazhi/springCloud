package com.example.zuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class LogFilter  extends ZuulFilter {
    RequestContext ctx = RequestContext.getCurrentContext();
    @Override
    public String filterType() {
        return null;
    }

    @Override
    public int filterOrder() {
        return 1;
    }

    @Override
    public boolean shouldFilter() {
        return (boolean) ctx.get("isSuccess");
    }

    @Override
    public Object run() throws ZuulException {

        return null;
    }
}
