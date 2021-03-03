package me.bscal.common.items;

import me.bscal.common.capability.healing.Heal;
import me.bscal.common.capability.healing.HealingProvider;
import me.bscal.common.capability.healing.IHealing;
import me.bscal.core.Healthy;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.UseAction;
import net.minecraft.util.ActionResult;
import net.minecraft.util.FoodStats;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.entity.EntityEvent;
import net.minecraftforge.event.entity.item.ItemEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;

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

			if (Healthy.DEBUG) {
				serverPlayer.setHealth(Math.max(0f, entityLiving.getHealth() - 10f));
			}

			LazyOptional<IHealing> healing = serverPlayer.getCapability(HealingProvider.HEALING_CAP);
			healing.ifPresent((healingCap -> {
				Heal heal = Heal.Builder(10, 10 * 20, 20);
				healingCap.Put(heal);
			}));

			stack.shrink(1);
		}
		return stack;
	}

	@Override
	public int getUseDuration(ItemStack stack)
	{
		return 32;
	}

	@Override
	public UseAction getUseAction(ItemStack stack)
	{
		return UseAction.BOW;
	}
}
