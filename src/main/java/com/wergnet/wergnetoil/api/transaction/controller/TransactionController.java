package com.wergnet.wergnetoil.api.transaction.controller;

import com.wergnet.wergnetoil.api.common.exception.InsufficientFundsInCardException;
import com.wergnet.wergnetoil.api.common.exceptionHandler.WergnetOilExceptionHandler.Erro;
import com.wergnet.wergnetoil.api.event.ResourceCreatedEvent;
import com.wergnet.wergnetoil.api.transaction.model.Transaction;
import com.wergnet.wergnetoil.api.transaction.repository.TransactionRepository;
import com.wergnet.wergnetoil.api.transaction.repository.filter.TransactionFilter;
import com.wergnet.wergnetoil.api.transaction.repository.projection.TransactionSummary;
import com.wergnet.wergnetoil.api.transaction.service.TransactionService;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/transactions")
public class TransactionController {

    private final TransactionRepository transactionRepository;
    private final TransactionService transactionService;
    private final ApplicationEventPublisher publisher;
    private final MessageSource messageSource;

    // Show transactions unique/limited by date | localhost:8080/transactions
    @GetMapping
    @PreAuthorize("hasAuthority('ROLE_SEARCH_TRANSACTION') and #oauth2.hasScope('read')")
    public Page<Transaction> research(TransactionFilter transactionFilter, Pageable pageable) {

        return transactionRepository.filter(transactionFilter, pageable);
    }

    //Get a summarized values of transaction | localhost:8080/transactions?summary&dateDebitFrom=2017-02-20&dateDebitTo=2017-10-20&size=2&page=0
    @GetMapping(params = "summary")
    @PreAuthorize("hasAuthority('ROLE_SEARCH_TRANSACTION') and #oauth2.hasScope('read')")
    public Page<TransactionSummary> summarize(TransactionFilter transactionFilter, Pageable pageable) {

        return transactionRepository.summarize(transactionFilter, pageable);
    }

    // Get a statement of Debit by Customer | localhost:8080/transactions/statement/3
    @GetMapping("/statement/{customer}")
    @PreAuthorize("hasAuthority('ROLE_SEARCH_TRANSACTION') and #oauth2.hasScope('read')")
    public Page<Transaction> listDebitByCustomer(@PathVariable Long customer, Pageable pageable) {

//        return transactionRepository.listDebitByCustomer(customer, pageable);
        Page<Transaction> listDebitByCustomer = transactionService.listDebitByCustomer(customer, pageable);

        return listDebitByCustomer;
    }

    // Show transaction by code | localhost:8080/transactions/2
    @GetMapping("/{code}")
    @PreAuthorize("hasAuthority('ROLE_SEARCH_TRANSACTION') and #oauth2.hasScope('read')")
    public ResponseEntity<Transaction> getByCode(@PathVariable Long code) {

    	Optional<Transaction> transaction = this.transactionRepository.findById(code);
    	return transaction.isPresent() ? ResponseEntity.ok(transaction.get()) : ResponseEntity.notFound().build();
    }

    // Test - Create transaction | localhost:8080/transactions
    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_REGISTER_TRANSACTION') and #oauth2.hasScope('write')")
    public ResponseEntity<Transaction> create(@Valid @RequestBody Transaction transaction, HttpServletResponse response) {

    	Transaction transactionSaved = transactionService.save(transaction);
    	publisher.publishEvent(new ResourceCreatedEvent(this, response, transactionSaved.getId()));
    	return ResponseEntity.status(HttpStatus.CREATED).body(transactionSaved);
    }

    /* Use of alternative option of @PostMapping :
     * @PostMapping(value = ("/{customer}"), params = {"bank"} )
     * public ResponseEntity<Transaction> createTransactionByCustomerByBank(@Valid @RequestBody Transaction transaction, @PathVariable Long customer, @RequestParam Long bank, HttpServletResponse response) {
    */
    // Create transaction to buy credit to customer's card from a bank | localhost:8080/transactions?bank=2
    @PostMapping(params = {"bank"})
    @PreAuthorize("hasAuthority('ROLE_REGISTER_TRANSACTION') and #oauth2.hasScope('write')")
    public ResponseEntity<Transaction> createTransactionToBuyCreditToCardFromBank(@Valid @RequestBody Transaction transaction, @RequestParam Long bank, HttpServletResponse response) {

    	Transaction transactionSaved = transactionService.buyCreditToCardFromBank(transaction, bank, response);
    	publisher.publishEvent(new ResourceCreatedEvent(this, response, transactionSaved.getId()));
    	return ResponseEntity.status(HttpStatus.CREATED).body(transactionSaved);
    }

    // Create transaction to debit of value in card of customer. Using default Bank. Id: 1 | localhost:8080/transactions?valueDebit=20
    @PostMapping(params = {"valueDebit"})
    @PreAuthorize("hasAuthority('ROLE_REGISTER_TRANSACTION') and #oauth2.hasScope('write')")
    public ResponseEntity<Transaction> createTransactionDebitInCardOfCustomer(@Valid @RequestBody Transaction transaction, @RequestParam BigDecimal valueDebit, HttpServletResponse response) {

    	Transaction transactionSaved = transactionService.debitInCardOfCustomer(transaction, valueDebit);
    	publisher.publishEvent(new ResourceCreatedEvent(this, response, transactionSaved.getId()));
    	return ResponseEntity.status(HttpStatus.CREATED).body(transactionSaved);
    }

    // Send message when has insufficient funds in Card.
    @ExceptionHandler({ InsufficientFundsInCardException.class })
    public ResponseEntity<Object> handlerInsufficientFundsInCardException(InsufficientFundsInCardException ex) {

      String mesageUser = messageSource.getMessage("card.insufficient-funds", null, LocaleContextHolder.getLocale());
      String mesageDeveloper = ex.toString();
      List<Erro> erros = Arrays.asList(new Erro(mesageUser, mesageDeveloper));
      return ResponseEntity.badRequest().body(erros);
    }
}
