package com.ipaye.private_chat_application.service;

import com.ipaye.private_chat_application.repository.UserRepository;
import com.ipaye.private_chat_application.user.Status;
import com.ipaye.private_chat_application.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;

    public void saveUser(User user){
        user.setStatus(Status.ONLINE);
        repository.save(user);
    }

    public void disconnect(User user){
        var storedUser = repository.findById(user.getNickName())
                .orElse(null);
        if(storedUser != null){
            storedUser.setStatus(Status.OFFLINE);
            repository.save(storedUser);
        }

    }

    public List<User> findConnectedUser(){
        return repository.findAllByStatus(Status.ONLINE);
    }

}
