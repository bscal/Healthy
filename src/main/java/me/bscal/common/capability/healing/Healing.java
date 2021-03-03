package me.bscal.common.capability.healing;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Healing implements IHealing
{

	private float m_currentHealing;
	private float m_lastHealing;

	private List<Heal> m_heals = new ArrayList<>();

	@Override
	public void Put(Heal heal)
	{
		m_heals.add(heal);
	}

	@Override
	public void Set(Heal heal)
	{
		m_heals.clear();
		m_heals.add(heal);
	}

	@Override
	public void Consume()
	{
		m_lastHealing = m_currentHealing;
		m_currentHealing = 0;

		for (Iterator<Heal> iter = m_heals.iterator(); iter.hasNext();)
		{
			Heal next = iter.next();
			m_currentHealing += next.ConsumeHealing();

			if (next.finished)
				iter.remove();
		}
	}

	@Override
	public float GetMaxFromRegen()
	{
		return 0;
	}

	@Override
	public float GetHealingFromComfort()
	{
		return 0;
	}

	@Override
	public float GetHealingFromFood()
	{
		return 0;
	}

	@Override
	public float GetTotalHealing()
	{
		return 0;
	}

	@Override
	public List<Heal> GetHeals()
	{
		return m_heals;
	}

}
