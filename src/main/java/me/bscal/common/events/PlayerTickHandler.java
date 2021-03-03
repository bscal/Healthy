package me.bscal.common.events;

import me.bscal.common.capability.healing.HealingProvider;
import me.bscal.common.capability.healing.IHealing;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class PlayerTickHandler
{

	@SubscribeEvent
	public static void OnPlayerTick(TickEvent.PlayerTickEvent event)
	{
		if (!event.player.world.isRemote && event.phase == TickEvent.Phase.START)
		{
			LazyOptional<IHealing> healingOptional = event.player.getCapability(HealingProvider.HEALING_CAP, null);
			healingOptional.ifPresent((healingCap) -> {
				float healing = healingCap.Consume();

				if (healing > 0f)
					event.player.heal(healing);
			});
		}
	}

}
