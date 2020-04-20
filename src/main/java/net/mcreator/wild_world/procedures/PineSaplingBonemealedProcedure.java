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
import net.minecraft.particles.ParticleTypes;
import net.minecraft.item.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;
import net.minecraft.block.Blocks;

import net.mcreator.wild_world.WildWorldElements;

@WildWorldElements.ModElement.Tag
public class PineSaplingBonemealedProcedure extends WildWorldElements.ModElement {
	public PineSaplingBonemealedProcedure(WildWorldElements instance) {
		super(instance, 148);
	}

	public static void executeProcedure(java.util.HashMap<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			System.err.println("Failed to load dependency entity for procedure PineSaplingBonemealed!");
			return;
		}
		if (dependencies.get("x") == null) {
			System.err.println("Failed to load dependency x for procedure PineSaplingBonemealed!");
			return;
		}
		if (dependencies.get("y") == null) {
			System.err.println("Failed to load dependency y for procedure PineSaplingBonemealed!");
			return;
		}
		if (dependencies.get("z") == null) {
			System.err.println("Failed to load dependency z for procedure PineSaplingBonemealed!");
			return;
		}
		if (dependencies.get("world") == null) {
			System.err.println("Failed to load dependency world for procedure PineSaplingBonemealed!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		int x = (int) dependencies.get("x");
		int y = (int) dependencies.get("y");
		int z = (int) dependencies.get("z");
		World world = (World) dependencies.get("world");
		double TreeGrowth = 0;
		double Succ = 0;
		double OffZ = 0;
		double OffX = 0;
		double OffY = 0;
		if ((((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY)
				.getItem() == new ItemStack(Items.BONE_MEAL, (int) (1)).getItem())) {
			if ((!((entity instanceof PlayerEntity) ? ((PlayerEntity) entity).abilities.isCreativeMode : false))) {
				if (entity instanceof PlayerEntity)
					((PlayerEntity) entity).inventory.clearMatchingItems(p -> new ItemStack(Items.BONE_MEAL, (int) (1)).getItem() == p.getItem(),
							(int) 1);
			}
			if (((Math.random() + (TreeGrowth)) >= 0.8)) {
				Succ = (double) 0;
				OffZ = (double) -2;
				for (int index0 = 0; index0 < (int) (5); index0++) {
					OffY = (double) 0;
					for (int index1 = 0; index1 < (int) (8); index1++) {
						OffX = (double) -2;
						for (int index2 = 0; index2 < (int) (5); index2++) {
							if (((world.getBlockState(new BlockPos((int) (x + (OffX)), (int) (y + (OffY)), (int) (z + (OffZ)))))
									.getBlock() == Blocks.AIR.getDefaultState().getBlock())) {
								Succ = (double) ((Succ) + 1);
							}
							OffX = (double) ((OffX) + 1);
						}
						OffY = (double) ((OffY) + 1);
					}
					OffZ = (double) ((OffZ) + 1);
				}
				if (((Succ) == 199)) {
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
			} else {
				TreeGrowth = (double) ((TreeGrowth) + 0.2);
				for (int _ct = 0; _ct < 10; _ct++) {
					world.addOptionalParticle(ParticleTypes.HAPPY_VILLAGER, (x + 0.5), (y + 0.5), (z + 0.5), 0.2, 0.2, 0.2);
				}
			}
		}
	}
}
