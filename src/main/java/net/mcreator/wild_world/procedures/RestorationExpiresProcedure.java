package net.mcreator.wild_world.procedures;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;

import net.mcreator.wild_world.WildWorldElements;

@WildWorldElements.ModElement.Tag
public class RestorationExpiresProcedure extends WildWorldElements.ModElement {
	public RestorationExpiresProcedure(WildWorldElements instance) {
		super(instance, 405);
	}

	public static void executeProcedure(java.util.HashMap<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			System.err.println("Failed to load dependency entity for procedure RestorationExpires!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		if (((((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHealth() : -1) < (entity.getPersistentData()
				.getDouble("restorationHealth"))) && (((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHealth() : -1) > 0))) {
			if (entity instanceof LivingEntity)
				((LivingEntity) entity).setHealth((float) (entity.getPersistentData().getDouble("restorationHealth")));
		}
	}
}
