package com.example.firstproject.entity;

import com.example.firstproject.dto.CommentDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="article_id") //Article 엔티티와 기본키(id)와 매핑
    private Article article;

    @Column
    private String nickname;
    @Column
    private String body;

    public static Comment createComment(CommentDto dto, Article article) {
        //exception occurs
        if (dto.getId() != null)
            throw new IllegalArgumentException("failed to add comments. it should not have an id");
        if (dto.getArticleId() != article.getId())
            throw new IllegalArgumentException("failed to add comments. article's id is wrong");

        return new Comment(
                dto.getId(),
                article,
                dto.getNickname(),
                dto.getBody()
        );
    }

    public void patch(CommentDto dto) {
        if (this.id != dto.getId())
            throw new IllegalArgumentException("failed to edit the comment. wrong id");
        // 예외 발생
        if (dto.getNickname()!= null)   //수정할 닉네임 데이터가 있다면
            this.nickname = dto.getNickname();
        if (dto.getBody() != null)  //수정할 본문 데이터가 있다면
            this.body = dto.getBody();
        // 객체 갱신
    }
}
