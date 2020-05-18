package net.mcreator.wild_world.procedures;

import net.minecraft.util.DamageSource;
import net.minecraft.entity.Entity;

import net.mcreator.wild_world.WildWorldElements;

@WildWorldElements.ModElement.Tag
public class WoundTickProcedure extends WildWorldElements.ModElement {
	public WoundTickProcedure(WildWorldElements instance) {
		super(instance, 409);
	}

	public static void executeProcedure(java.util.HashMap<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			System.err.println("Failed to load dependency entity for procedure WoundTick!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		entity.getPersistentData().putDouble("woundTick", ((entity.getPersistentData().getDouble("woundTick")) + 1));
		if (((entity.getPersistentData().getDouble("woundTick")) >= 80)) {
			entity.getPersistentData().putDouble("woundTick", 0);
			entity.attackEntityFrom(DamageSource.MAGIC, (float) 2);
		}
	}
}
