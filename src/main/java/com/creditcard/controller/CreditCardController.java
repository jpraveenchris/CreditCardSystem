package com.creditcard.controller;

import java.util.List;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.creditcard.dao.CreditCardRepository;
import com.creditcard.model.CreditCard;
import com.fasterxml.jackson.core.JsonProcessingException;


@RestController
public class CreditCardController {

	private final CreditCardRepository repository;

	CreditCardController(CreditCardRepository repository) {
		this.repository = repository;
	}


	@PostMapping(value="/creditCard/add",consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> createCreditCard(@RequestBody CreditCard card) {
		if (card.validate()) {
			try {
				repository.save(card);
				return new ResponseEntity<Object>(HttpStatus.OK);
			} catch (DuplicateKeyException e) {
				return new ResponseEntity<Error>(HttpStatus.CONFLICT);
			}
		}
		return new ResponseEntity<Error>(HttpStatus.FORBIDDEN);
	}

	@GetMapping(value="/creditCard/getAll")
	public List<CreditCard> getAllCreditCards() throws JsonProcessingException {
		return repository.findAll();
	}

}