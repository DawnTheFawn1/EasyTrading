package io.github.RysingDragon.EasyTrading.listeners;

import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.filter.cause.First;
import org.spongepowered.api.event.item.inventory.ClickInventoryEvent;
import org.spongepowered.api.event.item.inventory.DropItemEvent;
import org.spongepowered.api.item.inventory.ItemStack;
import org.spongepowered.api.text.Text;

import io.github.RysingDragon.EasyTrading.utils.Trade;
import io.github.RysingDragon.EasyTrading.utils.TradeUtils;

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
			Trade trade = TradeUtils.getTrade(player);
			if (!(e.getCursorTransaction().getFinal() == e.getCursorTransaction().getFinal().NONE)) {
				ItemStack item = e.getCursorTransaction().getFinal().createStack();
				if (trade.getSender() == player) {
					if (!trade.senderItems.contains(item)) {
						player.sendMessage(Text.of("Item stack added to offers"));
						trade.senderItems.add(item);
					} else {
						player.sendMessage(Text.of("Item stack removed from offers"));
						trade.senderItems.remove(item);
					}
				} else if (trade.getReceiver() == player) {
					if (!trade.senderItems.contains(item)) {
						player.sendMessage(Text.of("Item stack added to offers"));
						trade.receiverItems.add(item);
					} else {
						player.sendMessage(Text.of("Item stack removed from offers"));
						trade.receiverItems.remove(item);
					}
				}
			}
			e.setCancelled(true);
		}
	}
	
}
