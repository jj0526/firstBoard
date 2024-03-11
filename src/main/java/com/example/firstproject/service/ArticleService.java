package com.example.firstproject.service;

import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.entity.Article;
import com.example.firstproject.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service //서비스 객체 생성
public class ArticleService {
    @Autowired
    private ArticleRepository articleRepository;    // 게시글 레파지터리 객체 주입

    public List<Article> index() {
        return articleRepository.findAll();
    }

    public Article show(Long id) {
        return articleRepository.findById(id).orElse(null);
    }

    public Article create(ArticleForm dto) {
        Article article = dto.toEntity();
        if(article.getId()!=null){
            return null;    //article 객체에 id가 존재한다면
        }
        return articleRepository.save(article);
    }

    public Article update(Long id, ArticleForm dto) {
        Article article = dto.toEntity();
        log.info("id: {}, article: {}",id, article.toString());
        // 1. DTO->엔티티 변화하기

        Article target = articleRepository.findById(id).orElse(null);
        //Article articleEntity = articleRepository.findById(id).orElse(null);
        // 2. 타깃 조회하기

        if(target==null||id!=article.getId()){  //wrong request
            log.info("wrong request");
            return null;
        }
        // 3. 잘못된 요청 처리하기

        target.patch(article);
        Article updated = articleRepository.save(target);   //데이터를 안넣었을때 null값 들어가는 것 방지
        return updated;
        // 4. 업데이트 및 정상 응답하기
    }

    public Article delete(Long id) {
        Article target = articleRepository.findById(id).orElse(null);
        // 1.대상 찾기

        if(target==null){
            return null;
        }
        // 2. 잘못된 요청 처리하기

        articleRepository.delete(target);
        return target;
        // 3. 대상 삭제하기
    }

    @Transactional      //트랜잭션
    public List<Article> createArticles(List<ArticleForm> dtos) {
        List<Article> articleList = dtos.stream()
                .map(dto -> dto.toEntity())
                .collect(Collectors.toList());
        // 1. dto 묶음을 엔티티 묶음으로 변환하기
        articleList.stream()
                .forEach(article -> articleRepository.save(article));
        // 2. 엔티티 묶음을 DB에 저장하기
        articleRepository.findById(-1L)
                .orElseThrow(()-> new IllegalArgumentException("failed"));
        // 3. 강제 예외 발생시키기
        return articleList;
        // 4. 결과 값 반환하기
    }
}
