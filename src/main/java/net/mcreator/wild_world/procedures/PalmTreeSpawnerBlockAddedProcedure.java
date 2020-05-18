package net.mcreator.wild_world.procedures;

import net.minecraft.world.World;
import net.minecraft.util.math.BlockPos;

import net.mcreator.wild_world.block.RichSoilBlock;
import net.mcreator.wild_world.block.PalmLogBlock;
import net.mcreator.wild_world.WildWorldElements;

@WildWorldElements.ModElement.Tag
public class PalmTreeSpawnerBlockAddedProcedure extends WildWorldElements.ModElement {
	public PalmTreeSpawnerBlockAddedProcedure(WildWorldElements instance) {
		super(instance, 380);
	}

	public static void executeProcedure(java.util.HashMap<String, Object> dependencies) {
		if (dependencies.get("x") == null) {
			System.err.println("Failed to load dependency x for procedure PalmTreeSpawnerBlockAdded!");
			return;
		}
		if (dependencies.get("y") == null) {
			System.err.println("Failed to load dependency y for procedure PalmTreeSpawnerBlockAdded!");
			return;
		}
		if (dependencies.get("z") == null) {
			System.err.println("Failed to load dependency z for procedure PalmTreeSpawnerBlockAdded!");
			return;
		}
		if (dependencies.get("world") == null) {
			System.err.println("Failed to load dependency world for procedure PalmTreeSpawnerBlockAdded!");
			return;
		}
		int x = (int) dependencies.get("x");
		int y = (int) dependencies.get("y");
		int z = (int) dependencies.get("z");
		World world = (World) dependencies.get("world");
		world.setBlockState(new BlockPos((int) x, (int) y, (int) z), RichSoilBlock.block.getDefaultState(), 3);
		if ((world.isAirBlock(new BlockPos((int) x, (int) (y + 1), (int) z)))) {
			world.setBlockState(new BlockPos((int) x, (int) (y + 1), (int) z), PalmLogBlock.block.getDefaultState(), 3);
		}
		if ((world.isAirBlock(new BlockPos((int) x, (int) (y + 2), (int) z)))) {
			world.setBlockState(new BlockPos((int) x, (int) (y + 2), (int) z), PalmLogBlock.block.getDefaultState(), 3);
		}
		if ((world.isAirBlock(new BlockPos((int) x, (int) (y + 3), (int) z)))) {
			world.setBlockState(new BlockPos((int) x, (int) (y + 3), (int) z), PalmLogBlock.block.getDefaultState(), 3);
		}
		if ((world.isAirBlock(new BlockPos((int) x, (int) (y + 4), (int) z)))) {
			world.setBlockState(new BlockPos((int) x, (int) (y + 4), (int) z), PalmLogBlock.block.getDefaultState(), 3);
		}
		if ((world.isAirBlock(new BlockPos((int) x, (int) (y + 5), (int) z)))) {
			world.setBlockState(new BlockPos((int) x, (int) (y + 5), (int) z), PalmLogBlock.block.getDefaultState(), 3);
			if ((Math.random() >= 0.66)) {
				if ((world.isAirBlock(new BlockPos((int) x, (int) (y + 6), (int) z)))) {
					world.setBlockState(new BlockPos((int) x, (int) (y + 6), (int) z), PalmLogBlock.block.getDefaultState(), 3);
					if ((Math.random() >= 0.3)) {
						if ((world.isAirBlock(new BlockPos((int) x, (int) (y + 7), (int) z)))) {
							world.setBlockState(new BlockPos((int) x, (int) (y + 7), (int) z), PalmLogBlock.block.getDefaultState(), 3);
							{
								java.util.HashMap<String, Object> $_dependencies = new java.util.HashMap<>();
								$_dependencies.put("world", world);
								$_dependencies.put("x", (int) (x));
								$_dependencies.put("y", (int) ((y + 8)));
								$_dependencies.put("z", (int) (z));
								PalmLeavesGeneratorProcedure.executeProcedure($_dependencies);
							}
						}
					} else {
						{
							java.util.HashMap<String, Object> $_dependencies = new java.util.HashMap<>();
							$_dependencies.put("world", world);
							$_dependencies.put("x", (int) (x));
							$_dependencies.put("y", (int) ((y + 7)));
							$_dependencies.put("z", (int) (z));
							PalmLeavesGeneratorProcedure.executeProcedure($_dependencies);
						}
					}
				}
			} else {
				{
					java.util.HashMap<String, Object> $_dependencies = new java.util.HashMap<>();
					$_dependencies.put("world", world);
					$_dependencies.put("x", (int) (x));
					$_dependencies.put("y", (int) ((y + 6)));
					$_dependencies.put("z", (int) (z));
					PalmLeavesGeneratorProcedure.executeProcedure($_dependencies);
				}
			}
		}
	}
}
