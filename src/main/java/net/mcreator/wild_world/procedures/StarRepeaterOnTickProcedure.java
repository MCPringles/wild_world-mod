package net.mcreator.wild_world.procedures;

import net.minecraft.world.World;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.Direction;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.state.DirectionProperty;
import net.minecraft.block.BlockState;

import net.mcreator.wild_world.block.YronStarBoxOffBlock;
import net.mcreator.wild_world.WildWorldElements;

@WildWorldElements.ModElement.Tag
public class StarRepeaterOnTickProcedure extends WildWorldElements.ModElement {
	public StarRepeaterOnTickProcedure(WildWorldElements instance) {
		super(instance, 322);
	}

	public static void executeProcedure(java.util.HashMap<String, Object> dependencies) {
		if (dependencies.get("x") == null) {
			System.err.println("Failed to load dependency x for procedure StarRepeaterOnTick!");
			return;
		}
		if (dependencies.get("y") == null) {
			System.err.println("Failed to load dependency y for procedure StarRepeaterOnTick!");
			return;
		}
		if (dependencies.get("z") == null) {
			System.err.println("Failed to load dependency z for procedure StarRepeaterOnTick!");
			return;
		}
		if (dependencies.get("world") == null) {
			System.err.println("Failed to load dependency world for procedure StarRepeaterOnTick!");
			return;
		}
		int x = (int) dependencies.get("x");
		int y = (int) dependencies.get("y");
		int z = (int) dependencies.get("z");
		World world = (World) dependencies.get("world");
		if (((new Object() {
			public boolean getValue(BlockPos pos, String tag) {
				TileEntity tileEntity = world.getTileEntity(pos);
				if (tileEntity != null)
					return tileEntity.getTileData().getBoolean(tag);
				return false;
			}
		}.getValue(new BlockPos((int) x, (int) y, (int) z), "StarPowered")) == (false))) {
			world.setBlockState(new BlockPos((int) x, (int) y, (int) z), YronStarBoxOffBlock.block.getDefaultState(), 3);
		} else {
			if (((new Object() {
				public Direction getDirection(BlockPos pos) {
					try {
						BlockState _bs = world.getBlockState(pos);
						DirectionProperty property = (DirectionProperty) _bs.getBlock().getStateContainer().getProperty("facing");
						return _bs.get(property);
					} catch (Exception e) {
						return Direction.NORTH;
					}
				}
			}.getDirection(new BlockPos((int) (x + 1), (int) y, (int) z))) == Direction.EAST)) {
				if (!world.isRemote) {
					BlockPos _bp = new BlockPos((int) (x + 1), (int) y, (int) z);
					TileEntity _tileEntity = world.getTileEntity(_bp);
					BlockState _bs = world.getBlockState(_bp);
					if (_tileEntity != null)
						_tileEntity.getTileData().putBoolean("StarPowered", (true));
					world.notifyBlockUpdate(_bp, _bs, _bs, 3);
				}
			}
			if (((new Object() {
				public Direction getDirection(BlockPos pos) {
					try {
						BlockState _bs = world.getBlockState(pos);
						DirectionProperty property = (DirectionProperty) _bs.getBlock().getStateContainer().getProperty("facing");
						return _bs.get(property);
					} catch (Exception e) {
						return Direction.NORTH;
					}
				}
			}.getDirection(new BlockPos((int) (x - 1), (int) y, (int) z))) == Direction.WEST)) {
				if (!world.isRemote) {
					BlockPos _bp = new BlockPos((int) (x - 1), (int) y, (int) z);
					TileEntity _tileEntity = world.getTileEntity(_bp);
					BlockState _bs = world.getBlockState(_bp);
					if (_tileEntity != null)
						_tileEntity.getTileData().putBoolean("StarPowered", (true));
					world.notifyBlockUpdate(_bp, _bs, _bs, 3);
				}
			}
			if (((new Object() {
				public Direction getDirection(BlockPos pos) {
					try {
						BlockState _bs = world.getBlockState(pos);
						DirectionProperty property = (DirectionProperty) _bs.getBlock().getStateContainer().getProperty("facing");
						return _bs.get(property);
					} catch (Exception e) {
						return Direction.NORTH;
					}
				}
			}.getDirection(new BlockPos((int) x, (int) (y + 1), (int) z))) == Direction.UP)) {
				if (!world.isRemote) {
					BlockPos _bp = new BlockPos((int) x, (int) (y + 1), (int) z);
					TileEntity _tileEntity = world.getTileEntity(_bp);
					BlockState _bs = world.getBlockState(_bp);
					if (_tileEntity != null)
						_tileEntity.getTileData().putBoolean("StarPowered", (true));
					world.notifyBlockUpdate(_bp, _bs, _bs, 3);
				}
			}
			if (((new Object() {
				public Direction getDirection(BlockPos pos) {
					try {
						BlockState _bs = world.getBlockState(pos);
						DirectionProperty property = (DirectionProperty) _bs.getBlock().getStateContainer().getProperty("facing");
						return _bs.get(property);
					} catch (Exception e) {
						return Direction.NORTH;
					}
				}
			}.getDirection(new BlockPos((int) x, (int) (y - 1), (int) z))) == Direction.DOWN)) {
				if (!world.isRemote) {
					BlockPos _bp = new BlockPos((int) x, (int) (y - 1), (int) z);
					TileEntity _tileEntity = world.getTileEntity(_bp);
					BlockState _bs = world.getBlockState(_bp);
					if (_tileEntity != null)
						_tileEntity.getTileData().putBoolean("StarPowered", (true));
					world.notifyBlockUpdate(_bp, _bs, _bs, 3);
				}
			}
			if (((new Object() {
				public Direction getDirection(BlockPos pos) {
					try {
						BlockState _bs = world.getBlockState(pos);
						DirectionProperty property = (DirectionProperty) _bs.getBlock().getStateContainer().getProperty("facing");
						return _bs.get(property);
					} catch (Exception e) {
						return Direction.NORTH;
					}
				}
			}.getDirection(new BlockPos((int) x, (int) y, (int) (z + 1)))) == Direction.SOUTH)) {
				if (!world.isRemote) {
					BlockPos _bp = new BlockPos((int) x, (int) y, (int) (z + 1));
					TileEntity _tileEntity = world.getTileEntity(_bp);
					BlockState _bs = world.getBlockState(_bp);
					if (_tileEntity != null)
						_tileEntity.getTileData().putBoolean("StarPowered", (true));
					world.notifyBlockUpdate(_bp, _bs, _bs, 3);
				}
			}
			if (((new Object() {
				public Direction getDirection(BlockPos pos) {
					try {
						BlockState _bs = world.getBlockState(pos);
						DirectionProperty property = (DirectionProperty) _bs.getBlock().getStateContainer().getProperty("facing");
						return _bs.get(property);
					} catch (Exception e) {
						return Direction.NORTH;
					}
				}
			}.getDirection(new BlockPos((int) x, (int) y, (int) (z - 1)))) == Direction.NORTH)) {
				if (!world.isRemote) {
					BlockPos _bp = new BlockPos((int) x, (int) y, (int) (z - 1));
					TileEntity _tileEntity = world.getTileEntity(_bp);
					BlockState _bs = world.getBlockState(_bp);
					if (_tileEntity != null)
						_tileEntity.getTileData().putBoolean("StarPowered", (true));
					world.notifyBlockUpdate(_bp, _bs, _bs, 3);
				}
			}
		}
	}
}
