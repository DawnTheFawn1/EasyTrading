package io.github.RysingDragon.EasyTrading.data;

import java.util.Optional;

import org.spongepowered.api.Sponge;
import org.spongepowered.api.data.DataContainer;
import org.spongepowered.api.data.MemoryDataContainer;
import org.spongepowered.api.data.key.Key;
import org.spongepowered.api.data.manipulator.immutable.common.AbstractImmutableData;
import org.spongepowered.api.data.value.BaseValue;
import org.spongepowered.api.data.value.immutable.ImmutableValue;

import com.google.common.collect.ComparisonChain;

public class ImmutableTradeData extends AbstractImmutableData<ImmutableTradeData, TradeData>{
	
	private boolean tradeItem;
	
	public ImmutableTradeData(boolean tradeItem) {
		this.tradeItem = tradeItem;
		registerGetters();
	}

	public ImmutableValue<Boolean> tradeItem() {
		return Sponge.getRegistry().getValueFactory().createValue(TradeKeys.TRADE_ITEM, this.tradeItem, true).asImmutable();
	}
	
	@Override
	public <E> Optional<ImmutableTradeData> with(Key<? extends BaseValue<E>> key, E value) {
		if (this.supports(key)) {
			return Optional.of(asMutable().set(key, value).asImmutable());
		} else {
			return Optional.empty();
		}
	}

	@Override
	public TradeData asMutable() {
		return new TradeData(tradeItem);
	}

	@Override
	public int compareTo(ImmutableTradeData o) {
		return ComparisonChain.start()
				.compare(o.tradeItem, this.tradeItem).result();
	}

	@Override
	public int getContentVersion() {
		return 0;
	}

	@Override
	protected void registerGetters() {
		registerFieldGetter(TradeKeys.TRADE_ITEM, () -> this.tradeItem);
        registerKeyValue(TradeKeys.TRADE_ITEM, this::tradeItem);
	}

	@Override
	public DataContainer toContainer() {
		return new MemoryDataContainer().set(TradeKeys.TRADE_ITEM, this.tradeItem);
	}
	
}