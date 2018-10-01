package com.mananger.ads.server.dal;

import com.mananger.ads.server.models.Balance;
import com.mananger.ads.server.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface BalanceRepository extends JpaRepository<Balance, User> {}
