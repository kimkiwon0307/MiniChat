package com.chat.minichat.controller;

import com.chat.minichat.dto.ChatMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;


@Controller
public class ChatController {

    @Autowired
    private SimpMessageSendingOperations messagingTemplate;

    // 메시지를 받으면 처리하는 메서드
    @MessageMapping("/chat.send")
    public void sendMessage(@Payload ChatMessage message) {

        System.out.println("왓니");
        System.out.println(message.toString());

        // 입장 메시지일 경우, 메시지 내용을 자동으로 구성
        if (ChatMessage.MessageType.JOIN.equals(message.getType())) {
            message.setContent(message.getSender() + "님이 입장하셨습니다.");
        }

        // 퇴장 메시지일 경우
        if (ChatMessage.MessageType.LEAVE.equals(message.getType())) {
            message.setContent(message.getSender() + "님이 퇴장하셨습니다.");
        }

        // /sub/chat/room 채널을 구독 중인 클라이언트들에게 전송
        messagingTemplate.convertAndSend("/sub/chat/room", message);
    }
}
