package MIN.DosiNongBu.controller.post;

import MIN.DosiNongBu.domain.post.constant.PostType;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class PostCommentController {

    // 댓글 목록 조회
    @GetMapping("/comments/{postId}")
    public void commentList(Pageable pageable) {

    }

    // 댓글 달기
    @PostMapping("/comments/{postId}")
    public void comment(){

    }

    // 댓글 수정

    // 댓굴 삭제

    // 댓글 반응

    // 댓글 반응 취소

    // 댓글 신고
}
