
package net.mcreator.wild_world.block;

import net.minecraftforge.registries.ObjectHolder;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.world.storage.loot.LootContext;
import net.minecraft.world.World;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.IBlockReader;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.Direction;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.particles.RedstoneParticleData;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;
import net.minecraft.item.BlockItem;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.SoundType;
import net.minecraft.block.Blocks;
import net.minecraft.block.BlockState;
import net.minecraft.block.Block;

import net.mcreator.wild_world.itemgroup.StarTechnologyItemGroup;
import net.mcreator.wild_world.WildWorldElements;

import java.util.Random;
import java.util.List;
import java.util.Collections;

@WildWorldElements.ModElement.Tag
public class EnemySummoningVentBlock extends WildWorldElements.ModElement {
	@ObjectHolder("wild_world:enemysummoningvent")
	public static final Block block = null;
	public EnemySummoningVentBlock(WildWorldElements instance) {
		super(instance, 113);
	}

	@Override
	public void initElements() {
		elements.blocks.add(() -> new CustomBlock());
		elements.items
				.add(() -> new BlockItem(block, new Item.Properties().group(StarTechnologyItemGroup.tab)).setRegistryName(block.getRegistryName()));
	}
	public static class CustomBlock extends Block {
	
		public CustomBlock() {
			super(Block.Properties.create(Material.IRON).sound(SoundType.METAL).hardnessAndResistance(-1, 3600000).lightValue(15));
			setRegistryName("enemysummoningvent");
		}

		@Override
		public int tickRate(IWorldReader world) {
			return 5;
		}

		@Override
		public ItemStack getPickBlock(BlockState state, RayTraceResult target, IBlockReader world, BlockPos pos, PlayerEntity player) {
			return new ItemStack(Blocks.AIR, (int) (1));
		}

		@Override
		public MaterialColor getMaterialColor(BlockState state, IBlockReader blockAccess, BlockPos pos) {
			if (true) {
				return MaterialColor.YELLOW;
			}
			else {
				return MaterialColor.BLUE;
			}
		}

		@Override
		public boolean canConnectRedstone(BlockState state, IBlockReader world, BlockPos pos, Direction side) {
			return true;
		}

		@Override
		public List<ItemStack> getDrops(BlockState state, LootContext.Builder builder) {
			List<ItemStack> dropsOriginal = super.getDrops(state, builder);
			if (!dropsOriginal.isEmpty())
				return dropsOriginal;
			return Collections.singletonList(new ItemStack(this, 0));
		}

		@OnlyIn(Dist.CLIENT)
		@Override
		public void animateTick(BlockState state, World world, BlockPos pos, Random random) {
			super.animateTick(state, world, pos, random);
			PlayerEntity entity = Minecraft.getInstance().player;
			int x = pos.getX();
			int y = pos.getY();
			int z = pos.getZ();
			int i = x;
			int j = y;
			int k = z;
			if (true)
				for (int l = 0; l < 5; ++l) {
					double d0 = (i + 0.5) + (random.nextFloat() - 0.5) * 0.1D;
					double d1 = ((j + 0.7) + (random.nextFloat() - 0.5) * 0.1D * 100) + 0.5;
					double d2 = (k + 0.5) + (random.nextFloat() - 0.5) * 0.1D;
					world.addParticle(new RedstoneParticleData(1f, 0.975f, 0.6f * (float) Math.random(), 1f), d0, d1, d2, 0, 0, 0);
				}
		}
	}
}
