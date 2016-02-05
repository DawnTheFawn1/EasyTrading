package io.github.RysingDragon.EasyTrading.commands;

import java.util.List;

import org.spongepowered.api.Sponge;
import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.text.Text;

import io.github.RysingDragon.EasyTrading.events.TradeAcceptEvent;
import io.github.RysingDragon.EasyTrading.utils.Request;
import io.github.RysingDragon.EasyTrading.utils.TradeUtils;

public class TradeAccept implements CommandExecutor{

	public CommandResult execute(CommandSource src, CommandContext args) throws CommandException {
		
		if (src instanceof Player) {
			Player sender = (Player)src;
			Player receiver = (Player)args.getOne("player").get();
			List<Request> reqs = TradeUtils.requests;
			
			if (reqs.isEmpty()) {
				sender.sendMessage(Text.of("trade not found"));
				return CommandResult.success();
			} 
			
			for (Request r : reqs) {
				if ( (sender == r.getReceiver() ) && (receiver == r.getSender() ) ) {
					sender.sendMessage(Text.of("trade started"));
					receiver.sendMessage(Text.of("trade started"));
					Sponge.getEventManager().post(new TradeAcceptEvent(sender, receiver));
				} else {
					sender.sendMessage(Text.of("trade not found"));
				}
			}
			return CommandResult.success();
		} else {
			src.sendMessage(Text.of("You must be a player to use this command"));
			return CommandResult.empty();
		}
		
	}

}
