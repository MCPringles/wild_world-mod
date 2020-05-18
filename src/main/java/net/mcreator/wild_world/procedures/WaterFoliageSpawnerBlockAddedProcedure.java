package net.mcreator.wild_world.procedures;

import net.minecraft.world.World;
import net.minecraft.util.math.BlockPos;

import net.mcreator.wild_world.block.WaterFoliage3Block;
import net.mcreator.wild_world.block.WaterFoliage2Block;
import net.mcreator.wild_world.block.WaterFoliage1Block;
import net.mcreator.wild_world.WildWorldElements;

@WildWorldElements.ModElement.Tag
public class WaterFoliageSpawnerBlockAddedProcedure extends WildWorldElements.ModElement {
	public WaterFoliageSpawnerBlockAddedProcedure(WildWorldElements instance) {
		super(instance, 372);
	}

	public static void executeProcedure(java.util.HashMap<String, Object> dependencies) {
		if (dependencies.get("x") == null) {
			System.err.println("Failed to load dependency x for procedure WaterFoliageSpawnerBlockAdded!");
			return;
		}
		if (dependencies.get("y") == null) {
			System.err.println("Failed to load dependency y for procedure WaterFoliageSpawnerBlockAdded!");
			return;
		}
		if (dependencies.get("z") == null) {
			System.err.println("Failed to load dependency z for procedure WaterFoliageSpawnerBlockAdded!");
			return;
		}
		if (dependencies.get("world") == null) {
			System.err.println("Failed to load dependency world for procedure WaterFoliageSpawnerBlockAdded!");
			return;
		}
		int x = (int) dependencies.get("x");
		int y = (int) dependencies.get("y");
		int z = (int) dependencies.get("z");
		World world = (World) dependencies.get("world");
		if ((Math.random() <= 0.33)) {
			world.setBlockState(new BlockPos((int) x, (int) y, (int) z), WaterFoliage1Block.block.getDefaultState(), 3);
		} else if ((Math.random() >= 0.5)) {
			world.setBlockState(new BlockPos((int) x, (int) y, (int) z), WaterFoliage2Block.block.getDefaultState(), 3);
		} else {
			world.setBlockState(new BlockPos((int) x, (int) y, (int) z), WaterFoliage3Block.block.getDefaultState(), 3);
		}
	}
}
