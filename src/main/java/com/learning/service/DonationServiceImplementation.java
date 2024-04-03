package com.learning.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.learning.response.Donation;

import reactor.core.publisher.Mono;

@Service
public class DonationServiceImplementation implements IDonationOperations
{
	private WebClient webClient;
	private static final String BASE_URL="http://localhost:9090/track/api";

	public DonationServiceImplementation(WebClient.Builder webClientBuilder)
	{
		this.webClient=webClientBuilder.baseUrl(BASE_URL).build();
	}
	@Override
	public Mono<String> addDonation(Donation donation) {
		return this.webClient.post()
				.uri("/add")
				.bodyValue(donation)
				.retrieve()
				.bodyToMono(String.class);
	}

	@Override
	public Donation getDonationInfoById(Long id) {
		Donation donationDetails=this.webClient.get()
								 .uri("/get/{sl}",id)
								 .retrieve()
								 .bodyToMono(Donation.class).block();
		return donationDetails;
	}

	@Override
	public List<Donation> getAllDonationsInfo() {
		Donation[] donationsList=this.webClient.get()
								 .uri("/getAll")
								 .retrieve()
								 .bodyToMono(Donation[].class)
								 .block();
		return Arrays.stream(donationsList).toList();
	}

	@Override
	public Mono<String> updateDonation(Long id, Donation donation) {
		return this.webClient.put()
			   .uri("/update/{sl}",id)
			   .bodyValue(donation)
			   .retrieve()
			   .bodyToMono(String.class);
	}

	@Override
	public Mono<String> deleteDonation(Long id) {
		return this.webClient.delete()
				.uri("/delete/{sl}",id)
				.retrieve()
				.bodyToMono(String.class);
	}

}
