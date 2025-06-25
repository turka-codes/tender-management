package com.sanket.tender_management.controllers;

import com.sanket.tender_management.dtos.BiddingRequest;
import com.sanket.tender_management.dtos.BiddingResponse;
import com.sanket.tender_management.dtos.BiddingStatusUpdateRequest;
import com.sanket.tender_management.entities.Bidding;
import com.sanket.tender_management.mappers.BiddingMapper;
import com.sanket.tender_management.repositories.BiddingRepository;
import com.sanket.tender_management.services.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/bidding")
public class BiddingController {
    private final BiddingRepository biddingRepository;
    private final BiddingMapper biddingMapper;
    private final AuthService authService;

    @PostMapping("/add")
    public ResponseEntity<BiddingResponse> addBiding(@RequestBody BiddingRequest request) {
        var user = authService.getCurrentUser();
        var bidding = new Bidding(request.getBiddingId(), request.getBidAmount(), request.getYearsToComplete(), user);
        biddingRepository.save(bidding);
        return ResponseEntity.status(HttpStatus.CREATED).body(biddingMapper.toDto(bidding));
    }

    @GetMapping("/list")
    public ResponseEntity<?> listBidding(@RequestParam(required = false) Double bidAmount) {
        var biddings = bidAmount == null ? biddingRepository.findAll() : biddingRepository.findByBidAmountGreaterThan(bidAmount);
        if (biddings.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("no data available");
        }
        return ResponseEntity.ok(biddings.stream().map(biddingMapper::toDto).toList());
    }

    @PatchMapping("/update/{id}")
    public BiddingResponse updateBiddingStatus(@PathVariable Integer id, @RequestBody BiddingStatusUpdateRequest request) {
        var bidding = biddingRepository.findById(id).orElseThrow();
        bidding.setStatus(request.getStatus());
        biddingRepository.save(bidding);
        return biddingMapper.toDto(bidding);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteBidding(@PathVariable Integer id) {
        var currentUser = authService.getCurrentUser();
        var bidding = biddingRepository.findById(id).orElse(null);
        if (bidding == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("not found");
        }
        if (!currentUser.isApprover() && !currentUser.getId().equals(bidding.getBidderId())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("you don't have permission");
        }
        biddingRepository.delete(bidding);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("deleted successfully");
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Void> handleException() {
        return ResponseEntity.badRequest().build();
    }
}
