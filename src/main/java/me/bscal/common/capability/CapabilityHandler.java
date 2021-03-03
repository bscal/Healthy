package me.bscal.common.capability;

import me.bscal.common.capability.healing.HealingProvider;
import me.bscal.common.capability.healing.IHealing;
import me.bscal.core.Healthy;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class CapabilityHandler
{

	public static final ResourceLocation HEALING_CAP_KEY = new ResourceLocation(Healthy.MOD_ID,
			"healing");

	@SubscribeEvent
	public static void OnAttachCapability(AttachCapabilitiesEvent<Entity> event)
	{
		if (event.getObject() instanceof PlayerEntity)
		{
			event.addCapability(HEALING_CAP_KEY, new HealingProvider());
		}
	}

	@SubscribeEvent
	public static void OnPlayerClone(PlayerEvent.Clone event)
	{
		PlayerEntity player = event.getPlayer();
		PlayerEntity original = event.getOriginal();

		LazyOptional<IHealing> healing = player.getCapability(HealingProvider.HEALING_CAP, null);
		LazyOptional<IHealing> oldHealing = original.getCapability(HealingProvider.HEALING_CAP,
				null);

		healing.ifPresent((healingCap) -> {
			oldHealing.ifPresent((oldHealingCap -> {
				healingCap.Read(oldHealingCap.Write());
			}));
		});
	}

}
