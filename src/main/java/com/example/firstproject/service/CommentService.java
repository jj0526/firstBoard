package com.example.firstproject.service;

import com.example.firstproject.dto.CommentDto;
import com.example.firstproject.entity.Article;
import com.example.firstproject.entity.Comment;
import com.example.firstproject.repository.ArticleRepository;
import com.example.firstproject.repository.CommentRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private ArticleRepository articleRepository;

    public List<CommentDto> comments(Long articleId) {
        /*List<Comment> comments = commentRepository.findByArticleId(articleId);
        // 1. 댓글 조회
        List<CommentDto> dtos = new ArrayList<CommentDto>();
        for (int i = 0; i<comments.size(); i++){
            Comment c = comments.get(i);
            CommentDto dto = CommentDto.createCommentDto(c);
        }

        // 2. 엔티티 -> DTO 변환
        return dtos;
        // 3. 결과 반환
        */
        return commentRepository.findByArticleId(articleId)
                .stream()
                .map(comment->CommentDto.createCommentDto(comment))//a 요소를 꺼내 b를 수행한 결과로 매핑
                .collect(Collectors.toList());  //스트림 데이터를 리스트 자료형으로 변환
    }

    @Transactional
    public CommentDto create(Long articleId, CommentDto dto) {
        Article article = articleRepository.findById(articleId)
                .orElseThrow(()->new IllegalArgumentException("failed to add comments! no such article"));
        // 1. 게시글 조회 및 예외 발생
        Comment comment = Comment.createComment(dto, article);
        // 2. 댓글 엔티티 생성
        Comment created = commentRepository.save(comment);
        // 3. 댓글 엔티티를 DB에 저장
        return CommentDto.createCommentDto(created);
        // 4. DTO로 변화해 반환
    }


    
    @Transactional
    public CommentDto update(Long id, CommentDto dto) {
        Comment target = commentRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("failed to edit the comment. so such comment."));
        // 1. 댓글 조회 및 예외 발생
        target.patch(dto);
        // 2. 댓글 수정
        Comment updated = commentRepository.save(target);
        // 3. DB로 갱신
        return CommentDto.createCommentDto(updated);
        // 4. 댓글 엔티티를 DTO로 변환 및 반환
        
    }

    @Transactional
    public CommentDto delete(Long id) {
        Comment target = commentRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("no such comment"));
        commentRepository.delete(target);
        return  CommentDto.createCommentDto(target);
    }
    public void deleteCommentsByArticleId(Long articleId) {
        List<Comment> comments = commentRepository.findByArticleId(articleId);
        if (comments != null && !comments.isEmpty()) {
            commentRepository.deleteAll(comments);
        }
    }
}
