package com.insat.gestionformation.repositories;

import com.insat.gestionformation.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    User getUserByMail(String mail);


}
