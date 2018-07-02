package com.bluebanana.bidder.web;

import com.bluebanana.bidder.dtos.request.BidRequestDto;
import com.bluebanana.bidder.dtos.response.BidResponseDto;
import com.bluebanana.bidder.service.Bidder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/bid")
public class BidderController {

    @Autowired
    private Bidder bidder;

    @PostMapping(
        produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
        consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    public HttpEntity<BidResponseDto> bid(@RequestBody BidRequestDto bidRequestDto) {
        Optional<BidResponseDto> bidResponseDTO = bidder.makeBid(bidRequestDto);

        if (bidResponseDTO.isPresent()) {
            return new ResponseEntity<>(bidResponseDTO.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
}
