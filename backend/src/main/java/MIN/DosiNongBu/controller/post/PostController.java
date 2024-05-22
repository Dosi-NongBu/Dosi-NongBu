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
import org.hibernate.annotations.Parameter;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public List<PostListResponseDto> postList(@RequestParam PostType postType, Pageable pageable) {

        return postService.viewPostList(postType, pageable);
    }

    // 글 조회
    @GetMapping("/posts/{postId}")
    public PostResponseDto post(@PathVariable Long postId) {

        return postService.viewPost(postId);
    }

    // 글 작성
    @PostMapping("/posts")
    public Long createPost(@CookieValue(name = "User") String cookie, @RequestParam PostType postType, PostSaveRequestDto requestDto) {
        Long userId = UserCookie(cookie);

        return postService.registerPost(userId, postType, requestDto);
    }

    // 글 수정
    @PutMapping("/posts/{postId}")
    public Long updatePost(@PathVariable Long postId, PostUpdateRequestDto requestDto) {

        return postService.updatePost(postId, requestDto);
    }

    // 글 삭제
    @DeleteMapping("/posts/{postId}")
    public Long deletePost(@PathVariable Long postId) {

        return postService.deletePost(postId);
    }

    // 글 반응
    @PostMapping("/reactions/{postId}")
    public void createPostReaction(@CookieValue(name = "User") String cookie, @PathVariable Long postId, @RequestParam ReactionType reactionType){
        Long userId = UserCookie(cookie);

        postService.registerPostReaction(userId, postId, reactionType);
    }

    // 글 반응 취소
    @DeleteMapping("/reactions/{postId}")
    public void deletePostReaction(){

    }

    // 글 신고
    @PostMapping("/reposts/posts/{postId}")
    public void createPostReport(@CookieValue(name = "User") String cookie, @PathVariable Long postId, @RequestParam ReportType reportType){
        Long userId = UserCookie(cookie);

        postService.registerPostReport(userId, postId, reportType);
    }
}
