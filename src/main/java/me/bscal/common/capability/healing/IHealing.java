package me.bscal.common.capability.healing;

import java.util.List;
import java.util.UUID;

public interface IHealing
{

	// Add a new heal
	void Put(Heal heal);

	// Clears all heals than add.
	void Set(Heal heal);

	// Updates all heals
	void Consume();

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

}
