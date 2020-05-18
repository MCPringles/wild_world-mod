package net.mcreator.wild_world.procedures;

import net.minecraft.world.World;
import net.minecraft.world.Difficulty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.entity.Entity;

import net.mcreator.wild_world.entity.StarBossMobEntity;
import net.mcreator.wild_world.block.StarPillarBlock;
import net.mcreator.wild_world.block.StarAltarActiveBlock;
import net.mcreator.wild_world.WildWorldElements;

@WildWorldElements.ModElement.Tag
public class StarAltarActivatedProcedure extends WildWorldElements.ModElement {
	public StarAltarActivatedProcedure(WildWorldElements instance) {
		super(instance, 257);
	}

	public static void executeProcedure(java.util.HashMap<String, Object> dependencies) {
		if (dependencies.get("x") == null) {
			System.err.println("Failed to load dependency x for procedure StarAltarActivated!");
			return;
		}
		if (dependencies.get("y") == null) {
			System.err.println("Failed to load dependency y for procedure StarAltarActivated!");
			return;
		}
		if (dependencies.get("z") == null) {
			System.err.println("Failed to load dependency z for procedure StarAltarActivated!");
			return;
		}
		if (dependencies.get("world") == null) {
			System.err.println("Failed to load dependency world for procedure StarAltarActivated!");
			return;
		}
		int x = (int) dependencies.get("x");
		int y = (int) dependencies.get("y");
		int z = (int) dependencies.get("z");
		World world = (World) dependencies.get("world");
		double Found = 0;
		double OffX = 0;
		double OffY = 0;
		double OffZ = 0;
		OffZ = (double) -1;
		for (int index0 = 0; index0 < (int) (3); index0++) {
			OffY = (double) -1;
			for (int index1 = 0; index1 < (int) (3); index1++) {
				OffX = (double) -1;
				for (int index2 = 0; index2 < (int) (3); index2++) {
					if (((world.getBlockState(new BlockPos((int) (x + (OffX)), (int) (y + (OffY)), (int) (z + (OffZ)))))
							.getBlock() == StarPillarBlock.block.getDefaultState().getBlock())) {
						Found = (double) ((Found) + 1);
					}
					OffX = (double) ((OffX) + 1);
				}
				OffY = (double) ((OffY) + 1);
			}
			OffZ = (double) ((OffZ) + 1);
		}
		if (((Found) >= 4)) {
			if ((!(world.getDifficulty() == Difficulty.PEACEFUL))) {
				world.setBlockState(new BlockPos((int) x, (int) y, (int) z), StarAltarActiveBlock.block.getDefaultState(), 3);
				if (!world.isRemote) {
					Entity entityToSpawn = new StarBossMobEntity.CustomEntity(StarBossMobEntity.entity, world);
					entityToSpawn.setLocationAndAngles(x, (y + 5), z, world.rand.nextFloat() * 360F, 0);
					world.addEntity(entityToSpawn);
				}
			}
		}
	}
}
