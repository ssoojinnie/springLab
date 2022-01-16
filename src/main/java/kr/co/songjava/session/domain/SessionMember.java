package kr.co.songjava.session.domain;

import lombok.Data;

@Data
public class SessionMember {

    private String memberId;
    private String password;
    private String name;
    private String companyName;
    private String departName;
    private String email;
    private String telNumber;
    private String phoneNumber;

}
