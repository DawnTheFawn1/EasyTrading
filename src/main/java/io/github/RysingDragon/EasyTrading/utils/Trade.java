package io.github.RysingDragon.EasyTrading.utils;

import java.math.BigDecimal;
import java.util.List;

import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.item.inventory.ItemStack;

public class Trade {
	
	private Player sender;
	private Player receiver;
	
	public List<ItemStack> senderItems;
	public List<ItemStack> receiverItems;
	public BigDecimal senderMoney = new BigDecimal(0);
	public BigDecimal receiverMoney = new BigDecimal(0);
	
	public Trade(Player sender, Player receiver) {
		this.sender = sender;
		this.receiver = receiver;
	}
	
	public Player getSender() {
		return this.sender;
	}
	
	public Player getReceiver() {
		return this.receiver;
	}
	
}
