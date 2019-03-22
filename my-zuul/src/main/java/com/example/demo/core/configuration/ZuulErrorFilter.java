package com.example.demo.core.configuration;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.context.annotation.Configuration;

import javax.servlet.http.HttpServletRequest;

@Configuration
public class ZuulErrorFilter extends ZuulFilter {

    private static final String ERROR_STATUS_CODE_KEY = "throwable";

    private Logger log = LoggerFactory.getLogger(ZuulErrorFilter.class);

    public static final String DEFAULT_ERR_MSG = "系统繁忙,请稍后再试";

    @Override
    public String filterType() {
        return FilterConstants.POST_TYPE;
    }

    @Override
    public int filterOrder() {
        return 101;
    }

    @Override
    public boolean shouldFilter() {
        RequestContext ctx = RequestContext.getCurrentContext();
        return ctx.containsKey(ERROR_STATUS_CODE_KEY);
    }

    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        String message=null;
        try {
            HttpServletRequest request = ctx.getRequest();


            Throwable e = (Throwable) ctx.get(ERROR_STATUS_CODE_KEY);
            Throwable re = getOriginException(e);
            if(re instanceof java.net.ConnectException){
                message = "Real Service Connection refused";
                message = "服务出现异常！";
                log.error("uri:{},error:{}" ,request.getRequestURI(),re.getMessage());
            }else if(re instanceof java.net.SocketTimeoutException){
                message = "Real Service Timeout";
                message = "服务超时！";
                log.error("uri:{},error:{}" ,request.getRequestURI(),re.getMessage());
            }else if(re instanceof com.netflix.client.ClientException){
                message = re.getMessage();
                log.error("uri:{},error:{}" ,request.getRequestURI(),re.getMessage());
            }else {
                log.error("Error during filtering",e);
            }

            if(StringUtils.isBlank(message)){
                message = DEFAULT_ERR_MSG;
            }

        } catch (Exception e) {
            String error = "Error during filtering[ErrorFilter]";
            log.error(error,e);
        }

        ctx.setSendZuulResponse(false);
        ctx.setResponseStatusCode(200);
        ctx.addZuulResponseHeader("content-type", "application/json;charset=utf-8");
        ctx.setResponseBody("{\"code\": 0,\"message\": \""+message+"\"}");
        ctx.remove(ERROR_STATUS_CODE_KEY);
        ctx.put("responseDataStream","");


        return null;
    }

    private Throwable getOriginException(Throwable e){
        e = e.getCause();
        while(e.getCause() != null){
            e = e.getCause();
        }
        return e;
    }

}
