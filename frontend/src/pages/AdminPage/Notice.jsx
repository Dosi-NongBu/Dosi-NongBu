import React, { useState, useEffect } from "react";
import { getNoticeList } from "../../util/api";
import Pagination from "../../components/common/Pagination";
import OfficialContainer from "../../components/OfficialPage/OfficialContainer";
import Button from "../../components/common/Button";
import OfficialEditor from "./OfficialEditor";
import axios from "axios";

const Notice = () => {
  const [noticeList, setNoticeList] = useState();
  const [nowPage, setNowPage] = useState(0);
  const [selectNotice, setSelectNotice] = useState();
  const [mode, setMode] = useState("SHOW");

  useEffect(() => {
    const fetchList = async () => {
      const response = await getNoticeList(nowPage, 10);
      setNoticeList(response);
      console.log(response);
    };
    fetchList();
  }, [nowPage]);

  const handleSelectNotice = (id) => {
    setSelectNotice(id);
    setMode("SHOW");
  };

  const handleSubmitEdit = async (data) => {
    console.log("edit = ", data);
    try {
      await axios.put(`/api/v1/admins/notices/${selectNotice}`, data);
    } catch (error) {
      console.log(error);
    }
    setMode("SHOW");
  };

  const handleSubmitNew = async (data) => {
    console.log("new = ", data);
    try {
      await axios.post(
        `/api/v1/admins/notices?noticeType=${data.noticeType}`,
        data
      );
    } catch (error) {
      console.log(error);
    }
    setMode("SHOW");
  };

  if (!noticeList) {
    return <div>Loading...</div>;
  }

  return (
    <div className="admin-container">
      <h1 className="title">공지사항</h1>
      <Button
        title={"등록"}
        onClick={() => {
          setMode("NEW");
        }}
      />
      <table className="user-table">
        <thead>
          <tr>
            <th>ID</th>
            <th>제목</th>
            <th>수정일</th>
          </tr>
        </thead>
        <tbody>
          {noticeList.map((item, index) => (
            <tr
              key={index}
              className={index % 2 === 0 ? "even-row" : "odd-row"}
              onClick={() => {
                handleSelectNotice(item.id);
              }}
            >
              <td>{item.id}</td>
              <td>{item.title}</td>
              <td>{item.modifiedDate}</td>
            </tr>
          ))}
        </tbody>
      </table>
      <Pagination totalPage={5} nowPage={nowPage} onClick={setNowPage} />

      {mode === "SHOW" && selectNotice && (
        <>
          <OfficialContainer type="NOTICE" id={selectNotice} />
          <Button
            title={"수정"}
            onClick={() => {
              setMode("EDIT");
            }}
          />
        </>
      )}

      {mode === "EDIT" && (
        <OfficialEditor
          id={selectNotice}
          onSubmit={handleSubmitEdit}
          type="NOTICE"
        />
      )}
      {mode === "NEW" && (
        <OfficialEditor onSubmit={handleSubmitNew} type="NOTICE" />
      )}
    </div>
  );
};

export default Notice;
