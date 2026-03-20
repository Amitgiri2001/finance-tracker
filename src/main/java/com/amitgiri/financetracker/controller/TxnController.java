package com.amitgiri.financetracker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.amitgiri.financetracker.entity.Transaction;
import com.amitgiri.financetracker.service.TxnService;

@RestController
@RequestMapping("/api/txn")
public class TxnController {
	
	@Autowired
	private TxnService txnService;

	@GetMapping("/all")
	public List<Transaction> getAllTxn(){
		return txnService.getAll();
	}
	
	@PostMapping
	public Transaction createTxn(@RequestBody Transaction txn) {
		return txnService.save(txn);
	}
	
	
	@PutMapping("/{id}")
	public Transaction updateTxn(@PathVariable Long id, @RequestBody Transaction txn) {
		return txnService.update(id,txn);
	}
	
	@DeleteMapping("/{id}")
	public void deleteTxn(@PathVariable Long id) {
		txnService.delete(id);
	}
}
