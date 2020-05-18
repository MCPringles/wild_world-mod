package net.mcreator.wild_world.procedures;

import net.minecraft.world.World;
import net.minecraft.util.math.BlockPos;
import net.minecraft.block.Blocks;

import net.mcreator.wild_world.block.BarrenTopsoilBlock;
import net.mcreator.wild_world.WildWorldElements;

@WildWorldElements.ModElement.Tag
public class DehydrateGrassProcedure extends WildWorldElements.ModElement {
	public DehydrateGrassProcedure(WildWorldElements instance) {
		super(instance, 225);
	}

	public static void executeProcedure(java.util.HashMap<String, Object> dependencies) {
		if (dependencies.get("x") == null) {
			System.err.println("Failed to load dependency x for procedure DehydrateGrass!");
			return;
		}
		if (dependencies.get("y") == null) {
			System.err.println("Failed to load dependency y for procedure DehydrateGrass!");
			return;
		}
		if (dependencies.get("z") == null) {
			System.err.println("Failed to load dependency z for procedure DehydrateGrass!");
			return;
		}
		if (dependencies.get("world") == null) {
			System.err.println("Failed to load dependency world for procedure DehydrateGrass!");
			return;
		}
		int x = (int) dependencies.get("x");
		int y = (int) dependencies.get("y");
		int z = (int) dependencies.get("z");
		World world = (World) dependencies.get("world");
		double OffZ = 0;
		double OffY = 0;
		double OffX = 0;
		boolean Found = false;
		if (((world.getBiome(new BlockPos((int) x, (int) y, (int) z)).getTemperature(new BlockPos((int) x, (int) y, (int) z)) * 100.f) >= 95)) {
			if ((y >= 50)) {
				if (((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == Blocks.GRASS_BLOCK.getDefaultState().getBlock())) {
					OffZ = (double) -2;
					Found = (boolean) (false);
					for (int index0 = 0; index0 < (int) (5); index0++) {
						OffY = (double) -2;
						for (int index1 = 0; index1 < (int) (5); index1++) {
							OffX = (double) -2;
							for (int index2 = 0; index2 < (int) (5); index2++) {
								if (((world.getBlockState(new BlockPos((int) (x + (OffX)), (int) (y + (OffY)), (int) (z + (OffZ)))))
										.getBlock() == Blocks.WATER.getDefaultState().getBlock())) {
									Found = (boolean) (true);
									break;
								}
								OffX = (double) ((OffX) + 1);
							}
							OffY = (double) ((OffY) + 1);
						}
						OffZ = (double) ((OffZ) + 1);
					}
					if ((!(Found))) {
						if ((Math.random() < 0.8)) {
							world.setBlockState(new BlockPos((int) x, (int) y, (int) z), BarrenTopsoilBlock.block.getDefaultState(), 3);
						}
					}
				}
			}
		}
	}
}
