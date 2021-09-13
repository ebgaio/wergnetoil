package com.wergnet.wergnetoil.api.service;

import java.util.List;
import java.util.Vector;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.stereotype.Service;

@Service
public class CardNumberGenerator {

	public static void main(String[] args) {
		CardNumberGenerator number = new CardNumberGenerator();
		
		for (int i = 0; i < 20; i++) {
			System.out.println(number.generateNumber());
		}
	}
	
	public String generateNumber() {

		Integer randomNumber = ThreadLocalRandom.current().nextInt(7000, 7999 + 1);

		var ccNumber = randomNumber.toString();
		
		var cardSize = 16;

		while (ccNumber.length() < (cardSize - 1)) {
			ccNumber += Double.valueOf((Math.floor(Math.random() * 10))).intValue();
		}

		var reverseCcNumberString = ccNumber;

		List<Integer> reverseCcNumberList = new Vector<Integer>();
		for (int i = 0; i < reverseCcNumberString.length(); i++) {
			reverseCcNumberList.add(Integer.valueOf((String.valueOf(reverseCcNumberString.charAt(i)))));
		}

		var sum = 0;
		var pos = 0;

		Integer[] reverseCcNumber = reverseCcNumberList.toArray(new Integer[reverseCcNumberList.size()]);
		while (pos < cardSize - 1) {

			var odd = reverseCcNumber[pos] * 2;
			if (odd > 9) {
				odd -= 9;
			}

			sum += odd;

			if (pos != (cardSize - 2)) {
				sum += reverseCcNumber[pos + 1];
			}
			pos += 2;
		}

		var checkdigit = Double.valueOf((((Math.floor(sum / 10) + 1) * 10 - sum) % 10)).intValue();
		ccNumber += checkdigit;

		return ccNumber;
	}
	
	/*
	 * Numbers generated today
	 * 7777484843402274 7294388306634386 7763108925281738
	 * 7929549962065669 7658936063670231 7145393493136287 
	 * 7211993821759178	7472285323619529 7971974687981874 
	 * 7880417417936008 7281177397153249 7932812411723048 
	 * 7242457894562268 7659421281764776 7846425840473492
	 * 7813747679902724 7819033487830470 7144516955655326 
	 * 7684010281632031	7599808272609072
	 */
}