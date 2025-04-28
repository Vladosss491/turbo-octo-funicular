package net.vladosss491.tofmod.event;


import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.animal.horse.Horse;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;
import net.vladosss491.tofmod.TOFMod;
import net.vladosss491.tofmod.item.ModItems;
import net.vladosss491.tofmod.sound.ModSounds;

@EventBusSubscriber(modid = TOFMod.MOD_ID, bus = EventBusSubscriber.Bus.GAME, value = Dist.CLIENT)
public class ModEvents {
    private static final String TAG_HAS_DROPPED = "tofmod_has_dropped";

    @SubscribeEvent
    private static void onRightClickHorseEvent(PlayerInteractEvent.EntityInteract event) {
        if (event.getTarget() instanceof Horse horse && event.getItemStack().is(Items.SHEARS) && !event.getLevel().isClientSide()) {
            CompoundTag persistentData = horse.getPersistentData();
            if (!persistentData.getBoolean(TAG_HAS_DROPPED)) {
                ItemEntity drop = new ItemEntity(event.getLevel(), horse.getX() + 0, horse.getY() + 1.0, horse.getZ() + 0, new ItemStack((ItemLike) ModItems.P_HORSE));
                event.getLevel().addFreshEntity(drop);
                horse.playSound(ModSounds.USE_SHEARS_ON_HORSE.get(), 1.0f, 1.0f);
                horse.hurt(event.getLevel().damageSources().generic(), 1.0f);
                persistentData.putBoolean(TAG_HAS_DROPPED, true);
                event.setCancellationResult(InteractionResult.SUCCESS);
                event.setCanceled(true);
            } else {
                event.setCancellationResult(InteractionResult.PASS);
            }
        }
    }
}