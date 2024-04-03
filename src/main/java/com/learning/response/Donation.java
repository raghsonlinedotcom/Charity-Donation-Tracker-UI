package com.learning.response;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Donation 
{
	private Long sl;
	private String year;
	private String receiptNumber;
	private Long amount;
	private String institution;
	private String addr;
	private String pan;
	private String date;
	private String time;
}
