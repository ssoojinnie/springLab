package kr.co.songjava.framework.data.web;

import kr.co.songjava.framework.data.domain.MySQLPageRequest;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;


import javax.servlet.http.HttpServletRequest;
/*
MYSQL 쿼리 페이징 limit, offset 값을 자동계산하여
MysqlPageRequest클래스에 담아서 컨트롤러에서 받아 쓸 수 있음

 */
public class MySQLPageRequestHandleMethodArgumentResolver implements HandlerMethodArgumentResolver {
//HandlerMethodArgumentResolver : 컨트롤러 메서드에서 특정 조건에 맞는 파라미터가 있을 때 원하는 값 바인딩
    final Logger logger = LoggerFactory.getLogger(getClass());

    private static final String DEFAULT_PARAMETER_PAGE = "page";
    private static final String DEFAULT_PARAMETER_SIZE = "size";
    private static final int DEFAULT_SIZE = 20;
    @Override
    public boolean supportsParameter(MethodParameter parameter) {//현재 파라미터를 resolver 가 지원하는지 true/false 반환
        return MySQLPageRequest.class.isAssignableFrom(parameter.getParameterType());
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        //실제로 바인딩할 객체를 리턴
        HttpServletRequest request = (HttpServletRequest) webRequest.getNativeRequest();
        //현재페이지
        int page = NumberUtils.toInt(request.getParameter(DEFAULT_PARAMETER_PAGE), 1);
        //리스트 갯수
        int offset = NumberUtils.toInt(request.getParameter(DEFAULT_PARAMETER_SIZE), DEFAULT_SIZE);
        //시작지점
        int limit = (offset * page) - offset;

        logger.info("page : {}", page);
        logger.info("limit : {} , offset:{}", limit, offset);

        return new MySQLPageRequest(page, offset, limit, offset);
    }
}
