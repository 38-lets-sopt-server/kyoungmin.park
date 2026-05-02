package org.sopt.post.controller;

import org.sopt.global.response.ApiResponse;
import org.sopt.post.controller.dto.request.CreatePostRequest;
import org.sopt.post.controller.dto.request.UpdatePostRequest;
import org.sopt.post.controller.dto.response.PostDetailResponse;
import org.sopt.post.controller.dto.response.PostListResponse;
import org.springframework.http.ResponseEntity;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

@Tag(name = "게시판 기능", description = "게시판 관련 기능 API")
public interface PostControllerDocs {
	@Operation(summary = "게시글 생성", description = "게시판의 게시글을 생성합니다.")
	ResponseEntity<ApiResponse<PostDetailResponse>> createPost(long memberId, @Valid CreatePostRequest request);

	@Operation(summary = "게시글 목록 조회", description = "게시판 종류에 따라 게시글을 목록을 조회합니다.")
	ResponseEntity<ApiResponse<PostListResponse>> getAllPosts(@Min(value = 1) int page, @Min(value = 1) int size, String boardType);

	@Operation(summary = "게시글 검색", description = "게시글 제목으로 게시글 목록을 검색합니다.")
	ResponseEntity<ApiResponse<PostListResponse>> getPostsByTitle(@Min(value = 1) int page, @Min(value = 1) int size, @NotBlank String title);

	@Operation(summary = "게시글 상세 조회", description = "특정 게시글에 대한 상세 정보를 조회합니다.")
	ResponseEntity<ApiResponse<PostDetailResponse>> getPost(long postId);

	@Operation(summary = "게시글 수정", description = "특정 게시글에 대한 상세 내용을 수정합니다.")
	ResponseEntity<ApiResponse<PostDetailResponse>> updatePost(long memberId, long postId, @Valid UpdatePostRequest request);

	@Operation(summary = "게시글 좋아요", description = "게시글에 대한 좋아요를 추가하거나 취소합니다.")
	ResponseEntity<ApiResponse<Void>> likePost(long memberId, long postId);

	@Operation(summary = "게시글 삭제", description = "특정 게시글을 삭제합니다.")
	ResponseEntity<ApiResponse<Void>> deletePost(long memberId, long postId);
}
