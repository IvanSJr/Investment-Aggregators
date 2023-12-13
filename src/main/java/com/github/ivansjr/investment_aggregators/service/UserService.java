package com.github.ivansjr.investment_aggregators.service;

import com.github.ivansjr.investment_aggregators.entity.User;
import com.github.ivansjr.investment_aggregators.repository.UserRepository;
import com.github.ivansjr.investment_aggregators.service.dto.UserCreateDTO;
import com.github.ivansjr.investment_aggregators.service.dto.UserUpdateDTO;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public UserService(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public User create(UserCreateDTO userCreateDTO) {
        User user = convertToEntity(userCreateDTO);
        return userRepository.save(user);
    }

    public Optional<User> getById(UUID id) {
        return userRepository.findById(id);
    }

    private User convertToEntity(UserCreateDTO userCreateDTO) {
        User user = modelMapper.map(userCreateDTO, User.class);
        user.setUsername(userCreateDTO.username());
        user.setPassword(userCreateDTO.password());
        user.setEmail(userCreateDTO.email());
        return user;
    }

    public User update(UUID id, UserUpdateDTO userCreationDTO) {
        User userFound = getById(id).get();
        if (userFound == null) {
            return null;
        }
        userFound.setUsername(userCreationDTO.username());
        userFound.setEmail(userCreationDTO.email());
        return userRepository.save(userFound);
    }

    public void delete(UUID id) {
        userRepository.deleteById(id);
    }
}
