package com.amitgiri.financetracker.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.amitgiri.financetracker.entity.Transaction;

public interface TxnRepository extends JpaRepository<Transaction, Long> {
	public List<Transaction> findAllByOrderByDateAscTimeAsc();
	public List<Transaction> findByDateOrderByTime(LocalDate date);
	public List<Transaction> findByDateBetweenOrderByDate(LocalDate startDate,LocalDate eendDate);
}
