package io.github.RysingDragon.EasyTrading.utils;

import java.util.ArrayList;
import java.util.List;

import org.spongepowered.api.entity.living.player.Player;

public class TradeUtils {

	public static List<Request> requests = new ArrayList<>();
	
	public static List<Trade> trades = new ArrayList<>();
	
	
	public static boolean isTrading(Player player) {
		for (Trade t : trades) {
			if (player == t.getSender() || player == t.getReceiver()) {
				return true;
			}
		}
		return false;
	}
}
