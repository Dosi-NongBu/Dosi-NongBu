import React, { useEffect, useState, useCallback } from "react";
import "./style/Post.css";
import {
  deleteCommunityPost,
  getComment,
  getCommunityPost,
  postComment,
  postCommunityReaction,
} from "../../util/api";
import { useNavigate, useParams } from "react-router-dom";
import Comment from "./Comment";
import Gallery from "../common/Gallery";

const Post = () => {
  const { postId } = useParams();
  const nav = useNavigate();

  const [postData, setPostData] = useState({});
  const [likeCount, setLikeCount] = useState(0);
  const [dislikeCount, setDislikeCount] = useState(0);
  const [comments, setComments] = useState([]);
  const [newComment, setNewComment] = useState("");
  const [editedContent, setEditedContent] = useState("");

  // 글 내용 조회
  const fetchData = useCallback(async () => {
    const data = await getCommunityPost(Number(postId));
    setPostData(data);
    setLikeCount(data.good);
    setDislikeCount(data.bad);
    setEditedContent(data.content);
  }, [postId]);

  useEffect(() => {
    fetchData();
  }, [fetchData]);

  // 댓글 내용 조회
  const fetchComment = useCallback(async () => {
    const data = await getComment(Number(postId));
    console.log("댓글 내용", data);
    setComments(data);
  }, [postId]);

  useEffect(() => {
    fetchComment();
  }, [fetchComment]);

  // 글 좋아요 / 싫어요
  const handleReaction = async (type) => {
    await postCommunityReaction(Number(postId), type);
    fetchData();
  };

  // 댓글 제출 버튼
  const handleSubmitComment = async (e) => {
    e.preventDefault();
    await postComment(Number(postId), newComment);
    setNewComment("");
    fetchComment();
  };

  // 글 수정
  const handleEdit = () => {
    nav(`/community/edit/${Number(postId)}`);
  };

  // 글 삭제
  const handleDelete = async () => {
    if (!window.confirm("정말 삭제하시겠습니까?")) {
      return;
    }
    await deleteCommunityPost(Number(postId));
    nav("/community");
  };

  return (
    <div className="post">
      <div className="post-header">
        <img
          src={postData.profileImage}
          alt="profile"
          className="profile-image"
        />
        <div className="post-info">
          <h2>{postData.title}</h2>
          <p>
            {postData.nickname} | {postData.updateDate}
          </p>
        </div>
      </div>
      <div className="post-content">{postData.content}</div>

      <Gallery type="READ" readImages={postData.imageUrls} />

      <div className="post-footer">
        <button
          onClick={() => {
            handleReaction("GOOD");
          }}
        >
          👍 {likeCount}
        </button>
        <button
          onClick={() => {
            handleReaction("BAD");
          }}
        >
          👎 {dislikeCount}
        </button>
        <button onClick={handleEdit}> 수정</button>
        <button onClick={handleDelete}> 삭제</button>
      </div>

      <form className="comment-form" onSubmit={handleSubmitComment}>
        <input
          type="text"
          placeholder="댓글을 작성하세요..."
          value={newComment}
          onChange={(e) => setNewComment(e.target.value)}
        />
        <button type="submit">제출</button>
      </form>

      {comments &&
        comments.map((comment) => (
          <Comment
            key={comment.commentId}
            commentId={comment.commentId}
            profileImage={comment.profileImage}
            commentContent={comment.content}
            likeCount={comment.good}
            dislikeCount={comment.bad}
          />
        ))}
    </div>
  );
};

export default Post;
