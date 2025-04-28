package net.vladosss491.tofmod.item;

import net.minecraft.world.food.FoodProperties;

public class ModFoodProperties {
    public static final FoodProperties P_HORSE = new FoodProperties.Builder()
            .nutrition(3)
            .saturationModifier(0.25f)
            .alwaysEdible().build();
}
