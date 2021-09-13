package com.wergnet.wergnetoil.api.resource;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.wergnet.wergnetoil.api.event.ResourceCreatedEvent;
import com.wergnet.wergnetoil.api.model.Bank;
import com.wergnet.wergnetoil.api.repository.BankRepository;
import com.wergnet.wergnetoil.api.service.BankService;

@RestController
@RequestMapping("/banks")
public class BankResource {
    
    @Autowired
    private BankRepository bankRepository;
    
    @Autowired
    private BankService bankService;
    
    @Autowired
    private ApplicationEventPublisher publisher;

    // Get a list of banks | localhost:8080/banks
    @GetMapping
	@PreAuthorize("hasAuthority('ROLE_SEARCH_BANK') and #oauth2.hasScope('read')")
    public List<Bank> listAll() {

    	return bankRepository.findAll();
    }
    
	// Show bank by code | localhost:8080/banks/2
	@GetMapping("/{code}")
	@PreAuthorize("hasAuthority('ROLE_SEARCH_CUSTOMER') and #oauth2.hasScope('read')")
	public ResponseEntity<Bank> getByCode(@PathVariable Long code) {
		
		Optional<Bank> bank = bankRepository.findById(code);
		return bank.isPresent() ? ResponseEntity.ok(bank.get()) : ResponseEntity.notFound().build();
	}

	// Create a bank in the system  localhost:8080/banks
	@PostMapping
	@PreAuthorize("hasAuthority('ROLE_SEARCH_BANK') and #oauth2.hasScope('write')")
	public ResponseEntity<Bank> createBank(@Valid @RequestBody Bank bank, HttpServletResponse response) {
		
		Bank bankSaved = bankRepository.save(bank);
		publisher.publishEvent(new ResourceCreatedEvent(this, response, bankSaved.getId()));
		
		return ResponseEntity.status(HttpStatus.CREATED).body(bankSaved); 
	}
	
	// Delete bank by code | localhost:8080/banks/3
	@DeleteMapping("/{code}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@PreAuthorize("hasAuthority('ROLE_REMOVE_CUSTOMER') and #oauth2.hasScope('delete')")
	public void delete(@PathVariable Long code) {
		
		bankRepository.deleteById(code);
	}
	
	// Update bank to a specific code | localhost:8080/banks/5
	@PutMapping("/{code}")
	@PreAuthorize("hasAuthority('ROLE_REGISTER_CUSTOMER') and #oauth2.hasScope('update')")
	public ResponseEntity<Bank> update(@PathVariable Long code, @Valid @RequestBody Bank bank) {
		
		Bank bankSave = bankService.update(code, bank);
		
		return ResponseEntity.ok(bankSave);
	}
	
	// Update bank to specific code - Active true/false | localhost:8080/banks/2/active
	@PutMapping("/{code}/active")
	@PreAuthorize("hasAuthority('ROLE_REGISTER_CUSTOMER') and #oauth2.hasScope('update')")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void updatePropertyActive(@PathVariable Long code, @RequestBody Boolean active) {
		
		bankService.updatePropertyActive(code, active);
	}
}
