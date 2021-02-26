package me.bscal.common.items;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public class Bandage extends Item
{
	public Bandage(Properties properties)
	{
		super(properties);
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn,
			Hand handIn)
	{
		ItemStack itemstack = playerIn.getHeldItem(handIn);
		if (!worldIn.isRemote)
		{
			playerIn.setActiveHand(handIn);
			return ActionResult.resultSuccess(itemstack);
		}
		return ActionResult.resultFail(itemstack);
	}

	@Override
	public ItemStack onItemUseFinish(ItemStack stack, World worldIn, LivingEntity entityLiving)
	{
		if (!worldIn.isRemote && entityLiving instanceof ServerPlayerEntity)
		{
			ServerPlayerEntity serverPlayer = (ServerPlayerEntity) entityLiving;
			serverPlayer.setHealth(Math.max(0f, entityLiving.getHealth() - 5f));
			serverPlayer.performHurtAnimation();
			stack.shrink(1);
		}
		return stack;
	}

	@Override
	public int getUseDuration(ItemStack stack)
	{
		return 32;
	}
}
