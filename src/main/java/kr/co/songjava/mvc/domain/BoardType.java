package kr.co.songjava.mvc.domain;

public enum BoardType implements BaseCodeLabelEnum{
    NOTICE("공지사항"),
    FAQ("자주묻는질문"),
    INQUIRY("1:1 문의"),
    TEST("테스트용 타입"),
    COMMUNITY("커뮤니티"),
    ;

    private String code;
    private String label;


    BoardType(String label){
        this.code = name(); //enum에서 제공하는 기본 함수
        // name()호출하면 위에 정의한 enum 이 return
        this.label = label;
    }
    @Override
    public String code() {
        return code;
    }

    @Override
    public String label() {
        return label;
    }
}
