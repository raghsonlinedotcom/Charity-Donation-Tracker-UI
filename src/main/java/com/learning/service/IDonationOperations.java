package com.learning.service;

import java.util.List;

import com.learning.response.Donation;

import reactor.core.publisher.Mono;

public interface IDonationOperations 
{
	public Mono<String> addDonation(Donation donation);
	public Donation getDonationInfoById(Long id);
	public List<Donation> getAllDonationsInfo();
	public Mono<String> updateDonation(Long id,Donation donation);
	public Mono<String> deleteDonation(Long id);
	
}
