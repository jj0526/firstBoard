package com.example.firstproject.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor  //기본 생성자 추가 어노테이션
@ToString
@Entity
public class Article {
    @Id //엔티티의 대푯값 지정
    @GeneratedValue //자동 생성 기능 추가(숫자가 자동)
    private Long id;
    @Column
    private String title;
    @Column //content 필드 선언, DB 테이블의 content와 연결
    private String content;

}
