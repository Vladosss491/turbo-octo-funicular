package net.vladosss491.tofmod.item;

import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.vladosss491.tofmod.TOFMod;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(TOFMod.MOD_ID);

    public static final DeferredItem<Item> P_HORSE = ITEMS.register("p_horse",
            () -> new Item(new Item.Properties().food(ModFoodProperties.P_HORSE)));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
