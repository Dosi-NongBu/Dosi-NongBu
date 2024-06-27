import React, { useEffect, useState } from "react";
import { useNavigate, useParams, useLocation } from "react-router-dom";
import Gallery from "../common/Gallery";
import Button from "../common/Button";
import {
  postCommunityPost,
  putCommunityPost,
  getCommunityPost,
} from "../../util/api";
import "./style/Post.css";

const CommunityEditorContainer = ({ type }) => {
  const { postId } = useParams();
  const location = useLocation();
  const nav = useNavigate();
  const [request, setRequest] = useState({});
  const [images, setImages] = useState([]);
  const [title, setTitle] = useState("");
  const [content, setContent] = useState("");
  const [isEditMode, setIsEditMode] = useState(false);

  useEffect(() => {
    if (location.pathname.includes("edit")) {
      setIsEditMode(true);
      // 기존 글 데이터를 로드
      const fetchPost = async () => {
        const postData = await getCommunityPost(Number(postId));
        setTitle(postData.title);
        setContent(postData.content);
        setImages(postData.imageUrls);
      };
      fetchPost();
    } else {
      setIsEditMode(false);
    }
  }, [location, postId]);

  const handleSubmit = async () => {
    if (!validateInput()) return;

    const newRequest = {
      postType: type,
      title: title,
      content: content,
      imageUrls: images,
    };
    setRequest(newRequest);
    console.log("Request: ", newRequest);

    if (isEditMode) {
      // 수정 모드일 때
      await putCommunityPost(Number(postId), newRequest);
      nav(`/community/${postId}`);
    } else {
      // 새 글 작성 모드일 때
      await postCommunityPost(type, newRequest);
      nav("/community");
    }
  };

  const handleAddImage = (event) => {
    if (event) {
      console.log("event = ", event);
      const newImages = [...images, event];
      setImages(newImages);
      console.log("newImages = ", newImages);
    }
  };

  const validateInput = () => {
    if (!title) {
      alert("제목을 입력해주세요.");
      return false;
    }
    if (!content) {
      alert("내용을 입력해주세요.");
      return false;
    }
    return true;
  };

  return (
    <div className="register-container">
      <input
        type="text"
        placeholder="제목을 입력하세요"
        className="register-input"
        value={title}
        onChange={(e) => setTitle(e.target.value)}
      />
      <textarea
        type="text"
        placeholder="질문 내용을 입력하세요"
        className="register-input register-content"
        value={content}
        onChange={(e) => setContent(e.target.value)}
      />
      <Gallery
        type="WRITE"
        // setGalleryImages={(images) => setImages(images.map((img) => img))}
        setGalleryImages={handleAddImage}
        readImages={images}
      />

      <Button
        title={isEditMode ? "수정하기" : "등록하기"}
        type={"positive"}
        onClick={handleSubmit}
      />
    </div>
  );
};

export default CommunityEditorContainer;
