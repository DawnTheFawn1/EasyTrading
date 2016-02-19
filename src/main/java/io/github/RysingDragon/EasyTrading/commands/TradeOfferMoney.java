package io.github.RysingDragon.EasyTrading.commands;

import java.math.BigDecimal;

import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.text.Text;

import io.github.RysingDragon.EasyTrading.EasyTrading;
import io.github.RysingDragon.EasyTrading.utils.Trade;
import io.github.RysingDragon.EasyTrading.utils.TradeUtils;

public class TradeOfferMoney implements CommandExecutor{

	@Override
	public CommandResult execute(CommandSource src, CommandContext args) throws CommandException {

		if (src instanceof Player) {
			Player player = (Player) src;
			double amount = (double)args.getOne("amount").get();
			if (TradeUtils.isTrading(player)) {
				BigDecimal offeredBalance = new BigDecimal(amount);
				BigDecimal currentBalance = EasyTrading.economy.getAccount(player.getUniqueId()).get().getBalance(EasyTrading.economy.getDefaultCurrency());
				Trade trade = TradeUtils.getTrade(player);
				if (currentBalance.compareTo(offeredBalance) < 0) {
					player.sendMessage(Text.of("you do not have enough money!"));
					return CommandResult.empty();
				}
				if (trade.getSender() == player) {
					trade.getSenderMoney().add(offeredBalance);
				} else {
					trade.getReceiverMoney().add(offeredBalance);
				}
				player.sendMessage(Text.of(EasyTrading.economy.getDefaultCurrency().getSymbol(), offeredBalance, " have been added to your offers"));
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
