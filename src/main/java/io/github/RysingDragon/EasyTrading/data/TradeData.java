package io.github.RysingDragon.EasyTrading.data;

import java.util.Optional;

import org.spongepowered.api.Sponge;
import org.spongepowered.api.data.DataContainer;
import org.spongepowered.api.data.DataHolder;
import org.spongepowered.api.data.DataSerializable;
import org.spongepowered.api.data.manipulator.mutable.common.AbstractData;
import org.spongepowered.api.data.merge.MergeFunction;
import org.spongepowered.api.data.value.mutable.Value;

import com.google.common.collect.ComparisonChain;

public class TradeData extends AbstractData<TradeData, ImmutableTradeData> implements DataSerializable{

	private boolean tradeItem;
	
	public TradeData(boolean tradeItem) {
		this.tradeItem = tradeItem;
		registerGettersAndSetters();
	}
	
	public Value<Boolean> tradeItem() {
		return Sponge.getRegistry().getValueFactory().createValue(TradeKeys.TRADE_ITEM, this.tradeItem, true);
	}
	
	@Override
	public Optional<TradeData> fill(DataHolder dataHolder, MergeFunction overlap) {
		if (!dataHolder.getKeys().contains(TradeKeys.TRADE_ITEM)) {
			return Optional.empty();
		}
		final boolean tradeItem = dataHolder.get(TradeKeys.TRADE_ITEM).get();
		this.tradeItem = tradeItem;
		return Optional.of(this);
	}

	@Override
	public Optional<TradeData> from(DataContainer container) {
		if (!container.contains(TradeKeys.TRADE_ITEM)) {
			return Optional.empty();
		}
		final boolean tradeItem = container.getBoolean(TradeKeys.TRADE_ITEM.getQuery()).get();
		this.tradeItem = tradeItem;
		return Optional.of(this);
	}

	@Override
	public TradeData copy() {
		return new TradeData(this.tradeItem);
	}

	@Override
	public ImmutableTradeData asImmutable() {
		return new ImmutableTradeData(this.tradeItem);
	}

	@Override
	public int compareTo(TradeData o) {
		return ComparisonChain.start()
				.compare(o.tradeItem, this.tradeItem)
				.result();
	}

	@Override
	public int getContentVersion() {
		return 0;
	}

	@Override
	protected void registerGettersAndSetters() {
		registerFieldGetter(TradeKeys.TRADE_ITEM, () -> this.tradeItem);
		registerFieldSetter(TradeKeys.TRADE_ITEM, value -> this.tradeItem = value);
		registerKeyValue(TradeKeys.TRADE_ITEM, this::tradeItem);
		
	}
	
	@Override
	public DataContainer toContainer() {
        return super.toContainer().set(TradeKeys.TRADE_ITEM, this.tradeItem);
    }

}