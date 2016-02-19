package io.github.RysingDragon.EasyTrading.events;

import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.Cancellable;
import org.spongepowered.api.event.cause.Cause;
import org.spongepowered.api.event.impl.AbstractEvent;

import io.github.RysingDragon.EasyTrading.EasyTrading;

public class TradeAcceptEvent extends AbstractEvent implements Cancellable{

	private Player sender;
	private Player receiver;
	private boolean cancelled = false;
	
	public TradeAcceptEvent(Player sender, Player receiver) {
		this.sender = sender;
		this.receiver = receiver;
	}
	
	public Cause getCause() {
		return Cause.of(this.sender);
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
