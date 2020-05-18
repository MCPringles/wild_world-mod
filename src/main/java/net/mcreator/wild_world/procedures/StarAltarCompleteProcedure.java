package net.mcreator.wild_world.procedures;

import net.minecraft.world.World;

import net.mcreator.wild_world.WildWorldVariables;
import net.mcreator.wild_world.WildWorldElements;

@WildWorldElements.ModElement.Tag
public class StarAltarCompleteProcedure extends WildWorldElements.ModElement {
	public StarAltarCompleteProcedure(WildWorldElements instance) {
		super(instance, 260);
	}

	public static void executeProcedure(java.util.HashMap<String, Object> dependencies) {
		if (dependencies.get("world") == null) {
			System.err.println("Failed to load dependency world for procedure StarAltarComplete!");
			return;
		}
		World world = (World) dependencies.get("world");
		WildWorldVariables.MapVariables.get(world).StarBossKilled = (boolean) (false);
		WildWorldVariables.MapVariables.get(world).syncData(world);
	}
}
