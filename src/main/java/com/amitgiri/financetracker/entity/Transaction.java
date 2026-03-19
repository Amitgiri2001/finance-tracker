package com.amitgiri.financetracker.entity;

import java.time.LocalDate;

import jakarta.persistence.*;

@Entity
@Table(name = "transactions")
public class Transaction {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private String type;
	
	private String category;
	
	private int amount;
	
	private LocalDate date;
	
	private String note;
	
	public Transaction() {
		
	}

	public Transaction(String type, String category, int amount, LocalDate date, String note) {
		super();
		this.type = type;
		this.category = category;
		this.amount = amount;
		this.date = date;
		this.note = note;
	}

	public Long getId() {
		return id;
	}
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}
	
	
}
