package MIN.DosiNongBu.controller.post;

import MIN.DosiNongBu.controller.post.dto.request.PostSaveRequestDto;
import MIN.DosiNongBu.controller.post.dto.request.PostUpdateRequestDto;
import MIN.DosiNongBu.controller.post.dto.response.PostListResponseDto;
import MIN.DosiNongBu.controller.post.dto.response.PostResponseDto;
import MIN.DosiNongBu.domain.post.constant.PostType;
import MIN.DosiNongBu.domain.post.constant.ReactionType;
import MIN.DosiNongBu.domain.post.constant.ReportType;
import MIN.DosiNongBu.service.post.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.Parameter;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class PostController {

    private final PostService postService;

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

    // 카테고리 별 목록 조회
    @GetMapping("/posts")
    public List<PostListResponseDto> postList(@RequestParam("postType") String postType,
                                              Pageable pageable) {
        log.info("카테고리 별 목록조회");
        return postService.viewPostList(postType, pageable);
    }

    // 글 조회
    @GetMapping("/posts/{postId}")
    public PostResponseDto post(@PathVariable Long postId) {
        log.info("글 조회");
        return postService.viewPost(postId);
    }

    // 글 작성
    @PostMapping("/posts")
    public Long createPost(@CookieValue(name = "User") String cookie,
                           @RequestParam("postType") String postType,
                           @RequestBody PostSaveRequestDto requestDto) {
        log.info("글 작성");
        Long userId = UserCookie(cookie);

        return postService.registerPost(userId, postType, requestDto);
    }

    // 글 수정
    @PutMapping("/posts/{postId}")
    public Long updatePost(@PathVariable Long postId,
                           @RequestBody PostUpdateRequestDto requestDto) {
        log.info("글 수정");
        return postService.updatePost(postId, requestDto);
    }

    // 글 삭제
    @DeleteMapping("/posts/{postId}")
    public Long deletePost(@PathVariable Long postId) {
        log.info("글 삭제");
        return postService.deletePost(postId);
    }

    // 글 반응
    @PostMapping("/posts/reactions/{postId}")
    public Long createPostReaction(@CookieValue(name = "User") String cookie,
                                   @PathVariable Long postId,
                                   @RequestParam("reactionType") String reactionType){
        log.info("글 반응");
        Long userId = UserCookie(cookie);

        return postService.registerPostReaction(userId, postId, reactionType);
    }

    // 글 반응 취소
    @DeleteMapping("/posts/reactions/{postId}")
    public void deletePostReaction(){

    }

    // 글 신고
    @PostMapping("/reports/posts/{postId}")
    public Long createPostReport(@CookieValue(name = "User") String cookie,
                                 @PathVariable Long postId,
                                 @RequestParam("reportType") String reportType){
        log.info("글 신고");
        Long userId = UserCookie(cookie);

        return postService.registerPostReport(userId, postId, reportType);
    }
}
