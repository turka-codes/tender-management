package com.sanket.tender_management.dtos;

import lombok.Data;

@Data
public class BiddingRequest {
    private Integer biddingId;
    private Double bidAmount;
    private Double yearsToComplete;
}
