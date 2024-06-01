package com.jhproject.app.board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jhproject.app.board.dto.BoardCreateDto;
import com.jhproject.app.board.entity.Board;
import com.jhproject.app.board.repository.BoardRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class BoardServiceImpl implements BoardService{

	@Autowired
	private BoardRepository boardRepository;
	
	@Override
	public List<Board> getAllBoards() {
		List<Board> boards = boardRepository.getAllBoards();
		return boards;
	}
	
	@Override
	public int boardCreate(BoardCreateDto boardCreateDto) {
		int result = 0;
		result = boardRepository.boardCreate(boardCreateDto);
		return result;
	}

	/**
	 * 게시판 상세보기
	 * @param boardId 
	 * @return
	 */
	@Override
	public Board getBoardById(int boardId) {
		Board board = boardRepository.getBoardById(boardId);
		return board;
	}
}
