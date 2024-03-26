package com.car_service.user.model.user.repository;

import com.car_service.user.model.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
