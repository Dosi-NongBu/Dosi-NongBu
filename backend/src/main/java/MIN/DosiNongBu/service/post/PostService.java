package MIN.DosiNongBu.service.post;

import MIN.DosiNongBu.controller.post.dto.request.PostSaveRequestDto;
import MIN.DosiNongBu.controller.post.dto.request.PostUpdateRequestDto;
import MIN.DosiNongBu.controller.post.dto.response.PostListResponseDto;
import MIN.DosiNongBu.controller.post.dto.response.PostResponseDto;
import MIN.DosiNongBu.domain.post.constant.PostType;
import MIN.DosiNongBu.domain.post.constant.ReactionType;
import MIN.DosiNongBu.domain.post.constant.ReportType;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PostService {

    // 카테고리별 목록 조회
    List<PostListResponseDto> viewPostList(PostType postType, Pageable pageable);

    // 글 등록
    Long registerPost(Long userId, PostType postType, PostSaveRequestDto  requestDto);

    // 글 조회
    PostResponseDto viewPost(Long postId);

    // 글 수정
    Long updatePost(Long postId, PostUpdateRequestDto requestDto);

    // 글 삭제
    Long deletePost(Long postId);

    // 글 반응
    Long registerPostReaction(Long userId, Long postId, ReactionType reactionType);

    // 글 반응 취소
    void deletePostReaction();

    // 글 신고
    Long registerPostReport(Long userId, Long postId, ReportType reportType);
}
