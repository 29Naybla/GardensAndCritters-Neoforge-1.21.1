package com.x29naybla.gardens_and_critters.integration.jade;

import com.x29naybla.gardens_and_critters.common.entity.Snail;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.ai.attributes.Attributes;
import snownee.jade.api.EntityAccessor;
import snownee.jade.api.IEntityComponentProvider;
import snownee.jade.api.ITooltip;
import snownee.jade.api.config.IPluginConfig;
import snownee.jade.api.theme.IThemeHelper;
import snownee.jade.overlay.DisplayHelper;

public enum SnailSpeedProvider implements IEntityComponentProvider {
    SNAIL_SPEED;
    private static final double MAX_MOVEMENT_SPEED = 0.12 * 43.171815466666658 - 0.000000339999999;

    private static Component switchText(String key, boolean showMax, double value, double max) {
        IThemeHelper t = IThemeHelper.get();
        Component valueText = t.info(DisplayHelper.dfCommas.format(value));
        if (showMax) {
            return Component.translatable(key, Component.translatable("jade.fraction", valueText, DisplayHelper.dfCommas.format(max)));
        } else {
            return Component.translatable(key, valueText);
        }
    }

    @Override
    public void appendTooltip(ITooltip iTooltip, EntityAccessor entityAccessor, IPluginConfig iPluginConfig) {
        Snail snail = (Snail) entityAccessor.getEntity();
        boolean showMax = entityAccessor.showDetails();

        if (snail.getAttributes().hasAttribute(Attributes.MOVEMENT_SPEED)) {
            double speed = snail.getAttributeBaseValue(Attributes.MOVEMENT_SPEED) * 43.171815466666658 - 0.000000339999999;
            iTooltip.add(switchText("jade.horseStat.speed", showMax, speed, MAX_MOVEMENT_SPEED));
        }
    }

    @Override
    public ResourceLocation getUid() {
        return JadeIntegration.SNAIL_SPEED;
    }
}
