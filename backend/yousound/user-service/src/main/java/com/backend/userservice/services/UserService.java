package com.backend.userservice.services;


import com.backend.userservice.entities.User;
import com.backend.userservice.entities.UserSetting;
import com.backend.userservice.repositories.UserRepository;
import com.backend.userservice.repositories.UserSettingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserSettingRepository userSettingRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(String id) {
       return userRepository.findById(id).orElseThrow(() ->
               new ResourceNotFoundException("User does not exist with id: " + id));
    }

    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(() ->
                new ResourceNotFoundException("User does not exist with username: " + username));
    }

    public boolean deleteUserById(String id) {

        if (userRepository.findById(id).isPresent()) {
            userRepository.deleteById(id);
            deleteUserSettings(id);
            return true;
        } else {
            return false;
        }
    }

    public boolean deleteUserByUsername(String username) {

        if (userRepository.findByUsername(username).isPresent()) {
            userRepository.deleteById(username);
            deleteUserSettings(userRepository.findByUsername(username).get().getId());
            return true;
        } else {
            return false;
        }
    }

    private void deleteUserSettings(String user_id) {
        List<UserSetting> userSettings = userSettingRepository.findAll();
        for(UserSetting setting: userSettings) {
            if (setting.getUser_id().equals(user_id)) {
                userSettingRepository.delete(setting);
            }
        }
    }

}
