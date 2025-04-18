package com.chat.minichat.dto;


import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class ChatMessage {


    public enum MessageType {
        CHAT,    // 일반 채팅 메시지
        JOIN,    // 누군가 입장했을 때
        LEAVE    // 누군가 퇴장했을 때
    }
    private MessageType type; // 메시지 타입
    private String sender;  // 보낸사람 (닉네임)
    private String content; // 메시지 내용

}
