package net.mcreator.wild_world.procedures;

import net.minecraft.world.World;
import net.minecraft.util.math.BlockPos;
import net.minecraft.entity.Entity;

import net.mcreator.wild_world.WildWorldElements;

@WildWorldElements.ModElement.Tag
public class AmbientEyesOnMobTickUpdateProcedure extends WildWorldElements.ModElement {
	public AmbientEyesOnMobTickUpdateProcedure(WildWorldElements instance) {
		super(instance, 249);
	}

	public static void executeProcedure(java.util.HashMap<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			System.err.println("Failed to load dependency entity for procedure AmbientEyesOnMobTickUpdate!");
			return;
		}
		if (dependencies.get("x") == null) {
			System.err.println("Failed to load dependency x for procedure AmbientEyesOnMobTickUpdate!");
			return;
		}
		if (dependencies.get("y") == null) {
			System.err.println("Failed to load dependency y for procedure AmbientEyesOnMobTickUpdate!");
			return;
		}
		if (dependencies.get("z") == null) {
			System.err.println("Failed to load dependency z for procedure AmbientEyesOnMobTickUpdate!");
			return;
		}
		if (dependencies.get("world") == null) {
			System.err.println("Failed to load dependency world for procedure AmbientEyesOnMobTickUpdate!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		int x = (int) dependencies.get("x");
		int y = (int) dependencies.get("y");
		int z = (int) dependencies.get("z");
		World world = (World) dependencies.get("world");
		if (((world.getLight(new BlockPos((int) x, (int) y, (int) z))) >= 8)) {
			entity.remove();
		}
		if ((world.canBlockSeeSky(new BlockPos((int) x, (int) y, (int) z)))) {
			entity.remove();
		}
		if ((!(world.isAirBlock(new BlockPos((int) x, (int) y, (int) z))))) {
			entity.remove();
		}
	}
}
