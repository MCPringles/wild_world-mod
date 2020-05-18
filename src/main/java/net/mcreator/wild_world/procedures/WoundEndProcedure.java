package net.mcreator.wild_world.procedures;

import net.minecraft.entity.Entity;

import net.mcreator.wild_world.WildWorldElements;

@WildWorldElements.ModElement.Tag
public class WoundEndProcedure extends WildWorldElements.ModElement {
	public WoundEndProcedure(WildWorldElements instance) {
		super(instance, 410);
	}

	public static void executeProcedure(java.util.HashMap<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			System.err.println("Failed to load dependency entity for procedure WoundEnd!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		entity.getPersistentData().putDouble("woundTick", 0);
	}
}
