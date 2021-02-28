package me.bscal.core.data;

import me.bscal.core.Healthy;
import me.bscal.core.data.client.ModItemModelProvider;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent;

@Mod.EventBusSubscriber(modid = Healthy.MOD_ID, bus=Mod.EventBusSubscriber.Bus.MOD)
public final class DataGenerators
{

	private DataGenerators() {}

	@SubscribeEvent
	public static void gatherData(GatherDataEvent event)
	{
		DataGenerator gen = event.getGenerator();
		ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
		gen.addProvider(new ModItemModelProvider(gen, existingFileHelper));
	}

}
