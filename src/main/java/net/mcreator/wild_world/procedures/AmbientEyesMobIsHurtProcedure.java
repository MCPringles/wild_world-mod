package net.mcreator.wild_world.procedures;

import net.minecraft.entity.Entity;

import net.mcreator.wild_world.WildWorldElements;

@WildWorldElements.ModElement.Tag
public class AmbientEyesMobIsHurtProcedure extends WildWorldElements.ModElement {
	public AmbientEyesMobIsHurtProcedure(WildWorldElements instance) {
		super(instance, 248);
	}

	public static void executeProcedure(java.util.HashMap<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			System.err.println("Failed to load dependency entity for procedure AmbientEyesMobIsHurt!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		entity.remove();
	}
}
