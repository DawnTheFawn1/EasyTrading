package io.github.RysingDragon.SimpleTrading.events;

import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.Cancellable;
import org.spongepowered.api.event.cause.Cause;
import org.spongepowered.api.event.impl.AbstractEvent;

import io.github.RysingDragon.SimpleTrading.SimpleTrading;

public class TradeRequestEvent extends AbstractEvent implements Cancellable{

	private Player sender;
	private Player receiver;
	private boolean cancelled = false;
	
	public TradeRequestEvent(Player sender, Player receiver) {
		this.sender = sender;
		this.receiver = receiver;
	}
	
	public Cause getCause() {
		return Cause.of(SimpleTrading.instance);
	}

	public boolean isCancelled() {
		return this.cancelled;
	}

	public void setCancelled(boolean cancel) {
		this.cancelled = cancel;
		
	}
	
	public Player getSender() {
		return this.sender;
	}

	public Player getReceiver() {
		return this.receiver;
	}
	
}
