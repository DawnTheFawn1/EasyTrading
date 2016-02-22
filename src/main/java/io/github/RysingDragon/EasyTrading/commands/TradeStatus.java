package io.github.RysingDragon.EasyTrading.commands;

import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.item.inventory.ItemStack;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColors;

import io.github.RysingDragon.EasyTrading.EasyTrading;
import io.github.RysingDragon.EasyTrading.utils.Trade;
import io.github.RysingDragon.EasyTrading.utils.TradeUtils;

public class TradeStatus implements CommandExecutor{

	@Override
	public CommandResult execute(CommandSource src, CommandContext args) throws CommandException {
		
		if (src instanceof Player) {
			Player player = (Player) src;
			if (TradeUtils.isTrading(player)) {
				Trade trade = TradeUtils.getTrade(player);
				
				if (trade.getSender() == player) {
					player.sendMessage(Text.of(TextColors.GOLD, "Your offers:"));
					player.sendMessage(Text.of(EasyTrading.economy.getDefaultCurrency().getSymbol(), trade.getSenderMoney()));
					for (ItemStack i : trade.getSenderItems()) {
						Text.Builder text = Text.builder();
						text.append(Text.of(i.getTranslation()));
						text.append(Text.of("(", i.getQuantity(), ")"));
						player.sendMessage(text.build());
					}
					player.sendMessage(Text.of(TextColors.GOLD, "Offers to you:"));
					player.sendMessage(Text.of(EasyTrading.economy.getDefaultCurrency().getSymbol(), trade.getReceiverMoney()));
					for (ItemStack i : trade.getReceiverItems()) {
						Text.Builder text = Text.builder();
						text.append(Text.of(i.getTranslation()));
						text.append(Text.of("(", i.getQuantity(), ")"));
						player.sendMessage(text.build());
					}
					
				} else {
					player.sendMessage(Text.of(TextColors.GOLD, "Your offers: "));
					player.sendMessage(Text.of(EasyTrading.economy.getDefaultCurrency().getSymbol(), trade.getReceiverMoney()));
					for (ItemStack i : trade.getReceiverItems()) {
						Text.Builder text = Text.builder();
						text.append(Text.of(i.getTranslation()));
						text.append(Text.of("(", i.getQuantity(), ")"));
						player.sendMessage(text.build());
					}
					player.sendMessage(Text.of(TextColors.GOLD, "Offers to you:"));
					player.sendMessage(Text.of(EasyTrading.economy.getDefaultCurrency().getSymbol(), trade.getSenderMoney()));
					for (ItemStack i : trade.getSenderItems()) {
						Text.Builder text = Text.builder();
						text.append(Text.of(i.getTranslation()));
						text.append(Text.of("(", i.getQuantity(), ")"));
						player.sendMessage(text.build());
					}
				}
				
			} else {
				player.sendMessage(Text.of("You must be in a trade to use this command!"));
				return CommandResult.empty();
			}
			return CommandResult.success();
			
		} else {
			src.sendMessage(Text.of("You must be a player to use this command!"));
			return CommandResult.empty();
		} 
	}
}
