package kr.co.songjava.configuration.servlet.handler;

import kr.co.songjava.configuration.exception.BaseException;
import kr.co.songjava.configuration.http.BaseResponse;
import kr.co.songjava.configuration.http.BaseResponseCode;
import kr.co.songjava.framework.data.web.bind.annotation.RequestConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BaseHandlerInterceptor extends HandlerInterceptorAdapter {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler){
        logger.info("preHandle requestURI :{}", request.getRequestURI());
        if(handler instanceof HandlerMethod){
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            logger.info("handleMethod: {}", handlerMethod);
            RequestConfig requestConfig = handlerMethod.getMethodAnnotation(RequestConfig.class);
            if(requestConfig != null){//리스트나 상세조회 에는 추가하지 않았기 때문에 null 오류 날수 있음!
                //로그인체크가 필수 있경우
                if(requestConfig.loginCheck()){
                    throw new BaseException(BaseResponseCode.LOGIN_REQUIRED, new String[]{request.getRequestURI()});
                }

            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView){
        logger.info("postHandle requestURI :{}", request.getRequestURI());

    }
}
