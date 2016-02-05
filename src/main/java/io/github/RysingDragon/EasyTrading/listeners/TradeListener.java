package io.github.RysingDragon.EasyTrading.listeners;

import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

import org.spongepowered.api.Sponge;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.scheduler.Scheduler;
import org.spongepowered.api.scheduler.Task;
import org.spongepowered.api.text.Text;

import io.github.RysingDragon.EasyTrading.EasyTrading;
import io.github.RysingDragon.EasyTrading.events.TradeAcceptEvent;
import io.github.RysingDragon.EasyTrading.events.TradeRequestEvent;
import io.github.RysingDragon.EasyTrading.utils.Request;
import io.github.RysingDragon.EasyTrading.utils.Trade;
import io.github.RysingDragon.EasyTrading.utils.TradeUtils;

public class TradeListener {

	@Listener
	public void onTradeRequest(TradeRequestEvent event) {
		Request req = new Request(event.getSender(), event.getReceiver());
		TradeUtils.requests.add(req);
		event.getReceiver().sendMessage(Text.of("Trade request pending from ", event.getSender().getName(), "! Type /trade accept ", event.getSender().getName(), " to start trading"));
		Consumer<Task> TradeTimer = new Consumer<Task>() {
			private int seconds = 60;
			@Override
			public void accept(Task t) {
				this.seconds--;
				if (this.seconds < 1) {
					TradeUtils.requests.remove(req);
					t.cancel();
				}
			}
		};
		
		Sponge.getScheduler().createTaskBuilder().interval(1, TimeUnit.SECONDS).delay(1, TimeUnit.SECONDS).execute(TradeTimer).submit(EasyTrading.instance);
	}
	
	@Listener
	public void onTradeAccept(TradeAcceptEvent event) {
		
		Trade trade = new Trade(event.getSender(), event.getReceiver());
		TradeUtils.trades.add(trade);
	}
	
}
