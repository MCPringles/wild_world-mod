package net.mcreator.wild_world.procedures;

import net.minecraft.world.World;
import net.minecraft.util.math.BlockPos;
import net.minecraft.entity.Entity;
import net.minecraft.block.material.Material;
import net.minecraft.block.Blocks;

import net.mcreator.wild_world.entity.AerodusMobEntity;
import net.mcreator.wild_world.WildWorldElements;

@WildWorldElements.ModElement.Tag
public class SpawnAerodusProcedure extends WildWorldElements.ModElement {
	public SpawnAerodusProcedure(WildWorldElements instance) {
		super(instance, 407);
	}

	public static void executeProcedure(java.util.HashMap<String, Object> dependencies) {
		if (dependencies.get("x") == null) {
			System.err.println("Failed to load dependency x for procedure SpawnAerodus!");
			return;
		}
		if (dependencies.get("y") == null) {
			System.err.println("Failed to load dependency y for procedure SpawnAerodus!");
			return;
		}
		if (dependencies.get("z") == null) {
			System.err.println("Failed to load dependency z for procedure SpawnAerodus!");
			return;
		}
		if (dependencies.get("world") == null) {
			System.err.println("Failed to load dependency world for procedure SpawnAerodus!");
			return;
		}
		int x = (int) dependencies.get("x");
		int y = (int) dependencies.get("y");
		int z = (int) dependencies.get("z");
		World world = (World) dependencies.get("world");
		double X = 0;
		double Y = 0;
		double Z = 0;
		boolean Spawn = false;
		X = (double) -9;
		for (int index0 = 0; index0 < (int) (18); index0++) {
			Z = (double) -9;
			for (int index1 = 0; index1 < (int) (18); index1++) {
				if (((((X) == -9) || ((Z) == -9)) || (((X) == 9) || ((Z) == 9)))) {
					Y = (double) -4;
					for (int index2 = 0; index2 < (int) (9); index2++) {
						if (((((world.getBlockState(new BlockPos((int) (x + (X)), (int) (y + (Y)), (int) (z + (Z)))))
								.getMaterial() == Material.ORGANIC)
								|| ((world.getBlockState(new BlockPos((int) (x + (X)), (int) (y + (Y)), (int) (z + (Z)))))
										.getMaterial() == Material.EARTH))
								|| ((world.getBlockState(new BlockPos((int) (x + (X)), (int) (y + (Y)), (int) (z + (Z)))))
										.getMaterial() == Material.WATER))) {
							if (((((world.getBlockState(new BlockPos((int) (x + (X)), (int) ((y + 1) + (Y)), (int) (z + (Z)))))
									.getMaterial() == Material.PLANTS)
									|| ((world.getBlockState(new BlockPos((int) (x + (X)), (int) ((y + 1) + (Y)), (int) (z + (Z)))))
											.getBlock() == Blocks.AIR.getDefaultState().getBlock()))
									|| ((world.getBlockState(new BlockPos((int) (x + (X)), (int) ((y + 1) + (Y)), (int) (z + (Z)))))
											.getMaterial() == Material.TALL_PLANTS))) {
								if ((Math.random() > 0.95)) {
									Spawn = (boolean) (true);
									if (!world.isRemote) {
										Entity entityToSpawn = new AerodusMobEntity.CustomEntity(AerodusMobEntity.entity, world);
										entityToSpawn.setLocationAndAngles((x + (X)), ((y + 1) + (Y)), (z + (Z)), world.rand.nextFloat() * 360F, 0);
										world.addEntity(entityToSpawn);
									}
									break;
								}
							}
						}
						Y = (double) ((Y) + 1);
					}
					Z = (double) ((Z) + 1);
				}
				if ((Spawn)) {
					break;
				}
			}
			X = (double) ((X) + 1);
			if ((Spawn)) {
				break;
			}
		}
	}
}
