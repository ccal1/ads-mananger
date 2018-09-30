package com.mananger.ads.server.services;

import com.mananger.ads.server.dal.Balance;
import com.mananger.ads.server.dal.BalanceRepository;
import com.mananger.ads.server.dal.User;
import java.util.Optional;
import javax.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class UserService {
  BalanceRepository balanceRepository;

  boolean hasFunds(Long userId, Double value) {
    return getUserBalance(userId)
        .map(balance -> balance.getAmmount() != null && balance.getAmmount() >= value)
        .orElse(false);
  }

  private Optional<Balance> getUserBalance(Long id) {
    final User user = new User();
    user.setId(id);
    return Optional.ofNullable(balanceRepository.getOne(user));
  }
}
