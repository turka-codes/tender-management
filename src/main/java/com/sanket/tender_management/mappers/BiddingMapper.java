package com.sanket.tender_management.mappers;

import com.sanket.tender_management.dtos.BiddingResponse;
import com.sanket.tender_management.entities.Bidding;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Mapper(componentModel = "spring")
public interface BiddingMapper {
    @Mapping(target = "dateOfBidding", qualifiedByName = "formatDate")
    @Mapping(target = "bidderId", source = "bidder.id")
    BiddingResponse toDto(Bidding bidding);

    @Named("formatDate")
    static String formatDate(LocalDate date) {
        return date != null ? date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) : null;
    }
}
