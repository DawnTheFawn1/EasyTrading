package io.github.RysingDragon.SimpleTrading;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.spongepowered.api.Game;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.command.args.GenericArguments;
import org.spongepowered.api.command.spec.CommandSpec;
import org.spongepowered.api.config.ConfigDir;
import org.spongepowered.api.config.DefaultConfig;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.game.state.GameInitializationEvent;
import org.spongepowered.api.event.game.state.GamePreInitializationEvent;
import org.spongepowered.api.plugin.Plugin;
import org.spongepowered.api.text.Text;

import com.google.inject.Inject;

import io.github.RysingDragon.SimpleTrading.commands.TradeAccept;
import io.github.RysingDragon.SimpleTrading.commands.TradeCancel;
import io.github.RysingDragon.SimpleTrading.commands.TradeRequest;
import io.github.RysingDragon.SimpleTrading.config.Config;
import io.github.RysingDragon.SimpleTrading.listeners.InventoryListener;
import io.github.RysingDragon.SimpleTrading.listeners.TradeListener;
import ninja.leaping.configurate.commented.CommentedConfigurationNode;
import ninja.leaping.configurate.loader.ConfigurationLoader;

@Plugin(id = "simpletrading", name = "SimpleTrading", version = "0.1")
public class SimpleTrading {

	public static SimpleTrading instance;
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
	}
	
	@Listener
	public void onInit(GameInitializationEvent event) {
		Map offerChoices = new HashMap<String, String>();
		offerChoices.put("money", "money");
		offerChoices.put("item", "item");
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
		
		CommandSpec tradeOffer = CommandSpec.builder()
				.arguments(GenericArguments.choices(Text.of("offer"), offerChoices, true))
				.build();
		
		CommandSpec tradeCancel = CommandSpec.builder()
				.arguments(GenericArguments.player(Text.of("player")))
				.executor(new TradeCancel())
				.build();
		
		CommandSpec trade = CommandSpec.builder()
				.child(tradeCancel, "cancel")
				.child(tradeRequest, "request")
				.child(tradeAccept, "accept")
				.child(tradeOffer, "offer")
				.build();
		game.getCommandManager().register(this, trade, "trade");
		
		game.getEventManager().registerListeners(this, new TradeListener());
		game.getEventManager().registerListeners(this, new InventoryListener());
	}
	
	public Path getConfigPath() {
		return defaultConfig;
	}
	
	public Logger getLogger() {
		return logger;
	}
	
}
