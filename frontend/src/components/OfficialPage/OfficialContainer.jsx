import React, { useEffect, useState } from "react";
import "./style/OfficialContainer.css";
import Button from "../common/Button";
import { useNavigate, useParams } from "react-router-dom";
import ImageGallery from "react-image-gallery";
import { getFAQDetail, getNoticeDetail } from "../../util/api";

const OfficialContainer = ({ type }) => {
  const nav = useNavigate();
  const params = useParams();

  const handleToList = () => {
    if (type === "FAQ") {
      nav("/faq");
    } else if (type === "NOTICE") {
      nav("/notice");
    }
  };

  const [detail, setDetail] = useState();

  useEffect(() => {
    let data;
    const fetchData = async () => {
      if (type === "FAQ") {
        data = await getFAQDetail(Number(params.faqId));
      } else if (type === "NOTICE") {
        data = await getNoticeDetail(Number(params.noticeId));
      }
      console.log("data=", data);
      setDetail(data);
    };
    fetchData();
  }, [params.faqId, params.noticeId, type]);

  if (!detail) {
    return <div>Loading...</div>;
  }

  return (
    <div className="faqContainer">
      {type === "NOTICE" && (
        <>
          <h2 className="faq-title">{detail.title}</h2>
          <div className="faq-semiTitle">
            <span>{detail.noticeType}</span>
          </div>
          <div className="faq-content">{detail.content}</div>
          <div className="faq-images">
            {detail.imageUrls && detail.imageUrls.length > 0 && (
              <ImageGallery items={detail.imageUrls} />
            )}
          </div>
          <div className="tolist-container">
            <Button
              title={"목록으로"}
              type={"positive"}
              onClick={handleToList}
            />
          </div>
        </>
      )}

      {type === "FAQ" && (
        <>
          <h2 className="faq-title">{detail.question}</h2>
          <div className="faq-semiTitle">
            <span>{detail.faqType}</span>
          </div>
          <div className="faq-content">{detail.answer}</div>
          <div className="faq-images">
            {detail.imageUrls && detail.imageUrls.length > 0 && (
              <ImageGallery items={detail.imageUrls} />
            )}
          </div>
          <div className="tolist-container">
            <Button
              title={"목록으로"}
              type={"positive"}
              onClick={handleToList}
            />
          </div>
        </>
      )}
    </div>
  );
};

export default OfficialContainer;
