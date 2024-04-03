package com.learning.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.learning.response.Donation;
import com.learning.service.IDonationOperations;

@Controller
public class DonationController 
{
	@Autowired
	IDonationOperations service;
	
	@GetMapping("/donation/all")
	public String getAllDonations(Model model)
	{
		List<Donation> donationsList=service.getAllDonationsInfo();
		model.addAttribute("donations", donationsList);
		return "donation";
	}
	
	@GetMapping("/donation/add")
	public String showAddDonationForm(Model model)
	{
		model.addAttribute("donation",new Donation());
		return "addDonation";
	}
	
	@PostMapping("/donation/added")
    public String addCourse(@ModelAttribute Donation donation) {
        service.addDonation(donation).block();
        return "redirect:/donation/all";
    }
	
	@GetMapping("/donation/update/{id}")
    public String showEditDonationForm(@PathVariable Long id, Model model) {
        Donation donationDetails=service.getDonationInfoById(id);
        model.addAttribute("update_donation",donationDetails);
        return "updateDonationForm";
    }
	
	@PostMapping("/donation/update/{id}")
    public String updateDonation(@PathVariable Long id, 
    		@ModelAttribute("donation")Donation donation) 
    {
        service.updateDonation(id,donation).block();
        return "redirect:/donation/all";
    }
	
	@GetMapping("/donation/fetch")
    public String showDonationForm(Model model) {
    	model.addAttribute("donation",new Donation());
    	return "viewDonationForm";
    }

    @GetMapping("/donation/fetchDonation")
    public String getDonationInfoById(@RequestParam("sl") Long id, 
    		@ModelAttribute("donation")Donation donation, Model model) {
       Donation donationDetails=service.getDonationInfoById(id);
        model.addAttribute("donation",donationDetails);
        return "viewDonationDetails";
    }
    
    @GetMapping("/donation/delete/{id}")
    public String deleteDonationById(@PathVariable Long id) 
    {
    	service.deleteDonation(id).block();
        return "redirect:/donation/all";
    }
}
