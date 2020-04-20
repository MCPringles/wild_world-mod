package net.mcreator.wild_world.procedures;

import net.minecraft.world.World;
import net.minecraft.util.math.BlockPos;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.block.Blocks;
import net.minecraft.block.BlockState;

import net.mcreator.wild_world.block.PineLogBlock;
import net.mcreator.wild_world.block.MapleLogWildBlock;
import net.mcreator.wild_world.block.BlossieLogWildBlock;
import net.mcreator.wild_world.WildWorldElements;

@WildWorldElements.ModElement.Tag
public class LeafDecayProcedure extends WildWorldElements.ModElement {
	public LeafDecayProcedure(WildWorldElements instance) {
		super(instance, 275);
	}

	public static void executeProcedure(java.util.HashMap<String, Object> dependencies) {
		if (dependencies.get("x") == null) {
			System.err.println("Failed to load dependency x for procedure LeafDecay!");
			return;
		}
		if (dependencies.get("y") == null) {
			System.err.println("Failed to load dependency y for procedure LeafDecay!");
			return;
		}
		if (dependencies.get("z") == null) {
			System.err.println("Failed to load dependency z for procedure LeafDecay!");
			return;
		}
		if (dependencies.get("world") == null) {
			System.err.println("Failed to load dependency world for procedure LeafDecay!");
			return;
		}
		int x = (int) dependencies.get("x");
		int y = (int) dependencies.get("y");
		int z = (int) dependencies.get("z");
		World world = (World) dependencies.get("world");
		double X = 0;
		double Y = 0;
		double Z = 0;
		boolean Found = false;
		X = (double) -1;
		Y = (double) -1;
		Z = (double) -1;
		for (int index0 = 0; index0 < (int) (3); index0++) {
			if ((((world.getBlockState(new BlockPos((int) (x + (X)), (int) y, (int) z))).getBlock() == Blocks.OAK_LOG.getDefaultState().getBlock())
					|| (((world.getBlockState(new BlockPos((int) (x + (X)), (int) y, (int) z))).getBlock() == PineLogBlock.block.getDefaultState()
							.getBlock())
							|| (((world.getBlockState(new BlockPos((int) (x + (X)), (int) y, (int) z))).getBlock() == MapleLogWildBlock.block
									.getDefaultState().getBlock())
									|| (((world.getBlockState(new BlockPos((int) (x + (X)), (int) y, (int) z)))
											.getBlock() == BlossieLogWildBlock.block.getDefaultState().getBlock())
											|| ((world.getBlockState(new BlockPos((int) (x + (X)), (int) y, (int) z))).getBlock() == Blocks.ACACIA_LOG
													.getDefaultState().getBlock())))))) {
				Found = (boolean) (true);
				break;
			}
			X = (double) ((X) + 1);
		}
		for (int index1 = 0; index1 < (int) (3); index1++) {
			if ((((world.getBlockState(new BlockPos((int) x, (int) (y + (Y)), (int) z))).getBlock() == Blocks.OAK_LOG.getDefaultState().getBlock())
					|| (((world.getBlockState(new BlockPos((int) (x + (X)), (int) y, (int) z))).getBlock() == PineLogBlock.block.getDefaultState()
							.getBlock())
							|| (((world.getBlockState(new BlockPos((int) (x + (X)), (int) y, (int) z))).getBlock() == MapleLogWildBlock.block
									.getDefaultState().getBlock())
									|| (((world.getBlockState(new BlockPos((int) (x + (X)), (int) y, (int) z)))
											.getBlock() == BlossieLogWildBlock.block.getDefaultState().getBlock())
											|| ((world.getBlockState(new BlockPos((int) (x + (X)), (int) y, (int) z))).getBlock() == Blocks.ACACIA_LOG
													.getDefaultState().getBlock())))))) {
				Found = (boolean) (true);
				break;
			}
			Y = (double) ((Y) + 1);
		}
		for (int index2 = 0; index2 < (int) (3); index2++) {
			if ((((world.getBlockState(new BlockPos((int) x, (int) y, (int) (z + (Z))))).getBlock() == Blocks.OAK_LOG.getDefaultState().getBlock())
					|| (((world.getBlockState(new BlockPos((int) (x + (X)), (int) y, (int) z))).getBlock() == PineLogBlock.block.getDefaultState()
							.getBlock())
							|| (((world.getBlockState(new BlockPos((int) (x + (X)), (int) y, (int) z))).getBlock() == MapleLogWildBlock.block
									.getDefaultState().getBlock())
									|| (((world.getBlockState(new BlockPos((int) (x + (X)), (int) y, (int) z)))
											.getBlock() == BlossieLogWildBlock.block.getDefaultState().getBlock())
											|| ((world.getBlockState(new BlockPos((int) (x + (X)), (int) y, (int) z))).getBlock() == Blocks.ACACIA_LOG
													.getDefaultState().getBlock())))))) {
				Found = (boolean) (true);
				break;
			}
			Z = (double) ((Z) + 1);
		}
		X = (double) -1;
		Y = (double) -1;
		Z = (double) -1;
		if (((Found) == (false))) {
			if (((new Object() {
				public double getValue(BlockPos pos, String tag) {
					TileEntity tileEntity = world.getTileEntity(pos);
					if (tileEntity != null)
						return tileEntity.getTileData().getDouble(tag);
					return -1;
				}
			}.getValue(new BlockPos((int) x, (int) y, (int) z), "Distance")) >= 6)) {
				if ((Math.random() <= 0.1)) {
					world.setBlockState(new BlockPos((int) x, (int) y, (int) z), Blocks.AIR.getDefaultState(), 3);
				}
			}
		}
		if (!world.isRemote) {
			BlockPos _bp = new BlockPos((int) x, (int) y, (int) z);
			TileEntity _tileEntity = world.getTileEntity(_bp);
			BlockState _bs = world.getBlockState(_bp);
			if (_tileEntity != null)
				_tileEntity.getTileData().putDouble("Distance", 10);
			world.notifyBlockUpdate(_bp, _bs, _bs, 3);
		}
		if (((Found) == (true))) {
			if (!world.isRemote) {
				BlockPos _bp = new BlockPos((int) x, (int) y, (int) z);
				TileEntity _tileEntity = world.getTileEntity(_bp);
				BlockState _bs = world.getBlockState(_bp);
				if (_tileEntity != null)
					_tileEntity.getTileData().putDouble("Distance", 0);
				world.notifyBlockUpdate(_bp, _bs, _bs, 3);
			}
		}
		if (((true) == (true))) {
			X = (double) -1;
			Y = (double) -1;
			Z = (double) -1;
			for (int index3 = 0; index3 < (int) (3); index3++) {
				if (((new Object() {
					public double getValue(BlockPos pos, String tag) {
						TileEntity tileEntity = world.getTileEntity(pos);
						if (tileEntity != null)
							return tileEntity.getTileData().getDouble(tag);
						return -1;
					}
				}.getValue(new BlockPos((int) (x + (X)), (int) y, (int) z), "Distance")) == 5)) {
					if (((new Object() {
						public double getValue(BlockPos pos, String tag) {
							TileEntity tileEntity = world.getTileEntity(pos);
							if (tileEntity != null)
								return tileEntity.getTileData().getDouble(tag);
							return -1;
						}
					}.getValue(new BlockPos((int) x, (int) y, (int) z), "Distance")) >= 7)) {
						if (!world.isRemote) {
							BlockPos _bp = new BlockPos((int) x, (int) y, (int) z);
							TileEntity _tileEntity = world.getTileEntity(_bp);
							BlockState _bs = world.getBlockState(_bp);
							if (_tileEntity != null)
								_tileEntity.getTileData().putDouble("Distance", 6);
							world.notifyBlockUpdate(_bp, _bs, _bs, 3);
						}
					}
				}
				X = (double) ((X) + 1);
			}
			for (int index4 = 0; index4 < (int) (3); index4++) {
				if (((new Object() {
					public double getValue(BlockPos pos, String tag) {
						TileEntity tileEntity = world.getTileEntity(pos);
						if (tileEntity != null)
							return tileEntity.getTileData().getDouble(tag);
						return -1;
					}
				}.getValue(new BlockPos((int) x, (int) (y + (Y)), (int) z), "Distance")) == 5)) {
					if (((new Object() {
						public double getValue(BlockPos pos, String tag) {
							TileEntity tileEntity = world.getTileEntity(pos);
							if (tileEntity != null)
								return tileEntity.getTileData().getDouble(tag);
							return -1;
						}
					}.getValue(new BlockPos((int) x, (int) y, (int) z), "Distance")) >= 7)) {
						if (!world.isRemote) {
							BlockPos _bp = new BlockPos((int) x, (int) y, (int) z);
							TileEntity _tileEntity = world.getTileEntity(_bp);
							BlockState _bs = world.getBlockState(_bp);
							if (_tileEntity != null)
								_tileEntity.getTileData().putDouble("Distance", 6);
							world.notifyBlockUpdate(_bp, _bs, _bs, 3);
						}
					}
				}
				Y = (double) ((Y) + 1);
			}
			for (int index5 = 0; index5 < (int) (3); index5++) {
				if (((new Object() {
					public double getValue(BlockPos pos, String tag) {
						TileEntity tileEntity = world.getTileEntity(pos);
						if (tileEntity != null)
							return tileEntity.getTileData().getDouble(tag);
						return -1;
					}
				}.getValue(new BlockPos((int) x, (int) y, (int) (z + (Z))), "Distance")) == 5)) {
					if (((new Object() {
						public double getValue(BlockPos pos, String tag) {
							TileEntity tileEntity = world.getTileEntity(pos);
							if (tileEntity != null)
								return tileEntity.getTileData().getDouble(tag);
							return -1;
						}
					}.getValue(new BlockPos((int) x, (int) y, (int) z), "Distance")) >= 7)) {
						if (!world.isRemote) {
							BlockPos _bp = new BlockPos((int) x, (int) y, (int) z);
							TileEntity _tileEntity = world.getTileEntity(_bp);
							BlockState _bs = world.getBlockState(_bp);
							if (_tileEntity != null)
								_tileEntity.getTileData().putDouble("Distance", 6);
							world.notifyBlockUpdate(_bp, _bs, _bs, 3);
						}
					}
				}
				Z = (double) ((Z) + 1);
			}
		}
		if (((true) == (true))) {
			X = (double) -1;
			Y = (double) -1;
			Z = (double) -1;
			for (int index6 = 0; index6 < (int) (3); index6++) {
				if (((new Object() {
					public double getValue(BlockPos pos, String tag) {
						TileEntity tileEntity = world.getTileEntity(pos);
						if (tileEntity != null)
							return tileEntity.getTileData().getDouble(tag);
						return -1;
					}
				}.getValue(new BlockPos((int) (x + (X)), (int) y, (int) z), "Distance")) == 4)) {
					if (((new Object() {
						public double getValue(BlockPos pos, String tag) {
							TileEntity tileEntity = world.getTileEntity(pos);
							if (tileEntity != null)
								return tileEntity.getTileData().getDouble(tag);
							return -1;
						}
					}.getValue(new BlockPos((int) x, (int) y, (int) z), "Distance")) >= 6)) {
						if (!world.isRemote) {
							BlockPos _bp = new BlockPos((int) x, (int) y, (int) z);
							TileEntity _tileEntity = world.getTileEntity(_bp);
							BlockState _bs = world.getBlockState(_bp);
							if (_tileEntity != null)
								_tileEntity.getTileData().putDouble("Distance", 5);
							world.notifyBlockUpdate(_bp, _bs, _bs, 3);
						}
					}
				}
				X = (double) ((X) + 1);
			}
			for (int index7 = 0; index7 < (int) (3); index7++) {
				if (((new Object() {
					public double getValue(BlockPos pos, String tag) {
						TileEntity tileEntity = world.getTileEntity(pos);
						if (tileEntity != null)
							return tileEntity.getTileData().getDouble(tag);
						return -1;
					}
				}.getValue(new BlockPos((int) x, (int) (y + (Y)), (int) z), "Distance")) == 4)) {
					if (((new Object() {
						public double getValue(BlockPos pos, String tag) {
							TileEntity tileEntity = world.getTileEntity(pos);
							if (tileEntity != null)
								return tileEntity.getTileData().getDouble(tag);
							return -1;
						}
					}.getValue(new BlockPos((int) x, (int) y, (int) z), "Distance")) >= 6)) {
						if (!world.isRemote) {
							BlockPos _bp = new BlockPos((int) x, (int) y, (int) z);
							TileEntity _tileEntity = world.getTileEntity(_bp);
							BlockState _bs = world.getBlockState(_bp);
							if (_tileEntity != null)
								_tileEntity.getTileData().putDouble("Distance", 5);
							world.notifyBlockUpdate(_bp, _bs, _bs, 3);
						}
					}
				}
				Y = (double) ((Y) + 1);
			}
			for (int index8 = 0; index8 < (int) (3); index8++) {
				if (((new Object() {
					public double getValue(BlockPos pos, String tag) {
						TileEntity tileEntity = world.getTileEntity(pos);
						if (tileEntity != null)
							return tileEntity.getTileData().getDouble(tag);
						return -1;
					}
				}.getValue(new BlockPos((int) x, (int) y, (int) (z + (Z))), "Distance")) == 4)) {
					if (((new Object() {
						public double getValue(BlockPos pos, String tag) {
							TileEntity tileEntity = world.getTileEntity(pos);
							if (tileEntity != null)
								return tileEntity.getTileData().getDouble(tag);
							return -1;
						}
					}.getValue(new BlockPos((int) x, (int) y, (int) z), "Distance")) >= 6)) {
						if (!world.isRemote) {
							BlockPos _bp = new BlockPos((int) x, (int) y, (int) z);
							TileEntity _tileEntity = world.getTileEntity(_bp);
							BlockState _bs = world.getBlockState(_bp);
							if (_tileEntity != null)
								_tileEntity.getTileData().putDouble("Distance", 5);
							world.notifyBlockUpdate(_bp, _bs, _bs, 3);
						}
					}
				}
				Z = (double) ((Z) + 1);
			}
		}
		if (((true) == (true))) {
			X = (double) -1;
			Y = (double) -1;
			Z = (double) -1;
			for (int index9 = 0; index9 < (int) (3); index9++) {
				if (((new Object() {
					public double getValue(BlockPos pos, String tag) {
						TileEntity tileEntity = world.getTileEntity(pos);
						if (tileEntity != null)
							return tileEntity.getTileData().getDouble(tag);
						return -1;
					}
				}.getValue(new BlockPos((int) (x + (X)), (int) y, (int) z), "Distance")) == 3)) {
					if (((new Object() {
						public double getValue(BlockPos pos, String tag) {
							TileEntity tileEntity = world.getTileEntity(pos);
							if (tileEntity != null)
								return tileEntity.getTileData().getDouble(tag);
							return -1;
						}
					}.getValue(new BlockPos((int) x, (int) y, (int) z), "Distance")) >= 5)) {
						if (!world.isRemote) {
							BlockPos _bp = new BlockPos((int) x, (int) y, (int) z);
							TileEntity _tileEntity = world.getTileEntity(_bp);
							BlockState _bs = world.getBlockState(_bp);
							if (_tileEntity != null)
								_tileEntity.getTileData().putDouble("Distance", 4);
							world.notifyBlockUpdate(_bp, _bs, _bs, 3);
						}
					}
				}
				X = (double) ((X) + 1);
			}
			for (int index10 = 0; index10 < (int) (3); index10++) {
				if (((new Object() {
					public double getValue(BlockPos pos, String tag) {
						TileEntity tileEntity = world.getTileEntity(pos);
						if (tileEntity != null)
							return tileEntity.getTileData().getDouble(tag);
						return -1;
					}
				}.getValue(new BlockPos((int) x, (int) (y + (Y)), (int) z), "Distance")) == 3)) {
					if (((new Object() {
						public double getValue(BlockPos pos, String tag) {
							TileEntity tileEntity = world.getTileEntity(pos);
							if (tileEntity != null)
								return tileEntity.getTileData().getDouble(tag);
							return -1;
						}
					}.getValue(new BlockPos((int) x, (int) y, (int) z), "Distance")) >= 5)) {
						if (!world.isRemote) {
							BlockPos _bp = new BlockPos((int) x, (int) y, (int) z);
							TileEntity _tileEntity = world.getTileEntity(_bp);
							BlockState _bs = world.getBlockState(_bp);
							if (_tileEntity != null)
								_tileEntity.getTileData().putDouble("Distance", 4);
							world.notifyBlockUpdate(_bp, _bs, _bs, 3);
						}
					}
				}
				Y = (double) ((Y) + 1);
			}
			for (int index11 = 0; index11 < (int) (3); index11++) {
				if (((new Object() {
					public double getValue(BlockPos pos, String tag) {
						TileEntity tileEntity = world.getTileEntity(pos);
						if (tileEntity != null)
							return tileEntity.getTileData().getDouble(tag);
						return -1;
					}
				}.getValue(new BlockPos((int) x, (int) y, (int) (z + (Z))), "Distance")) == 3)) {
					if (((new Object() {
						public double getValue(BlockPos pos, String tag) {
							TileEntity tileEntity = world.getTileEntity(pos);
							if (tileEntity != null)
								return tileEntity.getTileData().getDouble(tag);
							return -1;
						}
					}.getValue(new BlockPos((int) x, (int) y, (int) z), "Distance")) >= 5)) {
						if (!world.isRemote) {
							BlockPos _bp = new BlockPos((int) x, (int) y, (int) z);
							TileEntity _tileEntity = world.getTileEntity(_bp);
							BlockState _bs = world.getBlockState(_bp);
							if (_tileEntity != null)
								_tileEntity.getTileData().putDouble("Distance", 4);
							world.notifyBlockUpdate(_bp, _bs, _bs, 3);
						}
					}
				}
				Z = (double) ((Z) + 1);
			}
		}
		if (((true) == (true))) {
			X = (double) -1;
			Y = (double) -1;
			Z = (double) -1;
			for (int index12 = 0; index12 < (int) (3); index12++) {
				if (((new Object() {
					public double getValue(BlockPos pos, String tag) {
						TileEntity tileEntity = world.getTileEntity(pos);
						if (tileEntity != null)
							return tileEntity.getTileData().getDouble(tag);
						return -1;
					}
				}.getValue(new BlockPos((int) (x + (X)), (int) y, (int) z), "Distance")) == 2)) {
					if (((new Object() {
						public double getValue(BlockPos pos, String tag) {
							TileEntity tileEntity = world.getTileEntity(pos);
							if (tileEntity != null)
								return tileEntity.getTileData().getDouble(tag);
							return -1;
						}
					}.getValue(new BlockPos((int) x, (int) y, (int) z), "Distance")) >= 4)) {
						if (!world.isRemote) {
							BlockPos _bp = new BlockPos((int) x, (int) y, (int) z);
							TileEntity _tileEntity = world.getTileEntity(_bp);
							BlockState _bs = world.getBlockState(_bp);
							if (_tileEntity != null)
								_tileEntity.getTileData().putDouble("Distance", 3);
							world.notifyBlockUpdate(_bp, _bs, _bs, 3);
						}
					}
				}
				X = (double) ((X) + 1);
			}
			for (int index13 = 0; index13 < (int) (3); index13++) {
				if (((new Object() {
					public double getValue(BlockPos pos, String tag) {
						TileEntity tileEntity = world.getTileEntity(pos);
						if (tileEntity != null)
							return tileEntity.getTileData().getDouble(tag);
						return -1;
					}
				}.getValue(new BlockPos((int) x, (int) (y + (Y)), (int) z), "Distance")) == 2)) {
					if (((new Object() {
						public double getValue(BlockPos pos, String tag) {
							TileEntity tileEntity = world.getTileEntity(pos);
							if (tileEntity != null)
								return tileEntity.getTileData().getDouble(tag);
							return -1;
						}
					}.getValue(new BlockPos((int) x, (int) y, (int) z), "Distance")) >= 4)) {
						if (!world.isRemote) {
							BlockPos _bp = new BlockPos((int) x, (int) y, (int) z);
							TileEntity _tileEntity = world.getTileEntity(_bp);
							BlockState _bs = world.getBlockState(_bp);
							if (_tileEntity != null)
								_tileEntity.getTileData().putDouble("Distance", 3);
							world.notifyBlockUpdate(_bp, _bs, _bs, 3);
						}
					}
				}
				Y = (double) ((Y) + 1);
			}
			for (int index14 = 0; index14 < (int) (3); index14++) {
				if (((new Object() {
					public double getValue(BlockPos pos, String tag) {
						TileEntity tileEntity = world.getTileEntity(pos);
						if (tileEntity != null)
							return tileEntity.getTileData().getDouble(tag);
						return -1;
					}
				}.getValue(new BlockPos((int) x, (int) y, (int) (z + (Z))), "Distance")) == 2)) {
					if (((new Object() {
						public double getValue(BlockPos pos, String tag) {
							TileEntity tileEntity = world.getTileEntity(pos);
							if (tileEntity != null)
								return tileEntity.getTileData().getDouble(tag);
							return -1;
						}
					}.getValue(new BlockPos((int) x, (int) y, (int) z), "Distance")) >= 4)) {
						if (!world.isRemote) {
							BlockPos _bp = new BlockPos((int) x, (int) y, (int) z);
							TileEntity _tileEntity = world.getTileEntity(_bp);
							BlockState _bs = world.getBlockState(_bp);
							if (_tileEntity != null)
								_tileEntity.getTileData().putDouble("Distance", 3);
							world.notifyBlockUpdate(_bp, _bs, _bs, 3);
						}
					}
				}
				Z = (double) ((Z) + 1);
			}
		}
		if (((true) == (true))) {
			X = (double) -1;
			Y = (double) -1;
			Z = (double) -1;
			for (int index15 = 0; index15 < (int) (3); index15++) {
				if (((new Object() {
					public double getValue(BlockPos pos, String tag) {
						TileEntity tileEntity = world.getTileEntity(pos);
						if (tileEntity != null)
							return tileEntity.getTileData().getDouble(tag);
						return -1;
					}
				}.getValue(new BlockPos((int) (x + (X)), (int) y, (int) z), "Distance")) == 1)) {
					if (((new Object() {
						public double getValue(BlockPos pos, String tag) {
							TileEntity tileEntity = world.getTileEntity(pos);
							if (tileEntity != null)
								return tileEntity.getTileData().getDouble(tag);
							return -1;
						}
					}.getValue(new BlockPos((int) x, (int) y, (int) z), "Distance")) >= 3)) {
						if (!world.isRemote) {
							BlockPos _bp = new BlockPos((int) x, (int) y, (int) z);
							TileEntity _tileEntity = world.getTileEntity(_bp);
							BlockState _bs = world.getBlockState(_bp);
							if (_tileEntity != null)
								_tileEntity.getTileData().putDouble("Distance", 2);
							world.notifyBlockUpdate(_bp, _bs, _bs, 3);
						}
					}
				}
				X = (double) ((X) + 1);
			}
			for (int index16 = 0; index16 < (int) (3); index16++) {
				if (((new Object() {
					public double getValue(BlockPos pos, String tag) {
						TileEntity tileEntity = world.getTileEntity(pos);
						if (tileEntity != null)
							return tileEntity.getTileData().getDouble(tag);
						return -1;
					}
				}.getValue(new BlockPos((int) x, (int) (y + (Y)), (int) z), "Distance")) == 1)) {
					if (((new Object() {
						public double getValue(BlockPos pos, String tag) {
							TileEntity tileEntity = world.getTileEntity(pos);
							if (tileEntity != null)
								return tileEntity.getTileData().getDouble(tag);
							return -1;
						}
					}.getValue(new BlockPos((int) x, (int) y, (int) z), "Distance")) >= 3)) {
						if (!world.isRemote) {
							BlockPos _bp = new BlockPos((int) x, (int) y, (int) z);
							TileEntity _tileEntity = world.getTileEntity(_bp);
							BlockState _bs = world.getBlockState(_bp);
							if (_tileEntity != null)
								_tileEntity.getTileData().putDouble("Distance", 2);
							world.notifyBlockUpdate(_bp, _bs, _bs, 3);
						}
					}
				}
				Y = (double) ((Y) + 1);
			}
			for (int index17 = 0; index17 < (int) (3); index17++) {
				if (((new Object() {
					public double getValue(BlockPos pos, String tag) {
						TileEntity tileEntity = world.getTileEntity(pos);
						if (tileEntity != null)
							return tileEntity.getTileData().getDouble(tag);
						return -1;
					}
				}.getValue(new BlockPos((int) x, (int) y, (int) (z + (Z))), "Distance")) == 1)) {
					if (((new Object() {
						public double getValue(BlockPos pos, String tag) {
							TileEntity tileEntity = world.getTileEntity(pos);
							if (tileEntity != null)
								return tileEntity.getTileData().getDouble(tag);
							return -1;
						}
					}.getValue(new BlockPos((int) x, (int) y, (int) z), "Distance")) >= 3)) {
						if (!world.isRemote) {
							BlockPos _bp = new BlockPos((int) x, (int) y, (int) z);
							TileEntity _tileEntity = world.getTileEntity(_bp);
							BlockState _bs = world.getBlockState(_bp);
							if (_tileEntity != null)
								_tileEntity.getTileData().putDouble("Distance", 2);
							world.notifyBlockUpdate(_bp, _bs, _bs, 3);
						}
					}
				}
				Z = (double) ((Z) + 1);
			}
		}
		if (((true) == (true))) {
			X = (double) -1;
			Y = (double) -1;
			Z = (double) -1;
			for (int index18 = 0; index18 < (int) (3); index18++) {
				if (((new Object() {
					public double getValue(BlockPos pos, String tag) {
						TileEntity tileEntity = world.getTileEntity(pos);
						if (tileEntity != null)
							return tileEntity.getTileData().getDouble(tag);
						return -1;
					}
				}.getValue(new BlockPos((int) (x + (X)), (int) y, (int) z), "Distance")) == 0)) {
					if (((new Object() {
						public double getValue(BlockPos pos, String tag) {
							TileEntity tileEntity = world.getTileEntity(pos);
							if (tileEntity != null)
								return tileEntity.getTileData().getDouble(tag);
							return -1;
						}
					}.getValue(new BlockPos((int) x, (int) y, (int) z), "Distance")) >= 2)) {
						if (!world.isRemote) {
							BlockPos _bp = new BlockPos((int) x, (int) y, (int) z);
							TileEntity _tileEntity = world.getTileEntity(_bp);
							BlockState _bs = world.getBlockState(_bp);
							if (_tileEntity != null)
								_tileEntity.getTileData().putDouble("Distance", 1);
							world.notifyBlockUpdate(_bp, _bs, _bs, 3);
						}
					}
				}
				X = (double) ((X) + 1);
			}
			for (int index19 = 0; index19 < (int) (3); index19++) {
				if (((new Object() {
					public double getValue(BlockPos pos, String tag) {
						TileEntity tileEntity = world.getTileEntity(pos);
						if (tileEntity != null)
							return tileEntity.getTileData().getDouble(tag);
						return -1;
					}
				}.getValue(new BlockPos((int) x, (int) (y + (Y)), (int) z), "Distance")) == 0)) {
					if (((new Object() {
						public double getValue(BlockPos pos, String tag) {
							TileEntity tileEntity = world.getTileEntity(pos);
							if (tileEntity != null)
								return tileEntity.getTileData().getDouble(tag);
							return -1;
						}
					}.getValue(new BlockPos((int) x, (int) y, (int) z), "Distance")) >= 2)) {
						if (!world.isRemote) {
							BlockPos _bp = new BlockPos((int) x, (int) y, (int) z);
							TileEntity _tileEntity = world.getTileEntity(_bp);
							BlockState _bs = world.getBlockState(_bp);
							if (_tileEntity != null)
								_tileEntity.getTileData().putDouble("Distance", 1);
							world.notifyBlockUpdate(_bp, _bs, _bs, 3);
						}
					}
				}
				Y = (double) ((Y) + 1);
			}
			for (int index20 = 0; index20 < (int) (3); index20++) {
				if (((new Object() {
					public double getValue(BlockPos pos, String tag) {
						TileEntity tileEntity = world.getTileEntity(pos);
						if (tileEntity != null)
							return tileEntity.getTileData().getDouble(tag);
						return -1;
					}
				}.getValue(new BlockPos((int) x, (int) y, (int) (z + (Z))), "Distance")) == 0)) {
					if (((new Object() {
						public double getValue(BlockPos pos, String tag) {
							TileEntity tileEntity = world.getTileEntity(pos);
							if (tileEntity != null)
								return tileEntity.getTileData().getDouble(tag);
							return -1;
						}
					}.getValue(new BlockPos((int) x, (int) y, (int) z), "Distance")) >= 2)) {
						if (!world.isRemote) {
							BlockPos _bp = new BlockPos((int) x, (int) y, (int) z);
							TileEntity _tileEntity = world.getTileEntity(_bp);
							BlockState _bs = world.getBlockState(_bp);
							if (_tileEntity != null)
								_tileEntity.getTileData().putDouble("Distance", 1);
							world.notifyBlockUpdate(_bp, _bs, _bs, 3);
						}
					}
				}
				Z = (double) ((Z) + 1);
			}
		}
	}
}
