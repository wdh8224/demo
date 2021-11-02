package me.demo.user;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import static javax.persistence.GenerationType.IDENTITY;

@Getter
@Entity
public class User {

    @Id @GeneratedValue(strategy = IDENTITY)
    private Long userID;

    private String userPassword;
    private String userName;
    private String userGender;
    private String userEmail;

}
