package me.demo.evaluation;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Getter
public class Evaluation {

    @Id @GeneratedValue(strategy = IDENTITY)
    private Long wik2ID;
    private String userID;
    private int likeWork;
    private int sosoWork;
    private int badWork;
}	
