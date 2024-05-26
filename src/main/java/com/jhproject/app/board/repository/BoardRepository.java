package com.jhproject.app.board.repository;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.jhproject.app.board.dto.BoardCreateDto;
import com.jhproject.app.board.entity.Board;

@Mapper
public interface BoardRepository {

	@Select("SELECT * FROM board ORDER BY regdate DESC")
	List<Board> getAllBoards();

	@Insert("INSERT INTO board (board_id, title, content, regdate, update_date, writer)\r\n"
			+ "VALUES (board_seq.NEXTVAL, #{title}, #{content}, SYSDATE, NULL, #{writer})")
	int boardCreate(BoardCreateDto boardCreateDto);

	@Select("select * from board where board_id = #{boardId}")
	Board getBoardById(int boardId);

}