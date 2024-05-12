package MIN.DosiNongBu.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class Controller {

    @GetMapping("/api/hello")
    public String test(){
        return "테스트";
    }

    @GetMapping("/test1")
    public String test1(HttpServletResponse response) throws IOException {
        response.addHeader("Authorization", "test1");

        response.sendRedirect("/test2");
        return "test1";
    }

    @GetMapping("/test2")
    public String test2(HttpServletResponse response) throws IOException {
        response.addHeader("Authorization", "test2");

        response.sendRedirect("/end");
        return "test2";
    }

    @GetMapping("/end")
    public String end(HttpServletResponse response){
        response.addHeader("Authorization", "end");

        return "end";
    }
}
