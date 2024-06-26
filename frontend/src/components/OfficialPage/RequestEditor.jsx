import React, { useEffect, useState } from "react";
import { useParams, useNavigate } from "react-router-dom";
import Select from "../common/Select";
import Button from "../common/Button";
import Gallery from "../../components/common/Gallery";
import { getRequestDetail, postRequest, putRequest } from "../../util/api";

import "./style/RequestEditor.css";

const RequestEditor = () => {
  const { requestId } = useParams();
  const nav = useNavigate();
  const [request, setRequest] = useState({});
  const [images, setImages] = useState([]);
  const [selected, setSelected] = useState("");
  const [title, setTitle] = useState("");
  const [content, setContent] = useState("");

  useEffect(() => {
    if (requestId) {
      const fetchRequest = async () => {
        const existingRequest = await getRequestDetail(Number(requestId));
        setSelected(existingRequest.inquiryType);
        setTitle(existingRequest.title);
        setContent(existingRequest.content);
        setImages(existingRequest.imagesUrls);
      };
      fetchRequest();
    }
  }, [requestId]);

  const handleSelectChange = (value) => {
    setSelected(value);
  };

  const handleSubmit = async () => {
    if (!validateInput()) return;

    const newRequest = {
      inquiryType: selected,
      title: title,
      content: content,
      imagesUrls: images,
    };
    setRequest(newRequest);
    console.log("Request: ", newRequest);

    try {
      if (requestId) {
        // 기존 내용을 수정하는 경우
        await putRequest(requestId, newRequest);
        alert("수정되었습니다.");
      } else {
        // 새로운 내용을 등록하는 경우
        await postRequest(newRequest);
        alert("등록되었습니다.");
      }
      nav("/request");
    } catch (error) {
      console.error(error);
      alert("오류가 발생했습니다. 다시 시도해주세요.");
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
    if (!selected) {
      alert("문의 유형을 선택해주세요.");
      return false;
    }
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
      <Select
        onSelectChange={handleSelectChange}
        value={selected}
        type="REQUEST"
      />
      <input
        type="text"
        placeholder="제목을 입력하세요"
        className="register-input"
        value={title ? title : ""}
        onChange={(e) => setTitle(e.target.value)}
      />
      <textarea
        type="text"
        placeholder="질문 내용을 입력하세요"
        className="register-input register-content"
        value={content ? content : ""}
        onChange={(e) => setContent(e.target.value)}
      />

      <Gallery
        type="WRITE"
        setGalleryImages={handleAddImage}
        initialImages={images}
      />

      <Button
        title={requestId ? "수정하기" : "등록하기"}
        type={"positive"}
        onClick={handleSubmit}
      />
    </div>
  );
};

export default RequestEditor;
