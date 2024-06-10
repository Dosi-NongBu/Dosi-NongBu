export const getInquiryType = (type) => {
  switch (type) {
    case "GENERAL_INQUIRY":
      return "일반 문의";
    case "ACCOUNT_ISSUE":
      return "계정 문의";
    case "TECHNICAL_SUPPORT":
      return "기술 지원";
    case "BILLING_ISSUE":
      return "결제 문의";
    case "FEEDBACK_SUGGESTION":
      return "피드백 및 제안";
  }
};
