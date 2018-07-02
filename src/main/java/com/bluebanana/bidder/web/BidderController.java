package com.bluebanana.bidder.web;

import com.bluebanana.bidder.dtos.request.BidRequestDTO;
import com.bluebanana.bidder.dtos.response.BidResponseDTO;
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
    public HttpEntity<BidResponseDTO> bid(@RequestBody BidRequestDTO bidRequestDTO) {
        Optional<BidResponseDTO> bidResponseDTO = bidder.makeBid(bidRequestDTO);

        if (bidResponseDTO.isPresent()) {
            return new ResponseEntity<>(bidResponseDTO.get(),HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
}
