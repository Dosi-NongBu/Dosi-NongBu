package MIN.DosiNongBu.exception;

import lombok.Getter;

@Getter
public class ErrorDto {

    private String code;
    private String message;

    public ErrorDto(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
