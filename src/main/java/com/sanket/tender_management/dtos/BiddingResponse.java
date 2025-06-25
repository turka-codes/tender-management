package com.sanket.tender_management.dtos;

import lombok.Data;

@Data
public class BiddingResponse {
    private Integer id;
    private Integer biddingId;
    private String projectName;
    private Double bidAmount;
    private Double yearsToComplete;
    private String dateOfBidding;
    private String status;
    private Integer bidderId;
}
