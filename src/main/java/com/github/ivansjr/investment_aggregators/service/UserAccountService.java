package com.github.ivansjr.investment_aggregators.service;

import com.github.ivansjr.investment_aggregators.entity.BillingAddress;
import com.github.ivansjr.investment_aggregators.entity.User;
import com.github.ivansjr.investment_aggregators.entity.UserAccount;
import com.github.ivansjr.investment_aggregators.repository.BillingAddressRepository;
import com.github.ivansjr.investment_aggregators.repository.UserAccountRepository;
import com.github.ivansjr.investment_aggregators.repository.UserRepository;
import com.github.ivansjr.investment_aggregators.service.dto.UserAccountCreateRequestDTO;
import com.github.ivansjr.investment_aggregators.service.dto.UserAccountResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class UserAccountService {

    private final UserRepository userRepository;
    private final UserAccountRepository userAccountRepository;
    private final BillingAddressRepository billingAddressRepository;

    public UserAccountService(UserRepository userRepository, UserAccountRepository userAccountRepository, BillingAddressRepository billingAddressRepository) {
        this.userRepository = userRepository;
        this.userAccountRepository = userAccountRepository;
        this.billingAddressRepository = billingAddressRepository;
    }

    public void createAccount(UUID userId, UserAccountCreateRequestDTO userAccountCreateRequestDTO) {
        User user = findUserById(userId);

        var account = new UserAccount(
                UUID.randomUUID(),
                userAccountCreateRequestDTO.description(),
                user,
                null,
                new ArrayList<>()
        );

        UserAccount accountCreated = userAccountRepository.save(account);

        var billingAddress = new BillingAddress(
                accountCreated.getId(),
                accountCreated,
                userAccountCreateRequestDTO.street(),
                userAccountCreateRequestDTO.number()
        );

        billingAddressRepository.save(billingAddress);
    }

    @Transactional(readOnly = true)
    public List<UserAccountResponseDTO> listUserAccountsByIdUser(UUID userId) {
        User user = findUserById(userId);

        List<UserAccountResponseDTO> userAccounts =
                user.getUserAccounts().stream().map(
                        account -> new UserAccountResponseDTO(account.getId(), account.getDescription())
                ).toList();

        return userAccounts;
    }

    private User findUserById(UUID userId) {
        return userRepository.findById(userId).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND)
        );
    }

}
