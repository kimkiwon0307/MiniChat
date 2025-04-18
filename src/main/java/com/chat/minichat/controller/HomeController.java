package com.chat.minichat.controller;



import com.chat.minichat.dto.UserDTO;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {


    @GetMapping("/chat")
    public String chat(HttpSession session, Model model) {
        // 세션에서 닉네임을 가져옴
        String nickname = (String) session.getAttribute("nickname");

        // 닉네임이 없을 경우 처리 (예: 로그인 페이지로 리디렉션)
        if (nickname == null) {
            return "redirect:/login"; // 로그인 페이지로 리디렉션
        }

        // 닉네임을 모델에 추가해서 chat 페이지로 전달
        model.addAttribute("nickname", nickname);

        return "chat"; // chat.html로 리턴
    }

    @RequestMapping("/home")
    public String home(HttpSession session, Model model) {


        String nickname = (String) session.getAttribute("nickname");


        // 사용자 정보를 DTO로 변환
        UserDTO userDTO = new UserDTO();
        userDTO.setNickname(nickname);

        // 모델에 DTO 전달
        model.addAttribute("user", userDTO);


        System.out.println("userDTO.toString() = " + userDTO.toString());

        // home 페이지로 리턴
        return "home";
    }

}
