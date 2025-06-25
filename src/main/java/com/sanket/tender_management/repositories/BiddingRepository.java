package com.sanket.tender_management.repositories;

import com.sanket.tender_management.entities.Bidding;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BiddingRepository extends JpaRepository<Bidding, Integer> {
    List<Bidding> findByBidAmountGreaterThan(Double amount);
}
