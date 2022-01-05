package kr.co.songjava.mvc.domain;

/*
메뉴 게시판 종류
실제 BoardType 과 navbar 의 url(소문자) 매핑하는 클래스스
*/
public enum MenuType {
    notice(BoardType.NOTICE),
    faq(BoardType.FAQ),
    inquiry(BoardType.INQUIRY),
    community(BoardType.COMMUNITY),
    ;
    private BoardType boardType;

    MenuType(BoardType boardType){
        this.boardType = boardType;
    }

    public BoardType get(){
        return boardType;
    }

    public BoardType[] getArray(){
        BoardType[] boardTypes = new BoardType[1];
        boardTypes[0] = boardType;
        return boardTypes;
    }



}
