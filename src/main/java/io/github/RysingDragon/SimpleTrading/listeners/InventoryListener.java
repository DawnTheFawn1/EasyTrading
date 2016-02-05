package io.github.RysingDragon.SimpleTrading.listeners;

import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.filter.cause.First;
import org.spongepowered.api.event.item.inventory.ClickInventoryEvent;
import org.spongepowered.api.event.item.inventory.DropItemEvent;
import org.spongepowered.api.text.Text;

import io.github.RysingDragon.SimpleTrading.utils.TradeUtils;

public class InventoryListener {

	@Listener
	public void onDrop(DropItemEvent e, @First Player player) {
		if (TradeUtils.isTrading(player)) {
			player.sendMessage(Text.of("You are not allowed to drop items during a trade"));
			e.setCancelled(true);
		}
	}
	
	@Listener
	public void onInventoryClick(ClickInventoryEvent e, @First Player player) {
		if (TradeUtils.isTrading(player)) {
			player.sendMessage(Text.of("You are not allowed to interact with your inventory during a trade"));
			e.setCancelled(true);
		}
	}
	
}
