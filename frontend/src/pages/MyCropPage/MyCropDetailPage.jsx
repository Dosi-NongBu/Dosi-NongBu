import React from "react";
import { useParams } from "react-router-dom";
import MyCropDetailContainer from "../../components/MyCropPage/MyCropDetailContainer";

const MyCropDetailPage = () => {
  const { cropId } = useParams();
  console.log("id ==== ", cropId);
  return (
    <div>
      <MyCropDetailContainer userCropId={cropId} />
    </div>
  );
};

export default MyCropDetailPage;
