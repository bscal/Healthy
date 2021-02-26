package me.bscal.core.register;

import me.bscal.common.items.Bandage;
import me.bscal.core.Healthy;
import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class HItemRegistry
{
	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS,
			Healthy.MOD_ID);

	public static final RegistryObject<Item> BANDAGE = ITEMS.register("healthy_bandage",
		() -> new Bandage(new Item.Properties().group(ItemGroup.MISC)));

	public static void Register()
	{
		ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
	}
}
