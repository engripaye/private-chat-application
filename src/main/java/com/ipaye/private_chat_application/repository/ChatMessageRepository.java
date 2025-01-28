package com.ipaye.private_chat_application.repository;

import com.ipaye.private_chat_application.chat.ChatMessages;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ChatMessageRepository extends MongoRepository<ChatMessages, String> {
    List<ChatMessages> findByChatId(String s);
}
