package com.femcoders.mynotes.repositories;

import com.femcoders.mynotes.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
