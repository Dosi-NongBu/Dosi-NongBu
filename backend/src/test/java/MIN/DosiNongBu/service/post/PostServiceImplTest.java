/*
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
import MIN.DosiNongBu.domain.user.constant.ProviderType;
import MIN.DosiNongBu.domain.user.constant.RoleType;
import MIN.DosiNongBu.repository.post.PostReactionRepository;
import MIN.DosiNongBu.repository.post.PostReportRepository;
import MIN.DosiNongBu.repository.post.PostRepository;
import MIN.DosiNongBu.repository.user.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Before;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;


@Slf4j
@SpringBootTest
@Transactional
class PostServiceImplTest {

    @Autowired PostService postService;

    @Autowired PostRepository postRepository;
    @Autowired PostReactionRepository postReactionRepository;
    @Autowired PostReportRepository postReportRepository;

    @Autowired UserRepository userRepository;

    @AfterEach
    void clear(){
        log.debug("debug log= DB 초기화");

        postRepository.deleteAll();
        userRepository.deleteAll();
    }

    @Test
    void 카테고리별_목록_조회(){
        log.debug("debug log= 카테고리별 목록 조회");

        // given
        User user = User.builder()
                .email("테스트 계정")
                .name("테스트")
                .role(RoleType.USER)
                .provider(ProviderType.DEFAULT)
                .build();
        userRepository.save(user);

        for(int i=1;i<=3;i++){
            Post post = Post.builder()
                    .title("DEFAULT 테스트 제목" + i)
                    .content("DEFAULT 테스트 본문" + i)
                    .postType(PostType.DEFAULT)
                    .build();
            post.setUser(user);
            postRepository.save(post);
        }

        for(int i=1;i<=3;i++){
            Post post = Post.builder()
                    .title("QNA 테스트 제목" + i)
                    .content("QNA 테스트 본문" + i)
                    .postType(PostType.QNA)
                    .build();
            post.setUser(user);
            postRepository.save(post);
        }

        Pageable pageable = PageRequest.of(0, 2);

        // when
        List<PostListResponseDto> DEFAULT = postService.viewPostList("DEFAULT", pageable);
        List<PostListResponseDto> QNA = postService.viewPostList("QNA", pageable);

        // then
        assertThat(DEFAULT.get(0).getTitle()).isEqualTo("DEFAULT 테스트 제목1");
        assertThat(DEFAULT.get(0).getContent()).isEqualTo("DEFAULT 테스트 본문1");
        assertThat(DEFAULT.get(0).getGood()).isEqualTo(0);
        assertThat(DEFAULT.get(0).getBad()).isEqualTo(0);

        assertThat(QNA.get(0).getTitle()).isEqualTo("QNA 테스트 제목1");
        assertThat(QNA.get(0).getContent()).isEqualTo("QNA 테스트 본문1");

    }

    @Test
    @Transactional
    void 글_등록(){
        log.debug("debug log= 글 등록");

        // given
        User user = User.builder()
                .email("테스트 계정")
                .name("테스트")
                .role(RoleType.USER)
                .provider(ProviderType.DEFAULT)
                .build();
        userRepository.save(user);

        // when
        Long findUserId = user.getUserId();

        List<String> imageUrls = new ArrayList<>();
        imageUrls.add("테스트 Urls");

        PostSaveRequestDto requestDto = PostSaveRequestDto.builder()
                .title("테스트 제목")
                .content("테스트 본문")
                .imageUrls(imageUrls)
                .build();

        Long findPostId = postService.registerPost(findUserId, "DEFAULT", requestDto);

        Post post = postRepository.findById(findPostId)
                .orElseThrow(() -> new IllegalStateException("존재하지 않는 글입니다. postId=" + findPostId));

        // then
        assertThat(post.getTitle()).isEqualTo("테스트 제목");
        assertThat(post.getContent()).isEqualTo("테스트 본문");
        assertThat(post.getImageUrls().get(0)).isEqualTo("테스트 Urls");

        assertThat(post.getBad()).isEqualTo(0);
        assertThat(post.getGood()).isEqualTo(0);
    }

    @Test
    void 글_조회(){
        log.debug("debug log= 글 조회");

        // given
        User user = User.builder()
                .email("테스트 계정")
                .name("테스트")
                .role(RoleType.USER)
                .provider(ProviderType.DEFAULT)
                .build();
        userRepository.save(user);

        List<String> imageUrls = new ArrayList<>();
        imageUrls.add("테스트 Urls");

        Post post = Post.builder()
                    .title("DEFAULT 테스트 제목")
                    .content("DEFAULT 테스트 본문")
                    .postType(PostType.DEFAULT)
                    .imageUrls(imageUrls)
                    .build();
        post.setUser(user);
        postRepository.save(post);

        // when
        Long findPostId = post.getPostId();

        PostResponseDto responseDto = postService.viewPost(findPostId);

        // then
        assertThat(responseDto.getPostType()).isEqualTo(PostType.DEFAULT);
        assertThat(responseDto.getTitle()).isEqualTo("DEFAULT 테스트 제목");
        assertThat(responseDto.getContent()).isEqualTo("DEFAULT 테스트 본문");
        assertThat(responseDto.getBad()).isEqualTo(0);
        assertThat(responseDto.getGood()).isEqualTo(0);
        assertThat(responseDto.getImageUrls().get(0)).isEqualTo("테스트 Urls");

    }

    @Test
    void 글_수정(){
        log.debug("debug log= 글 수정");

        // given
        User user = User.builder()
                .email("테스트 계정")
                .name("테스트")
                .role(RoleType.USER)
                .provider(ProviderType.DEFAULT)
                .build();
        userRepository.save(user);

        Post post = Post.builder()
                .title("DEFAULT 테스트 제목")
                .content("DEFAULT 테스트 본문")
                .postType(PostType.DEFAULT)
                .build();
        post.setUser(user);
        postRepository.save(post);

        // when
        Long findPostId = post.getPostId();

        PostUpdateRequestDto requestDto = PostUpdateRequestDto.builder()
                .title("수정된 테스트 제목")
                .content("수정된 테스트 본문")
                .imageUrls(new ArrayList<>())
                .build();

        Long updatedPostId = postService.updatePost(findPostId, requestDto);

        // then
        Post updatedPost = postRepository.findById(updatedPostId)
                .orElseThrow(() -> new IllegalStateException("존재하지 않는 글입니다. postId=" + updatedPostId));

        assertThat(updatedPost.getTitle()).isEqualTo("수정된 테스트 제목");
        assertThat(updatedPost.getContent()).isEqualTo("수정된 테스트 본문");
        assertThat(updatedPost.getImageUrls().size()).isEqualTo(0);
    }

    @Test
    void 글_삭제(){
        log.debug("debug log= 글 삭제");

        // given
        User user = User.builder()
                .email("테스트 계정")
                .name("테스트")
                .role(RoleType.USER)
                .provider(ProviderType.DEFAULT)
                .build();
        userRepository.save(user);

        Post post = Post.builder()
                .title("DEFAULT 테스트 제목")
                .content("DEFAULT 테스트 본문")
                .postType(PostType.DEFAULT)
                .build();
        post.setUser(user);
        postRepository.save(post);

        // when
        Long findPostId = post.getPostId();

        Long deletedPostId = postService.deletePost(findPostId);

        // then
         IllegalStateException exception = assertThrows(IllegalStateException.class, () -> {
            postRepository.findById(findPostId)
                    .orElseThrow(() -> new IllegalStateException("존재하지 않는 글입니다. postId=" + findPostId));
        });

        assertThat(exception.getMessage()).isEqualTo("존재하지 않는 글입니다. postId=" + findPostId);
    }

    @Test
    void 글_반응_최초반응(){
        log.debug("debug log= 글 반응 최초반응");

        // given
        User user = User.builder()
                .email("테스트 계정")
                .name("테스트")
                .role(RoleType.USER)
                .provider(ProviderType.DEFAULT)
                .build();
        userRepository.save(user);

        Post post = Post.builder()
                .title("DEFAULT 테스트 제목")
                .content("DEFAULT 테스트 본문")
                .postType(PostType.DEFAULT)
                .build();
        post.setUser(user);
        postRepository.save(post);

        // when
        Long findUserId = user.getUserId();
        Long findPostId = post.getPostId();

        Long findPostReactionId = postService.registerPostReaction(findUserId, findPostId, "GOOD");

        // then
        PostReaction findPostReaction = postReactionRepository.findById(findPostReactionId)
                .orElseThrow(() -> new IllegalStateException("존재하지 않는 반응입니다. postReactionId=" + findPostReactionId));

        assertThat(post.getGood()).isEqualTo(1);
        assertThat(post.getBad()).isEqualTo(0);
        assertThat(findPostReaction.getReaction()).isEqualTo(ReactionType.GOOD);

    }

    @Test
    void 글_반응_반응수정(){
        log.debug("debug log= 글 반응 반응수정");

        // given
        User user = User.builder()
                .email("테스트 계정")
                .name("테스트")
                .role(RoleType.USER)
                .provider(ProviderType.DEFAULT)
                .build();
        userRepository.save(user);

        Post post = Post.builder()
                .title("DEFAULT 테스트 제목")
                .content("DEFAULT 테스트 본문")
                .postType(PostType.DEFAULT)
                .build();
        post.setUser(user);
        postRepository.save(post);

        // when
        Long findUserId = user.getUserId();
        Long findPostId = post.getPostId();

        postService.registerPostReaction(findUserId, findPostId, "GOOD");
        Long findPostReactionId = postService.registerPostReaction(findUserId, findPostId, "BAD");

        // then
        PostReaction findPostReaction = postReactionRepository.findById(findPostReactionId)
                .orElseThrow(() -> new IllegalStateException("존재하지 않는 반응입니다. postReactionId=" + findPostReactionId));

        assertThat(post.getBad()).isEqualTo(1);
        assertThat(post.getGood()).isEqualTo(0);
        assertThat(findPostReaction.getReaction()).isEqualTo(ReactionType.BAD);

    }

    // 글 반응 취소
    //void deletePostReaction();
    @Test
    void 글_반응_취소(){
        log.debug("debug log= 초기 DB");

        // given

        // when

        // then

    }

    @Test
    void 글_신고(){
        log.debug("debug log= 글 신고");

        // given
        User user = User.builder()
                .email("테스트 계정")
                .name("테스트")
                .role(RoleType.USER)
                .provider(ProviderType.DEFAULT)
                .build();
        userRepository.save(user);

        Post post = Post.builder()
                .title("DEFAULT 테스트 제목")
                .content("DEFAULT 테스트 본문")
                .postType(PostType.DEFAULT)
                .build();
        post.setUser(user);
        postRepository.save(post);

        // when
        Long findUserId = user.getUserId();
        Long findPostId = post.getPostId();

        Long findPostReportId = postService.registerPostReport(findUserId, findPostId, "OTHER");

        // then
        PostReport findPostReport = postReportRepository.findById(findPostReportId)
                .orElseThrow(() -> new IllegalStateException("존재하지 않는 반응입니다. postReportId=" + findPostReportId));

        assertThat(findPostReport.getReportType()).isEqualTo(ReportType.OTHER);

    }

    @Test
    void 글_신고_중복(){
        log.debug("debug log= 글 신고 중복");

        // given
        User user = User.builder()
                .email("테스트 계정")
                .name("테스트")
                .role(RoleType.USER)
                .provider(ProviderType.DEFAULT)
                .build();
        userRepository.save(user);

        Post post = Post.builder()
                .title("DEFAULT 테스트 제목")
                .content("DEFAULT 테스트 본문")
                .postType(PostType.DEFAULT)
                .build();
        post.setUser(user);
        postRepository.save(post);

        // when
        Long findUserId = user.getUserId();
        Long findPostId = post.getPostId();

        Long postReportId = postService.registerPostReport(findUserId, findPostId, "OTHER");

        // then
        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> {
            postService.registerPostReport(findUserId, findPostId, "OTHER");
        });

        assertThat(exception.getMessage()).isEqualTo("이미 신고했습니다.");
    }


}*/
