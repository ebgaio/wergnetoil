package com.wergnet.wergnetoil.api.card.controller;

import com.wergnet.wergnetoil.api.card.model.Card;
import com.wergnet.wergnetoil.api.card.repository.CardRepository;
import com.wergnet.wergnetoil.api.card.service.CardService;
import com.wergnet.wergnetoil.api.event.ResourceCreatedEvent;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/cards")
public class CardController {

	private final CardRepository cardRepository;
		
	private final ApplicationEventPublisher publisher;
	
	private final CardService cardService;
	
	// List all Cards | localhost:8080/cards
	@GetMapping
	@PreAuthorize("hasAuthority('ROLE_SEARCH_CARD') and #oauth2.hasScope('read')")
	public List<Card> listAll() {
		
		return cardRepository.findAll();
	}
	
	// Show card by a code | localhost:8080/cards/4
	@GetMapping("/{code}")
	@PreAuthorize("hasAuthority('ROLE_SEARCH_CARD') and #oauth2.hasScope('read')")
	public ResponseEntity<Card> getByCode(@PathVariable Long code, HttpServletResponse response) {
		
		if (code < 0) {
			return ResponseEntity.badRequest().build();
		}
		
		Card card = cardService.getCardByCode(code);
		publisher.publishEvent(new ResourceCreatedEvent(this, response, card.getId()));

		return ResponseEntity.status(HttpStatus.OK).body(card);
	}
	
	// Create new card to a specific customerId | localhost:8080/cards/5
	@PostMapping("/{customerId}")
	@PreAuthorize("hasAuthority('ROLE_REGISTER_CARD') and #oauth2.hasScope('write')")
	public ResponseEntity<Card> createCard(@Valid @RequestBody Card card, @PathVariable Long customerId, HttpServletResponse response) {
		
		Card cardSave = cardService.createCard(card, customerId);
		publisher.publishEvent(new ResourceCreatedEvent(this, response, card.getId()));
		
		return ResponseEntity.status(HttpStatus.CREATED).body(cardSave);
	}
	
	// Delete a card by code | localhost:8080/cards/6
	@DeleteMapping("/{code}")
	@PreAuthorize("hasAuthority('ROLE_REMOVE_CARD') and #oauth2.hasScope('delete')")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long code) {
		
		cardRepository.deleteById(code);
	}
	
	// Update card to specific code - Active true/false | localhost:8080/customers/5/active
	@PutMapping("/{code}/active")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@PreAuthorize("hasAuthority('ROLE_REGISTER_CARD') and #oauth2.hasScope('update')")
	public void updatePropertyActive(@PathVariable Long code, @RequestBody Boolean active) {
		
		cardService.updatePropertyActive(code, active);
	}
	
}
