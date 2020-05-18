package net.mcreator.wild_world.procedures;

import net.minecraft.world.World;
import net.minecraft.util.math.BlockPos;
import net.minecraft.entity.Entity;

import net.mcreator.wild_world.WildWorldVariables;
import net.mcreator.wild_world.WildWorldElements;

@WildWorldElements.ModElement.Tag
public class StarBossKilledProcedure extends WildWorldElements.ModElement {
	public StarBossKilledProcedure(WildWorldElements instance) {
		super(instance, 259);
	}

	public static void executeProcedure(java.util.HashMap<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			System.err.println("Failed to load dependency entity for procedure StarBossKilled!");
			return;
		}
		if (dependencies.get("world") == null) {
			System.err.println("Failed to load dependency world for procedure StarBossKilled!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		World world = (World) dependencies.get("world");
		WildWorldVariables.MapVariables.get(world).StarBossKilled = (boolean) (true);
		WildWorldVariables.MapVariables.get(world).syncData(world);
		world.notifyNeighborsOfStateChange(
				new BlockPos((int) (entity.getPersistentData().getDouble("AltarX")), (int) (entity.getPersistentData().getDouble("AltarY")),
						(int) (entity.getPersistentData().getDouble("AltarZ"))),
				world.getBlockState(new BlockPos((int) (entity.getPersistentData().getDouble("AltarX")),
						(int) (entity.getPersistentData().getDouble("AltarY")), (int) (entity.getPersistentData().getDouble("AltarZ")))).getBlock());
	}
}
