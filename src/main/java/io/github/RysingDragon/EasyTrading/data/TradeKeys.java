package io.github.RysingDragon.EasyTrading.data;

import org.spongepowered.api.data.DataQuery;
import org.spongepowered.api.data.key.Key;
import org.spongepowered.api.data.key.KeyFactory;
import org.spongepowered.api.data.value.mutable.Value;

public class TradeKeys {

	public static final Key<Value<Boolean>> TRADE_ITEM = KeyFactory.makeSingleKey(Boolean.class, Value.class, DataQuery.of("Item_Trading"));
	
}
