package net.mcreator.wild_world.procedures;

import net.minecraft.world.World;
import net.minecraft.entity.Entity;

import net.mcreator.wild_world.WildWorldElements;

@WildWorldElements.ModElement.Tag
public class SkyPortalUsedProcedure extends WildWorldElements.ModElement {
	public SkyPortalUsedProcedure(WildWorldElements instance) {
		super(instance, 208);
	}

	public static void executeProcedure(java.util.HashMap<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			System.err.println("Failed to load dependency entity for procedure SkyPortalUsed!");
			return;
		}
		if (dependencies.get("world") == null) {
			System.err.println("Failed to load dependency world for procedure SkyPortalUsed!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		World world = (World) dependencies.get("world");
		if ((1012 != (world.getDimension().getType().getId()))) {
			{
				java.util.HashMap<String, Object> $_dependencies = new java.util.HashMap<>();
				$_dependencies.put("entity", entity);
				SkyPortalProcedureProcedure.executeProcedure($_dependencies);
			}
		}
		if ((1012 == (world.getDimension().getType().getId()))) {
			{
				java.util.HashMap<String, Object> $_dependencies = new java.util.HashMap<>();
				$_dependencies.put("entity", entity);
				$_dependencies.put("world", world);
				SkyPortalReturnProcedure.executeProcedure($_dependencies);
			}
		}
	}
}
