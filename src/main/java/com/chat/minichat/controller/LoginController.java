package com.chat.minichat.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String login() {
        return "login"; // 로그인 버튼 있는 HTML
    }

    @GetMapping("/loginSuccess")
    public String loginSuccess(@AuthenticationPrincipal OAuth2User oAuth2User, Model model, HttpSession session) {

        // 1. 전체 Attribute에서 properties 꺼내기
        Map<String, Object> properties = (Map<String, Object>) oAuth2User.getAttributes().get("properties");

        // 2. properties에서 nickname 꺼내기
        String nickname = (String) properties.get("nickname");

        System.out.println("nickname = " + nickname);

        // 세션
        session.setAttribute("nickname", nickname);

        model.addAttribute("user", oAuth2User.getAttributes());

        return "redirect:/home";
    }




}