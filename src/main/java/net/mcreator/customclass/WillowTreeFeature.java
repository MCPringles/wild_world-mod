package net.mcreator.customclass;

import net.mcreator.wild_world.block.LogWillowBlock;
import net.mcreator.wild_world.block.WillowLeavesBlock;
import net.mcreator.wild_world.block.LuminodeBlock;
import net.mcreator.wild_world.block.WillowSaplingBlock;

import com.mojang.datafixers.Dynamic;
import java.util.Random;
import java.util.Set;
import java.util.function.Function;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.tags.BlockTags;
import net.minecraft.state.BooleanProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.IWorldGenerationReader;
import net.minecraft.world.gen.IWorldGenerationBaseReader;
import net.minecraft.world.gen.feature.AbstractTreeFeature;
import net.minecraft.world.gen.feature.NoFeatureConfig;

public class WillowTreeFeature extends AbstractTreeFeature<NoFeatureConfig> {
   	private static final BlockState TRUNK = LogWillowBlock.block.getDefaultState();
   	private static final BlockState LEAF = WillowLeavesBlock.block.getDefaultState();
   	private static final BlockState NODE = LuminodeBlock.block.getDefaultState();

   	public WillowTreeFeature(Function<Dynamic<?>, ? extends NoFeatureConfig> p_i51425_1_, boolean doBlockNotifyIn) {
      	super(p_i51425_1_, doBlockNotifyIn);
      	this.setSapling((net.minecraftforge.common.IPlantable)WillowSaplingBlock.block);
   	}

   	protected static boolean isAirLeavesOrNode(IWorldGenerationBaseReader worldIn, BlockPos pos) {
      	if (!(worldIn instanceof net.minecraft.world.IWorldReader)) // FORGE: Redirect to state method when possible
      		return worldIn.hasBlockState(pos, (p_214581_0_) -> {
         		return p_214581_0_.isAir() || p_214581_0_.isIn(BlockTags.LEAVES) || p_214581_0_.getBlock() == LuminodeBlock.block; 
      	});
      	else return worldIn.hasBlockState(pos, state -> state.canBeReplacedByLeaves((net.minecraft.world.IWorldReader)worldIn, pos));
   	}

   	public boolean place(Set<BlockPos> changedBlocks, IWorldGenerationReader worldIn, Random rand, BlockPos position, MutableBoundingBox p_208519_5_) {
      	int i = rand.nextInt(3) + 6;
      	boolean flag = true;
      	if (position.getY() >= 1 && position.getY() + i + 1 <= worldIn.getMaxHeight()) {
         	for(int j = position.getY(); j <= position.getY() + 1 + i; ++j) {
            	int k = 1;
            	if (j == position.getY()) {
               		k = 0;
            	}

	            if (j >= position.getY() + 1 + i - 2) {
	              	k = 3;
	            }

            	BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();

            	for(int l = position.getX() - k; l <= position.getX() + k && flag; ++l) {
               		for(int i1 = position.getZ() - k; i1 <= position.getZ() + k && flag; ++i1) {
                  		if (j >= 0 && j < worldIn.getMaxHeight()) {
                     		blockpos$mutableblockpos.setPos(l, j, i1);
                     		if (!isAirOrLeaves(worldIn, blockpos$mutableblockpos)) {
                        		if (isWater(worldIn, blockpos$mutableblockpos)) {
                           			if (j > position.getY()) {
                              			flag = false;
                           			}
                    			} else {
                           			flag = false;
                        		}
                     		}
                  		} else {
                     		flag = false;
                  		}
               		}
            	}
         	}
         	if (!flag) {
            	return false;
         	} else if (isSoil(worldIn, position.down(), getSapling()) && position.getY() < worldIn.getMaxHeight() - i - 1) {
            	this.setDirtAt(worldIn, position.down(), position);

            	for(int l1 = position.getY() - 3 + i; l1 <= position.getY() + i; ++l1) {
               		int k2 = l1 - position.getY() - i;
               		
               		int i3 = 2 - k2 / 2;

               		for(int k3 = position.getX() - i3; k3 <= position.getX() + i3; ++k3) {
                  		int l3 = k3 - position.getX();

                  		for(int j1 = position.getZ() - i3; j1 <= position.getZ() + i3; ++j1) {
                     		int k1 = j1 - position.getZ();
                     		if (k2 >= -3) {
                     			if (Math.abs(l3) != i3 || Math.abs(k1) != i3 || rand.nextInt(4) != 0) {
	                        		BlockPos blockpos = new BlockPos(k3, l1, j1);
	                        		if (isAirOrLeaves(worldIn, blockpos) || func_214576_j(worldIn, blockpos)) {
	                        			if (rand.nextInt(40) != 5) 
	                        			{
	                        				this.setLogState(changedBlocks, worldIn, blockpos, LEAF, p_208519_5_);
	                        			}
	                        			else 
	                        			{
	                        				this.setLogState(changedBlocks, worldIn, blockpos, NODE, p_208519_5_);
	                        			}
	                        		}
                     			}
                     		}
               			}
            		}
            		//Trunk
            		for(int i2 = 0; i2 < i; ++i2) {
               			BlockPos blockpos3 = position.up(i2);
               			if (isAirOrLeaves(worldIn, blockpos3) || isWater(worldIn, blockpos3)) {
                  			this.setLogState(changedBlocks, worldIn, blockpos3, TRUNK, p_208519_5_);
              			}
            		}
        		}
        		//Low hanging leaves
        		for (int l1 = position.getY() + i - 3; l1 > position.getY() + i - 7; l1--) {
        			int k2 = l1 - position.getY() - i;
        			int i3 = 3;
        			
        			for(int k3 = position.getX() - i3; k3 <= position.getX() + i3; ++k3) {
                  		int l3 = k3 - position.getX();

                  		for(int j1 = position.getZ() - i3; j1 <= position.getZ() + i3; ++j1) {
                     		int k1 = j1 - position.getZ();
                     		if (Math.abs(l3) == i3 ^ Math.abs(k1) == i3) {
                     			BlockPos blockpos = new BlockPos(k3, l1, j1);
                 				if (isAirOrLeaves(worldIn, blockpos) || func_214576_j(worldIn, blockpos)) {
                    				if (rand.nextInt(10) != 5 && !isAir(worldIn, blockpos.up())) 
                    				{
                    					this.setLogState(changedBlocks, worldIn, blockpos, LEAF, p_208519_5_);
                    				}
                 				}	
                 			}
                  		}
            		}
        		}
        		return true;
      	  	}
   		}
   		return false;
	}
}
