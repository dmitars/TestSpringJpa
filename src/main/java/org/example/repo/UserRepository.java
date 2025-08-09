package org.example.repo;

import org.example.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    @Query(
            "from User u left join fetch u.addresses where u.name=:name"
    )
    List<User> findAllByName(String name);

    @Query(
            "delete from User u where u.name=:name"
    )
    @Modifying
    @Transactional
    void deleteByName(String name);
}
