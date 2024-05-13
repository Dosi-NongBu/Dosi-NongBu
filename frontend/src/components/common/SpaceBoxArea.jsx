import React, { useState, useRef, useEffect } from "react";
import InputBox from "./InputBox";
import Button from "./Button";

import "./style/SpaceBoxArea.css";
import { getDataInEng } from "../../util/getDataInEng";
import { getExistingUserSpace, postNewUserPlace } from "../../util/api";

const SpaceBox = ({ type, name, onClick, isSelected }) => {
  return (
    <>
      {type === "existing" && (
        <div
          className={`spaceBox ${isSelected ? "space-box-selected" : ""}`}
          onClick={onClick}
        >
          {name}
        </div>
      )}
      {type === "add" && (
        <div className="spaceBox space-box-add" onClick={onClick}>
          {name}
        </div>
      )}
    </>
  );
};

const selectData = ["베란다(창 안)", "베란다(창 밖)", "실내"];
const selectData2 = ["남향", "동향", "서향"];
const selectData3 = ["직사광", "창문을 통한 빛", "없음"];
const selectData4 = ["많음", "중간", "적음"];

const SelectInfo = [
  { title: "장소 위치", select: selectData, setValue: "placeType" },
  { title: "방향", select: selectData2, setValue: "directionType" },
  { title: "햇빛", select: selectData3, setValue: "lightType" },
  { title: "햇빛 양", select: selectData4, setValue: "quantityType" },
];

const SpaceBoxArea = ({ onSelectSpace }) => {
  // API 로 가져올 것
  const [spaceData, setSpaceData] = useState([
    { id: 1, name: "공간1" },
    { id: 2, name: "베란다" },
    { id: 3, name: "책상 위" },
  ]);
  const idRef = useRef(4);
  const [addMoreButton, setAddMoreButton] = useState(
    // 공간을 더 추가할 수 있는지 여부
    spaceData.length < 5 ? true : false
  );

  const [selectedSpace, setSeletedSpace] = useState(0);
  const [onAdd, setOnAdd] = useState(false); // 공간 추가 섹션 표시
  const [newSpace, setNewSpace] = useState({});

  // 사용자 공간 조회 API
  // useEffect(()=>{
  //   const fetchUserSpace = async (token, page, size) => {
  //     let response = await getExistingUserSpace(token, page, size);
  //     setSpaceData(response);
  //   };

  //   // fetchUserSpace(token,page,size);
  // },[]);

  // 사용자 공간이 5개 이하일 때만 버튼 활성화
  useEffect(() => {
    setAddMoreButton(spaceData.length < 5 ? true : false);
  }, [spaceData]);

  // 공간 버튼을 클릭했을 떄
  const handleSelectSpace = (userSpaceId) => {
    setSeletedSpace(userSpaceId);
    onSelectSpace(userSpaceId);
  };

  // 새로운 공간 이름을 입력했을 때
  const handleNewSpaceName = (e) => {
    setNewSpace({ ...newSpace, id: idRef.current, name: e.target.value });
  };

  // 새로운 공간 정보 API 로 전송하기
  const handleCreateSpace = async () => {
    if (!newSpace.name) {
      alert("이름을 입력하세요");
      return;
    }
    if (Object.keys(newSpace).length < 6) {
      alert("모든 선택을 완료해주세요.");
      return;
    }

    setOnAdd(false);
    setSpaceData([...spaceData, newSpace]);
    setNewSpace({});
    idRef.current++;

    alert("새 공간이 등록되었습니다.");

    // jwt
    // const response = await postNewUserPlace(newSpace);
  };

  return (
    <>
      <div className="user-space-container">
        {spaceData.map((space) => (
          <SpaceBox
            key={space.id}
            type={"existing"}
            name={space.name}
            onClick={() => handleSelectSpace(space.id)}
            isSelected={selectedSpace === space.id}
          />
        ))}
        {addMoreButton && (
          <SpaceBox
            type={"add"}
            name={"+"}
            onClick={() => {
              setOnAdd(true);
            }}
          />
        )}
      </div>
      {onAdd && (
        <>
          <div className="add-space">
            <InputBox
              type={"text"}
              title={"장소 이름"}
              onChange={handleNewSpaceName}
            />
            {SelectInfo.map((item, index) => (
              <InputBox
                key={index}
                type={"select"}
                title={item.title}
                select={item.select}
                onSelect={(value) =>
                  setNewSpace({
                    ...newSpace,
                    [item.setValue]: getDataInEng(value),
                  })
                }
              />
            ))}
          </div>
          <Button
            title="공간 생성하기"
            type="positive"
            onClick={handleCreateSpace}
          />
        </>
      )}{" "}
    </>
  );
};

export default SpaceBoxArea;
