package net.mcreator.wild_world.procedures;

import net.minecraft.world.World;
import net.minecraft.util.math.BlockPos;
import net.minecraft.entity.Entity;
import net.minecraft.block.Blocks;

import net.mcreator.wild_world.entity.DwarfStarGuardEntity;
import net.mcreator.wild_world.block.YronStarBoxOffBlock;
import net.mcreator.wild_world.block.BlueStoneRodBlockBlock;
import net.mcreator.wild_world.WildWorldElements;

@WildWorldElements.ModElement.Tag
public class BlueStoneRodBlockUpdateTickProcedure extends WildWorldElements.ModElement {
	public BlueStoneRodBlockUpdateTickProcedure(WildWorldElements instance) {
		super(instance, 366);
	}

	public static void executeProcedure(java.util.HashMap<String, Object> dependencies) {
		if (dependencies.get("x") == null) {
			System.err.println("Failed to load dependency x for procedure BlueStoneRodBlockUpdateTick!");
			return;
		}
		if (dependencies.get("y") == null) {
			System.err.println("Failed to load dependency y for procedure BlueStoneRodBlockUpdateTick!");
			return;
		}
		if (dependencies.get("z") == null) {
			System.err.println("Failed to load dependency z for procedure BlueStoneRodBlockUpdateTick!");
			return;
		}
		if (dependencies.get("world") == null) {
			System.err.println("Failed to load dependency world for procedure BlueStoneRodBlockUpdateTick!");
			return;
		}
		int x = (int) dependencies.get("x");
		int y = (int) dependencies.get("y");
		int z = (int) dependencies.get("z");
		World world = (World) dependencies.get("world");
		if (((world.getBlockState(new BlockPos((int) x, (int) (y - 1), (int) z))).getBlock() == BlueStoneRodBlockBlock.block.getDefaultState()
				.getBlock())) {
			if (((world.getBlockState(new BlockPos((int) x, (int) (y + 1), (int) z))).getBlock() == YronStarBoxOffBlock.block.getDefaultState()
					.getBlock())) {
				world.setBlockState(new BlockPos((int) x, (int) y, (int) z), Blocks.AIR.getDefaultState(), 3);
				world.setBlockState(new BlockPos((int) x, (int) (y - 1), (int) z), Blocks.AIR.getDefaultState(), 3);
				world.setBlockState(new BlockPos((int) x, (int) (y + 1), (int) z), Blocks.AIR.getDefaultState(), 3);
				if (!world.isRemote) {
					Entity entityToSpawn = new DwarfStarGuardEntity.CustomEntity(DwarfStarGuardEntity.entity, world);
					entityToSpawn.setLocationAndAngles((x + 0.5), (y - 1), (z + 0.5), world.rand.nextFloat() * 360F, 0);
					world.addEntity(entityToSpawn);
				}
			}
		}
	}
}
