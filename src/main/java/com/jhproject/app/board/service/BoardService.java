package com.jhproject.app.board.service;

import java.util.List;

import com.jhproject.app.board.dto.BoardCreateDto;
import com.jhproject.app.board.entity.Board;

public interface BoardService {

	List<Board> getAllBoards();

	int boardCreate(BoardCreateDto boardCreateDto);

	Board getBoardById(int boardId);

}
