package kr.co.songjava.mvc.domain;

public enum ThumbnailType {
    WEB_MAIN(500, 300),
    WEB_SUB(150, 70),
    ;
    ThumbnailType(int width, int height){
        this.width = width;
        this.height = height;
    }
    ;

    private int width;
    private int height;

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
