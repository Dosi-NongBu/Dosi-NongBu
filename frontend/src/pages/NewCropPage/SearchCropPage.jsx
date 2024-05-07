import React, { useState } from "react";
import CropBoxContainer from "../../components/NewCropPage/CropBoxContainer";
import SelectContainer from "../../components/NewCropPage/SelectContainer";

const SearchCropPage = () => {
  const [type, setType] = useState("none");

  const handleSelectButton = (selectedType) => {
    setType(selectedType);
  };

  return (
    <div>
      <SelectContainer onChangeSelect={handleSelectButton} />
      <CropBoxContainer type={type} />
    </div>
  );
};

export default SearchCropPage;
