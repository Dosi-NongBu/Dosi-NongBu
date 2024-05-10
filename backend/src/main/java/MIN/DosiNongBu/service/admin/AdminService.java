package MIN.DosiNongBu.service.admin;

public interface AdminService {

    // 신규 작물 등록
    void registerNewCrop();
    // 사용자 정보 목록 조회
    void viewUserList();
    // 사용자 정보 조히
    void viewUser();
    // 사용자 정보 추가
    void registerUser();
    // 사용자 정보 수정
    void updateUser();
    // 1:1 문의 답변 등록
    void registerInquiryResponse();
    // 1:1 문의 답변 수정
    void updateInquiryResponse();
    // 공지사항 등록
    void registerNotice();
    // 공지사항 수정
    void updateNotice();
    // FAQ 등록
    void registerFAQ();
    // FAQ 수정
    void updateFAQ();

}
