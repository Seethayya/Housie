package com.speoma.housie.rest.ticketgenerator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.stereotype.Component;


@Component
public class TicketGenerator {

	public Ticket generateTicket() {
		final Map<Integer, SortedSet<Integer>> ticketColumns= geneteValuePerColumn();
		int numbersNeeded = 6;
		while (numbersNeeded > 0) {
			int nextNo = generateRandomNumberBetweenRange(1, 91);
			int columnIndex = nextNo == 90 ? ((nextNo -1)/10): (nextNo/10);
			if (!ticketColumns.get(columnIndex).contains(nextNo) && ticketColumns.get(columnIndex).size()<3) {
				ticketColumns.get(columnIndex).add(nextNo);
				numbersNeeded--;
			}
		}
		Ticket ticket  = new Ticket();
		List<Integer> columns = IntStream.rangeClosed(0, 8).boxed().collect(Collectors.toCollection(ArrayList::new));	
		Collections.shuffle(columns);
		Collections.sort(columns, new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				return ticketColumns.get(o2).size() - ticketColumns.get(o1).size();
			}
			
		});
		IntStream.rangeClosed(0, 4).forEach(index -> {
									int columnNo = columns.get(index);
									int  value = ticketColumns.get(columnNo).iterator().next();
									ticketColumns.get(columnNo).remove(value);
									ticket.getNumbers().add(value);
									ticket.getRow1().set(columnNo, value);
									if (ticketColumns.get(columnNo).size() == 0) {
										columns.remove(index);
									}
									});
		Collections.shuffle(columns);
		Collections.sort(columns, new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				return ticketColumns.get(o2).size() - ticketColumns.get(o1).size();
			}
			
		});
		IntStream.rangeClosed(0, 4).forEach(index -> {
									int columnNo = columns.get(index);
									int  value = ticketColumns.get(columnNo).iterator().next();
									ticketColumns.get(columnNo).remove(value);
									ticket.getNumbers().add(value);
									ticket.getRow2().set(columnNo, value);
									if (ticketColumns.get(columnNo).size() == 0) {
										columns.remove(index);
									}
									});
		Collections.shuffle(columns);
		IntStream.rangeClosed(0, 4).forEach(index -> {
									int columnNo = columns.get(index);
									int  value = ticketColumns.get(columnNo).iterator().next();
									ticketColumns.get(columnNo).remove(value);
									ticket.getNumbers().add(value);
									ticket.getRow3().set(columnNo, value);
//									if (ticketColumns.get(columnNo).size() == 0) {
//										columns.remove(index);
//									}
									});
		return ticket;
	}

	public static Map<Integer, SortedSet<Integer>> geneteValuePerColumn() {
		Map<Integer, SortedSet<Integer>> ticketColumns= new HashMap<>();
		
		IntStream.rangeClosed(0, 8).forEach(index-> {
									int start = index == 0 ? index +1 : index*10;
									SortedSet<Integer> values = new TreeSet<>();
									values.add(generateRandomNumberBetweenRange(start, (index*10)+10));
									ticketColumns.put(index, values);									
									});
		return ticketColumns;
	}
	
	public static List<Integer> generateHousieNumber() {
		List<Integer> numbers = IntStream.rangeClosed(1, 90).boxed().collect(Collectors.toCollection(ArrayList::new));	
		Collections.shuffle(numbers);
		//List<Integer> numbers = new Random().ints(1, 91).boxed().collect(Collectors.toCollection(ArrayList::new));
		return numbers;
	}
	
	public static String formatHousieNos() {
		StringBuilder housieNos = new StringBuilder();

		 generateHousieNumber().stream().forEach(value -> housieNos.append(value).append("|"));
		 housieNos.deleteCharAt(housieNos.length()-1);
		 return housieNos.toString();

	}
	
	public static int generateRandomNumberBetweenRange(int startInclusive, int endExclusive) {
		return new Random().ints(1, startInclusive, endExclusive).findFirst().getAsInt();
	}
	
	
}
