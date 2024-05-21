package MIN.DosiNongBu.service.post;

import MIN.DosiNongBu.controller.post.dto.request.PostSaveRequestDto;
import MIN.DosiNongBu.controller.post.dto.request.PostUpdateRequestDto;
import MIN.DosiNongBu.controller.post.dto.response.PostListResponseDto;
import MIN.DosiNongBu.controller.post.dto.response.PostResponseDto;
import MIN.DosiNongBu.domain.post.Post;
import MIN.DosiNongBu.domain.post.constant.PostType;
import MIN.DosiNongBu.domain.post.constant.ReactionType;
import MIN.DosiNongBu.domain.post.constant.ReportType;
import MIN.DosiNongBu.repository.post.PostRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
@Transactional
class PostServiceImplTest {

    @Autowired PostRepository postRepository;

    Post post;

    @AfterEach
    void add(){
        log.debug("debug log= 초기 DB");

        post = Post.builder()
                

                .build();

    }

    @BeforeEach
    void clear(){
        log.debug("debug log= DB 삭제");

    }

    // 카테고리별 목록 조회
    //List<PostListResponseDto> viewPostList(PostType postType, Pageable pageable);
    @Test
    void 카테고리별_목록_조회(){

    }

    // 글 등록
    //Long registerPost(PostType postType, PostSaveRequestDto requestDto);
    @Test
    void 글_등록(){

    }

    // 글 조회
    //PostResponseDto viewPost(Long postId);
    @Test
    void 글_조회(){

    }

    // 글 수정
    //Long updatePost(Long postId, PostUpdateRequestDto requestDto);
    @Test
    void 글_수정(){

    }

    // 글 삭제
    //Long deletePost(Long postId);
    @Test
    void 글_삭제(){

    }

    // 글 반응
    //void registerPostReaction(Long userId, Long postId, ReactionType reactionType);
    @Test
    void 글_반응(){

    }

    // 글 반응 취소
    //void deletePostReaction();
    @Test
    void 글_반응_취소(){

    }

    // 글 신고
    //void registerPostReport(Long userId, Long postId, ReportType reportType);
    @Test
    void 글_신고(){

    }

}