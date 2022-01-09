package kr.co.songjava.mvc.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import java.util.Date;

@Data
@Entity(name = "T_MEMBER") //실제 테이블명과 클래스명이 다를경우
public class Member {
    @Id
    private String memberId;

    @Enumerated(EnumType.STRING)
    private MemberType memberType;
    private String password;
    private String name;
    private String companyName;
    private String departName;
    private String email;
    private String telNumber;
    private String phoneNumber;
    private String zipcode;
    private String address;
    private String addressDetail;
    private boolean agreeTerms;
    private Date loginDate;
    private Date delDate;
    private Date joinDate;
    private Date updateDate;
}
