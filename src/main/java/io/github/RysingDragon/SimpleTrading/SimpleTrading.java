package io.github.RysingDragon.SimpleTrading;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

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
import io.github.RysingDragon.SimpleTrading.commands.TradeRequest;
import io.github.RysingDragon.SimpleTrading.config.Config;
import io.github.RysingDragon.SimpleTrading.listeners.TradeListener;
import io.github.RysingDragon.SimpleTrading.utils.Request;
import ninja.leaping.configurate.commented.CommentedConfigurationNode;
import ninja.leaping.configurate.loader.ConfigurationLoader;

@Plugin(id = "simpletrading", name = "SimpleTrading", version = "0.1")
public class SimpleTrading {

	public static SimpleTrading instance;
	private Game game = Sponge.getGame();
	
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
		CommandSpec tradeRequest = CommandSpec.builder()
				.arguments(GenericArguments.player(Text.of("player")))
				.executor(new TradeRequest())
				.build();
		
		CommandSpec tradeAccept = CommandSpec.builder()
				.arguments(GenericArguments.player(Text.of("player")))
				.executor(new TradeAccept())
				.build();
		
		CommandSpec trade = CommandSpec.builder()
				.child(tradeRequest, "request")
				.child(tradeAccept, "accept")
				.build();
		game.getCommandManager().register(this, trade, "trade");
		
		game.getEventManager().registerListeners(this, new TradeListener());
	}
	
	public Path getConfigPath() {
		return defaultConfig;
	}
	
}
