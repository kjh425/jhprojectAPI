package com.jhproject.app.board.controller;

import java.util.List;

import javax.sound.midi.SysexMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.ui.Model;

import com.jhproject.app.board.dto.BoardCreateDto;
import com.jhproject.app.board.entity.Board;
import com.jhproject.app.board.service.BoardService;
import com.jhproject.app.member.entity.Member;
import com.jhproject.app.member.entity.MemberDetails;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/board")
public class BoardController {

    @Autowired
    private BoardService boardService;

    @GetMapping
    public List<Board> getAllBoards() {
        List<Board> boards = boardService.getAllBoards();
        System.out.println("게시판 테스트 =" + boards);
        return boards;
    }
    
    
    @PostMapping("/boardCreate.do")
    public String boardCreate(@RequestBody BoardCreateDto boardCreateDto) {
        // 실제로 로그인한 사용자인 경우
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println("이건받아노는디야.."+boardCreateDto);
        System.out.println(authentication);
        System.out.println(authentication.getPrincipal());
        if (authentication != null && authentication.getPrincipal() instanceof MemberDetails) {
            System.out.println("여기로옴");
            MemberDetails userDetails = (MemberDetails) authentication.getPrincipal();
            String memberId = userDetails.getMemberId();
            boardCreateDto.setWriter(memberId);

            int result = boardService.boardCreate(boardCreateDto);
            System.out.println(result);
        } else {
            // 사용자 정보를 가져오지 못한 경우 또는 다른 상황 처리
        }
        return null;
    }


    // 게시글 detail
    @GetMapping("/boardDetail")
    public ResponseEntity<Board> getBoardById(@RequestParam int boardId) {
        try {
            Board board = boardService.getBoardById(boardId);
            System.out.println(boardId);
            log.debug("받아왔쥬? ㅋ={}" , board);
            return ResponseEntity.ok(board);
        } catch (Exception e) {
            log.error("에러 발생: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    
}

