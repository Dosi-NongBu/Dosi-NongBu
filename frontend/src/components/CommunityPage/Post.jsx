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
  const [comment, setComment] = useState("");
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
  useEffect(() => {
    const fetchComment = async () => {
      const data = await getComment(Number(postId));
      console.log("댓글 내용", data);
      setComment(data);
    };
    fetchComment();
  }, [postId]);

  const handleLike = async () => {
    setLikeCount(likeCount + 1);
    await postCommunityReaction(Number(postId), "GOOD");
    fetchData();
  };

  const handleDislike = async () => {
    setDislikeCount(dislikeCount + 1);
    await postCommunityReaction(Number(postId), "BAD");
    fetchData();
  };

  const handleSubmitComment = async (e) => {
    e.preventDefault();
    await postComment(Number(postId), comment);
    setComment("");
  };

  const handleEdit = () => {
    nav(`/community/edit/${Number(postId)}`);
  };

  const handleDelete = async () => {
    await deleteCommunityPost(Number(postId));
  };

  // const comments = [
  //   {
  //     commentId: 1,
  //     author: "강희민",
  //     profileImage: "https://randomuser.me/api/portraits/men/1.jpg",
  //     content: "정말 좋은 글입니다! 많은 도움이 되었습니다.",
  //     good: 5,
  //     bad: 0,
  //   },
  //   {
  //     commentId: 2,
  //     author: "강희민",
  //     profileImage: "https://randomuser.me/api/portraits/women/2.jpg",
  //     content: "좋은 정보 감사합니다. 앞으로도 좋은 글 부탁드려요.",
  //     good: 3,
  //     bad: 1,
  //   },
  //   {
  //     commentId: 3,
  //     author: "강희민",
  //     profileImage: "https://randomuser.me/api/portraits/men/3.jpg",
  //     content:
  //       "이 부분에 대해 좀 더 자세히 알고 싶어요.이 부분에 대해 좀 더 자세히 알고 싶어요.이 부분에 대해 좀 더 자세히 알고 싶어요.이 부분에 대해 좀 더 자세히 알고 싶어요.",
  //     good: 2,
  //     bad: 0,
  //   },
  // ];

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
        <button onClick={handleLike}>👍 {likeCount}</button>
        <button onClick={handleDislike}>👎 {dislikeCount}</button>
        <button onClick={handleEdit}> 수정</button>
        <button onClick={handleDelete}> 삭제</button>
      </div>

      <form className="comment-form" onSubmit={handleSubmitComment}>
        <input
          type="text"
          placeholder="댓글을 작성하세요..."
          value={comment}
          onChange={(e) => setComment(e.target.value)}
        />
        <button type="submit">제출</button>
      </form>

      {comment.map((comment) => (
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
