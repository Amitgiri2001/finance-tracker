package com.amitgiri.financetracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.amitgiri.financetracker.entity.Transaction;

public interface TxnRepository extends JpaRepository<Transaction, Long> {

}
