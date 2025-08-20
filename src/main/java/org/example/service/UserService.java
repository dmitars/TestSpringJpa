package org.example.service;

import org.example.model.User;
import org.example.repo.UserRepository;
import org.example.validation.ServerException;
import org.hibernate.query.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {
    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void deleteByName(String name) {
        if(name == null)
            throw new ServerException("name must not be null");

        userRepository.deleteByName(name);
    }

    public void deleteById(int id) {
        userRepository.deleteById(id);
    }

    @Transactional
    public void updateNameById(int id, String name) {
        var userOptional = userRepository.findById(id);
        userOptional.ifPresent(user -> {
            user.setName(name);
        });
    }

    public boolean canBeUpdated(User user) {
        return true;
    }

    public List<User> findAllByName(String name) {
        if(name == null)
            throw new ServerException("name must not be null");

        return userRepository.findAllByName(name);
    }

    public List<User> getAll(int offset, int limit) {
        PageRequest page = PageRequest.of(offset, limit, Sort.by( "id"));
        var result = userRepository.findAll(page);
        return result.getContent();
    }
}
