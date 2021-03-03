package me.bscal.common.events;

import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class PlayerTickHandler
{

	@SubscribeEvent
	public void PlayerTickHandler(TickEvent.PlayerTickEvent event)
	{
		if (event.side.isServer())
		{
		}
	}

}
