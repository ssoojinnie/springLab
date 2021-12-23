package kr.co.songjava.framework.data.domain;

/*
페이지 요청 정보와 파라미터 정보 저장
 */
public class PageRequestParameter<T> {
    private MySQLPageRequest pageRequest;
    private T parameter;

    public PageRequestParameter (MySQLPageRequest pageRequest, T parameter){
        this.pageRequest = pageRequest;
        this.parameter = parameter;
    }
}
