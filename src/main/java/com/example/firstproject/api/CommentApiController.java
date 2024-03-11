package com.example.firstproject.api;

import com.example.firstproject.dto.CommentDto;
import com.example.firstproject.service.CommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class CommentApiController {
    @Autowired
    private CommentService commentService;
    @GetMapping("/api/articles/{articleId}/comments")
    public ResponseEntity<List<CommentDto>> comments(@PathVariable Long articleId){
        List<CommentDto> dtos = commentService.comments(articleId);
        // 서비스에 위임
        return ResponseEntity.status(HttpStatus.OK).body(dtos);
        // 결과 응답
    }

    // 1. 댓글 조회

    @PostMapping("/api/articles/{articleId}/comments")
    public ResponseEntity<CommentDto> create(@PathVariable Long articleId, @RequestBody CommentDto dto){
        CommentDto createdDto = commentService.create(articleId, dto);
        //서비스 위임
        return ResponseEntity.status(HttpStatus.OK).body(createdDto);
        // 결과 응답
    }
    // 2. 댓글 생성


    @PatchMapping("/api/comments/{id}")
    public ResponseEntity<CommentDto> update(@PathVariable Long id, @RequestBody CommentDto dto){
        CommentDto updatedDto = commentService.update(id, dto);
        //서비스에 위임
        return ResponseEntity.status(HttpStatus.OK).body(updatedDto);
        //결과 응답
    }
    // 3. 댓글 수정

    @DeleteMapping("/api/comments/{id}")
    public ResponseEntity<CommentDto> delete(@PathVariable Long id){
        CommentDto deletedDto = commentService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body(deletedDto);
    }
    // 4. 댓글 삭제

}
