package org.sopt.member.service;

import org.sopt.global.security.jwt.JwtProvider;
import org.sopt.member.entity.Member;
import org.sopt.member.exception.DuplicateEmailException;
import org.sopt.member.exception.InvalidPasswordException;
import org.sopt.member.exception.MemberNotfoundException;
import org.sopt.member.repository.MemberRepository;
import org.sopt.member.service.dto.command.LoginCommand;
import org.sopt.member.service.dto.command.SignUpCommand;
import org.sopt.member.service.dto.information.LoginInfo;
import org.sopt.member.service.dto.information.TokenInfo;
import org.sopt.member.service.mapper.MemberMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AuthService {
	private final MemberRepository memberRepository;
	private final JwtProvider jwtProvider;
	private final PasswordEncoder passwordEncoder;

	@Transactional
	public void signUp(SignUpCommand command) {
		if (memberRepository.existsByEmail(command.email())) {
			throw new DuplicateEmailException();
		}
		String encodedPassword = passwordEncoder.encode(command.password());
		memberRepository.save(MemberMapper.toDomain(command, encodedPassword));
	}

	@Transactional
	public LoginInfo login(LoginCommand command) {
		Member member = memberRepository.findByEmail(command.email())
				.orElseThrow(MemberNotfoundException::new);

		if (!passwordEncoder.matches(command.password(), member.getPassword())) {
			throw new InvalidPasswordException();
		}

		TokenInfo tokens = TokenInfo.of(
				jwtProvider.generateAccessToken(member.getId()),
				jwtProvider.generateRefreshToken(member.getId())
		);

		return MemberMapper.toLoginInfo(member, tokens);
	}
}
