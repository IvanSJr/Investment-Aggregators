package com.github.ivansjr.investment_aggregators.service;

import com.github.ivansjr.investment_aggregators.entity.User;
import com.github.ivansjr.investment_aggregators.repository.BillingAddressRepository;
import com.github.ivansjr.investment_aggregators.repository.UserAccountRepository;
import com.github.ivansjr.investment_aggregators.repository.UserRepository;
import com.github.ivansjr.investment_aggregators.service.dto.UserCreateRequestDTO;
import com.github.ivansjr.investment_aggregators.service.dto.UserUpdateRequestDTO;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final UserAccountRepository userAccountRepository;
    private final BillingAddressRepository billingAddressRepository;

    public UserService(UserRepository userRepository, ModelMapper modelMapper, UserAccountRepository userAccountRepository, BillingAddressRepository billingAddressRepository) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.userAccountRepository = userAccountRepository;
        this.billingAddressRepository = billingAddressRepository;
    }

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public User create(UserCreateRequestDTO userCreateRequestDTO) {
        User user = convertToEntity(userCreateRequestDTO);
        return userRepository.save(user);
    }

    public Optional<User> getById(UUID id) {
        return userRepository.findById(id);
    }

    private User convertToEntity(UserCreateRequestDTO userCreateRequestDTO) {
        User user = modelMapper.map(userCreateRequestDTO, User.class);
        user.setUsername(userCreateRequestDTO.username());
        user.setPassword(userCreateRequestDTO.password());
        user.setEmail(userCreateRequestDTO.email());
        return user;
    }

    public User update(UUID id, UserUpdateRequestDTO userCreationDTO) {
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
