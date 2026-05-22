package org.sopt.member.service.mapper;

import org.sopt.member.entity.Member;
import org.sopt.member.service.dto.command.SignUpCommand;
import org.sopt.member.service.dto.information.LoginInfo;
import org.sopt.member.service.dto.information.TokenInfo;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MemberMapper {

	public static Member toDomain(SignUpCommand command, String encodedPassword) {
		return new Member(command.nickname(), command.email(), encodedPassword);
	}

	public static LoginInfo toLoginInfo(Member member, TokenInfo tokenInfo) {
		return new LoginInfo(member.getNickname(), tokenInfo);
	}
}
