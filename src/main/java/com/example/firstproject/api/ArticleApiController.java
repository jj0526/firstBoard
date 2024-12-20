package com.example.firstproject.api;

import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.entity.Article;
import com.example.firstproject.service.ArticleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j  //able to leave logs
@RestController
public class ArticleApiController {
    @Autowired
    private ArticleService articleService;
//    @Autowired
//    private ArticleRepository articleRepository;
//    public ArticleApiController(ArticleRepository articleRepository){
//        this.articleRepository = articleRepository; //의존성 주입
//    }
//
    // GET
    @GetMapping("/api/articles")
    public List<Article> index(){
        return articleService.index();
        //return articleRepository.findAll();
    }

    @GetMapping("/api/articles/{id}")
    public Article show(@PathVariable Long id){
        return articleService.show(id);
        //return articleRepository.findById(id).orElse(null);
    }

//    // POST
//    @PostMapping("/api/articles")
//    public Article create(@RequestBody ArticleForm dto) {
//        Article article = dto.toEntity();
//        log.info(" article: {}", article.toString());
//        return articleRepository.save(article);
//    }


    @PostMapping("/api/articles")
    public ResponseEntity<Article> create(@RequestBody ArticleForm dto) {
        Article created = articleService.create(dto);
        return (created != null)?
                ResponseEntity.status(HttpStatus.OK).body(created):
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
//
//
//    // PATCH
//
//    @PatchMapping("/api/articles/{id}")
//    public ResponseEntity<Article> update(@PathVariable Long id, @RequestBody ArticleForm dto){
//        Article article = dto.toEntity();
//        log.info("id: {}, article: {}",id, article.toString());
//        // 1. DTO->엔티티 변화하기
//
//        Article target = articleRepository.findById(id).orElse(null);
//        //Article articleEntity = articleRepository.findById(id).orElse(null);
//        // 2. 타깃 조회하기
//
//        if(target==null||id!=article.getId()){  //wrong request
//            log.info("wrong request");
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
//        }
//        // 3. 잘못된 요청 처리하기
//
//        target.patch(article);
//        Article updated = articleRepository.save(target);   //데이터를 안넣었을때 null값 들어가는 것 방지
//        return ResponseEntity.status(HttpStatus.OK).body(updated);
//        // 4. 업데이트 및 정상 응답하기
//
//    }



//    @PatchMapping("/api/articles/{id}")
//    public ResponseEntity<Article> update(@PathVariable Long id, @RequestBody ArticleForm dto){
//        Article article = dto.toEntity();
//        log.info("id: {}, article: {}",id, article.toString());
//        // 1. DTO->엔티티 변화하기
//
//        Article target = articleRepository.findById(id).orElse(null);
//        //Article articleEntity = articleRepository.findById(id).orElse(null);
//        // 2. 타깃 조회하기
//
//        if(target==null||id!=article.getId()){  //wrong request
//            log.info("wrong request");
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
//        }
//        // 3. 잘못된 요청 처리하기
//
//        target.patch(article);
//        Article updated = articleRepository.save(target);   //데이터를 안넣었을때 null값 들어가는 것 방지
//        return ResponseEntity.status(HttpStatus.OK).body(updated);
//        // 4. 업데이트 및 정상 응답하기
//
//    }

    @PatchMapping("/api/articles/{id}")
    public ResponseEntity<Article> update(@PathVariable Long id, @RequestBody ArticleForm dto){
        Article updated = articleService.update(id, dto);
        return (updated != null)?
                ResponseEntity.status(HttpStatus.OK).body(updated):
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

    }
//
//
//
//    // DELETE
//    @DeleteMapping("/api/articles/{id}")
//    public ResponseEntity<Article> delete(@PathVariable Long id){
//        Article target = articleRepository.findById(id).orElse(null);
//        // 1.대상 찾기
//        if(target==null){
//            log.info("wrong request");
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
//        }
//        // 2. 잘못된 요청 처리하기
//        articleRepository.delete(target);
//        return ResponseEntity.status(HttpStatus.OK).build();
//        // 3. 대상 삭제하기
//    }

    // DELETE
    @DeleteMapping("/api/articles/{id}")
    public ResponseEntity<Article> delete(@PathVariable Long id){
        Article deleted = articleService.delete(id);

        return (deleted != null)?
                ResponseEntity.status(HttpStatus.OK).body(deleted):
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @PostMapping("/api/transaction-test")
    public ResponseEntity<List<Article>> transactionTest
            (@RequestBody List<ArticleForm> dtos){
        List<Article> createdList = articleService.createArticles(dtos);
        return (createdList != null)?
                ResponseEntity.status(HttpStatus.OK).body(createdList):
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }



}
