package com.bluebanana.bidder.web;

import com.bluebanana.bidder.dtos.BidRequestDTO;
import com.bluebanana.bidder.dtos.BidResponseDTO;
import com.bluebanana.bidder.service.Bidder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bid")
public class BidderController {

    @Autowired
    private Bidder bidder;

    @PostMapping(
        produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
        consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    public HttpEntity<BidResponseDTO> bid(@RequestBody BidRequestDTO bidRequestDTO) {
        BidResponseDTO bidResponseDTO = bidder.makeBid(bidRequestDTO);

        return new ResponseEntity<>(bidResponseDTO,HttpStatus.OK);
    }
}