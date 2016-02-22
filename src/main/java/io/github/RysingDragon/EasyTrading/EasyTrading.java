package io.github.RysingDragon.EasyTrading;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.spongepowered.api.Game;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.command.args.GenericArguments;
import org.spongepowered.api.command.spec.CommandSpec;
import org.spongepowered.api.config.ConfigDir;
import org.spongepowered.api.config.DefaultConfig;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.game.state.GameInitializationEvent;
import org.spongepowered.api.event.game.state.GamePostInitializationEvent;
import org.spongepowered.api.event.game.state.GamePreInitializationEvent;
import org.spongepowered.api.event.service.ChangeServiceProviderEvent;
import org.spongepowered.api.plugin.Plugin;
import org.spongepowered.api.service.economy.EconomyService;
import org.spongepowered.api.text.Text;

import com.google.inject.Inject;

import io.github.RysingDragon.EasyTrading.commands.TradeAccept;
import io.github.RysingDragon.EasyTrading.commands.TradeCancel;
import io.github.RysingDragon.EasyTrading.commands.TradeOfferMoney;
import io.github.RysingDragon.EasyTrading.commands.TradeRequest;
import io.github.RysingDragon.EasyTrading.commands.TradeStatus;
import io.github.RysingDragon.EasyTrading.config.Config;
import io.github.RysingDragon.EasyTrading.data.ImmutableTradeData;
import io.github.RysingDragon.EasyTrading.data.TradeData;
import io.github.RysingDragon.EasyTrading.data.TradeDataManipulatorBuilder;
import io.github.RysingDragon.EasyTrading.listeners.InventoryListener;
import io.github.RysingDragon.EasyTrading.listeners.TradeListener;
import ninja.leaping.configurate.commented.CommentedConfigurationNode;
import ninja.leaping.configurate.loader.ConfigurationLoader;

@Plugin(id = "easytrading", name = "EasyTrading", version = "1.0")
public class EasyTrading {

	public static EasyTrading instance;
	public static EconomyService economy;
	private Game game = Sponge.getGame();
	
	@Inject
	private Logger logger;
	
	@Inject
	@DefaultConfig(sharedRoot=true)
	private Path defaultConfig;
	
	@Inject
	@DefaultConfig(sharedRoot=true)
	private ConfigurationLoader<CommentedConfigurationNode> loader;
	
	@Inject
	@ConfigDir(sharedRoot=true)
	private Path configDir;
	
	@Listener
	public void onPreInit(GamePreInitializationEvent event) {
		instance = this;
		Config.getConfig().setup();
		Sponge.getDataManager().register(TradeData.class, ImmutableTradeData.class, new TradeDataManipulatorBuilder());
	}
	
	@Listener
	public void onInit(GameInitializationEvent event) {		
		Map offerChoices = new HashMap<String, String>();
		offerChoices.put("money", "money");
		if(Config.configNode.getNode("pixelmonEnabled").getBoolean())
			offerChoices.put("pixelmon", "pixelmon");
		CommandSpec tradeRequest = CommandSpec.builder()
				.arguments(GenericArguments.player(Text.of("player")))
				.executor(new TradeRequest())
				.build();
		
		CommandSpec tradeAccept = CommandSpec.builder()
				.arguments(GenericArguments.player(Text.of("player")))
				.executor(new TradeAccept())
				.build();
		
		CommandSpec tradeCancel = CommandSpec.builder()
				.executor(new TradeCancel())
				.build();
		
		CommandSpec tradeMoney = CommandSpec.builder()
				.arguments(GenericArguments.doubleNum(Text.of("amount")))
				.executor(new TradeOfferMoney())
				.build();
		
		CommandSpec tradeOffer = CommandSpec.builder()
				.child(tradeMoney, "money")
				.build();
		
		CommandSpec tradeStatus = CommandSpec.builder()
				.executor(new TradeStatus())
				.build();
		
		CommandSpec trade = CommandSpec.builder()
				.child(tradeStatus, "status")
				.child(tradeOffer, "offer")
				.child(tradeCancel, "cancel")
				.child(tradeRequest, "request")
				.child(tradeAccept, "accept")
				.build();
		game.getCommandManager().register(this, trade, "trade");
		
		game.getEventManager().registerListeners(this, new TradeListener());
		game.getEventManager().registerListeners(this, new InventoryListener());
	}
	
	@Listener
	public void onServiceChange(ChangeServiceProviderEvent event) {
		if (event.getService().equals(EconomyService.class)) {
			economy = (EconomyService) event.getNewProviderRegistration().getProvider();
		}
	}
	
	public Path getConfigPath() {
		return defaultConfig;
	}
	
	public Logger getLogger() {
		return logger;
	}
	
}