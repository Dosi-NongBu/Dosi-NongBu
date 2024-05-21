package MIN.DosiNongBu.service.post;

import MIN.DosiNongBu.domain.post.constant.PostType;
import org.springframework.data.domain.Pageable;

public interface PostCommentService {

    // 댓글 목록 조회
    void viewCommentList(Pageable pageable);

    // 댓글 달기
    void registerComment();

    // 댓글 수정
    void updateComment();

    // 댓글 삭제
    void deleteComment();

    // 댓글 반응
    void registerCommentReaction();

    // 댓글 반응 취소
    void deleteCommentReaction();

    // 댓글 신고
    void registerPostReport();

}
