package com.example.firstproject.repository;

import com.example.firstproject.entity.Article;
import com.example.firstproject.entity.Comment;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest //JPA와 연동해 테스트
class CommentRepositoryTest {
    @Autowired
    CommentRepository commentRepository;

    @Test
    @DisplayName("특정 게시글의 모든 댓글 조회")
    void findByArticleId(){
        /* Case 1: 4번 게시글의 모든 댓글 조희*/
        {
            Long articleId = 4L;
            // 1. 입력 데이터 준비
            List<Comment> comments = commentRepository.findByArticleId((articleId));
            // 2. 실제 데이터
            Article article = new Article(4L, "your favorite movie?", "leave comments");
            Comment a = new Comment(1L, article, "Park", "Harry Potter");
            Comment b = new Comment(2L, article, "Kim", "Harry Potter2");
            Comment c = new Comment(3L, article, "Lee", "Harry Potter3");
            List<Comment> expected = Arrays.asList(a, b, c);
            // 3. 예상 데이터
            assertEquals(expected.toString(), comments.toString(), "print all the comments of article 4");
            // 4. 비교 및 검증
        }
        /* Case 2: 1번 게시글의 모든 댓글 조희*/
        {
            Long articleId = 1L;
            // 1. 입력 데이터 준비
            List<Comment> comments = commentRepository.findByArticleId((articleId));
            // 2. 실제 데이터
            Article article = new Article(1L, "aaaa", "1111");

            List<Comment> expected = Arrays.asList();
            // 3. 예상 데이터

            assertEquals(expected.toString(), comments.toString(), "1번 글은 댓글이 없음");
            // 4. 비교 및 검증
        }
    }
    @Test
    @DisplayName("특정 닉네임의 모든 댓글 조회")
    void findByNickname(){
        /* Case 1: "Park"의 모든 댓글 조회*/
        String nickname = "Park";
        // 1. 입력 데이터 준비
        List<Comment> comments = commentRepository.findByNickname(nickname);
        // 2. 실제 데이터
        Comment a = new Comment(1L, new Article(4L, "your favorite movie?", "leave comments" ),
                nickname, "Harry Potter");
        Comment b = new Comment(4L, new Article(5L, "your favorite food?", "leave comments!" ),
                nickname, "steak");
        Comment c = new Comment(7L, new Article(6L, "your hobby?", "leave comments!!" ),
                nickname, "Reading a book");
        List<Comment> expected = Arrays.asList(a, b, c);
        // 3. 예상 데이터
        assertEquals(expected.toString(), comments.toString(), "shows Park's comments");
        // 4. 비교 및 검증

    }

}