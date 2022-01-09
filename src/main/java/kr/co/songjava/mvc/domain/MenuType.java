package kr.co.songjava.mvc.domain;

/*
메뉴 게시판 종류
실제 BoardType 과 navbar 의 url(소문자) 매핑하는 클래스스
*/
public enum MenuType {
    notice(BoardType.NOTICE, "menu.notice", "/notice"),
    faq(BoardType.FAQ, "menu.faq", "/faq"),
    inquiry(BoardType.INQUIRY, "menu.inquiry", "/inquiry"),
    community(BoardType.COMMUNITY, "menu.community", "/community"),

    ;
    private BoardType boardType;
    private String menuCode;
    private String url;

    MenuType(BoardType boardType, String menuCode, String url){
        this.boardType = boardType;
        this.menuCode = menuCode;
        this.url = url;
    }

    public BoardType boardType(){
        return boardType;
    }

    public String menuCode(){
        return menuCode;
    }

    public String url(){
        return url;
    }

/*
    public BoardType[] getArray(){
        BoardType[] boardTypes = new BoardType[1];
        boardTypes[0] = boardType;
        return boardTypes;
    }*/



}
