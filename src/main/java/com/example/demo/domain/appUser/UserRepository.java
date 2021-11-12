package com.example.demo.domain.appUser;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID>, PagingAndSortingRepository<User, UUID> {
    User findByUsername(String username);

    Page<User> findAllUserByGroupId(UUID uuid, Pageable pageable);
}