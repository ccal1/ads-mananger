package com.mananger.ads.server.dal;

import com.mananger.ads.server.models.Balance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BalanceRepository extends JpaRepository<Balance, String> {
  @Query(value = "Select amount from Balance where id = ?1")
  Double balanceById(String email);
}
