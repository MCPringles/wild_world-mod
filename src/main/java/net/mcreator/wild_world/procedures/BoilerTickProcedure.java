package net.mcreator.wild_world.procedures;

import net.minecraft.world.World;
import net.minecraft.util.math.BlockPos;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.LockableLootTileEntity;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.item.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.block.BlockState;

import net.mcreator.wild_world.item.SyrupBucketItem;
import net.mcreator.wild_world.item.SyrupBirchBucketItem;
import net.mcreator.wild_world.item.SapItemItem;
import net.mcreator.wild_world.item.BirchSapItemItem;
import net.mcreator.wild_world.WildWorldElements;

@WildWorldElements.ModElement.Tag
public class BoilerTickProcedure extends WildWorldElements.ModElement {
	public BoilerTickProcedure(WildWorldElements instance) {
		super(instance, 180);
	}

	public static void executeProcedure(java.util.HashMap<String, Object> dependencies) {
		if (dependencies.get("x") == null) {
			System.err.println("Failed to load dependency x for procedure BoilerTick!");
			return;
		}
		if (dependencies.get("y") == null) {
			System.err.println("Failed to load dependency y for procedure BoilerTick!");
			return;
		}
		if (dependencies.get("z") == null) {
			System.err.println("Failed to load dependency z for procedure BoilerTick!");
			return;
		}
		if (dependencies.get("world") == null) {
			System.err.println("Failed to load dependency world for procedure BoilerTick!");
			return;
		}
		int x = (int) dependencies.get("x");
		int y = (int) dependencies.get("y");
		int z = (int) dependencies.get("z");
		World world = (World) dependencies.get("world");
		double SapCounter = 0;
		double SapAvailable = 0;
		double BirchSapCounter = 0;
		double BirchSapAvailable = 0;
		boolean Sap = false;
		boolean Bucket = false;
		boolean Success = false;
		boolean BirchSap = false;
		boolean BirchSuccess = false;
		if (((new Object() {
			public boolean getValue(BlockPos pos, String tag) {
				TileEntity tileEntity = world.getTileEntity(pos);
				if (tileEntity != null)
					return tileEntity.getTileData().getBoolean(tag);
				return false;
			}
		}.getValue(new BlockPos((int) x, (int) y, (int) z), "Active")) == (true))) {
			Success = (boolean) (false);
			if (((new Object() {
				public boolean getValue(BlockPos pos, String tag) {
					TileEntity tileEntity = world.getTileEntity(pos);
					if (tileEntity != null)
						return tileEntity.getTileData().getBoolean(tag);
					return false;
				}
			}.getValue(new BlockPos((int) x, (int) y, (int) z), "Active")) == (true))) {
				if ((!((new Object() {
					public ItemStack getItemStack(BlockPos pos, int sltid) {
						TileEntity inv = world.getTileEntity(pos);
						if (inv instanceof LockableLootTileEntity)
							return ((LockableLootTileEntity) inv).getStackInSlot(sltid);
						return ItemStack.EMPTY;
					}
				}.getItemStack(new BlockPos((int) x, (int) y, (int) z), (int) (0))).getItem() == new ItemStack(SapItemItem.block, (int) (1))
						.getItem()))) {
					if ((!((new Object() {
						public ItemStack getItemStack(BlockPos pos, int sltid) {
							TileEntity inv = world.getTileEntity(pos);
							if (inv instanceof LockableLootTileEntity)
								return ((LockableLootTileEntity) inv).getStackInSlot(sltid);
							return ItemStack.EMPTY;
						}
					}.getItemStack(new BlockPos((int) x, (int) y, (int) z), (int) (1))).getItem() == new ItemStack(SapItemItem.block, (int) (1))
							.getItem()))) {
						if ((!((new Object() {
							public ItemStack getItemStack(BlockPos pos, int sltid) {
								TileEntity inv = world.getTileEntity(pos);
								if (inv instanceof LockableLootTileEntity)
									return ((LockableLootTileEntity) inv).getStackInSlot(sltid);
								return ItemStack.EMPTY;
							}
						}.getItemStack(new BlockPos((int) x, (int) y, (int) z), (int) (2))).getItem() == new ItemStack(SapItemItem.block, (int) (1))
								.getItem()))) {
							if (!world.isRemote) {
								BlockPos _bp = new BlockPos((int) x, (int) y, (int) z);
								TileEntity _tileEntity = world.getTileEntity(_bp);
								BlockState _bs = world.getBlockState(_bp);
								if (_tileEntity != null)
									_tileEntity.getTileData().putBoolean("HasSap", (false));
								world.notifyBlockUpdate(_bp, _bs, _bs, 3);
							}
						}
					}
				}
				if ((!((new Object() {
					public ItemStack getItemStack(BlockPos pos, int sltid) {
						TileEntity inv = world.getTileEntity(pos);
						if (inv instanceof LockableLootTileEntity)
							return ((LockableLootTileEntity) inv).getStackInSlot(sltid);
						return ItemStack.EMPTY;
					}
				}.getItemStack(new BlockPos((int) x, (int) y, (int) z), (int) (0))).getItem() == new ItemStack(Items.BUCKET, (int) (1)).getItem()))) {
					if ((!((new Object() {
						public ItemStack getItemStack(BlockPos pos, int sltid) {
							TileEntity inv = world.getTileEntity(pos);
							if (inv instanceof LockableLootTileEntity)
								return ((LockableLootTileEntity) inv).getStackInSlot(sltid);
							return ItemStack.EMPTY;
						}
					}.getItemStack(new BlockPos((int) x, (int) y, (int) z), (int) (1))).getItem() == new ItemStack(Items.BUCKET, (int) (1))
							.getItem()))) {
						if ((!((new Object() {
							public ItemStack getItemStack(BlockPos pos, int sltid) {
								TileEntity inv = world.getTileEntity(pos);
								if (inv instanceof LockableLootTileEntity)
									return ((LockableLootTileEntity) inv).getStackInSlot(sltid);
								return ItemStack.EMPTY;
							}
						}.getItemStack(new BlockPos((int) x, (int) y, (int) z), (int) (2))).getItem() == new ItemStack(Items.BUCKET, (int) (1))
								.getItem()))) {
							if (!world.isRemote) {
								BlockPos _bp = new BlockPos((int) x, (int) y, (int) z);
								TileEntity _tileEntity = world.getTileEntity(_bp);
								BlockState _bs = world.getBlockState(_bp);
								if (_tileEntity != null)
									_tileEntity.getTileData().putBoolean("HasBucket", (false));
								world.notifyBlockUpdate(_bp, _bs, _bs, 3);
							}
						}
					}
				}
				if ((!((new Object() {
					public ItemStack getItemStack(BlockPos pos, int sltid) {
						TileEntity inv = world.getTileEntity(pos);
						if (inv instanceof LockableLootTileEntity)
							return ((LockableLootTileEntity) inv).getStackInSlot(sltid);
						return ItemStack.EMPTY;
					}
				}.getItemStack(new BlockPos((int) x, (int) y, (int) z), (int) (0))).getItem() == new ItemStack(BirchSapItemItem.block, (int) (1))
						.getItem()))) {
					if ((!((new Object() {
						public ItemStack getItemStack(BlockPos pos, int sltid) {
							TileEntity inv = world.getTileEntity(pos);
							if (inv instanceof LockableLootTileEntity)
								return ((LockableLootTileEntity) inv).getStackInSlot(sltid);
							return ItemStack.EMPTY;
						}
					}.getItemStack(new BlockPos((int) x, (int) y, (int) z), (int) (1))).getItem() == new ItemStack(BirchSapItemItem.block, (int) (1))
							.getItem()))) {
						if ((!((new Object() {
							public ItemStack getItemStack(BlockPos pos, int sltid) {
								TileEntity inv = world.getTileEntity(pos);
								if (inv instanceof LockableLootTileEntity)
									return ((LockableLootTileEntity) inv).getStackInSlot(sltid);
								return ItemStack.EMPTY;
							}
						}.getItemStack(new BlockPos((int) x, (int) y, (int) z), (int) (2)))
								.getItem() == new ItemStack(BirchSapItemItem.block, (int) (1)).getItem()))) {
							if (!world.isRemote) {
								BlockPos _bp = new BlockPos((int) x, (int) y, (int) z);
								TileEntity _tileEntity = world.getTileEntity(_bp);
								BlockState _bs = world.getBlockState(_bp);
								if (_tileEntity != null)
									_tileEntity.getTileData().putBoolean("HasBirchSap", (false));
								world.notifyBlockUpdate(_bp, _bs, _bs, 3);
							}
						}
					}
				}
				if (((new Object() {
					public boolean getValue(BlockPos pos, String tag) {
						TileEntity tileEntity = world.getTileEntity(pos);
						if (tileEntity != null)
							return tileEntity.getTileData().getBoolean(tag);
						return false;
					}
				}.getValue(new BlockPos((int) x, (int) y, (int) z), "HasSap")) == (false))) {
					if (!world.isRemote) {
						BlockPos _bp = new BlockPos((int) x, (int) y, (int) z);
						TileEntity _tileEntity = world.getTileEntity(_bp);
						BlockState _bs = world.getBlockState(_bp);
						if (_tileEntity != null)
							_tileEntity.getTileData().putBoolean("Active", (false));
						world.notifyBlockUpdate(_bp, _bs, _bs, 3);
					}
				} else {
					if (((new Object() {
						public boolean getValue(BlockPos pos, String tag) {
							TileEntity tileEntity = world.getTileEntity(pos);
							if (tileEntity != null)
								return tileEntity.getTileData().getBoolean(tag);
							return false;
						}
					}.getValue(new BlockPos((int) x, (int) y, (int) z), "HasBucket")) == (false))) {
						if (!world.isRemote) {
							BlockPos _bp = new BlockPos((int) x, (int) y, (int) z);
							TileEntity _tileEntity = world.getTileEntity(_bp);
							BlockState _bs = world.getBlockState(_bp);
							if (_tileEntity != null)
								_tileEntity.getTileData().putBoolean("Active", (false));
							world.notifyBlockUpdate(_bp, _bs, _bs, 3);
						}
					} else {
						if (!world.isRemote) {
							BlockPos _bp = new BlockPos((int) x, (int) y, (int) z);
							TileEntity _tileEntity = world.getTileEntity(_bp);
							BlockState _bs = world.getBlockState(_bp);
							if (_tileEntity != null)
								_tileEntity.getTileData().putBoolean("Active", (true));
							world.notifyBlockUpdate(_bp, _bs, _bs, 3);
						}
					}
				}
				if (((new Object() {
					public boolean getValue(BlockPos pos, String tag) {
						TileEntity tileEntity = world.getTileEntity(pos);
						if (tileEntity != null)
							return tileEntity.getTileData().getBoolean(tag);
						return false;
					}
				}.getValue(new BlockPos((int) x, (int) y, (int) z), "HasBirchSap")) == (false))) {
					if (!world.isRemote) {
						BlockPos _bp = new BlockPos((int) x, (int) y, (int) z);
						TileEntity _tileEntity = world.getTileEntity(_bp);
						BlockState _bs = world.getBlockState(_bp);
						if (_tileEntity != null)
							_tileEntity.getTileData().putBoolean("Active", (false));
						world.notifyBlockUpdate(_bp, _bs, _bs, 3);
					}
				} else {
					if (((new Object() {
						public boolean getValue(BlockPos pos, String tag) {
							TileEntity tileEntity = world.getTileEntity(pos);
							if (tileEntity != null)
								return tileEntity.getTileData().getBoolean(tag);
							return false;
						}
					}.getValue(new BlockPos((int) x, (int) y, (int) z), "HasBucket")) == (false))) {
						if (!world.isRemote) {
							BlockPos _bp = new BlockPos((int) x, (int) y, (int) z);
							TileEntity _tileEntity = world.getTileEntity(_bp);
							BlockState _bs = world.getBlockState(_bp);
							if (_tileEntity != null)
								_tileEntity.getTileData().putBoolean("Active", (false));
							world.notifyBlockUpdate(_bp, _bs, _bs, 3);
						}
					} else {
						if (!world.isRemote) {
							BlockPos _bp = new BlockPos((int) x, (int) y, (int) z);
							TileEntity _tileEntity = world.getTileEntity(_bp);
							BlockState _bs = world.getBlockState(_bp);
							if (_tileEntity != null)
								_tileEntity.getTileData().putBoolean("Active", (true));
							world.notifyBlockUpdate(_bp, _bs, _bs, 3);
						}
					}
				}
			}
			if (((new Object() {
				public boolean getValue(BlockPos pos, String tag) {
					TileEntity tileEntity = world.getTileEntity(pos);
					if (tileEntity != null)
						return tileEntity.getTileData().getBoolean(tag);
					return false;
				}
			}.getValue(new BlockPos((int) x, (int) y, (int) z), "Sap")) == (true))) {
				Sap = (boolean) (true);
			}
			if (((new Object() {
				public boolean getValue(BlockPos pos, String tag) {
					TileEntity tileEntity = world.getTileEntity(pos);
					if (tileEntity != null)
						return tileEntity.getTileData().getBoolean(tag);
					return false;
				}
			}.getValue(new BlockPos((int) x, (int) y, (int) z), "Sap")) == (false))) {
				Sap = (boolean) (false);
			}
			if (((new Object() {
				public boolean getValue(BlockPos pos, String tag) {
					TileEntity tileEntity = world.getTileEntity(pos);
					if (tileEntity != null)
						return tileEntity.getTileData().getBoolean(tag);
					return false;
				}
			}.getValue(new BlockPos((int) x, (int) y, (int) z), "BirchSap")) == (true))) {
				BirchSap = (boolean) (true);
			}
			if (((new Object() {
				public boolean getValue(BlockPos pos, String tag) {
					TileEntity tileEntity = world.getTileEntity(pos);
					if (tileEntity != null)
						return tileEntity.getTileData().getBoolean(tag);
					return false;
				}
			}.getValue(new BlockPos((int) x, (int) y, (int) z), "BirchSap")) == (false))) {
				BirchSap = (boolean) (false);
			}
			if (((Sap) == (true))) {
				if (((new Object() {
					public ItemStack getItemStack(BlockPos pos, int sltid) {
						TileEntity inv = world.getTileEntity(pos);
						if (inv instanceof LockableLootTileEntity)
							return ((LockableLootTileEntity) inv).getStackInSlot(sltid);
						return ItemStack.EMPTY;
					}
				}.getItemStack(new BlockPos((int) x, (int) y, (int) z), (int) (0))).getItem() == new ItemStack(SapItemItem.block, (int) (1))
						.getItem())) {
					SapCounter = (double) ((new Object() {
						public double getValue(BlockPos pos, String tag) {
							TileEntity tileEntity = world.getTileEntity(pos);
							if (tileEntity != null)
								return tileEntity.getTileData().getDouble(tag);
							return -1;
						}
					}.getValue(new BlockPos((int) x, (int) y, (int) z), "SapCounter")) + 1);
					{
						TileEntity inv = world.getTileEntity(new BlockPos((int) x, (int) y, (int) z));
						if (inv instanceof LockableLootTileEntity)
							((LockableLootTileEntity) inv).decrStackSize((int) (0), (int) (1));
					}
					for (int _ct = 0; _ct < 3; _ct++) {
						world.addOptionalParticle(ParticleTypes.EXPLOSION, (x + 0.5), (y + 1.25), (z + 0.5), 0.05, 0.5, 0.05);
					}
					Success = (boolean) (true);
					if (!world.isRemote) {
						BlockPos _bp = new BlockPos((int) x, (int) y, (int) z);
						TileEntity _tileEntity = world.getTileEntity(_bp);
						BlockState _bs = world.getBlockState(_bp);
						if (_tileEntity != null)
							_tileEntity.getTileData().putDouble("SapCounter", (SapCounter));
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
				}.getItemStack(new BlockPos((int) x, (int) y, (int) z), (int) (1))).getItem() == new ItemStack(SapItemItem.block, (int) (1))
						.getItem())) {
					SapCounter = (double) ((new Object() {
						public double getValue(BlockPos pos, String tag) {
							TileEntity tileEntity = world.getTileEntity(pos);
							if (tileEntity != null)
								return tileEntity.getTileData().getDouble(tag);
							return -1;
						}
					}.getValue(new BlockPos((int) x, (int) y, (int) z), "SapCounter")) + 1);
					{
						TileEntity inv = world.getTileEntity(new BlockPos((int) x, (int) y, (int) z));
						if (inv instanceof LockableLootTileEntity)
							((LockableLootTileEntity) inv).decrStackSize((int) (1), (int) (1));
					}
					for (int _ct = 0; _ct < 3; _ct++) {
						world.addOptionalParticle(ParticleTypes.EXPLOSION, (x + 0.5), (y + 1.25), (z + 0.5), 0.05, 0.5, 0.05);
					}
					Success = (boolean) (true);
					if (!world.isRemote) {
						BlockPos _bp = new BlockPos((int) x, (int) y, (int) z);
						TileEntity _tileEntity = world.getTileEntity(_bp);
						BlockState _bs = world.getBlockState(_bp);
						if (_tileEntity != null)
							_tileEntity.getTileData().putDouble("SapCounter", (SapCounter));
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
				}.getItemStack(new BlockPos((int) x, (int) y, (int) z), (int) (2))).getItem() == new ItemStack(SapItemItem.block, (int) (1))
						.getItem())) {
					SapCounter = (double) ((new Object() {
						public double getValue(BlockPos pos, String tag) {
							TileEntity tileEntity = world.getTileEntity(pos);
							if (tileEntity != null)
								return tileEntity.getTileData().getDouble(tag);
							return -1;
						}
					}.getValue(new BlockPos((int) x, (int) y, (int) z), "SapCounter")) + 1);
					{
						TileEntity inv = world.getTileEntity(new BlockPos((int) x, (int) y, (int) z));
						if (inv instanceof LockableLootTileEntity)
							((LockableLootTileEntity) inv).decrStackSize((int) (2), (int) (1));
					}
					for (int _ct = 0; _ct < 3; _ct++) {
						world.addOptionalParticle(ParticleTypes.EXPLOSION, (x + 0.5), (y + 1.25), (z + 0.5), 0.05, 0.5, 0.05);
					}
					Success = (boolean) (true);
					if (!world.isRemote) {
						BlockPos _bp = new BlockPos((int) x, (int) y, (int) z);
						TileEntity _tileEntity = world.getTileEntity(_bp);
						BlockState _bs = world.getBlockState(_bp);
						if (_tileEntity != null)
							_tileEntity.getTileData().putDouble("SapCounter", (SapCounter));
						world.notifyBlockUpdate(_bp, _bs, _bs, 3);
					}
				}
			}
			if (((BirchSap) == (true))) {
				if (((new Object() {
					public ItemStack getItemStack(BlockPos pos, int sltid) {
						TileEntity inv = world.getTileEntity(pos);
						if (inv instanceof LockableLootTileEntity)
							return ((LockableLootTileEntity) inv).getStackInSlot(sltid);
						return ItemStack.EMPTY;
					}
				}.getItemStack(new BlockPos((int) x, (int) y, (int) z), (int) (0))).getItem() == new ItemStack(BirchSapItemItem.block, (int) (1))
						.getItem())) {
					BirchSapCounter = (double) ((new Object() {
						public double getValue(BlockPos pos, String tag) {
							TileEntity tileEntity = world.getTileEntity(pos);
							if (tileEntity != null)
								return tileEntity.getTileData().getDouble(tag);
							return -1;
						}
					}.getValue(new BlockPos((int) x, (int) y, (int) z), "BirchSapCounter")) + 1);
					{
						TileEntity inv = world.getTileEntity(new BlockPos((int) x, (int) y, (int) z));
						if (inv instanceof LockableLootTileEntity)
							((LockableLootTileEntity) inv).decrStackSize((int) (0), (int) (1));
					}
					for (int _ct = 0; _ct < 3; _ct++) {
						world.addOptionalParticle(ParticleTypes.EXPLOSION, (x + 0.5), (y + 1.25), (z + 0.5), 0.05, 0.5, 0.05);
					}
					BirchSuccess = (boolean) (true);
					if (!world.isRemote) {
						BlockPos _bp = new BlockPos((int) x, (int) y, (int) z);
						TileEntity _tileEntity = world.getTileEntity(_bp);
						BlockState _bs = world.getBlockState(_bp);
						if (_tileEntity != null)
							_tileEntity.getTileData().putDouble("BirchSapCounter", (BirchSapCounter));
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
				}.getItemStack(new BlockPos((int) x, (int) y, (int) z), (int) (1))).getItem() == new ItemStack(BirchSapItemItem.block, (int) (1))
						.getItem())) {
					BirchSapCounter = (double) ((new Object() {
						public double getValue(BlockPos pos, String tag) {
							TileEntity tileEntity = world.getTileEntity(pos);
							if (tileEntity != null)
								return tileEntity.getTileData().getDouble(tag);
							return -1;
						}
					}.getValue(new BlockPos((int) x, (int) y, (int) z), "BirchSapCounter")) + 1);
					{
						TileEntity inv = world.getTileEntity(new BlockPos((int) x, (int) y, (int) z));
						if (inv instanceof LockableLootTileEntity)
							((LockableLootTileEntity) inv).decrStackSize((int) (1), (int) (1));
					}
					for (int _ct = 0; _ct < 3; _ct++) {
						world.addOptionalParticle(ParticleTypes.EXPLOSION, (x + 0.5), (y + 1.25), (z + 0.5), 0.05, 0.5, 0.05);
					}
					BirchSuccess = (boolean) (true);
					if (!world.isRemote) {
						BlockPos _bp = new BlockPos((int) x, (int) y, (int) z);
						TileEntity _tileEntity = world.getTileEntity(_bp);
						BlockState _bs = world.getBlockState(_bp);
						if (_tileEntity != null)
							_tileEntity.getTileData().putDouble("BirchSapCounter", (BirchSapCounter));
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
				}.getItemStack(new BlockPos((int) x, (int) y, (int) z), (int) (2))).getItem() == new ItemStack(BirchSapItemItem.block, (int) (1))
						.getItem())) {
					BirchSapCounter = (double) ((new Object() {
						public double getValue(BlockPos pos, String tag) {
							TileEntity tileEntity = world.getTileEntity(pos);
							if (tileEntity != null)
								return tileEntity.getTileData().getDouble(tag);
							return -1;
						}
					}.getValue(new BlockPos((int) x, (int) y, (int) z), "BirchSapCounter")) + 1);
					{
						TileEntity inv = world.getTileEntity(new BlockPos((int) x, (int) y, (int) z));
						if (inv instanceof LockableLootTileEntity)
							((LockableLootTileEntity) inv).decrStackSize((int) (2), (int) (1));
					}
					for (int _ct = 0; _ct < 3; _ct++) {
						world.addOptionalParticle(ParticleTypes.EXPLOSION, (x + 0.5), (y + 1.25), (z + 0.5), 0.05, 0.5, 0.05);
					}
					BirchSuccess = (boolean) (true);
					if (!world.isRemote) {
						BlockPos _bp = new BlockPos((int) x, (int) y, (int) z);
						TileEntity _tileEntity = world.getTileEntity(_bp);
						BlockState _bs = world.getBlockState(_bp);
						if (_tileEntity != null)
							_tileEntity.getTileData().putDouble("BirchSapCounter", (BirchSapCounter));
						world.notifyBlockUpdate(_bp, _bs, _bs, 3);
					}
				}
			}
			if (((SapCounter) >= 16)) {
				if (!world.isRemote) {
					BlockPos _bp = new BlockPos((int) x, (int) y, (int) z);
					TileEntity _tileEntity = world.getTileEntity(_bp);
					BlockState _bs = world.getBlockState(_bp);
					if (_tileEntity != null)
						_tileEntity.getTileData().putDouble("SapAvailable", ((new Object() {
							public double getValue(BlockPos pos, String tag) {
								TileEntity tileEntity = world.getTileEntity(pos);
								if (tileEntity != null)
									return tileEntity.getTileData().getDouble(tag);
								return -1;
							}
						}.getValue(new BlockPos((int) x, (int) y, (int) z), "SapAvailable")) + 1));
					world.notifyBlockUpdate(_bp, _bs, _bs, 3);
				}
				SapCounter = (double) ((new Object() {
					public double getValue(BlockPos pos, String tag) {
						TileEntity tileEntity = world.getTileEntity(pos);
						if (tileEntity != null)
							return tileEntity.getTileData().getDouble(tag);
						return -1;
					}
				}.getValue(new BlockPos((int) x, (int) y, (int) z), "SapCounter")) - 16);
				if (!world.isRemote) {
					BlockPos _bp = new BlockPos((int) x, (int) y, (int) z);
					TileEntity _tileEntity = world.getTileEntity(_bp);
					BlockState _bs = world.getBlockState(_bp);
					if (_tileEntity != null)
						_tileEntity.getTileData().putDouble("SapCounter", (SapCounter));
					world.notifyBlockUpdate(_bp, _bs, _bs, 3);
				}
			}
			if (((BirchSapCounter) >= 32)) {
				if (!world.isRemote) {
					BlockPos _bp = new BlockPos((int) x, (int) y, (int) z);
					TileEntity _tileEntity = world.getTileEntity(_bp);
					BlockState _bs = world.getBlockState(_bp);
					if (_tileEntity != null)
						_tileEntity.getTileData().putDouble("BirchSapAvailable", ((new Object() {
							public double getValue(BlockPos pos, String tag) {
								TileEntity tileEntity = world.getTileEntity(pos);
								if (tileEntity != null)
									return tileEntity.getTileData().getDouble(tag);
								return -1;
							}
						}.getValue(new BlockPos((int) x, (int) y, (int) z), "BirchSapAvailable")) + 1));
					world.notifyBlockUpdate(_bp, _bs, _bs, 3);
				}
				BirchSapCounter = (double) ((new Object() {
					public double getValue(BlockPos pos, String tag) {
						TileEntity tileEntity = world.getTileEntity(pos);
						if (tileEntity != null)
							return tileEntity.getTileData().getDouble(tag);
						return -1;
					}
				}.getValue(new BlockPos((int) x, (int) y, (int) z), "BirchSapCounter")) - 32);
				if (!world.isRemote) {
					BlockPos _bp = new BlockPos((int) x, (int) y, (int) z);
					TileEntity _tileEntity = world.getTileEntity(_bp);
					BlockState _bs = world.getBlockState(_bp);
					if (_tileEntity != null)
						_tileEntity.getTileData().putDouble("BirchSapCounter", (BirchSapCounter));
					world.notifyBlockUpdate(_bp, _bs, _bs, 3);
				}
			}
			if (((new Object() {
				public double getValue(BlockPos pos, String tag) {
					TileEntity tileEntity = world.getTileEntity(pos);
					if (tileEntity != null)
						return tileEntity.getTileData().getDouble(tag);
					return -1;
				}
			}.getValue(new BlockPos((int) x, (int) y, (int) z), "SapAvailable")) >= 1)) {
				if (((new Object() {
					public ItemStack getItemStack(BlockPos pos, int sltid) {
						TileEntity inv = world.getTileEntity(pos);
						if (inv instanceof LockableLootTileEntity)
							return ((LockableLootTileEntity) inv).getStackInSlot(sltid);
						return ItemStack.EMPTY;
					}
				}.getItemStack(new BlockPos((int) x, (int) y, (int) z), (int) (0))).getItem() == new ItemStack(Items.BUCKET, (int) (1)).getItem())) {
					{
						TileEntity inv = world.getTileEntity(new BlockPos((int) x, (int) y, (int) z));
						if (inv instanceof LockableLootTileEntity)
							((LockableLootTileEntity) inv).decrStackSize((int) (0), (int) (1));
					}
					Bucket = (boolean) (true);
				} else {
					if (((new Object() {
						public ItemStack getItemStack(BlockPos pos, int sltid) {
							TileEntity inv = world.getTileEntity(pos);
							if (inv instanceof LockableLootTileEntity)
								return ((LockableLootTileEntity) inv).getStackInSlot(sltid);
							return ItemStack.EMPTY;
						}
					}.getItemStack(new BlockPos((int) x, (int) y, (int) z), (int) (1))).getItem() == new ItemStack(Items.BUCKET, (int) (1))
							.getItem())) {
						{
							TileEntity inv = world.getTileEntity(new BlockPos((int) x, (int) y, (int) z));
							if (inv instanceof LockableLootTileEntity)
								((LockableLootTileEntity) inv).decrStackSize((int) (1), (int) (1));
						}
						Bucket = (boolean) (true);
					} else {
						if (((new Object() {
							public ItemStack getItemStack(BlockPos pos, int sltid) {
								TileEntity inv = world.getTileEntity(pos);
								if (inv instanceof LockableLootTileEntity)
									return ((LockableLootTileEntity) inv).getStackInSlot(sltid);
								return ItemStack.EMPTY;
							}
						}.getItemStack(new BlockPos((int) x, (int) y, (int) z), (int) (2))).getItem() == new ItemStack(Items.BUCKET, (int) (1))
								.getItem())) {
							{
								TileEntity inv = world.getTileEntity(new BlockPos((int) x, (int) y, (int) z));
								if (inv instanceof LockableLootTileEntity)
									((LockableLootTileEntity) inv).decrStackSize((int) (2), (int) (1));
							}
							Bucket = (boolean) (true);
						} else {
							Bucket = (boolean) (false);
						}
					}
				}
				if (((Bucket) == (true))) {
					if (((new Object() {
						public int getAmount(BlockPos pos, int sltid) {
							TileEntity inv = world.getTileEntity(pos);
							if (inv instanceof LockableLootTileEntity) {
								ItemStack stack = ((LockableLootTileEntity) inv).getStackInSlot(sltid);
								if (stack != null)
									return stack.getCount();
							}
							return 0;
						}
					}.getAmount(new BlockPos((int) x, (int) y, (int) z), (int) (3))) == 0)) {
						{
							TileEntity inv = world.getTileEntity(new BlockPos((int) x, (int) y, (int) z));
							if (inv != null && (inv instanceof LockableLootTileEntity)) {
								ItemStack _setstack = new ItemStack(SyrupBucketItem.block, (int) (1));
								_setstack.setCount(1);
								((LockableLootTileEntity) inv).setInventorySlotContents((int) (3), _setstack);
							}
						}
					} else {
						if (((new Object() {
							public int getAmount(BlockPos pos, int sltid) {
								TileEntity inv = world.getTileEntity(pos);
								if (inv instanceof LockableLootTileEntity) {
									ItemStack stack = ((LockableLootTileEntity) inv).getStackInSlot(sltid);
									if (stack != null)
										return stack.getCount();
								}
								return 0;
							}
						}.getAmount(new BlockPos((int) x, (int) y, (int) z), (int) (4))) == 0)) {
							{
								TileEntity inv = world.getTileEntity(new BlockPos((int) x, (int) y, (int) z));
								if (inv != null && (inv instanceof LockableLootTileEntity)) {
									ItemStack _setstack = new ItemStack(SyrupBucketItem.block, (int) (1));
									_setstack.setCount(1);
									((LockableLootTileEntity) inv).setInventorySlotContents((int) (4), _setstack);
								}
							}
						} else {
							if (((new Object() {
								public int getAmount(BlockPos pos, int sltid) {
									TileEntity inv = world.getTileEntity(pos);
									if (inv instanceof LockableLootTileEntity) {
										ItemStack stack = ((LockableLootTileEntity) inv).getStackInSlot(sltid);
										if (stack != null)
											return stack.getCount();
									}
									return 0;
								}
							}.getAmount(new BlockPos((int) x, (int) y, (int) z), (int) (5))) == 0)) {
								{
									TileEntity inv = world.getTileEntity(new BlockPos((int) x, (int) y, (int) z));
									if (inv != null && (inv instanceof LockableLootTileEntity)) {
										ItemStack _setstack = new ItemStack(SyrupBucketItem.block, (int) (1));
										_setstack.setCount(1);
										((LockableLootTileEntity) inv).setInventorySlotContents((int) (5), _setstack);
									}
								}
							} else {
								if (!world.isRemote) {
									ItemEntity entityToSpawn = new ItemEntity(world, (x + 0.5), (y + 1), (z + 0.5),
											new ItemStack(SyrupBucketItem.block, (int) (1)));
									entityToSpawn.setPickupDelay(10);
									world.addEntity(entityToSpawn);
								}
							}
						}
					}
					SapAvailable = (double) ((new Object() {
						public double getValue(BlockPos pos, String tag) {
							TileEntity tileEntity = world.getTileEntity(pos);
							if (tileEntity != null)
								return tileEntity.getTileData().getDouble(tag);
							return -1;
						}
					}.getValue(new BlockPos((int) x, (int) y, (int) z), "SapAvailable")) - 1);
					if (!world.isRemote) {
						BlockPos _bp = new BlockPos((int) x, (int) y, (int) z);
						TileEntity _tileEntity = world.getTileEntity(_bp);
						BlockState _bs = world.getBlockState(_bp);
						if (_tileEntity != null)
							_tileEntity.getTileData().putDouble("SapAvailable", (SapAvailable));
						world.notifyBlockUpdate(_bp, _bs, _bs, 3);
					}
				}
			}
			if (((new Object() {
				public double getValue(BlockPos pos, String tag) {
					TileEntity tileEntity = world.getTileEntity(pos);
					if (tileEntity != null)
						return tileEntity.getTileData().getDouble(tag);
					return -1;
				}
			}.getValue(new BlockPos((int) x, (int) y, (int) z), "BirchSapAvailable")) >= 1)) {
				if (((new Object() {
					public ItemStack getItemStack(BlockPos pos, int sltid) {
						TileEntity inv = world.getTileEntity(pos);
						if (inv instanceof LockableLootTileEntity)
							return ((LockableLootTileEntity) inv).getStackInSlot(sltid);
						return ItemStack.EMPTY;
					}
				}.getItemStack(new BlockPos((int) x, (int) y, (int) z), (int) (0))).getItem() == new ItemStack(Items.BUCKET, (int) (1)).getItem())) {
					{
						TileEntity inv = world.getTileEntity(new BlockPos((int) x, (int) y, (int) z));
						if (inv instanceof LockableLootTileEntity)
							((LockableLootTileEntity) inv).decrStackSize((int) (0), (int) (1));
					}
					Bucket = (boolean) (true);
				} else {
					if (((new Object() {
						public ItemStack getItemStack(BlockPos pos, int sltid) {
							TileEntity inv = world.getTileEntity(pos);
							if (inv instanceof LockableLootTileEntity)
								return ((LockableLootTileEntity) inv).getStackInSlot(sltid);
							return ItemStack.EMPTY;
						}
					}.getItemStack(new BlockPos((int) x, (int) y, (int) z), (int) (1))).getItem() == new ItemStack(Items.BUCKET, (int) (1))
							.getItem())) {
						{
							TileEntity inv = world.getTileEntity(new BlockPos((int) x, (int) y, (int) z));
							if (inv instanceof LockableLootTileEntity)
								((LockableLootTileEntity) inv).decrStackSize((int) (1), (int) (1));
						}
						Bucket = (boolean) (true);
					} else {
						if (((new Object() {
							public ItemStack getItemStack(BlockPos pos, int sltid) {
								TileEntity inv = world.getTileEntity(pos);
								if (inv instanceof LockableLootTileEntity)
									return ((LockableLootTileEntity) inv).getStackInSlot(sltid);
								return ItemStack.EMPTY;
							}
						}.getItemStack(new BlockPos((int) x, (int) y, (int) z), (int) (2))).getItem() == new ItemStack(Items.BUCKET, (int) (1))
								.getItem())) {
							{
								TileEntity inv = world.getTileEntity(new BlockPos((int) x, (int) y, (int) z));
								if (inv instanceof LockableLootTileEntity)
									((LockableLootTileEntity) inv).decrStackSize((int) (2), (int) (1));
							}
							Bucket = (boolean) (true);
						} else {
							Bucket = (boolean) (false);
						}
					}
				}
				if (((Bucket) == (true))) {
					if (((new Object() {
						public int getAmount(BlockPos pos, int sltid) {
							TileEntity inv = world.getTileEntity(pos);
							if (inv instanceof LockableLootTileEntity) {
								ItemStack stack = ((LockableLootTileEntity) inv).getStackInSlot(sltid);
								if (stack != null)
									return stack.getCount();
							}
							return 0;
						}
					}.getAmount(new BlockPos((int) x, (int) y, (int) z), (int) (3))) == 0)) {
						{
							TileEntity inv = world.getTileEntity(new BlockPos((int) x, (int) y, (int) z));
							if (inv != null && (inv instanceof LockableLootTileEntity)) {
								ItemStack _setstack = new ItemStack(SyrupBirchBucketItem.block, (int) (1));
								_setstack.setCount(1);
								((LockableLootTileEntity) inv).setInventorySlotContents((int) (3), _setstack);
							}
						}
					} else {
						if (((new Object() {
							public int getAmount(BlockPos pos, int sltid) {
								TileEntity inv = world.getTileEntity(pos);
								if (inv instanceof LockableLootTileEntity) {
									ItemStack stack = ((LockableLootTileEntity) inv).getStackInSlot(sltid);
									if (stack != null)
										return stack.getCount();
								}
								return 0;
							}
						}.getAmount(new BlockPos((int) x, (int) y, (int) z), (int) (4))) == 0)) {
							{
								TileEntity inv = world.getTileEntity(new BlockPos((int) x, (int) y, (int) z));
								if (inv != null && (inv instanceof LockableLootTileEntity)) {
									ItemStack _setstack = new ItemStack(SyrupBirchBucketItem.block, (int) (1));
									_setstack.setCount(1);
									((LockableLootTileEntity) inv).setInventorySlotContents((int) (4), _setstack);
								}
							}
						} else {
							if (((new Object() {
								public int getAmount(BlockPos pos, int sltid) {
									TileEntity inv = world.getTileEntity(pos);
									if (inv instanceof LockableLootTileEntity) {
										ItemStack stack = ((LockableLootTileEntity) inv).getStackInSlot(sltid);
										if (stack != null)
											return stack.getCount();
									}
									return 0;
								}
							}.getAmount(new BlockPos((int) x, (int) y, (int) z), (int) (5))) == 0)) {
								{
									TileEntity inv = world.getTileEntity(new BlockPos((int) x, (int) y, (int) z));
									if (inv != null && (inv instanceof LockableLootTileEntity)) {
										ItemStack _setstack = new ItemStack(SyrupBirchBucketItem.block, (int) (1));
										_setstack.setCount(1);
										((LockableLootTileEntity) inv).setInventorySlotContents((int) (5), _setstack);
									}
								}
							} else {
								if (!world.isRemote) {
									ItemEntity entityToSpawn = new ItemEntity(world, (x + 0.5), (y + 1), (z + 0.5),
											new ItemStack(SyrupBirchBucketItem.block, (int) (1)));
									entityToSpawn.setPickupDelay(10);
									world.addEntity(entityToSpawn);
								}
							}
						}
					}
					BirchSapAvailable = (double) ((new Object() {
						public double getValue(BlockPos pos, String tag) {
							TileEntity tileEntity = world.getTileEntity(pos);
							if (tileEntity != null)
								return tileEntity.getTileData().getDouble(tag);
							return -1;
						}
					}.getValue(new BlockPos((int) x, (int) y, (int) z), "BirchSapAvailable")) - 1);
					if (!world.isRemote) {
						BlockPos _bp = new BlockPos((int) x, (int) y, (int) z);
						TileEntity _tileEntity = world.getTileEntity(_bp);
						BlockState _bs = world.getBlockState(_bp);
						if (_tileEntity != null)
							_tileEntity.getTileData().putDouble("BirchSapAvailable", (BirchSapAvailable));
						world.notifyBlockUpdate(_bp, _bs, _bs, 3);
					}
				}
			}
			if (((Success) == (false))) {
				Sap = (boolean) (false);
				if (!world.isRemote) {
					BlockPos _bp = new BlockPos((int) x, (int) y, (int) z);
					TileEntity _tileEntity = world.getTileEntity(_bp);
					BlockState _bs = world.getBlockState(_bp);
					if (_tileEntity != null)
						_tileEntity.getTileData().putBoolean("Active", (false));
					world.notifyBlockUpdate(_bp, _bs, _bs, 3);
				}
				if (!world.isRemote) {
					BlockPos _bp = new BlockPos((int) x, (int) y, (int) z);
					TileEntity _tileEntity = world.getTileEntity(_bp);
					BlockState _bs = world.getBlockState(_bp);
					if (_tileEntity != null)
						_tileEntity.getTileData().putBoolean("Sap", (false));
					world.notifyBlockUpdate(_bp, _bs, _bs, 3);
				}
			}
			if (((Success) == (true))) {
				if (!world.isRemote) {
					BlockPos _bp = new BlockPos((int) x, (int) y, (int) z);
					TileEntity _tileEntity = world.getTileEntity(_bp);
					BlockState _bs = world.getBlockState(_bp);
					if (_tileEntity != null)
						_tileEntity.getTileData().putBoolean("Active", (true));
					world.notifyBlockUpdate(_bp, _bs, _bs, 3);
				}
				if (!world.isRemote) {
					BlockPos _bp = new BlockPos((int) x, (int) y, (int) z);
					TileEntity _tileEntity = world.getTileEntity(_bp);
					BlockState _bs = world.getBlockState(_bp);
					if (_tileEntity != null)
						_tileEntity.getTileData().putBoolean("Sap", (true));
					world.notifyBlockUpdate(_bp, _bs, _bs, 3);
				}
			}
			if (((BirchSuccess) == (false))) {
				BirchSap = (boolean) (false);
				if (!world.isRemote) {
					BlockPos _bp = new BlockPos((int) x, (int) y, (int) z);
					TileEntity _tileEntity = world.getTileEntity(_bp);
					BlockState _bs = world.getBlockState(_bp);
					if (_tileEntity != null)
						_tileEntity.getTileData().putBoolean("Active", (false));
					world.notifyBlockUpdate(_bp, _bs, _bs, 3);
				}
				if (!world.isRemote) {
					BlockPos _bp = new BlockPos((int) x, (int) y, (int) z);
					TileEntity _tileEntity = world.getTileEntity(_bp);
					BlockState _bs = world.getBlockState(_bp);
					if (_tileEntity != null)
						_tileEntity.getTileData().putBoolean("BirchSap", (false));
					world.notifyBlockUpdate(_bp, _bs, _bs, 3);
				}
			}
			if (((Success) == (true))) {
				if (!world.isRemote) {
					BlockPos _bp = new BlockPos((int) x, (int) y, (int) z);
					TileEntity _tileEntity = world.getTileEntity(_bp);
					BlockState _bs = world.getBlockState(_bp);
					if (_tileEntity != null)
						_tileEntity.getTileData().putBoolean("Active", (true));
					world.notifyBlockUpdate(_bp, _bs, _bs, 3);
				}
				if (!world.isRemote) {
					BlockPos _bp = new BlockPos((int) x, (int) y, (int) z);
					TileEntity _tileEntity = world.getTileEntity(_bp);
					BlockState _bs = world.getBlockState(_bp);
					if (_tileEntity != null)
						_tileEntity.getTileData().putBoolean("BirchSap", (true));
					world.notifyBlockUpdate(_bp, _bs, _bs, 3);
				}
			}
		}
		if (((new Object() {
			public boolean getValue(BlockPos pos, String tag) {
				TileEntity tileEntity = world.getTileEntity(pos);
				if (tileEntity != null)
					return tileEntity.getTileData().getBoolean(tag);
				return false;
			}
		}.getValue(new BlockPos((int) x, (int) y, (int) z), "Active")) == (false))) {
		}
	}
}
