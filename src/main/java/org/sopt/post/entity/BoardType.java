package org.sopt.post.entity;

import org.sopt.post.exception.InvalidBoardTypeException;

public enum BoardType {
	FREE, HOT, SECRET;

	public static BoardType from(String boardType) {
		try {
			return BoardType.valueOf(boardType.toUpperCase());
		} catch (IllegalArgumentException e) {
			throw new InvalidBoardTypeException();
		}
	}
}
