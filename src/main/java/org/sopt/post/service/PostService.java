package org.sopt.post.service;

import org.sopt.member.entity.Member;
import org.sopt.member.exception.InvalidMemberException;
import org.sopt.member.exception.MemberNotfoundException;
import org.sopt.member.repository.MemberRepository;
import org.sopt.post.entity.BoardType;
import org.sopt.post.entity.PostLike;
import org.sopt.post.repository.PostLikeRepository;
import org.sopt.post.service.dto.command.CreatePostCommand;
import org.sopt.post.service.dto.command.UpdatePostCommand;
import org.sopt.post.service.dto.information.PostDetailInfo;
import org.sopt.post.service.dto.information.PostListInfo;
import org.sopt.post.service.mapper.PostMapper;
import org.sopt.post.entity.Post;
import org.sopt.post.exception.PostNotFoundException;
import org.sopt.post.repository.PostRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostService {
	private final PostRepository postRepository;
	private final PostLikeRepository postLikeRepository;
	private final MemberRepository memberRepository;

	// CREATE
	@Transactional
	public PostDetailInfo createPost(long memberId, CreatePostCommand command) {
		Member author = memberRepository.findById(memberId)
				.orElseThrow(MemberNotfoundException::new);
		Post newPost = postRepository.save(PostMapper.toDomain(command, author));

		return PostMapper.toDetailInfo(newPost);
	}

	// READALL
	public PostListInfo getAllPosts(int page, int size, String boardType) {
		Pageable pageable = PageRequest.of(page - 1, size);
		BoardType target = boardType == null ? null : BoardType.from(boardType);

		Slice<Post> posts = postRepository.findAllByBoardType(target, pageable);
		return PostMapper.toListInfo(posts.getContent(), posts.getNumber() + 1, posts.hasNext());
	}

	public PostListInfo getPostsByTitle(String title, int page, int size) {
		Pageable pageable = PageRequest.of(page - 1, size);
		Slice<Post> posts = postRepository.searchByTitle(title, pageable);

		return PostMapper.toListInfo(posts.getContent(), posts.getNumber() + 1, posts.hasNext());
	}

	// READ
	public PostDetailInfo getPost(long postId) {
		return PostMapper.toDetailInfo(postRepository.findById(postId).orElseThrow(PostNotFoundException::new));
	}

	// UPDATE
	@Transactional
	public PostDetailInfo updatePost(long memberId, long postId, UpdatePostCommand command) {
		Post post = postRepository.findByIdWithMember(postId).orElseThrow(PostNotFoundException::new);

		assertAuthor(memberId, post);

		post.update(command.title(), command.content());
		return PostMapper.toDetailInfo(post);
	}

	@Transactional
	public void updateLike(long memberId, long postId) {
		Post post = postRepository.findById(postId).orElseThrow(PostNotFoundException::new);
		Member member = memberRepository.findById(memberId).orElseThrow(MemberNotfoundException::new);

		if (!postLikeRepository.existsByMemberIdAndPostId(memberId, postId)) {
			PostLike postLike = new PostLike(post, member);
			postLikeRepository.save(postLike);
			postRepository.increaseLikeCount(postId);
		} else {
			postLikeRepository.deleteByMemberIdAndPostId(memberId, postId);
			postRepository.decreaseLikeCount(postId);
		}
	}

	// DELETE
	@Transactional
	public void deletePost(long memberId, long postId) {
		Post post = postRepository.findByIdWithMember(postId).orElseThrow(PostNotFoundException::new);

		assertAuthor(memberId, post);

		postLikeRepository.deleteByPostId(postId);
		postRepository.delete(post);
	}

	private void assertAuthor(long memberId, Post post) {
		if (!memberRepository.existsById(memberId)) {
			throw new MemberNotfoundException();
		}

		if (!post.getMember().getId().equals(memberId)) {
			throw new InvalidMemberException();
		}
	}
}
