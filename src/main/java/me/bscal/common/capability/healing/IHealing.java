package me.bscal.common.capability.healing;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.nbt.ListNBT;

import java.util.List;
import java.util.UUID;

public interface IHealing
{

	// Add a new heal
	void Put(Heal heal);

	// Clears all heals than add.
	void Set(Heal heal);

	// Updates all heals
	float Consume();

	// Max healing without comfort
	float GetMaxFromRegen();

	// Healing from comfort
	float GetHealingFromComfort();

	// Healing from food
	float GetHealingFromFood();

	// Total Healing
	float GetTotalHealing();

	// Returns List of current heals
	List<Heal> GetHeals();

	ListNBT Write();

	void Read(INBT data);

}
