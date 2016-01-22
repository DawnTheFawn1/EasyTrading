package io.github.RysingDragon.SimpleTrading.utils;

import org.spongepowered.api.item.inventory.ItemStack;

public class ItemOffer extends TradeOffer{
	
	private ItemStack item;

	public ItemOffer(ItemStack item) {
		this.item = item;
	}
	
	public ItemStack getOffer() {
		return this.item;
	}
	
	@Override
	public String getType() {
		return ITEM;
	}
	
}
