package kr.co.songjava.configuration.session;

import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
*
* AbstractHttpSession 세션(httpSession) 관리를 class + Spring
*
* @author psjin
* @version 1.0.0
* 작성일 2022-01-16
**/
public abstract class AbstractHttpSession<T> {

    /**
    *  세션에 사용되는 이름
    * @return
    **/
    protected abstract String name();

    /**
    *  value를 세션에 저장
    * @param value
    **/
    public void setAttribute(T value){
        getSession().setAttribute(name(), value);
    }

    /**
    * 세션에 저장된 정보를 리턴
    **/
    @SuppressWarnings("unchecked")
    public T getAttribute(){
        return (T) getSession().getAttribute(name());
    }

    /**
    * 세션에 저장된 정보를 삭제
    **/
    public void removeAttribute(){
        getSession().removeAttribute(name());
    }

    /**
    * 세션에 저장된 모든 정보 삭제 및 초기화
    **/
    public void invalidate(){
        getSession().invalidate();
    }

    /**
    *  HttpSession 리턴
    **/
    public HttpSession getSession() {
        return getRequest().getSession();
    }

    /**
    * HttpServletRequest 리턴
     * RequestAttributes : Spring 에서 제공하는 클래스
     * RequestContextHolder를 활용하면 쉽게 현재 request 를 가져올 수 있음
    **/
    protected HttpServletRequest getRequest(){
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if(requestAttributes != null){
            return ((ServletRequestAttributes) requestAttributes).getRequest();
        }
        return null;
    }
}
