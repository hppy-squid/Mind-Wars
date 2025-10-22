package com.mindWar.mindWar.user.service;

import org.springframework.stereotype.Service;

import com.mindWar.mindWar.user.dto.UserDto;
import com.mindWar.mindWar.user.model.User;
import com.mindWar.mindWar.user.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;
    
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
   
    }


    private UserDto convertToDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setUsername(user.getUsername());
        userDto.setPoints(user.getPoints());
        userDto.setOnline(user.isOnline());
        return userDto;
    }

    private User convertToEntity(UserDto userDto) {
        User user = new User();
        user.setId(userDto.getId());
        user.setUsername(userDto.getUsername());
        user.setPoints(userDto.getPoints());
        user.setOnline(userDto.isOnline());
        user.setPassword(userDto.getPassword());
        return user;
    }

    public UserDto getUserById(Long id) {
        
        User user = userRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("User not found"));

        return convertToDto(user);
    }

    public UserDto createUser(UserDto userDto) {
        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setPassword(userDto.getPassword());
        user.setPoints(0);
        user.setOnline(false);
        
        User savedUser = userRepository.save(user);

        return convertToDto(savedUser);
    }


    public UserDto login(UserDto userDto) {

        User user = userRepository.findByUsernameAndPassword(userDto.getUsername(), userDto.getPassword());

        if (user == null) {
            throw new RuntimeException("Invalid username or password");
        }

        user.setOnline(true);
        userRepository.save(user);

        return convertToDto(user);}


    public void logout(UserDto userDto) {

        User user = userRepository.findByUsernameAndPassword(userDto.getUsername(), userDto.getPassword());

        if (user == null) {
            
            throw new RuntimeException("Not logged in");
        }
        user.setOnline(false);
        userRepository.save(user);


       }
    
}
