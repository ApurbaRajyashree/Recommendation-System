package com.example.nrs.service.serviceImpl;

import com.example.nrs.dto.UserDto;
import com.example.nrs.entity.Role;
import com.example.nrs.entity.User;
import com.example.nrs.repository.UserRepo;
import com.example.nrs.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepo userRepo, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDto createUser(UserDto userDto) {
        User user = new User(userDto);
        if(user.getDepartment()==null){
            throw new RuntimeException("Department should be selected");
        }
        user.setUserPassword(passwordEncoder.encode(userDto.getUserPassword()));
        user.setRole(Role.USER);
        userRepo.save(user);
        return new UserDto(user);
    }
}
