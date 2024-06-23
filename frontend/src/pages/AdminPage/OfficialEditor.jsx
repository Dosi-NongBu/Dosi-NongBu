import React, { useState, useEffect } from "react";
import { useNavigate, useParams } from "react-router-dom";
import Select from "../../components/common/Select";
import Gallery from "../../components/common/Gallery";
import Button from "../../components/common/Button";
import { getFAQDetail, getNoticeDetail } from "../../util/api";
import axios from "axios";

const OfficialEditor = ({ type, id, onSubmit }) => {
  const nav = useNavigate();
  const [notice, setNotice] = useState({});
  const [images, setImages] = useState([]);
  const [selected, setSelected] = useState("");
  const [title, setTitle] = useState("");
  const [content, setContent] = useState("");

  useEffect(() => {
    if (id) {
      const fetchNotice = async () => {
        let existingNotice;
        if (type === "NOTICE") {
          existingNotice = await getNoticeDetail(Number(id));
          setSelected(existingNotice.noticeType);
          setTitle(existingNotice.title);
          setContent(existingNotice.content);
          setImages(existingNotice.imagesUrls);
        } else if (type === "FAQ") {
          existingNotice = await getFAQDetail(Number(id));
          setSelected(existingNotice.faqType);
          setTitle(existingNotice.question);
          setContent(existingNotice.answer);
          setImages(existingNotice.imagesUrls);
        }
      };
      fetchNotice();
    }
  }, [type, id]);

  const handleSelectChange = (value) => {
    setSelected(value);
  };

  const handleSubmit = async () => {
    if (!validateInput()) return;

    // let newNotice;
    // if (type === "NOTICE") {
    //   newNotice = {
    //     noticeType: selected,
    //     title: title,
    //     content: content,
    //     imagesUrls: images ? images : [],
    //   };
    // } else if (type === "FAQ") {
    //   newNotice = {
    //     faqType: selected,
    //     question: title,
    //     answer: content,
    //     imagesUrls: images ? images : [],
    //   };
    // }
    const newNotice = {
      noticeType: selected,
      title: title,
      content: content,
      imagesUrls: images ? images : [],
    };
    console.log("new notice = ", newNotice);
    setNotice(newNotice);
    onSubmit(newNotice);
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
      {type === "NOTICE" && (
        <Select
          onSelectChange={handleSelectChange}
          value={selected}
          type={"NOTICE"}
        />
      )}
      {type === "FAQ" && (
        <Select
          onSelectChange={handleSelectChange}
          value={selected}
          type={"FAQ"}
        />
      )}
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
        setGalleryImages={(images) => setImages(images.map((img) => img.name))}
        initialImages={images}
      />
      <Button
        title={id ? "수정하기" : "등록하기"}
        type={"positive"}
        onClick={handleSubmit}
      />
    </div>
  );
};

export default OfficialEditor;
