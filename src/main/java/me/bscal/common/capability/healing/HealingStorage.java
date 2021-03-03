package me.bscal.common.capability.healing;


import net.minecraft.nbt.INBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;

import java.util.List;

public class HealingStorage implements Capability.IStorage<IHealing>
{
	@Override
	public INBT writeNBT(Capability<IHealing> capability, IHealing instance, Direction side)
	{
		ListNBT data = new ListNBT();
		List<Heal> heals = instance.GetHeals();

		for (int i = 0; i < heals.size(); i++)
			data.addNBTByIndex(i, heals.get(i).Write());

		return data;
	}


	@Override
	public void readNBT(Capability<IHealing> capability, IHealing instance, Direction side,
			INBT nbt)
	{
		if (nbt instanceof ListNBT)
		{
			ListNBT data = (ListNBT)nbt;
			for (int i = 0; i < data.size(); i++)
			{
				Heal heal = new Heal();
				heal.Read(data.getCompound(i));
				instance.Put(heal);
			}
		}
	}
}
