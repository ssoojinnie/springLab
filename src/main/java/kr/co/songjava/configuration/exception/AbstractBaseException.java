package kr.co.songjava.configuration.exception;

import kr.co.songjava.configuration.http.BaseResponse;
import kr.co.songjava.configuration.http.BaseResponseCode;

public abstract class AbstractBaseException extends RuntimeException {
    //자바에서 제공하는 예외클래스 말고도 커스텀 에러, 공통에러 포맷에 맞게 사용할 수 있음
    private static final long serialVersionUID = 8342235231880246631L;

    protected BaseResponseCode responseCode;
    protected Object[] args;

    public AbstractBaseException(){
    }

    public AbstractBaseException(BaseResponseCode responseCode){
        this.responseCode = responseCode;
    }

    public BaseResponseCode getResponseCode(){
        return responseCode;
    }

    public Object[] getArgs(){
        return  args;
    }
}
