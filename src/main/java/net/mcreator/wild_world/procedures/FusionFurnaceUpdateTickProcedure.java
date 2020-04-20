package net.mcreator.wild_world.procedures;

import net.minecraft.world.World;
import net.minecraft.util.math.BlockPos;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.LockableLootTileEntity;
import net.minecraft.item.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.block.BlockState;

import net.mcreator.wild_world.item.TopazItemItem;
import net.mcreator.wild_world.item.StarTopazItem;
import net.mcreator.wild_world.item.StarRubyItem;
import net.mcreator.wild_world.item.StarEmeraldItem;
import net.mcreator.wild_world.item.StarDiamondItem;
import net.mcreator.wild_world.item.RubyItemItem;
import net.mcreator.wild_world.item.BluestoneItemItem;
import net.mcreator.wild_world.WildWorldElements;

@WildWorldElements.ModElement.Tag
public class FusionFurnaceUpdateTickProcedure extends WildWorldElements.ModElement {
	public FusionFurnaceUpdateTickProcedure(WildWorldElements instance) {
		super(instance, 313);
	}

	public static void executeProcedure(java.util.HashMap<String, Object> dependencies) {
		if (dependencies.get("x") == null) {
			System.err.println("Failed to load dependency x for procedure FusionFurnaceUpdateTick!");
			return;
		}
		if (dependencies.get("y") == null) {
			System.err.println("Failed to load dependency y for procedure FusionFurnaceUpdateTick!");
			return;
		}
		if (dependencies.get("z") == null) {
			System.err.println("Failed to load dependency z for procedure FusionFurnaceUpdateTick!");
			return;
		}
		if (dependencies.get("world") == null) {
			System.err.println("Failed to load dependency world for procedure FusionFurnaceUpdateTick!");
			return;
		}
		int x = (int) dependencies.get("x");
		int y = (int) dependencies.get("y");
		int z = (int) dependencies.get("z");
		World world = (World) dependencies.get("world");
		double FusionDust = 0;
		FusionDust = (double) (new Object() {
			public double getValue(BlockPos pos, String tag) {
				TileEntity tileEntity = world.getTileEntity(pos);
				if (tileEntity != null)
					return tileEntity.getTileData().getDouble(tag);
				return -1;
			}
		}.getValue(new BlockPos((int) x, (int) y, (int) z), "FusionDust"));
		if (((new Object() {
			public ItemStack getItemStack(BlockPos pos, int sltid) {
				TileEntity inv = world.getTileEntity(pos);
				if (inv instanceof LockableLootTileEntity)
					return ((LockableLootTileEntity) inv).getStackInSlot(sltid);
				return ItemStack.EMPTY;
			}
		}.getItemStack(new BlockPos((int) x, (int) y, (int) z), (int) (1))).getItem() == new ItemStack(BluestoneItemItem.block, (int) (1))
				.getItem())) {
			{
				TileEntity inv = world.getTileEntity(new BlockPos((int) x, (int) y, (int) z));
				if (inv instanceof LockableLootTileEntity)
					((LockableLootTileEntity) inv).decrStackSize((int) (1), (int) (1));
			}
			if (!world.isRemote) {
				BlockPos _bp = new BlockPos((int) x, (int) y, (int) z);
				TileEntity _tileEntity = world.getTileEntity(_bp);
				BlockState _bs = world.getBlockState(_bp);
				if (_tileEntity != null)
					_tileEntity.getTileData().putDouble("FusionDust", ((FusionDust) + 4));
				world.notifyBlockUpdate(_bp, _bs, _bs, 3);
			}
			FusionDust = (double) (new Object() {
				public double getValue(BlockPos pos, String tag) {
					TileEntity tileEntity = world.getTileEntity(pos);
					if (tileEntity != null)
						return tileEntity.getTileData().getDouble(tag);
					return -1;
				}
			}.getValue(new BlockPos((int) x, (int) y, (int) z), "FusionDust"));
		}
		if ((0 < (new Object() {
			public double getValue(BlockPos pos, String tag) {
				TileEntity tileEntity = world.getTileEntity(pos);
				if (tileEntity != null)
					return tileEntity.getTileData().getDouble(tag);
				return -1;
			}
		}.getValue(new BlockPos((int) x, (int) y, (int) z), "FusionDust")))) {
			if (((new Object() {
				public ItemStack getItemStack(BlockPos pos, int sltid) {
					TileEntity inv = world.getTileEntity(pos);
					if (inv instanceof LockableLootTileEntity)
						return ((LockableLootTileEntity) inv).getStackInSlot(sltid);
					return ItemStack.EMPTY;
				}
			}.getItemStack(new BlockPos((int) x, (int) y, (int) z), (int) (0))).getItem() == new ItemStack(TopazItemItem.block, (int) (1))
					.getItem())) {
				{
					TileEntity inv = world.getTileEntity(new BlockPos((int) x, (int) y, (int) z));
					if (inv instanceof LockableLootTileEntity)
						((LockableLootTileEntity) inv).decrStackSize((int) (0), (int) (1));
				}
				if ((0 == (new Object() {
					public int getAmount(BlockPos pos, int sltid) {
						TileEntity inv = world.getTileEntity(pos);
						if (inv instanceof LockableLootTileEntity) {
							ItemStack stack = ((LockableLootTileEntity) inv).getStackInSlot(sltid);
							if (stack != null)
								return stack.getCount();
						}
						return 0;
					}
				}.getAmount(new BlockPos((int) x, (int) y, (int) z), (int) (2))))) {
					{
						TileEntity inv = world.getTileEntity(new BlockPos((int) x, (int) y, (int) z));
						if (inv != null && (inv instanceof LockableLootTileEntity)) {
							ItemStack _setstack = new ItemStack(StarTopazItem.block, (int) (1));
							_setstack.setCount(((new Object() {
								public int getAmount(BlockPos pos, int sltid) {
									TileEntity inv = world.getTileEntity(pos);
									if (inv instanceof LockableLootTileEntity) {
										ItemStack stack = ((LockableLootTileEntity) inv).getStackInSlot(sltid);
										if (stack != null)
											return stack.getCount();
									}
									return 0;
								}
							}.getAmount(new BlockPos((int) x, (int) y, (int) z), (int) (2))) + 1));
							((LockableLootTileEntity) inv).setInventorySlotContents((int) (2), _setstack);
						}
					}
				} else {
					if ((((new Object() {
						public ItemStack getItemStack(BlockPos pos, int sltid) {
							TileEntity inv = world.getTileEntity(pos);
							if (inv instanceof LockableLootTileEntity)
								return ((LockableLootTileEntity) inv).getStackInSlot(sltid);
							return ItemStack.EMPTY;
						}
					}.getItemStack(new BlockPos((int) x, (int) y, (int) z), (int) (2))).getItem() == new ItemStack(StarTopazItem.block, (int) (1))
							.getItem()) && (64 > (new Object() {
								public int getAmount(BlockPos pos, int sltid) {
									TileEntity inv = world.getTileEntity(pos);
									if (inv instanceof LockableLootTileEntity) {
										ItemStack stack = ((LockableLootTileEntity) inv).getStackInSlot(sltid);
										if (stack != null)
											return stack.getCount();
									}
									return 0;
								}
							}.getAmount(new BlockPos((int) x, (int) y, (int) z), (int) (2)))))) {
						{
							TileEntity inv = world.getTileEntity(new BlockPos((int) x, (int) y, (int) z));
							if (inv != null && (inv instanceof LockableLootTileEntity)) {
								ItemStack _setstack = new ItemStack(StarTopazItem.block, (int) (1));
								_setstack.setCount(((new Object() {
									public int getAmount(BlockPos pos, int sltid) {
										TileEntity inv = world.getTileEntity(pos);
										if (inv instanceof LockableLootTileEntity) {
											ItemStack stack = ((LockableLootTileEntity) inv).getStackInSlot(sltid);
											if (stack != null)
												return stack.getCount();
										}
										return 0;
									}
								}.getAmount(new BlockPos((int) x, (int) y, (int) z), (int) (2))) + 1));
								((LockableLootTileEntity) inv).setInventorySlotContents((int) (2), _setstack);
							}
						}
					} else {
						if (!world.isRemote) {
							ItemEntity entityToSpawn = new ItemEntity(world, (x + 0.5), (y + 0.95), (z + 0.5),
									new ItemStack(StarTopazItem.block, (int) (1)));
							entityToSpawn.setPickupDelay(10);
							world.addEntity(entityToSpawn);
						}
					}
				}
				if (!world.isRemote) {
					BlockPos _bp = new BlockPos((int) x, (int) y, (int) z);
					TileEntity _tileEntity = world.getTileEntity(_bp);
					BlockState _bs = world.getBlockState(_bp);
					if (_tileEntity != null)
						_tileEntity.getTileData().putDouble("FusionDust", ((FusionDust) - 1));
					world.notifyBlockUpdate(_bp, _bs, _bs, 3);
				}
			}
			if (((new Object() {
				public ItemStack getItemStack(BlockPos pos, int sltid) {
					TileEntity inv = world.getTileEntity(pos);
					if (inv instanceof LockableLootTileEntity)
						return ((LockableLootTileEntity) inv).getStackInSlot(sltid);
					return ItemStack.EMPTY;
				}
			}.getItemStack(new BlockPos((int) x, (int) y, (int) z), (int) (0))).getItem() == new ItemStack(RubyItemItem.block, (int) (1))
					.getItem())) {
				{
					TileEntity inv = world.getTileEntity(new BlockPos((int) x, (int) y, (int) z));
					if (inv instanceof LockableLootTileEntity)
						((LockableLootTileEntity) inv).decrStackSize((int) (0), (int) (1));
				}
				if ((0 == (new Object() {
					public int getAmount(BlockPos pos, int sltid) {
						TileEntity inv = world.getTileEntity(pos);
						if (inv instanceof LockableLootTileEntity) {
							ItemStack stack = ((LockableLootTileEntity) inv).getStackInSlot(sltid);
							if (stack != null)
								return stack.getCount();
						}
						return 0;
					}
				}.getAmount(new BlockPos((int) x, (int) y, (int) z), (int) (2))))) {
					{
						TileEntity inv = world.getTileEntity(new BlockPos((int) x, (int) y, (int) z));
						if (inv != null && (inv instanceof LockableLootTileEntity)) {
							ItemStack _setstack = new ItemStack(StarRubyItem.block, (int) (1));
							_setstack.setCount(((new Object() {
								public int getAmount(BlockPos pos, int sltid) {
									TileEntity inv = world.getTileEntity(pos);
									if (inv instanceof LockableLootTileEntity) {
										ItemStack stack = ((LockableLootTileEntity) inv).getStackInSlot(sltid);
										if (stack != null)
											return stack.getCount();
									}
									return 0;
								}
							}.getAmount(new BlockPos((int) x, (int) y, (int) z), (int) (2))) + 1));
							((LockableLootTileEntity) inv).setInventorySlotContents((int) (2), _setstack);
						}
					}
				} else {
					if ((((new Object() {
						public ItemStack getItemStack(BlockPos pos, int sltid) {
							TileEntity inv = world.getTileEntity(pos);
							if (inv instanceof LockableLootTileEntity)
								return ((LockableLootTileEntity) inv).getStackInSlot(sltid);
							return ItemStack.EMPTY;
						}
					}.getItemStack(new BlockPos((int) x, (int) y, (int) z), (int) (2))).getItem() == new ItemStack(StarRubyItem.block, (int) (1))
							.getItem()) && (64 > (new Object() {
								public int getAmount(BlockPos pos, int sltid) {
									TileEntity inv = world.getTileEntity(pos);
									if (inv instanceof LockableLootTileEntity) {
										ItemStack stack = ((LockableLootTileEntity) inv).getStackInSlot(sltid);
										if (stack != null)
											return stack.getCount();
									}
									return 0;
								}
							}.getAmount(new BlockPos((int) x, (int) y, (int) z), (int) (2)))))) {
						{
							TileEntity inv = world.getTileEntity(new BlockPos((int) x, (int) y, (int) z));
							if (inv != null && (inv instanceof LockableLootTileEntity)) {
								ItemStack _setstack = new ItemStack(StarRubyItem.block, (int) (1));
								_setstack.setCount(((new Object() {
									public int getAmount(BlockPos pos, int sltid) {
										TileEntity inv = world.getTileEntity(pos);
										if (inv instanceof LockableLootTileEntity) {
											ItemStack stack = ((LockableLootTileEntity) inv).getStackInSlot(sltid);
											if (stack != null)
												return stack.getCount();
										}
										return 0;
									}
								}.getAmount(new BlockPos((int) x, (int) y, (int) z), (int) (2))) + 1));
								((LockableLootTileEntity) inv).setInventorySlotContents((int) (2), _setstack);
							}
						}
					} else {
						if (!world.isRemote) {
							ItemEntity entityToSpawn = new ItemEntity(world, (x + 0.5), (y + 0.95), (z + 0.5),
									new ItemStack(StarRubyItem.block, (int) (1)));
							entityToSpawn.setPickupDelay(10);
							world.addEntity(entityToSpawn);
						}
					}
				}
				if (!world.isRemote) {
					BlockPos _bp = new BlockPos((int) x, (int) y, (int) z);
					TileEntity _tileEntity = world.getTileEntity(_bp);
					BlockState _bs = world.getBlockState(_bp);
					if (_tileEntity != null)
						_tileEntity.getTileData().putDouble("FusionDust", ((FusionDust) - 1));
					world.notifyBlockUpdate(_bp, _bs, _bs, 3);
				}
			}
			if (((new Object() {
				public ItemStack getItemStack(BlockPos pos, int sltid) {
					TileEntity inv = world.getTileEntity(pos);
					if (inv instanceof LockableLootTileEntity)
						return ((LockableLootTileEntity) inv).getStackInSlot(sltid);
					return ItemStack.EMPTY;
				}
			}.getItemStack(new BlockPos((int) x, (int) y, (int) z), (int) (0))).getItem() == new ItemStack(Items.DIAMOND, (int) (1)).getItem())) {
				{
					TileEntity inv = world.getTileEntity(new BlockPos((int) x, (int) y, (int) z));
					if (inv instanceof LockableLootTileEntity)
						((LockableLootTileEntity) inv).decrStackSize((int) (0), (int) (1));
				}
				if ((0 == (new Object() {
					public int getAmount(BlockPos pos, int sltid) {
						TileEntity inv = world.getTileEntity(pos);
						if (inv instanceof LockableLootTileEntity) {
							ItemStack stack = ((LockableLootTileEntity) inv).getStackInSlot(sltid);
							if (stack != null)
								return stack.getCount();
						}
						return 0;
					}
				}.getAmount(new BlockPos((int) x, (int) y, (int) z), (int) (2))))) {
					{
						TileEntity inv = world.getTileEntity(new BlockPos((int) x, (int) y, (int) z));
						if (inv != null && (inv instanceof LockableLootTileEntity)) {
							ItemStack _setstack = new ItemStack(StarDiamondItem.block, (int) (1));
							_setstack.setCount(((new Object() {
								public int getAmount(BlockPos pos, int sltid) {
									TileEntity inv = world.getTileEntity(pos);
									if (inv instanceof LockableLootTileEntity) {
										ItemStack stack = ((LockableLootTileEntity) inv).getStackInSlot(sltid);
										if (stack != null)
											return stack.getCount();
									}
									return 0;
								}
							}.getAmount(new BlockPos((int) x, (int) y, (int) z), (int) (2))) + 1));
							((LockableLootTileEntity) inv).setInventorySlotContents((int) (2), _setstack);
						}
					}
				} else {
					if ((((new Object() {
						public ItemStack getItemStack(BlockPos pos, int sltid) {
							TileEntity inv = world.getTileEntity(pos);
							if (inv instanceof LockableLootTileEntity)
								return ((LockableLootTileEntity) inv).getStackInSlot(sltid);
							return ItemStack.EMPTY;
						}
					}.getItemStack(new BlockPos((int) x, (int) y, (int) z), (int) (2))).getItem() == new ItemStack(StarDiamondItem.block, (int) (1))
							.getItem()) && (64 > (new Object() {
								public int getAmount(BlockPos pos, int sltid) {
									TileEntity inv = world.getTileEntity(pos);
									if (inv instanceof LockableLootTileEntity) {
										ItemStack stack = ((LockableLootTileEntity) inv).getStackInSlot(sltid);
										if (stack != null)
											return stack.getCount();
									}
									return 0;
								}
							}.getAmount(new BlockPos((int) x, (int) y, (int) z), (int) (2)))))) {
						{
							TileEntity inv = world.getTileEntity(new BlockPos((int) x, (int) y, (int) z));
							if (inv != null && (inv instanceof LockableLootTileEntity)) {
								ItemStack _setstack = new ItemStack(StarDiamondItem.block, (int) (1));
								_setstack.setCount(((new Object() {
									public int getAmount(BlockPos pos, int sltid) {
										TileEntity inv = world.getTileEntity(pos);
										if (inv instanceof LockableLootTileEntity) {
											ItemStack stack = ((LockableLootTileEntity) inv).getStackInSlot(sltid);
											if (stack != null)
												return stack.getCount();
										}
										return 0;
									}
								}.getAmount(new BlockPos((int) x, (int) y, (int) z), (int) (2))) + 1));
								((LockableLootTileEntity) inv).setInventorySlotContents((int) (2), _setstack);
							}
						}
					} else {
						if (!world.isRemote) {
							ItemEntity entityToSpawn = new ItemEntity(world, (x + 0.5), (y + 0.95), (z + 0.5),
									new ItemStack(StarDiamondItem.block, (int) (1)));
							entityToSpawn.setPickupDelay(10);
							world.addEntity(entityToSpawn);
						}
					}
				}
				if (!world.isRemote) {
					BlockPos _bp = new BlockPos((int) x, (int) y, (int) z);
					TileEntity _tileEntity = world.getTileEntity(_bp);
					BlockState _bs = world.getBlockState(_bp);
					if (_tileEntity != null)
						_tileEntity.getTileData().putDouble("FusionDust", ((FusionDust) - 1));
					world.notifyBlockUpdate(_bp, _bs, _bs, 3);
				}
			}
			if (((new Object() {
				public ItemStack getItemStack(BlockPos pos, int sltid) {
					TileEntity inv = world.getTileEntity(pos);
					if (inv instanceof LockableLootTileEntity)
						return ((LockableLootTileEntity) inv).getStackInSlot(sltid);
					return ItemStack.EMPTY;
				}
			}.getItemStack(new BlockPos((int) x, (int) y, (int) z), (int) (0))).getItem() == new ItemStack(Items.EMERALD, (int) (1)).getItem())) {
				{
					TileEntity inv = world.getTileEntity(new BlockPos((int) x, (int) y, (int) z));
					if (inv instanceof LockableLootTileEntity)
						((LockableLootTileEntity) inv).decrStackSize((int) (0), (int) (1));
				}
				if ((0 == (new Object() {
					public int getAmount(BlockPos pos, int sltid) {
						TileEntity inv = world.getTileEntity(pos);
						if (inv instanceof LockableLootTileEntity) {
							ItemStack stack = ((LockableLootTileEntity) inv).getStackInSlot(sltid);
							if (stack != null)
								return stack.getCount();
						}
						return 0;
					}
				}.getAmount(new BlockPos((int) x, (int) y, (int) z), (int) (2))))) {
					{
						TileEntity inv = world.getTileEntity(new BlockPos((int) x, (int) y, (int) z));
						if (inv != null && (inv instanceof LockableLootTileEntity)) {
							ItemStack _setstack = new ItemStack(StarEmeraldItem.block, (int) (1));
							_setstack.setCount(((new Object() {
								public int getAmount(BlockPos pos, int sltid) {
									TileEntity inv = world.getTileEntity(pos);
									if (inv instanceof LockableLootTileEntity) {
										ItemStack stack = ((LockableLootTileEntity) inv).getStackInSlot(sltid);
										if (stack != null)
											return stack.getCount();
									}
									return 0;
								}
							}.getAmount(new BlockPos((int) x, (int) y, (int) z), (int) (2))) + 1));
							((LockableLootTileEntity) inv).setInventorySlotContents((int) (2), _setstack);
						}
					}
				} else {
					if ((((new Object() {
						public ItemStack getItemStack(BlockPos pos, int sltid) {
							TileEntity inv = world.getTileEntity(pos);
							if (inv instanceof LockableLootTileEntity)
								return ((LockableLootTileEntity) inv).getStackInSlot(sltid);
							return ItemStack.EMPTY;
						}
					}.getItemStack(new BlockPos((int) x, (int) y, (int) z), (int) (2))).getItem() == new ItemStack(StarEmeraldItem.block, (int) (1))
							.getItem()) && (64 > (new Object() {
								public int getAmount(BlockPos pos, int sltid) {
									TileEntity inv = world.getTileEntity(pos);
									if (inv instanceof LockableLootTileEntity) {
										ItemStack stack = ((LockableLootTileEntity) inv).getStackInSlot(sltid);
										if (stack != null)
											return stack.getCount();
									}
									return 0;
								}
							}.getAmount(new BlockPos((int) x, (int) y, (int) z), (int) (2)))))) {
						{
							TileEntity inv = world.getTileEntity(new BlockPos((int) x, (int) y, (int) z));
							if (inv != null && (inv instanceof LockableLootTileEntity)) {
								ItemStack _setstack = new ItemStack(StarEmeraldItem.block, (int) (1));
								_setstack.setCount(((new Object() {
									public int getAmount(BlockPos pos, int sltid) {
										TileEntity inv = world.getTileEntity(pos);
										if (inv instanceof LockableLootTileEntity) {
											ItemStack stack = ((LockableLootTileEntity) inv).getStackInSlot(sltid);
											if (stack != null)
												return stack.getCount();
										}
										return 0;
									}
								}.getAmount(new BlockPos((int) x, (int) y, (int) z), (int) (2))) + 1));
								((LockableLootTileEntity) inv).setInventorySlotContents((int) (2), _setstack);
							}
						}
					} else {
						if (!world.isRemote) {
							ItemEntity entityToSpawn = new ItemEntity(world, (x + 0.5), (y + 0.95), (z + 0.5),
									new ItemStack(StarEmeraldItem.block, (int) (1)));
							entityToSpawn.setPickupDelay(10);
							world.addEntity(entityToSpawn);
						}
					}
				}
				if (!world.isRemote) {
					BlockPos _bp = new BlockPos((int) x, (int) y, (int) z);
					TileEntity _tileEntity = world.getTileEntity(_bp);
					BlockState _bs = world.getBlockState(_bp);
					if (_tileEntity != null)
						_tileEntity.getTileData().putDouble("FusionDust", ((FusionDust) - 1));
					world.notifyBlockUpdate(_bp, _bs, _bs, 3);
				}
			}
		}
	}
}
