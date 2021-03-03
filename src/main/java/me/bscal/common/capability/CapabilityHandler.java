package me.bscal.common.capability;

import me.bscal.common.capability.healing.HealingProvider;
import me.bscal.core.Healthy;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.AttachCapabilitiesEvent;

public class CapabilityHandler
{

	public static final ResourceLocation HEALING_CAP = new ResourceLocation(Healthy.MOD_ID, "healing");

	public void OnAttachCapability(AttachCapabilitiesEvent<PlayerEntity> event)
	{
		event.addCapability(HEALING_CAP, new HealingProvider());
	}

}
