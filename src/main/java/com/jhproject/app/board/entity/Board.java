package com.jhproject.app.board.entity;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class Board {

	private int boardId;
	private String title;
	private String content;
	private LocalDateTime regdate;
	private LocalDateTime updateDate;
	private String writer;
}
