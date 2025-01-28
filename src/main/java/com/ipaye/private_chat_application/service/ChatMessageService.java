package com.ipaye.private_chat_application.service;

import com.ipaye.private_chat_application.chat.ChatMessages;
import com.ipaye.private_chat_application.repository.ChatMessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatMessageService {

    private final ChatMessageRepository chatMessageRepository;
    private final ChatRoomService chatRoomService;


    public ChatMessages save(ChatMessages chatMessages){

        var chatId = chatRoomService.getChatRoomId(
                chatMessages.getSenderId(), chatMessages.getRecipientId(),
                true
        ).orElseThrow(); // you can throw dedicated exception
        chatMessages.setChatId(chatId);
        chatMessageRepository.save(chatMessages);
        return chatMessages;
    }

    public List<ChatMessages> findChatMessages (
            String senderId, String recipientId
    ){
        var chatId = chatRoomService.getChatRoomId(
                senderId, recipientId, false);
        return chatId.map(chatMessageRepository::findByChatId).orElse(new ArrayList<>());
    }
}
