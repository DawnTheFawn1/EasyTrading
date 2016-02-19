package io.github.RysingDragon.EasyTrading.data;

import java.util.Optional;

import org.spongepowered.api.data.DataHolder;
import org.spongepowered.api.data.DataView;
import org.spongepowered.api.data.manipulator.DataManipulatorBuilder;
import org.spongepowered.api.util.persistence.DataBuilder;
import org.spongepowered.api.util.persistence.InvalidDataException;

public class TradeDataManipulatorBuilder implements DataManipulatorBuilder<TradeData, ImmutableTradeData>{

	@Override
	public Optional<TradeData> build(DataView container) throws InvalidDataException {
		if (container.contains(TradeKeys.TRADE_ITEM)) {
			final boolean tradeItem = container.getBoolean(TradeKeys.TRADE_ITEM.getQuery()).get();
			return Optional.of(new TradeData(tradeItem));
		}
		return Optional.empty();
	}

	@Override
	public TradeData create() {
		return new TradeData(true);
	}

	@Override
	public Optional<TradeData> createFrom(DataHolder dataHolder) {
		return Optional.of(dataHolder.get(TradeData.class).orElse(new TradeData(true)));
	}

}
