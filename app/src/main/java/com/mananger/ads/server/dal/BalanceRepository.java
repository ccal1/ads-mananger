package com.mananger.ads.server.dal;

import com.mananger.ads.server.models.Balance;
import com.mananger.ads.user.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BalanceRepository extends JpaRepository<Balance, User> {}
