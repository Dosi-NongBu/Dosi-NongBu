import React, { useState } from "react";
import "./style/Comment.css";
import { postCommentReaction, putComment, deleteComment } from "../../util/api";

function Comment({
  profileImage,
  commentId,
  commentContent,
  likeCount,
  dislikeCount,
}) {
  const [isEditing, setIsEditing] = useState(false);
  const [newCommentContent, setNewCommentContent] = useState(commentContent);

  // 댓글 반응
  const sendCommentReaction = async (type) => {
    await postCommentReaction(Number(commentId), type);
  };

  // 댓글 수정
  const handleUpdateComment = async () => {
    await putComment(Number(commentId), newCommentContent);
    setIsEditing(false);
  };

  // 댓글 삭제
  const handleDeleteComment = async () => {
    await deleteComment(Number(commentId));
    // 댓글 삭제 후 UI 갱신
  };

  return (
    <div className="comment">
      <div className="comment-profile">
        <img
          src={profileImage}
          alt="profile"
          className="comment-profile-image"
        />
      </div>
      <div className="comment-body">
        {isEditing ? (
          <textarea
            className="comment-edit"
            value={newCommentContent}
            onChange={(e) => setNewCommentContent(e.target.value)}
          />
        ) : (
          <p className="comment-content">{commentContent}</p>
        )}
        <div className="comment-actions">
          <button
            onClick={() => {
              sendCommentReaction("GOOD");
            }}
          >
            👍 {likeCount}
          </button>
          <button
            onClick={() => {
              sendCommentReaction("BAD");
            }}
          >
            👎 {dislikeCount}
          </button>
          <button onClick={() => setIsEditing(!isEditing)}>
            {isEditing ? "취소" : "수정"}
          </button>
          {isEditing ? (
            <button onClick={handleUpdateComment}>저장</button>
          ) : (
            <button onClick={handleDeleteComment}>삭제</button>
          )}
        </div>
      </div>
    </div>
  );
}

export default Comment;
