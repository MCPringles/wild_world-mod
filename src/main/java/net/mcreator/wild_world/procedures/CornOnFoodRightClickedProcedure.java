package net.mcreator.wild_world.procedures;

import net.minecraftforge.registries.ForgeRegistries;

import net.minecraft.world.World;
import net.minecraft.util.math.RayTraceContext;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Hand;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;
import net.minecraft.block.Blocks;

import net.mcreator.wild_world.item.CornItem;
import net.mcreator.wild_world.block.MarshFarmLandBlock;
import net.mcreator.wild_world.block.CornStage0Block;
import net.mcreator.wild_world.WildWorldElements;

@WildWorldElements.ModElement.Tag
public class CornOnFoodRightClickedProcedure extends WildWorldElements.ModElement {
	public CornOnFoodRightClickedProcedure(WildWorldElements instance) {
		super(instance, 299);
	}

	public static void executeProcedure(java.util.HashMap<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			System.err.println("Failed to load dependency entity for procedure CornOnFoodRightClicked!");
			return;
		}
		if (dependencies.get("world") == null) {
			System.err.println("Failed to load dependency world for procedure CornOnFoodRightClicked!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		World world = (World) dependencies.get("world");
		double x = 0;
		double y = 0;
		double z = 0;
		x = (double) (entity.world.rayTraceBlocks(new RayTraceContext(entity.getEyePosition(1f),
				entity.getEyePosition(1f).add(entity.getLook(1f).x * 4, entity.getLook(1f).y * 4, entity.getLook(1f).z * 4),
				RayTraceContext.BlockMode.OUTLINE, RayTraceContext.FluidMode.NONE, entity)).getPos().getX());
		y = (double) (entity.world.rayTraceBlocks(new RayTraceContext(entity.getEyePosition(1f),
				entity.getEyePosition(1f).add(entity.getLook(1f).x * 4, entity.getLook(1f).y * 4, entity.getLook(1f).z * 4),
				RayTraceContext.BlockMode.OUTLINE, RayTraceContext.FluidMode.NONE, entity)).getPos().getY());
		z = (double) (entity.world.rayTraceBlocks(new RayTraceContext(entity.getEyePosition(1f),
				entity.getEyePosition(1f).add(entity.getLook(1f).x * 4, entity.getLook(1f).y * 4, entity.getLook(1f).z * 4),
				RayTraceContext.BlockMode.OUTLINE, RayTraceContext.FluidMode.NONE, entity)).getPos().getZ());
		if ((((world.getBlockState(new BlockPos((int) (x), (int) (y), (int) (z)))).getBlock() == Blocks.FARMLAND.getDefaultState().getBlock())
				|| ((world.getBlockState(new BlockPos((int) (x), (int) (y), (int) (z)))).getBlock() == MarshFarmLandBlock.block.getDefaultState()
						.getBlock()))) {
			if ((world.isAirBlock(new BlockPos((int) (x), (int) ((y) + 1), (int) (z))))) {
				world.setBlockState(new BlockPos((int) (x), (int) ((y) + 1), (int) (z)), CornStage0Block.block.getDefaultState(), 3);
				if ((((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY)
						.getItem() == new ItemStack(CornItem.block, (int) (1)).getItem())) {
					if (entity instanceof LivingEntity) {
						((LivingEntity) entity).swingArm(Hand.MAIN_HAND);
					}
				} else {
					if (entity instanceof LivingEntity) {
						((LivingEntity) entity).swingArm(Hand.OFF_HAND);
					}
				}
				world.playSound((PlayerEntity) null, ((x) + 0.5), ((y) + 0.5), ((z) + 0.5),
						(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("block.grass.place")),
						SoundCategory.NEUTRAL, (float) 0.3, (float) 1.3);
			}
		}
	}
}
