package io.github.RysingDragon.EasyTrading.utils;

import org.spongepowered.api.entity.living.player.Player;

public class Request {

	private Player sender;
	private Player receiver;
	
	public Request(Player sender, Player receiver) {
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
