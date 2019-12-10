package com.epam.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.epam.beans.Users;

@Repository
public interface UserRepository extends CrudRepository<Users, Integer> {

	Users findByUsernameAndPassword(String username, String password);

	Optional<Users> findByUsername(String username);
}
