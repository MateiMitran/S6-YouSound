package com.backend.userservice.repositories;

import com.backend.userservice.entities.UserSetting;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserSettingRepository extends MongoRepository<UserSetting, String> {
    Optional<UserSetting> findById(String id);
}
