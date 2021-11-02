package me.demo.wik2;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import static javax.persistence.GenerationType.IDENTITY;

@Getter
@Entity
public class Bmk {

    @Id @GeneratedValue(strategy = IDENTITY)
    private Long wik2ID;
    private String userID;

}