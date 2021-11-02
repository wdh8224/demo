package me.demo.wik2;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Getter
public class Wik2 {

	@Id @GeneratedValue(strategy = IDENTITY)
	private Long wik2ID;

	private int boardID;

	private String wik2Title;
	private String userID;
	private String wik2Date;
	private String wik2Content;
	private int wik2Available;
	private String map;
	private int wik2Count;
	
}

