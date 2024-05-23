package MIN.DosiNongBu.service.post;

import MIN.DosiNongBu.controller.post.dto.request.CommentSaveRequestDto;
import MIN.DosiNongBu.controller.post.dto.request.CommentUpdateRequestDto;
import MIN.DosiNongBu.controller.post.dto.response.CommentListResponseDto;
import MIN.DosiNongBu.domain.post.constant.PostType;
import MIN.DosiNongBu.domain.post.constant.ReactionType;
import MIN.DosiNongBu.domain.post.constant.ReportType;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PostCommentService {

    // 댓글 목록 조회
    List<CommentListResponseDto> viewCommentList(Long postId, Pageable pageable);

    // 댓글 달기
    Long registerComment(Long postId, Long userId, CommentSaveRequestDto requestDto);

    // 댓글 수정
    Long updateComment(Long commentId, CommentUpdateRequestDto requestDto);

    // 댓글 삭제
    Long deleteComment(Long commentId);

    // 댓글 반응
    Long registerCommentReaction(Long userId, Long commentId, ReactionType reactionType);

    // 댓글 반응 취소
    void deleteCommentReaction();

    // 댓글 신고
    Long registerPostReport(Long userId, Long commentId, ReportType reportType);

}
