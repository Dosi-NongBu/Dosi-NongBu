export function stringToNumberArray(str) {
  const parts = str.split(",");

  const numberArray = parts.map((part) => parseInt(part, 10));

  return numberArray;
}
