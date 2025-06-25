package com.sanket.tender_management.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "biddings")
public class Bidding {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer biddingId;

    private final String projectName = "Metro Phase V 2024";

    private Double bidAmount;

    private Double yearsToComplete;

    private LocalDate dateOfBidding;

    private String status = "pending";

    @ManyToOne()
    @JoinColumn(name = "bidder_id")
    private User bidder;

    public Bidding(Integer biddingId, Double bidAmount, Double yearsToComplete, User bidder) {
        this.biddingId = biddingId;
        this.bidAmount = bidAmount;
        this.yearsToComplete = yearsToComplete;
        this.dateOfBidding = LocalDate.now();
        this.bidder = bidder;
    }

    public Integer getBidderId() {
        return bidder.getId();
    }
}
