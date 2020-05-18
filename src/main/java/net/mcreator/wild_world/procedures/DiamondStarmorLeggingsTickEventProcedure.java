package net.mcreator.wild_world.procedures;

import net.minecraft.entity.Entity;

import net.mcreator.wild_world.WildWorldElements;

@WildWorldElements.ModElement.Tag
public class DiamondStarmorLeggingsTickEventProcedure extends WildWorldElements.ModElement {
	public DiamondStarmorLeggingsTickEventProcedure(WildWorldElements instance) {
		super(instance, 326);
	}

	public static void executeProcedure(java.util.HashMap<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			System.err.println("Failed to load dependency entity for procedure DiamondStarmorLeggingsTickEvent!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		entity.extinguish();
	}
}
