package com.ipaye.private_chat_application.controller;

import com.ipaye.private_chat_application.chat.ChatMessages;
import com.ipaye.private_chat_application.chat.ChatNotification;
import com.ipaye.private_chat_application.service.ChatMessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ChatMessageController {

    private final SimpMessagingTemplate messagingTemplate;
    private final ChatMessageService chatMessageService;

    @MessageMapping("/chat")
    public void processMessage(
            @Payload ChatMessages chatMessages
    ){
        ChatMessages savedMsg = chatMessageService.save(chatMessages);
        messagingTemplate.convertAndSendToUser(
                chatMessages.getRecipientId(), "/queue/messages",
                ChatNotification.builder()
                        .id(savedMsg.getId())
                        .senderId(savedMsg.getSenderId())
                        .recipientId(savedMsg.getRecipientId())
                        .content(savedMsg.getContent())
                        .build()

        );
    }

    @GetMapping("/messages/{senderId}/{recipientId}")
    public ResponseEntity<List<ChatMessages>> findChatMessages(
            @PathVariable("senderId") String senderId,
            @PathVariable("recipientId") String recipientId
    ){
        return ResponseEntity.ok(chatMessageService.findChatMessages(senderId, recipientId));
    }



}
