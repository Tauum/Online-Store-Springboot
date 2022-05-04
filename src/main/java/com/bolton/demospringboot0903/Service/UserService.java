package com.bolton.demospringboot0903.Service;

import com.bolton.demospringboot0903.Models.User;
import com.bolton.demospringboot0903.Repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;

@Service
public class UserService {
    private final UserRepo userRepo;
    private final ItemService itemService;

    @Autowired
    public UserService(UserRepo userRepository, ItemService itemService) {
        this.userRepo = userRepository;
        this.itemService = itemService;
    }

    public User addUser(User user) throws Exception {

        if (user.getEmail().length() == 0) {
            throw new Exception("Email invalid");
        }
        if (user.getPassword().length() <= 6) {
            throw new Exception("Password is too short");
        } else {
            if (findAllContainingEmail(user.getEmail()).size() != 0) {
                throw new Exception("Email is already registered");
            }
        }
        return userRepo.save(user);
    }

    public List<User> findAll() {
        return userRepo.findAll();
    }

    public User updateUser(User user) {
        User original = findUserById(user.getId());
        if (original != null) {
            try {
                if (user.getEmail() != null && user.getEmail() != "") {
                    original.setEmail(user.getEmail());
                }
            } catch (Exception e) {
            }
            try {
                if (user.getPassword() != null && user.getPassword().length() >= 7) {
//                    implement password hashing here
                    original.setPassword(user.getPassword());
                }
            } catch (Exception e) {
            }
            try {
                if (user.getFirstName() != null && user.getFirstName() != "") {
                    original.setFirstName(user.getFirstName());
                }
            } catch (Exception e) {
            }
            try {
                if (user.getLastName() != null && user.getLastName() != "") {
                    original.setLastName(user.getLastName());
                }
            } catch (Exception e) {
            }
            try {
                if (user.getRole() != null) {
                    original.setRole(user.getRole());
                }
            } catch (Exception e) {
            }
            try {
                if (user.getAddress() != null) {
                    try {
                        original.address.setLine1(user.address.getLine1());
                    } catch (Exception e) {
                    }
                    try {
                        original.address.setLine2(user.address.getLine2());
                    } catch (Exception e) {
                    }
                    try {
                        original.address.setLine3(user.address.getLine3());
                    } catch (Exception e) {
                    }
                    try {
                        original.address.setNumber(user.address.getNumber());
                    } catch (Exception e) {
                    }
                    try {
                        original.address.setPostCode(user.address.getPostCode());
                    } catch (Exception e) {
                    }
                }
            } catch (Exception e) {
            }
        }

        return userRepo.save(original);
    }

    //query method (auto generates method in spring back-backend)
    @Transactional
    public void deleteUser(Long id) {
        userRepo.deleteUserById(id);
    }

    public User findUserById(Long id) {
        return userRepo.findUserById(id)
                .orElseThrow(() -> new EntityNotFoundException("Entity not foud with id: " + id));
    }

    public List<User> findAllContainingLastName(String lastName) {
        return userRepo.findUserByLastNameContains(lastName);
    }

    public List<User> findAllContainingEmail(String email) {
        return userRepo.findUserByEmailContains(email);
    }

    public User findByEmail(String email) {
        return userRepo.findUserByEmail(email);
    }

    public Boolean verifyAttempt(User attempt) throws Exception {
        User find = findByEmail(attempt.getEmail());
        if (find != null) {
            if (attempt.getPassword().equals(find.getPassword())) {
                return true;
            } else {
                throw new Exception("Fail - Password incorrect");
            }
        }
        throw new Exception("Fail - Username incorrect");

    }
}
