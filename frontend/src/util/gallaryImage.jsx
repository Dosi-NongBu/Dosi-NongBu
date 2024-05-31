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

export const makeSendImage = (data) => {
  const imageURLs = data.map((obj) => obj.original);
  console.log("변환됨 ", imageURLs);
  return imageURLs;
};
