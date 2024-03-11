package com.example.firstproject.service;

import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.entity.Article;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest //스프링부트와 연동해 테스트
class ArticleServiceTest {
    @Autowired
    ArticleService articleService;
    @Test
    void index() {
        Article a = new Article(1L, "aaaa", "1111");
        Article b = new Article(2L, "bbbb", "2222");
        Article c = new Article(3L, "cccc", "3333");
        List<Article> expected = new ArrayList<Article>(Arrays.asList(a, b, c));
        // 1. 예상 데이터
        List<Article> articles = articleService.index();
        // 2. 실제 데이터
        assertEquals(expected.toString(), articles.toString());
        // 3. 비교 및 검증

    }

    @Test
    void show_succ() {
        Long id = 1L;
        Article expected = new Article(id, "aaaa", "1111");
        // 1. 예상 데이터
        Article article = articleService.show(id);
        // 2. 실제 데이터
        assertEquals(expected.toString(), article.toString());
        // 3. 비교 및 검증
    }

    @Test
    void show_failed() {
        Long id = -1L;
        Article expected = null;
        // 1. 예상 데이터
        Article article = articleService.show(id);
        // 2. 실제 데이터
        assertEquals(expected, article);
        // 3. 비교 및 검증
    }

    @Test
    @Transactional
     void create_succ() {
        String title = "dddd";
        String content = "4444";
        ArticleForm dto = new ArticleForm(null, title, content);
        Article exptected = new Article(4L, title, content);
        // 1. 예상 데이터
        Article article = articleService.create(dto);
        // 2. 실제 데이터
        assertEquals(exptected.toString(), article.toString());
        // 3. 비교 및 검증
    }
    @Test
    void create_failed() {
        Long id = 4L;
        String title = "dddd";
        String content = "4444";
        ArticleForm dto = new ArticleForm(id, title, content);
        Article exptected = null;
        // 1. 예상 데이터
        Article article = articleService.create(dto);
        // 2. 실제 데이터
        assertEquals(exptected, article);
        // 3. 비교 및 검증
    }

    // 1. 예상 데이터
    // 2. 실제 데이터
    // 3. 비교 및 검증
}