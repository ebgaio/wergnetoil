package com.wergnet.wergnetoil.api.resource;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
import com.wergnet.wergnetoil.api.model.Card;
import com.wergnet.wergnetoil.api.model.Customer;
import com.wergnet.wergnetoil.api.repository.CardRepository;
import com.wergnet.wergnetoil.api.repository.CustomerRepository;
import com.wergnet.wergnetoil.api.service.CustomerService;

@RestController
@RequestMapping("/customers")
public class CustomerResource {
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private CardRepository cardRepository;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@Autowired
	private CustomerService customerService;
	
	// List all Customers | localhost:8080/customers  
	@GetMapping
	@PreAuthorize("hasAuthority('ROLE_SEARCH_CUSTOMER') and #oauth2.hasScope('read')")
	public List<Customer> listAll() {
		
		return customerRepository.findAll();
	}
	
	// Show customer by code | localhost:8080/customers/2
	@GetMapping("/{code}")
	@PreAuthorize("hasAuthority('ROLE_SEARCH_CUSTOMER') and #oauth2.hasScope('read')")
	public ResponseEntity<Customer> getByCode(@PathVariable Long code) {
		
		Optional<Customer> customer = this.customerRepository.findById(code);
		return customer.isPresent() ? ResponseEntity.ok(customer.get()) : ResponseEntity.notFound().build();
		
//        final HttpHeaders httpHeaders= new HttpHeaders();
//        httpHeaders.setContentType(MediaType.APPLICATION_XML);
//
//        Optional<Customer> customer = this.customerRepository.findById(code);
//
//        return customer.isPresent() ? new ResponseEntity<Customer>(customer.get(), httpHeaders, HttpStatus.OK) : ResponseEntity.notFound().build();
	}

	// Create a new Customer | localhost:8080/customers
	@PostMapping
	@PreAuthorize("hasAuthority('ROLE_REGISTER_CUSTOMER') and #oauth2.hasScope('write')")
	public ResponseEntity<Customer> create(@Valid @RequestBody Customer customer, HttpServletResponse response) {
		
		Customer customerSave =  customerRepository.save(customer);
		publisher.publishEvent(new ResourceCreatedEvent(this, response, customerSave.getId()));
		
		return ResponseEntity.status(HttpStatus.CREATED).body(customerSave);
	}
	
	/* Use with alternate option to create a customer with card:
	 * @PostMapping(value = "/{codeCard}", params = "asdf")
	 */
	// Create new Customer with a specific codeCard | localhost:8080/customers/4
	@PostMapping("/{codeCard}")
	@PreAuthorize("hasAuthority('ROLE_REGISTER_CUSTOMER') and #oauth2.hasScope('write')")
	public ResponseEntity<Card> createCustomerWithCard(@Valid @RequestBody Customer customer, @PathVariable Long codeCard, HttpServletResponse response) {
		
		Customer customerSave = customerRepository.save(customer);
		Optional<Card> cardSave = cardRepository.findById(codeCard);

		cardSave.get().setCustomer(customerSave);
		cardRepository.save(cardSave.get());
		
		publisher.publishEvent(new ResourceCreatedEvent(this, response, customerSave.getId()));
		publisher.publishEvent(new ResourceCreatedEvent(this, response, cardSave.get().getId()));
		
		return ResponseEntity.status(HttpStatus.CREATED).body(cardSave.get());
	}
	
	// Create new Card to a specific codeCustomer | localhost:8080/customers/card/4
	@PostMapping("/card/{codeCustomer}")
	@PreAuthorize("hasAuthority('ROLE_REGISTER_CUSTOMER') and #oauth2.hasScope('write')")
	public ResponseEntity<Card> createNewCardToCustomer(@RequestBody Customer codeCustomer, HttpServletResponse response) {
		
		Card card = customerService.createNewCardToCustomer(codeCustomer);
		publisher.publishEvent(new ResourceCreatedEvent(this, response, card.getId()));
				
		return ResponseEntity.status(HttpStatus.CREATED).body(card);
	}

	// Delete customer by code | localhost:8080/customers/3
	@DeleteMapping("/{code}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@PreAuthorize("hasAuthority('ROLE_REMOVE_CUSTOMER') and #oauth2.hasScope('delete')")
	public void delete(@PathVariable Long code) {
		
		customerRepository.deleteById(code);
	}
	
	// Update customer to a specific code | localhost:8080/customers/5
	@PutMapping("/{code}")
	@PreAuthorize("hasAuthority('ROLE_REGISTER_CUSTOMER') and #oauth2.hasScope('update')")
	public ResponseEntity<Customer> update(@PathVariable Long code, @Valid @RequestBody Customer customer) {
		
		Customer customerSave = customerService.update(code, customer);
		
		return ResponseEntity.ok(customerSave);
	}
	
	// Update customer to specific code - Active true/false | localhost:8080/customers/2/active
	@PutMapping("/{code}/active")
	@PreAuthorize("hasAuthority('ROLE_REGISTER_CUSTOMER') and #oauth2.hasScope('update')")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void updatePropertyActive(@PathVariable Long code, @RequestBody Boolean active) {
		
		customerService.updatePropertyActive(code, active);
	}
	
}
