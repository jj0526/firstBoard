package com.example.firstproject.controller;

import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.dto.CommentDto;
import com.example.firstproject.entity.Article;
import com.example.firstproject.repository.ArticleRepository;
import com.example.firstproject.repository.CommentRepository;
import com.example.firstproject.service.ArticleService;
import com.example.firstproject.service.CommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@Slf4j //able to leave logs
@Controller
public class ArticleController {
    @Autowired
    private ArticleRepository articleRepository;
    @Autowired
    private CommentService commentService;

    @GetMapping("/articles/new")
    public String newArticleForm(){
        return "articles/new";
    }

    @PostMapping("/articles/create")
    public String createArticle(ArticleForm form){
        Article article = form.toEntity();
        Article saved = articleRepository.save(article);
        log.info(form.toString());
        return "redirect:/articles/" + saved.getId();
    }


    @GetMapping("/articles/{id}")
    public String show(@PathVariable Long id, Model model){
        Article articleEntity = articleRepository.findById(id).orElse(null);
        List<CommentDto> commentsDtos = commentService.comments(id);
        model.addAttribute("article", articleEntity);   //add articleEntity as name "article"
        model.addAttribute("commentDtos", commentsDtos);
        return "articles/show";
    }

    /*
    @Autowired
    private ArticleService articleService;
    @GetMapping("/articles/{id}")
    public String show(@PathVariable Long id, Model model){
        Article article = articleService.show(id);
        List<CommentDto> commentsDtos = commentService.comments(id);
        model.addAttribute("article", article);
        model.addAttribute("commentDtos", commentsDtos);
        return "articles/show";

    }
    */

    @GetMapping("/articles")
    public String index(Model model){
        ArrayList<Article> articleEntityList = articleRepository.findAll();
        //1. 모든 데이터 가져오기
        model.addAttribute("articleList", articleEntityList);
        //2. 모델에 데이터 등록하기
        return "articles/index";
        //3. 뷰 페이지 설정하기
    }

    @GetMapping("/articles/{id}/edit")  //edit 페이지 로딩
    public String edit(@PathVariable Long id, Model model){
        Article articleEntity = articleRepository.findById(id).orElse(null);
        //수정할 데이터 가져오기

        model.addAttribute("article", articleEntity);
        //모델에 데이터 등록하기
        return "articles/edit";//뷰 페이지 설정하기
    }

    @PostMapping("/articles/update")
    public String update(ArticleForm form){ //매개변수로 DTO 받아오기
        Article articleEntity = form.toEntity();
        // 1. DTO를 엔티티로 변환하기
        Article target = articleRepository.findById(articleEntity.getId()).orElse(null);
        if (target != null){
            articleRepository.save(articleEntity);  //엔티티를 DB에 저장
        }
        // 2. 엔티티를 DB에 저장하기
        return "redirect:/articles/"+articleEntity.getId();
        // 3. 수정 결과 페이지로 리다이렉트하기
    }

    @GetMapping("/articles/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes rttr){   //id를 매개 변수로
        log.info("삭제 요청이 들어왔습니다");

        Article target = articleRepository.findById(id).orElse(null);
        //1. 삭제할 대상 가져오기
        if (target != null) {
            commentService.deleteCommentsByArticleId(id);
            articleRepository.delete(target);
            rttr.addFlashAttribute("msg", "삭제되었습니다");   //redirect to the referer
        }
        //2. 대상 엔티티 삭제하기
        return "redirect:/articles";
        //3. 결과 페이지로 리다이렉트하기
    }


}
