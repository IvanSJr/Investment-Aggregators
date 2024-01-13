package com.github.ivansjr.investment_aggregators.service;

import com.github.ivansjr.investment_aggregators.client.BrapiClient;
import com.github.ivansjr.investment_aggregators.client.dto.BrapiResponseDTO;
import com.github.ivansjr.investment_aggregators.entity.UserAccountStock;
import com.github.ivansjr.investment_aggregators.entity.UserAccountStockId;
import com.github.ivansjr.investment_aggregators.entity.Stock;
import com.github.ivansjr.investment_aggregators.entity.UserAccount;
import com.github.ivansjr.investment_aggregators.repository.StockRepository;
import com.github.ivansjr.investment_aggregators.repository.UserAccountStockRepository;
import com.github.ivansjr.investment_aggregators.repository.UserAccountRepository;
import com.github.ivansjr.investment_aggregators.service.dto.UserAccountStockCreateRequestDTO;
import com.github.ivansjr.investment_aggregators.service.dto.UserAccountStockResponseDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class UserAccountStockService {

    private final UserAccountStockRepository userAccountStockRepository;
    private final UserAccountRepository userAccountRepository;
    private final StockRepository stockRepository;
    private final BrapiClient brapiClient;

    @Value("#{environment.TOKEN_BRAPI}")
    private String TOKEN;

    public UserAccountStockService(UserAccountStockRepository userAccountStockRepository,
                                   UserAccountRepository userAccountRepository,
                                   StockRepository stockRepository, BrapiClient brapiClient) {
        this.userAccountStockRepository = userAccountStockRepository;
        this.userAccountRepository = userAccountRepository;
        this.stockRepository = stockRepository;
        this.brapiClient = brapiClient;
    }

    public void relatedStockWithAccount(UUID accountId, UserAccountStockCreateRequestDTO userAccountStockCreateRequestDTO) {
        UserAccount userAccount = getUserAccount(accountId);

        Stock stock = stockRepository.findById(userAccountStockCreateRequestDTO.stockId()).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND)
        );

        UserAccountStockId userAccountStockId = new UserAccountStockId(userAccount.getId(), stock.getStockId());
        UserAccountStock userAccountStock = new UserAccountStock(userAccountStockId, stock, userAccount,
                userAccountStockCreateRequestDTO.quantity());

        userAccountStockRepository.save(userAccountStock);
    }

    public List<UserAccountStockResponseDTO> getAllRelatedStocksByAccount(UUID accountId) {
        UserAccount userAccount = getUserAccount(accountId);

        return userAccount.getStocks().stream().map(
            accountStocks ->
                new UserAccountStockResponseDTO
                    (
                        userAccount.getId(),
                        accountStocks.getStock(),
                        getTotal(accountStocks.getQuantity(), accountStocks.getStock().getStockId())
                    )
        ).toList();
    }

    private UserAccount getUserAccount(UUID accountId) {
        return userAccountRepository.findById(accountId).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND)
        );
    }

    private Double getTotal(Integer quantity, String stockId) {
        BrapiResponseDTO brapiResponse = brapiClient.getQuote(TOKEN, stockId);
        return brapiResponse.results().getFirst().regularMarketPrice() * quantity;
    }
}
