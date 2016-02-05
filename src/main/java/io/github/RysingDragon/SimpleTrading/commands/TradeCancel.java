package io.github.RysingDragon.SimpleTrading.commands;

import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.text.Text;

import io.github.RysingDragon.SimpleTrading.utils.Trade;
import io.github.RysingDragon.SimpleTrading.utils.TradeUtils;

public class TradeCancel implements CommandExecutor{

	public CommandResult execute(CommandSource src, CommandContext args) throws CommandException {
		if (src instanceof Player) {
			Player player = (Player)src;
			Player trader = (Player)args.getOne("player").get();
			if(TradeUtils.isTrading(player)) {
				for (Trade t : TradeUtils.trades) {
					if ((t.getSender() == player || t.getReceiver() == player) && (t.getSender() == trader || t.getReceiver() == trader)) {
						TradeUtils.trades.remove(t);
						player.sendMessage(Text.of("Your trade with ", trader.getName(), " has been cancelled!"));
						trader.sendMessage(Text.of("Your trade with ", player.getName(), " has been cancelled!"));
					}
				}
			}
			return CommandResult.success();
		} else {
			src.sendMessage(Text.of("You must be a player to use this command!"));
			return CommandResult.empty();
		}
	}

}
