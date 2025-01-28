package com.ipaye.private_chat_application.repository;

import com.ipaye.private_chat_application.user.Status;
import com.ipaye.private_chat_application.user.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface UserRepository extends MongoRepository<User, String> {
    List<User> findAllByStatus(Status status);
}
