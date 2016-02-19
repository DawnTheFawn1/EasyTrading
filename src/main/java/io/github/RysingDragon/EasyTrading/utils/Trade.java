package io.github.RysingDragon.EasyTrading.utils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.item.inventory.ItemStack;
import org.spongepowered.api.item.inventory.ItemStackSnapshot;

public class Trade {
	
	private Player sender;
	private Player receiver;
	
	private List<ItemStack> senderItems = new ArrayList<>();
	private List<ItemStack> receiverItems = new ArrayList<>();
	private BigDecimal senderMoney = new BigDecimal(0);
	private BigDecimal receiverMoney = new BigDecimal(0);
	
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
	
	public List<ItemStack> getSenderItems() {
		return this.senderItems;
	}
	
	public List<ItemStack> getReceiverItems() {
		return this.receiverItems;
	}
	
	public BigDecimal getSenderMoney() {
		return this.senderMoney;
	}
	
	public BigDecimal getReceiverMoney() {
		return this.receiverMoney;
	}
	
}