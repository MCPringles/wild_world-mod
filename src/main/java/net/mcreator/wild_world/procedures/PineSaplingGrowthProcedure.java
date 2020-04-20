package net.mcreator.wild_world.procedures;

import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.gen.feature.template.Template;
import net.minecraft.world.gen.feature.template.PlacementSettings;
import net.minecraft.world.World;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.Rotation;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Mirror;
import net.minecraft.block.trees.Tree;
import net.minecraft.block.Blocks;

import net.mcreator.wild_world.WildWorldElements;

@WildWorldElements.ModElement.Tag
public class PineSaplingGrowthProcedure extends WildWorldElements.ModElement {
	public PineSaplingGrowthProcedure(WildWorldElements instance) {
		super(instance, 147);
	}

	public static void executeProcedure(java.util.HashMap<String, Object> dependencies) {
		if (dependencies.get("x") == null) {
			System.err.println("Failed to load dependency x for procedure PineSaplingGrowth!");
			return;
		}
		if (dependencies.get("y") == null) {
			System.err.println("Failed to load dependency y for procedure PineSaplingGrowth!");
			return;
		}
		if (dependencies.get("z") == null) {
			System.err.println("Failed to load dependency z for procedure PineSaplingGrowth!");
			return;
		}
		if (dependencies.get("world") == null) {
			System.err.println("Failed to load dependency world for procedure PineSaplingGrowth!");
			return;
		}
		int x = (int) dependencies.get("x");
		int y = (int) dependencies.get("y");
		int z = (int) dependencies.get("z");
		World world = (World) dependencies.get("world");
		double OffZ = 0;
		double OffY = 0;
		double OffX = 0;
		double StageTimer = 0;
		double Success = 0;
		boolean Found = false;
		boolean Tree = false;
		if (((world.getLight(new BlockPos((int) x, (int) y, (int) z))) >= 9)) {
			if (((Tree) == (false))) {
				if ((Math.random() <= 0.01)) {
					StageTimer = (double) ((StageTimer) + 1);
				}
				if (((StageTimer) >= 4)) {
					Tree = (boolean) (true);
				}
			}
			if (((Tree) == (true))) {
				OffZ = (double) -2;
				Found = (boolean) (false);
				Success = (double) 0;
				for (int index0 = 0; index0 < (int) (5); index0++) {
					OffY = (double) 0;
					for (int index1 = 0; index1 < (int) (8); index1++) {
						OffX = (double) -2;
						for (int index2 = 0; index2 < (int) (5); index2++) {
							if (((world.getBlockState(new BlockPos((int) (x + (OffX)), (int) (y + (OffY)), (int) (z + (OffZ)))))
									.getBlock() == Blocks.AIR.getDefaultState().getBlock())) {
								Success = (double) ((Success) + 1);
							}
							OffX = (double) ((OffX) + 1);
						}
						OffY = (double) ((OffY) + 1);
					}
					OffZ = (double) ((OffZ) + 1);
				}
				if (((Success) == 199)) {
					if ((Math.random() <= 0.25)) {
						if (!world.isRemote) {
							Template template = ((ServerWorld) world.getWorld()).getSaveHandler().getStructureTemplateManager()
									.getTemplateDefaulted(new ResourceLocation("wild_world", "pine5"));
							if (template != null) {
								template.addBlocksToWorldChunk(world, new BlockPos((int) (x - 2), (int) y, (int) (z - 2)), new PlacementSettings()
										.setRotation(Rotation.NONE).setMirror(Mirror.NONE).setChunk((ChunkPos) null).setIgnoreEntities(false));
							}
						}
					} else {
						if ((Math.random() <= 0.5)) {
							if (!world.isRemote) {
								Template template = ((ServerWorld) world.getWorld()).getSaveHandler().getStructureTemplateManager()
										.getTemplateDefaulted(new ResourceLocation("wild_world", "pine6"));
								if (template != null) {
									template.addBlocksToWorldChunk(world, new BlockPos((int) (x - 2), (int) y, (int) (z - 2)), new PlacementSettings()
											.setRotation(Rotation.NONE).setMirror(Mirror.NONE).setChunk((ChunkPos) null).setIgnoreEntities(false));
								}
							}
						} else {
							if ((Math.random() <= 0.75)) {
								if (!world.isRemote) {
									Template template = ((ServerWorld) world.getWorld()).getSaveHandler().getStructureTemplateManager()
											.getTemplateDefaulted(new ResourceLocation("wild_world", "pine7"));
									if (template != null) {
										template.addBlocksToWorldChunk(world, new BlockPos((int) (x - 2), (int) y, (int) (z - 2)),
												new PlacementSettings().setRotation(Rotation.NONE).setMirror(Mirror.NONE).setChunk((ChunkPos) null)
														.setIgnoreEntities(false));
									}
								}
							} else {
								if (!world.isRemote) {
									Template template = ((ServerWorld) world.getWorld()).getSaveHandler().getStructureTemplateManager()
											.getTemplateDefaulted(new ResourceLocation("wild_world", "pine8"));
									if (template != null) {
										template.addBlocksToWorldChunk(world, new BlockPos((int) (x - 2), (int) y, (int) (z - 2)),
												new PlacementSettings().setRotation(Rotation.NONE).setMirror(Mirror.NONE).setChunk((ChunkPos) null)
														.setIgnoreEntities(false));
									}
								}
							}
						}
					}
				}
			}
		}
	}
}
