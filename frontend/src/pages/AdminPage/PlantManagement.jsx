import axios from "axios";
import React, { useState } from "react";

import "./style/AdminPage.css";

const PlantManagement = () => {
  const [plantData, setPlantData] = useState({
    name: "",
    difficulty: 0,
    maxTemperature: 0,
    minTemperature: 0,
    humidity: 0,
    month: 0,
    imageUrls: [],
    classification: "",
    origin: "",
    feature: "",
    planting: "",
    cultivation: "",
    pest: "",
    tip: "",
    harvestManage: "",
    water: 0,
    ventilation: 0,
    repot: 0,
    pruning: 0,
  });

  const handleInputChange = (e) => {
    const { name, value, type } = e.target;
    let parsedValue = value;

    if (type === "number") {
      parsedValue = parseFloat(value);
    }

    setPlantData((prevData) => ({
      ...prevData,
      [name]: parsedValue,
    }));
  };

  const handleImageUpload = (index, file) => {
    setPlantData((prevData) => ({
      ...prevData,
      imageUrls: prevData.imageUrls.map((url, i) =>
        i === index ? file.name : url
      ),
    }));
  };

  const handleAddImageUrl = () => {
    setPlantData((prevData) => ({
      ...prevData,
      imageUrls: [...prevData.imageUrls, ""],
    }));
  };

  const handleSubmit = async () => {
    console.log("plant data = ", plantData);
    const response = await axios.post(`/api/v1/admins/crops/add`, plantData);
    if (response.status === 200 || response.status === 201) {
      console.log("작물 추가 성공");
    }
  };

  return (
    <div className="admin-container">
      <h1>신규 작물 추가</h1>
      <div>
        <label htmlFor="name">Name:</label>
        <input
          type="text"
          id="name"
          name="name"
          value={plantData.name}
          onChange={handleInputChange}
        />
      </div>
      <div>
        <label htmlFor="difficulty">Difficulty:</label>
        <input
          type="number"
          id="difficulty"
          name="difficulty"
          value={plantData.difficulty}
          onChange={handleInputChange}
        />
      </div>
      <div>
        <label htmlFor="maxTemperature">Max Temperature:</label>
        <input
          type="number"
          id="maxTemperature"
          name="maxTemperature"
          value={plantData.maxTemperature}
          onChange={handleInputChange}
        />
      </div>
      <div>
        <label htmlFor="minTemperature">Min Temperature:</label>
        <input
          type="number"
          id="minTemperature"
          name="minTemperature"
          value={plantData.minTemperature}
          onChange={handleInputChange}
        />
      </div>
      <div>
        <label htmlFor="humidity">Humidity:</label>
        <input
          type="number"
          id="humidity"
          name="humidity"
          value={plantData.humidity}
          onChange={handleInputChange}
        />
      </div>
      <div>
        <label htmlFor="month">Month:</label>
        <input
          type="number"
          id="month"
          name="month"
          value={plantData.month}
          onChange={handleInputChange}
        />
      </div>
      <div>
        <h4>이미지</h4>
        {plantData.imageUrls.map((url, index) => (
          <div key={index}>
            <label htmlFor={`imageUrl${index + 1}`}>Image {index + 1}:</label>
            <input
              type="file"
              id={`imageUrl${index + 1}`}
              name={`imageUrl${index + 1}`}
              onChange={(e) => handleImageUpload(index, e.target.files[0])}
            />
          </div>
        ))}
        <button onClick={handleAddImageUrl}>Add Image</button>
      </div>
      <div>
        <label htmlFor="classification">Classification:</label>
        <input
          type="text"
          id="classification"
          name="classification"
          value={plantData.classification}
          onChange={handleInputChange}
        />
      </div>
      <div>
        <label htmlFor="origin">Origin:</label>
        <input
          type="text"
          id="origin"
          name="origin"
          value={plantData.origin}
          onChange={handleInputChange}
        />
      </div>
      <div>
        <label htmlFor="feature">Feature:</label>
        <input
          type="text"
          id="feature"
          name="feature"
          value={plantData.feature}
          onChange={handleInputChange}
        />
      </div>
      <div>
        <label htmlFor="planting">Planting:</label>
        <input
          type="text"
          id="planting"
          name="planting"
          value={plantData.planting}
          onChange={handleInputChange}
        />
      </div>
      <div>
        <label htmlFor="cultivation">Cultivation:</label>
        <input
          type="text"
          id="cultivation"
          name="cultivation"
          value={plantData.cultivation}
          onChange={handleInputChange}
        />
      </div>
      <div>
        <label htmlFor="pest">Pest:</label>
        <input
          type="text"
          id="pest"
          name="pest"
          value={plantData.pest}
          onChange={handleInputChange}
        />
      </div>
      <div>
        <label htmlFor="tip">Tip:</label>
        <input
          type="text"
          id="tip"
          name="tip"
          value={plantData.tip}
          onChange={handleInputChange}
        />
      </div>
      <div>
        <label htmlFor="harvestManage">Harvest Manage:</label>
        <input
          type="text"
          id="harvestManage"
          name="harvestManage"
          value={plantData.harvestManage}
          onChange={handleInputChange}
        />
      </div>
      <div>
        <label htmlFor="water">Water:</label>
        <input
          type="number"
          id="water"
          name="water"
          value={plantData.water}
          onChange={handleInputChange}
        />
      </div>
      <div>
        <label htmlFor="ventilation">Ventilation:</label>
        <input
          type="number"
          id="ventilation"
          name="ventilation"
          value={plantData.ventilation}
          onChange={handleInputChange}
        />
      </div>
      <div>
        <label htmlFor="repot">Repot:</label>
        <input
          type="number"
          id="repot"
          name="repot"
          value={plantData.repot}
          onChange={handleInputChange}
        />
      </div>
      <div>
        <label htmlFor="pruning">Pruning:</label>
        <input
          type="number"
          id="pruning"
          name="pruning"
          value={plantData.pruning}
          onChange={handleInputChange}
        />
      </div>
      <button type="submit" onClick={handleSubmit}>
        Save
      </button>
    </div>
  );
};

export default PlantManagement;
