package io.github.RysingDragon.EasyTrading.listeners;

import java.util.Optional;

import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.filter.cause.First;
import org.spongepowered.api.event.item.inventory.ClickInventoryEvent;
import org.spongepowered.api.event.item.inventory.DropItemEvent;
import org.spongepowered.api.item.inventory.ItemStack;
import org.spongepowered.api.item.inventory.Slot;
import org.spongepowered.api.text.Text;

import io.github.RysingDragon.EasyTrading.data.TradeData;
import io.github.RysingDragon.EasyTrading.data.TradeKeys;
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
			e.setCancelled(true);
			Trade trade = TradeUtils.getTrade(player);
			Slot slot = e.getTransactions().get(0).getSlot();
			Optional<ItemStack> optStack = (slot.peek().isPresent())? Optional.of(slot.peek().get()) : Optional.empty();
			
			if (optStack.isPresent()) {
				ItemStack stack = optStack.get();
				if (stack.get(TradeKeys.TRADE_ITEM).isPresent()) {
					if (stack.get(TradeKeys.TRADE_ITEM).get()) {
						stack.offer(TradeKeys.TRADE_ITEM, false);
					} else {
						stack.offer(TradeKeys.TRADE_ITEM, true);
					}
				} else {
					stack.offer(new TradeData(true));
				}
				slot.set(stack);
				
				if (trade.getSender() == player) {

					if (stack.get(TradeKeys.TRADE_ITEM).get()) {
						trade.getSenderItems().add(stack);
						player.sendMessage(Text.of("Item stack added to offers"));
					} else {
						trade.getSenderItems().remove(stack);
						player.sendMessage(Text.of("Item stack removed from offers"));
					}		
				} else {
					
					if (stack.get(TradeKeys.TRADE_ITEM).get()) {
						trade.getReceiverItems().add(stack);
						player.sendMessage(Text.of("Item stack added to offers"));
					} else {
						trade.getReceiverItems().remove(stack);
						player.sendMessage(Text.of("Item stack removed from offers"));
					}
				}
			}
		}
	}
}


