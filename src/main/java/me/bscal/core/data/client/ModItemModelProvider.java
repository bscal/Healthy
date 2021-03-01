package me.bscal.core.data.client;

import me.bscal.core.Healthy;
import me.bscal.core.register.ItemRegistry;
import net.minecraft.block.Block;
import net.minecraft.data.DataGenerator;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.fml.RegistryObject;

public class ModItemModelProvider extends ItemModelProvider
{

	public ModItemModelProvider(DataGenerator generator, ExistingFileHelper existingFileHelper)
	{
		super(generator, Healthy.MOD_ID, existingFileHelper);
	}

	@Override
	protected void registerModels()
	{
		ModelFile itemGenerated = getExistingFile(mcLoc("item/generated"));

		builder(ItemRegistry.BANDAGE, itemGenerated);
	}

	// Helper methods

	private void blockBuilder(Block block)
	{
		String name = block.getRegistryName().getPath();
		withExistingParent(name, modLoc("block/" + name));
	}

	private void builder(RegistryObject<Item> item, ModelFile parent)
	{
		String name = item.getId().getPath();
		builder(item, parent, "item/" + name);
	}

	private void builder(RegistryObject<Item> item, ModelFile parent, String texture)
	{
		String name = item.getId().getPath();
		getBuilder(name).parent(parent).texture("layer0", modLoc(texture));
	}

}
