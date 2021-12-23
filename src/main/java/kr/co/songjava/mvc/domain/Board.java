package kr.co.songjava.mvc.domain;

import lombok.Data;

import java.util.Date;

@Data
public class Board {
    private int boardSeq;
    private String title;
    private String contents;
    private Date regDate;
    private BoardType boardType;
    private boolean delYn;
}
