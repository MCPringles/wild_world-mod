package net.mcreator.wild_world.procedures;

import net.minecraft.world.World;
import net.minecraft.util.math.BlockPos;
import net.minecraft.block.Blocks;

import net.mcreator.wild_world.block.BarrenTopsoilBlock;
import net.mcreator.wild_world.block.BarrenDirtBlock;
import net.mcreator.wild_world.WildWorldElements;

@WildWorldElements.ModElement.Tag
public class HydrateDirtProcedure extends WildWorldElements.ModElement {
	public HydrateDirtProcedure(WildWorldElements instance) {
		super(instance, 141);
	}

	public static void executeProcedure(java.util.HashMap<String, Object> dependencies) {
		if (dependencies.get("x") == null) {
			System.err.println("Failed to load dependency x for procedure HydrateDirt!");
			return;
		}
		if (dependencies.get("y") == null) {
			System.err.println("Failed to load dependency y for procedure HydrateDirt!");
			return;
		}
		if (dependencies.get("z") == null) {
			System.err.println("Failed to load dependency z for procedure HydrateDirt!");
			return;
		}
		if (dependencies.get("world") == null) {
			System.err.println("Failed to load dependency world for procedure HydrateDirt!");
			return;
		}
		int x = (int) dependencies.get("x");
		int y = (int) dependencies.get("y");
		int z = (int) dependencies.get("z");
		World world = (World) dependencies.get("world");
		double OffZ = 0;
		double OffY = 0;
		double OffX = 0;
		double GrassZ = 0;
		double GrassY = 0;
		double GrassX = 0;
		boolean Found = false;
		boolean Grass = false;
		if (((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == BarrenDirtBlock.block.getDefaultState().getBlock())) {
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
			if (((Found) == (true))) {
				if ((Math.random() < 0.075)) {
					world.setBlockState(new BlockPos((int) x, (int) y, (int) z), Blocks.AIR.getDefaultState(), 3);
					world.setBlockState(new BlockPos((int) x, (int) y, (int) z), Blocks.DIRT.getDefaultState(), 3);
				}
			}
		}
		if (((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == BarrenDirtBlock.block.getDefaultState().getBlock())) {
			if (((world.getLight(new BlockPos((int) x, (int) (y + 1), (int) z))) >= 4)) {
				GrassZ = (double) -1;
				Grass = (boolean) (false);
				for (int index3 = 0; index3 < (int) (3); index3++) {
					GrassY = (double) -3;
					for (int index4 = 0; index4 < (int) (5); index4++) {
						GrassX = (double) -1;
						for (int index5 = 0; index5 < (int) (3); index5++) {
							if (((world.getBlockState(new BlockPos((int) (x + (GrassX)), (int) (y + (GrassY)), (int) (z + (GrassZ)))))
									.getBlock() == BarrenTopsoilBlock.block.getDefaultState().getBlock())) {
								if (((world.getLight(new BlockPos((int) (x + (GrassX)), (int) ((y + 1) + (GrassY)), (int) (z + (GrassZ))))) >= 9)) {
									Grass = (boolean) (true);
									break;
								}
							}
							GrassX = (double) ((GrassX) + 1);
						}
						GrassY = (double) ((GrassY) + 1);
					}
					GrassZ = (double) ((GrassZ) + 1);
				}
				if (((Grass) == (true))) {
					if ((Math.random() < 0.005)) {
						world.setBlockState(new BlockPos((int) x, (int) y, (int) z), Blocks.AIR.getDefaultState(), 3);
						world.setBlockState(new BlockPos((int) x, (int) y, (int) z), BarrenTopsoilBlock.block.getDefaultState(), 3);
					}
				}
			}
		}
	}
}
