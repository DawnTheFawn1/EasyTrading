package io.github.RysingDragon.SimpleTrading.utils;

import java.util.ArrayList;
import java.util.List;

public class TradeUtils {

	public static List<Request> requests = new ArrayList<>();
	
	public static void addRequest(Request r) {
		requests.add(r);
	}
	
}
