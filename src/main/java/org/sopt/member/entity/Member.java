package org.sopt.member.entity;

import org.sopt.global.common.entity.BaseTimeEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Member extends BaseTimeEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String nickname;

	private String email;

	protected Member() {}

	public Member(String nickname, String email) {
		this.nickname = nickname;
		this.email = email;
	}

	public Long getId() {
		return this.id;
	}

	public String getNickname() {
		return this.nickname;
	}

	public String getEmail() {
		return this.email;
	}
}
