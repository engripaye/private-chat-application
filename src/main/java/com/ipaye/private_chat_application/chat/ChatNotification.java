package com.ipaye.private_chat_application.chat;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChatNotification {


    private String id;
    private String senderId;
    private String recipientId;
    private String content;
}
