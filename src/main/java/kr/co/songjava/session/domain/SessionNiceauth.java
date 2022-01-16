package kr.co.songjava.session.domain;

import lombok.Data;

@Data
public class SessionNiceauth {
    private String authId;
    private String phoneNumber;
    private String name;

}
