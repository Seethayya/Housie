package com.speoma.housie.rest.ticketgenerator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Ticket {

	private List<Integer> numbers;
	private List<Integer> row1;
	private List<Integer> row2;
	private List<Integer> row3;
	
	public Ticket() {
		numbers = new ArrayList<Integer>();
		row1 = Arrays.asList(null,null,null,null,null,null,null,null,null);
		row2 = Arrays.asList(null,null,null,null,null,null,null,null,null);
		row3 = Arrays.asList(null,null,null,null,null,null,null,null,null);
	}
	
	public Ticket(String formattedTicket) {
		numbers = new ArrayList<Integer>();
		String[] rows = formattedTicket.split("-");
		row1 = new ArrayList<Integer>();
		row2 = new ArrayList<Integer>();
		row3 = new ArrayList<Integer>();
		for (String number : rows[0].split("\\|")) {
			if (!"null".equals(number)) {
				row1.add(Integer.valueOf(number));
				numbers.add(Integer.valueOf(number));
			}
		}
		for (String number : rows[1].split("\\|")) {
			if (!"null".equals(number)) {
				row2.add(Integer.valueOf(number));
				numbers.add(Integer.valueOf(number));
			}
		}
		for (String number : rows[2].split("\\|")) {
			if (!"null".equals(number)) {
				row3.add(Integer.valueOf(number));
				numbers.add(Integer.valueOf(number));
			}
		}
	}
	
	/**
	 * @return the numbers
	 */
	public List<Integer> getNumbers() {
		return numbers;
	}


	/**
	 * @param numbers the numbers to set
	 */
	public void setNumbers(List<Integer> numbers) {
		this.numbers = numbers;
	}


	/**
	 * @return the row1
	 */
	public List<Integer> getRow1() {
		return row1;
	}


	/**
	 * @param row1 the row1 to set
	 */
	public void setRow1(List<Integer> row1) {
		this.row1 = row1;
	}


	/**
	 * @return the row2
	 */
	public List<Integer> getRow2() {
		return row2;
	}


	/**
	 * @param row2 the row2 to set
	 */
	public void setRow2(List<Integer> row2) {
		this.row2 = row2;
	}


	/**
	 * @return the row3
	 */
	public List<Integer> getRow3() {
		return row3;
	}


	/**
	 * @param row3 the row3 to set
	 */
	public void setRow3(List<Integer> row3) {
		this.row3 = row3;
	}
	
	public String getFormattedTicket() {
		StringBuilder ticket = new StringBuilder();
		row1.stream().forEach(value -> ticket.append(value).append("|"));
		ticket.deleteCharAt(ticket.length()-1);
		ticket.append("-");
		row2.stream().forEach(value -> ticket.append(value).append("|"));
		ticket.deleteCharAt(ticket.length()-1);
		ticket.append("-");
		row3.stream().forEach(value -> ticket.append(value).append("|"));
		ticket.deleteCharAt(ticket.length()-1);
		ticket.append("-");
		return ticket.toString();
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Ticket---------\n" + row1 + "\n" + row2 + "\n "+ row3+"\n---------";
	}
	
}
