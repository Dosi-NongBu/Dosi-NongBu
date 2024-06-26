import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import "./style/Board.css";
import Pagination from "../common/Pagination";
import Button from "../common/Button";
import Status from "./Status";
import { getFAQList, getNoticeList, getRequestList } from "../../util/api";

const Board = ({ type }) => {
  const nav = useNavigate();
  const [posts, setPosts] = useState([]);
  const [nowPage, setNowPage] = useState(0);

  useEffect(() => {
    let data;
    const fetchData = async () => {
      if (type === "FAQ") {
        data = await getFAQList(nowPage, 10);
      } else if (type === "NOTICE") {
        data = await getNoticeList(nowPage, 10);
      } else if (type === "REQUEST") {
        data = await getRequestList(nowPage, 10);
      }
      setPosts(data);
      console.log("게시판 리스트 : ", data);
    };
    fetchData();
  }, [nowPage, type]);

  const handleMoveDetail = (index) => {
    if (type === "FAQ") {
      nav(`/faq/${index}`);
    } else if (type === "NOTICE") {
      nav(`/notice/${index}`);
    } else if (type === "REQUEST") {
      nav(`/request/${index}`);
    }
  };

  return (
    <div className="board">
      {type === "REQUEST" && (
        <Button
          title="질문 작성"
          type="negative"
          onClick={() => {
            nav("/request/register");
          }}
        />
      )}
      <table>
        <thead>
          <tr className="table-header-row">
            <th className="column-id">글 번호</th>
            <th className="column-title">제목</th>
            <th className="column-date">등록일자</th>
            {type === "REQUEST" && <th className="column-author">작성자</th>}
            {type === "REQUEST" && <th className="column-status">처리 상태</th>}
          </tr>
        </thead>
        <tbody>
          {posts.map((post, index) => (
            <tr
              key={index}
              className="table-row"
              onClick={() => handleMoveDetail(post.id)}
            >
              <td className="column-id">{post.id}</td>
              <td className="column-title">{post.title}</td>
              <td className="column-date">
                {post.createdDate && post.createdDate.split("T")[0]}
              </td>
              {type === "REQUEST" && (
                <td className="column-author">{post.author}</td>
              )}
              {type === "REQUEST" && (
                <td className="column-status">
                  <Status type={post.inquiryStatusType} />
                </td>
              )}
            </tr>
          ))}
        </tbody>
      </table>
      <Pagination totalPage={5} nowPage={nowPage} onClick={setNowPage} />
    </div>
  );
};

export default Board;
