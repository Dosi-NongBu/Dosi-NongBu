import React from "react";

import "./style/SearchBar.css";
import Button from "./Button";

const SearchBar = ({ onSearchChange, onClickButton }) => {
  return (
    <div className="searchBar">
      <div className="search-container">
        <input type="text" onChange={onSearchChange} />
        <Button title={"검색"} onClick={onClickButton} />
      </div>
    </div>
  );
};

export default SearchBar;
