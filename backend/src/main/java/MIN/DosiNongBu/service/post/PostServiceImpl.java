package MIN.DosiNongBu.service.post;

import MIN.DosiNongBu.controller.post.dto.request.PostSaveRequestDto;
import MIN.DosiNongBu.controller.post.dto.request.PostUpdateRequestDto;
import MIN.DosiNongBu.controller.post.dto.response.PostListResponseDto;
import MIN.DosiNongBu.controller.post.dto.response.PostResponseDto;
import MIN.DosiNongBu.domain.post.Post;
import MIN.DosiNongBu.domain.post.PostReaction;
import MIN.DosiNongBu.domain.post.PostReport;
import MIN.DosiNongBu.domain.post.constant.PostType;
import MIN.DosiNongBu.domain.post.constant.ReactionType;
import MIN.DosiNongBu.domain.post.constant.ReportType;
import MIN.DosiNongBu.domain.user.User;
import MIN.DosiNongBu.repository.post.PostReactionRepository;
import MIN.DosiNongBu.repository.post.PostReportRepository;
import MIN.DosiNongBu.repository.post.PostRepository;
import MIN.DosiNongBu.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService{

    private final UserRepository userRepository;

    private final PostRepository postRepository;
    private final PostReactionRepository postReactionRepository;
    private final PostReportRepository postReportRepository;

    // 카테고리별 목록 조회
    @Override
    public List<PostListResponseDto> viewPostList(PostType postType, Pageable pageable) {
        Page<Post> entity = postRepository.findByPostType(postType, pageable);

        return entity.stream().map(PostListResponseDto::new).toList();
    }

    // 글 등록
    @Override
    @Transactional
    public Long registerPost(Long userId, PostType postType, PostSaveRequestDto requestDto) {
        Post entity= requestDto.toEntity(postType);

        User user =  userRepository.findById(userId)
                .orElseThrow(() -> new IllegalStateException("존재하지 않는 회원입니다. userId=" + userId));

        entity.setUser(user);
        postRepository.save(entity);
        return entity.getPostId();
    }

    // 글 조회
    @Override
    public PostResponseDto viewPost(Long postId) {
        Post entity = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalStateException("존재하지 않는 글입니다. postId=" + postId));

        return new PostResponseDto(entity);
    }

    // 글 수정
    @Override
    @Transactional
    public Long updatePost(Long postId, PostUpdateRequestDto requestDto) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalStateException("존재하지 않는 글입니다. postId=" + postId));

        post.update(requestDto.getTitle(), requestDto.getContent(), requestDto.getImageUrls());
        return postId;
    }

    // 글 삭제
    @Override
    @Transactional
    public Long deletePost(Long postId) {
        postRepository.deleteById(postId);

        return postId;
    }

    // 글 반응
    @Override
    @Transactional
    public Long registerPostReaction(Long userId, Long postId, ReactionType reactionType) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalStateException("존재하지 않는 글입니다. postId=" + postId));

        User user =  userRepository.findById(userId)
                .orElseThrow(() -> new IllegalStateException("존재하지 않는 회원입니다. userId=" + userId));

        PostReaction postReaction = postReactionRepository.findByPostAndUser(post, user);

        // 기존 반응이 없었다면
        if(postReaction == null){
            PostReaction entity = PostReaction.builder()
                    .reaction(reactionType)
                    .build();

            entity.setUser(user);
            entity.setPost(post);
            postReactionRepository.save(entity);
            post.addReaction(reactionType);

            return entity.getPostReactionId();
        }
        // 기존 반응이 있었다면
        else{
            postReaction.update(reactionType);
            post.updateReaction(reactionType);

            return postReaction.getPostReactionId();
        }
    }

    // 글 반응 취소
    @Override
    @Transactional
    public void deletePostReaction() {

    }

    // 글 신고
    @Override
    @Transactional
    public Long registerPostReport(Long userId, Long postId, ReportType reportType) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalStateException("존재하지 않는 글입니다. postId=" + postId));

        User user =  userRepository.findById(userId)
                .orElseThrow(() -> new IllegalStateException("존재하지 않는 회원입니다. userId=" + userId));

        PostReport postReport = postReportRepository.findByPostAndUser(post, user);

        // 기존 신고가 없었다면
        if(postReport == null){
            PostReport entity = PostReport.builder()
                    .reportType(reportType)
                    .build();

            entity.setUser(user);
            entity.setPost(post);
            postReportRepository.save(entity);

            return entity.getPostReportId();
        }
        // 기존 신고가 있었다면
        else{
            throw new IllegalStateException("이미 신고했습니다.");
        }

    }
}
