package com.example.functional.repository;

import com.example.functional.entity.User;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

  List<User> findAll();
}
