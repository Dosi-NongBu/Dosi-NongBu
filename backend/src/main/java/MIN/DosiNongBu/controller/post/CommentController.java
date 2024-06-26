package MIN.DosiNongBu.controller.post;

import MIN.DosiNongBu.controller.post.dto.request.CommentSaveRequestDto;
import MIN.DosiNongBu.controller.post.dto.request.CommentUpdateRequestDto;
import MIN.DosiNongBu.controller.post.dto.response.CommentListResponseDto;
import MIN.DosiNongBu.domain.post.constant.ReportType;
import MIN.DosiNongBu.service.post.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class CommentController {

    private final CommentService commentService;

    public Long UserCookie(String cookie){
        Long userId = null;
        try {
            if(cookie != null){
                userId = Long.parseLong(cookie);
            }
        } catch (NumberFormatException e){
            //변환 할 수 없을 때
            throw new IllegalArgumentException("잘못된 유저 ID 입니다");
        }
        return userId;
    }

    // 댓글 목록 조회
    @GetMapping("/comments/{postId}")
    public List<CommentListResponseDto> commentList(@PathVariable Long postId,
                                                    Pageable pageable) {
        log.info("댓글 목록 조회");
        return commentService.viewCommentList(postId, pageable);
    }

    // 댓글 달기
    @PostMapping("/comments/{postId}")
    public Long comment(@CookieValue(name = "User") String cookie,
                        @PathVariable Long postId,
                        @RequestBody CommentSaveRequestDto requestDto){
        log.info("댓글 달기");
        Long userId = UserCookie(cookie);

        return commentService.registerComment(postId, userId, requestDto);

    }

    // 댓글 수정
    @PutMapping("/comments/{commentId}")
    public Long updateComment(@PathVariable Long commentId,
                              @RequestBody CommentUpdateRequestDto requestDto){
        log.info("댓글 수정");
        return commentService.updateComment(commentId, requestDto);
    }


    // 댓굴 삭제
    @DeleteMapping("/comments/{commentId}")
    public Long deleteComment(@PathVariable Long commentId){
        log.info("댓글 삭제");
        return commentService.deleteComment(commentId);
    }

    // 댓글 반응
    @PostMapping("/comments/reactions/{commentId}")
    public Long createCommentReaction(@CookieValue(name = "User") String cookie,
                                      @PathVariable Long commentId,
                                      @RequestParam("reactionType") String reactionType){
        log.info("댓글 반응");
        Long userId = UserCookie(cookie);

        return commentService.registerCommentReaction(userId, commentId, reactionType);
    }

    // 댓글 반응 취소
    @DeleteMapping("/comments/reactions/{commentId}")
    public void deleteCommentReaction(@PathVariable Long commentId){
        log.info("댓글 반응 취소");
    }

    // 댓글 신고
    @PostMapping("/reports/comments/{commentId}")
    public Long createPostReport(@CookieValue(name = "User") String cookie,
                                 @PathVariable Long commentId,
                                 @RequestParam("reportType") String reportType){
        log.info("댓글 신고");
        Long userId = UserCookie(cookie);

        return commentService.registerCommentReport(userId, commentId, reportType);
    }
}
