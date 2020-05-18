package net.mcreator.wild_world.procedures;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;

import net.mcreator.wild_world.WildWorldElements;

@WildWorldElements.ModElement.Tag
public class RestorationStartedProcedure extends WildWorldElements.ModElement {
	public RestorationStartedProcedure(WildWorldElements instance) {
		super(instance, 404);
	}

	public static void executeProcedure(java.util.HashMap<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			System.err.println("Failed to load dependency entity for procedure RestorationStarted!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		entity.getPersistentData().putDouble("restorationHealth", ((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHealth() : -1));
	}
}
