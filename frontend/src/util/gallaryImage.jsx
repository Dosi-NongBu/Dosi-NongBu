export const makeOriginalThumbnail = (initialData) => {
  const formattedData = initialData.imageUrls.map((url, index) => ({
    id: index + 1,
    original: url.imageUrl,
    thumbnail: url.imageUrl,
  }));

  const updatedData = {
    ...initialData,
    imageUrls: formattedData,
  };

  return updatedData;
};

export const makeSendImage = (imageUrls) => {
  console.log("image Urls -> ", imageUrls);
  const formattedData = imageUrls.map((url, index) => ({
    imageUrl: url.original,
  }));
  console.log("send data ", formattedData);
  return formattedData;

  // const imageURL = {
  //   img1URL: null,
  //   img2URL: null,
  //   img3URL: null,
  //   img4URL: null,
  //   img5URL: null,
  // };

  // return imageURL;
};
