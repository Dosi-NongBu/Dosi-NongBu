package MIN.DosiNongBu.service.post;

import MIN.DosiNongBu.controller.post.dto.request.CommentSaveRequestDto;
import MIN.DosiNongBu.controller.post.dto.request.CommentUpdateRequestDto;
import MIN.DosiNongBu.controller.post.dto.response.CommentListResponseDto;
import MIN.DosiNongBu.domain.post.*;
import MIN.DosiNongBu.domain.post.constant.ReactionType;
import MIN.DosiNongBu.domain.post.constant.ReportType;
import MIN.DosiNongBu.domain.user.User;
import MIN.DosiNongBu.repository.post.CommentReactionRepository;
import MIN.DosiNongBu.repository.post.CommentReportRepository;
import MIN.DosiNongBu.repository.post.CommentRepository;
import MIN.DosiNongBu.repository.post.PostRepository;
import MIN.DosiNongBu.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostCommentServiceImpl implements PostCommentService{

    private final UserRepository userRepository;
    private final PostRepository postRepository;

    private final CommentRepository commentRepository;
    private final CommentReactionRepository commentReactionRepository;
    private final CommentReportRepository commentReportRepository;

    @Override
    public List<CommentListResponseDto> viewCommentList(Long postId, Pageable pageable) {
        Page<Comment> entity = commentRepository.findByPost_PostId(postId, pageable);

        return entity.stream().map(CommentListResponseDto::new).toList();
    }

    @Override
    public Long registerComment(Long postId, Long userId, CommentSaveRequestDto requestDto) {
        Comment entity = requestDto.toEntity();

        User user =  userRepository.findById(userId)
                .orElseThrow(() -> new IllegalStateException("존재하지 않는 회원입니다. userId=" + userId));

        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalStateException("존재하지 않는 글입니다. postId=" + postId));

        entity.setUser(user);
        entity.setPost(post);
        commentRepository.save(entity);

        return entity.getCommentId();
    }

    @Override
    public Long updateComment(Long commentId, CommentUpdateRequestDto requestDto) {
        Comment entity = commentRepository.findById(commentId)
                .orElseThrow(() -> new IllegalStateException("존재하지 않는 글입니다. commentId=" + commentId));

        entity.update(requestDto.getContent());

        return commentId;
    }

    @Override
    public Long deleteComment(Long commentId) {
        commentRepository.deleteById(commentId);

        return commentId;
    }

    @Override
    public Long registerCommentReaction(Long userId, Long commentId, ReactionType reactionType) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new IllegalStateException("존재하지 않는 댓글입니다. commentId=" + commentId));

        User user =  userRepository.findById(userId)
                .orElseThrow(() -> new IllegalStateException("존재하지 않는 회원입니다. userId=" + userId));

        CommentReaction commentReaction = commentReactionRepository.findByCommentAndUser(comment, user);

        // 기존 반응이 없었다면
        if(commentReaction == null){
            CommentReaction entity = CommentReaction.builder()
                    .reaction(reactionType)
                    .build();

            entity.setUser(user);
            entity.setComment(comment);
            commentReactionRepository.save(entity);
            comment.addReaction(reactionType);

            return entity.getCommentReactionId();
        }
        // 기존 반응이 있었다면
        else{
            commentReaction.update(reactionType);
            comment.updateReaction(reactionType);

            return commentReaction.getCommentReactionId();
        }
    }

    @Override
    public void deleteCommentReaction() {

    }

    @Override
    public Long registerPostReport(Long userId, Long commentId, ReportType reportType) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new IllegalStateException("존재하지 않는 댓글입니다. commentId=" + commentId));

        User user =  userRepository.findById(userId)
                .orElseThrow(() -> new IllegalStateException("존재하지 않는 회원입니다. userId=" + userId));

        CommentReport commentReport = commentReportRepository.findByCommentAndUser(comment, user);

        // 기존 신고가 없었다면
        if(commentReport == null){
            CommentReport entity = CommentReport.builder()
                    .reportType(reportType)
                    .build();

            entity.setUser(user);
            entity.setComment(comment);
            commentReportRepository.save(entity);

            return entity.getCommentReportId();
        }
        // 기존 신고가 있었다면
        else{
            throw new IllegalStateException("이미 신고했습니다.");
        }
    }
}
