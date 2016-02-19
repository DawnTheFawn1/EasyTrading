package io.github.RysingDragon.EasyTrading.commands;

import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.text.Text;

import io.github.RysingDragon.EasyTrading.utils.Trade;
import io.github.RysingDragon.EasyTrading.utils.TradeUtils;

public class TradeCancel implements CommandExecutor{

	public CommandResult execute(CommandSource src, CommandContext args) throws CommandException {
		if (src instanceof Player) {
			Player player = (Player)src;
			
			if(TradeUtils.isTrading(player)) { 
				Trade t = TradeUtils.getTrade(player);
				TradeUtils.getTrades().remove(t);
				t.getReceiver().sendMessage(Text.of("Your trade with ", t.getSender().getName(), " has been cancelled!"));
				t.getSender().sendMessage(Text.of("Your trade with ", t.getReceiver().getName(), " has been cancelled!"));
				return CommandResult.success();
			} else {
				player.sendMessage(Text.of("You are not in a trade!"));
				return CommandResult.empty();
			}
		} else {
			src.sendMessage(Text.of("You must be a player to use this command!"));
			return CommandResult.empty();
		}
	}

}
