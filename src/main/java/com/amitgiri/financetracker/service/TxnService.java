package com.amitgiri.financetracker.service;

import java.time.LocalDate;
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
	
	public List<Transaction> getByDate(LocalDate date){
		return txnRepo.findByDateOrderByTime(date);
	}
	
	public List<Transaction> getByWeek(LocalDate date){
		LocalDate startDate=date.minusDays(date.getDayOfWeek().getValue());
		LocalDate endDate=startDate.plusDays(6);
		return txnRepo.findByDateBetweenOrderByDate(startDate,endDate);
	}
	
	public List<Transaction> getByMonth(int year,int month){
		LocalDate startDate=LocalDate.of(year,month,1);
		LocalDate endDate=startDate.plusDays(startDate.lengthOfMonth());
		return txnRepo.findByDateBetweenOrderByDate(startDate,endDate);
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
