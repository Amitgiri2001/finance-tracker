package com.amitgiri.financetracker.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amitgiri.financetracker.entity.Transaction;
import com.amitgiri.financetracker.repository.TxnRepository;

@Service
public class TxnService {
	
	@Autowired
	private TxnRepository txnRepo;
	
	public Transaction save(Transaction txn) {
		return txnRepo.save(txn);
	}
	
	public List<Transaction> getAll(){
		return txnRepo.findAll();
	}
	
	public Transaction update(Long id,Transaction txn) {
		Transaction existingTxn=txnRepo.findById(id).orElseThrow(()->
		new RuntimeException("Transaction not found with this id: "+id));
		
		existingTxn.setAmount(txn.getAmount());
		existingTxn.setCategory(txn.getCategory());
		existingTxn.setDate(txn.getDate());
		existingTxn.setTime(txn.getTime());
		existingTxn.setNote(txn.getNote());
		existingTxn.setType(txn.getType());
		
		return txnRepo.save(existingTxn);
	}
	
	public void delete(Long id) {
		txnRepo.deleteById(id);
	}
	
}
