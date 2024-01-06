package com.example.firstproject.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor  //기본 생성자 추가 어노테이션
@ToString
@Entity
@Getter
public class Article {
    @Id //엔티티의 대푯값 지정
    @GeneratedValue(strategy = GenerationType.IDENTITY) //자동 생성 기능 추가(숫자가 자동)
    private Long id;

    @Column
    private String title;

    @Column //content 필드 선언, DB 테이블의 content와 연결
    private String content;

    public void patch(Article article) {
        if (article.title != null)
            this.title = article.title;
        if (article.content != null)
            this.content = article.content;
    }

}
