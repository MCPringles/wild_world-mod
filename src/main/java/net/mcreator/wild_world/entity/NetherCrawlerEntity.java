
package net.mcreator.wild_world.entity;

import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.fml.network.FMLPlayMessages;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.World;
import net.minecraft.world.IWorld;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.DamageSource;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.item.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Item;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.monster.SpiderEntity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.RandomWalkingGoal;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.LeapAtTargetGoal;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ILivingEntityData;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EntitySpawnPlacementRegistry;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.Entity;
import net.minecraft.entity.CreatureAttribute;
import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.MobRenderer;

import net.mcreator.wild_world.procedures.NetherCrawlerOnInitialMobSpawnProcedure;
import net.mcreator.wild_world.WildWorldElements;

@WildWorldElements.ModElement.Tag
public class NetherCrawlerEntity extends WildWorldElements.ModElement {
	public static EntityType entity = null;
	public NetherCrawlerEntity(WildWorldElements instance) {
		super(instance, 90);
		FMLJavaModLoadingContext.get().getModEventBus().register(this);
	}

	@Override
	public void initElements() {
		entity = (EntityType.Builder.<CustomEntity>create(CustomEntity::new, EntityClassification.MONSTER).setShouldReceiveVelocityUpdates(true)
				.setTrackingRange(24).setUpdateInterval(3).setCustomClientFactory(CustomEntity::new).immuneToFire().size(1.5f, 0.8f))
						.build("nethercrawler").setRegistryName("nethercrawler");
		elements.entities.add(() -> entity);
		elements.items.add(
				() -> new SpawnEggItem(entity, -11852271, -9306112, new Item.Properties().group(ItemGroup.MISC)).setRegistryName("nethercrawler"));
	}

	@Override
	public void init(FMLCommonSetupEvent event) {
		for (Biome biome : ForgeRegistries.BIOMES.getValues()) {
			boolean biomeCriteria = false;
			if (ForgeRegistries.BIOMES.getKey(biome).equals(new ResourceLocation("nether")))
				biomeCriteria = true;
			if (!biomeCriteria)
				continue;
			biome.getSpawns(EntityClassification.MONSTER).add(new Biome.SpawnListEntry(entity, 2, 1, 3));
		}
		EntitySpawnPlacementRegistry.register(entity, EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES,
				MonsterEntity::func_223315_a);
	}

	@SubscribeEvent
	@OnlyIn(Dist.CLIENT)
	public void registerModels(ModelRegistryEvent event) {
		RenderingRegistry.registerEntityRenderingHandler(CustomEntity.class, renderManager -> {
			return new MobRenderer(renderManager, new modelnethercrawler(), 0.75f) {
				protected ResourceLocation getEntityTexture(Entity entity) {
					return new ResourceLocation("wild_world:textures/nethercrawlertexturemap.png");
				}
			};
		});
	}
	public static class CustomEntity extends SpiderEntity {
		public CustomEntity(FMLPlayMessages.SpawnEntity packet, World world) {
			this(entity, world);
		}

		public CustomEntity(EntityType<CustomEntity> type, World world) {
			super(type, world);
			experienceValue = 7;
			setNoAI(false);
		}

		@Override
		protected void registerGoals() {
			super.registerGoals();
			this.goalSelector.addGoal(1, new RandomWalkingGoal(this, 1));
			this.goalSelector.addGoal(2, new LookRandomlyGoal(this));
			this.goalSelector.addGoal(3, new SwimGoal(this));
			this.goalSelector.addGoal(4, new LeapAtTargetGoal(this, (float) 0.2));
			this.targetSelector.addGoal(5, new HurtByTargetGoal(this));
			this.targetSelector.addGoal(6, new NearestAttackableTargetGoal(this, PlayerEntity.class, false, true));
			this.targetSelector.addGoal(7, new NearestAttackableTargetGoal(this, ServerPlayerEntity.class, false, true));
		}

		@Override
		public CreatureAttribute getCreatureAttribute() {
			return CreatureAttribute.ARTHROPOD;
		}

		protected void dropSpecialItems(DamageSource source, int looting, boolean recentlyHitIn) {
			super.dropSpecialItems(source, looting, recentlyHitIn);
			this.entityDropItem(new ItemStack(Items.SPIDER_EYE, (int) (1)));
		}

		@Override
		public net.minecraft.util.SoundEvent getAmbientSound() {
			return (net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.spider.ambient"));
		}

		@Override
		public net.minecraft.util.SoundEvent getHurtSound(DamageSource ds) {
			return (net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.spider.hurt"));
		}

		@Override
		public net.minecraft.util.SoundEvent getDeathSound() {
			return (net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.spider.death"));
		}

		@Override
		protected float getSoundVolume() {
			return 1.0F;
		}

		@Override
		public boolean attackEntityFrom(DamageSource source, float amount) {
			if (source == DamageSource.FALL)
				return false;
			return super.attackEntityFrom(source, amount);
		}

		@Override
		public ILivingEntityData onInitialSpawn(IWorld world, DifficultyInstance difficulty, SpawnReason reason, ILivingEntityData livingdata,
				CompoundNBT tag) {
			ILivingEntityData retval = super.onInitialSpawn(world, difficulty, reason, livingdata, tag);
			int x = (int) this.posX;
			int y = (int) this.posY;
			int z = (int) this.posZ;
			Entity entity = this;
			{
				java.util.HashMap<String, Object> $_dependencies = new java.util.HashMap<>();
				$_dependencies.put("entity", entity);
				$_dependencies.put("x", x);
				$_dependencies.put("y", y);
				$_dependencies.put("z", z);
				$_dependencies.put("world", world);
				NetherCrawlerOnInitialMobSpawnProcedure.executeProcedure($_dependencies);
			}
			return retval;
		}

		@Override
		protected void registerAttributes() {
			super.registerAttributes();
			if (this.getAttribute(SharedMonsterAttributes.ARMOR) != null)
				this.getAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(0);
			if (this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED) != null)
				this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.3);
			if (this.getAttribute(SharedMonsterAttributes.MAX_HEALTH) != null)
				this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(24);
			if (this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE) != null)
				this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(3);
		}
	}

	/**
	 * NetherCrawler - Undefined Created using Tabula 7.0.1
	 */
	public static class modelnethercrawler extends EntityModel<Entity> {
		public RendererModel head;
		public RendererModel lleg1;
		public RendererModel rleg2;
		public RendererModel rleg1;
		public RendererModel lleg2;
		public RendererModel body;
		public RendererModel mouth;
		public RendererModel gaster;
		public RendererModel spikes;
		public modelnethercrawler() {
			this.textureWidth = 80;
			this.textureHeight = 48;
			this.spikes = new RendererModel(this, 25, -14);
			this.spikes.setRotationPoint(0.0F, 0.0F, 0.0F);
			this.spikes.addBox(0.0F, -6.0F, -5.0F, 0, 3, 14, 0.0F);
			this.rleg2 = new RendererModel(this, 0, 34);
			this.rleg2.setRotationPoint(3.0000000000000107F, 15.99999999999996F, 3.0000000000000013F);
			this.rleg2.addBox(-1.0F, 0.0F, -1.0F, 2, 12, 2, 0.0F);
			this.setRotateAngle(rleg2, 0.5235987755982988F, -0.3141592653589793F, -1.0471975511965976F);
			this.lleg2 = new RendererModel(this, 0, 34);
			this.lleg2.setRotationPoint(-3.0000000000000013F, 15.99999999999996F, 3.0000000000000013F);
			this.lleg2.addBox(-1.0F, 0.0F, -1.0F, 2, 12, 2, 0.0F);
			this.setRotateAngle(lleg2, 0.5235987755982988F, 0.3141592653589793F, 1.0471975511965976F);
			this.mouth = new RendererModel(this, 24, 5);
			this.mouth.setRotationPoint(0.0F, 0.0F, 0.0F);
			this.mouth.addBox(-2.0F, 2.5F, -10.0F, 4, 1, 2, 0.0F);
			this.body = new RendererModel(this, 0, 16);
			this.body.setRotationPoint(-1.582067810090848E-15F, 15.99999999999996F, 1.5265566588595902E-15F);
			this.body.addBox(-3.0F, -3.0F, -5.0F, 6, 6, 10, 0.0F);
			this.lleg1 = new RendererModel(this, 0, 34);
			this.lleg1.setRotationPoint(-3.0000000000000013F, 15.99999999999996F, -3.000000000000007F);
			this.lleg1.addBox(-1.0F, 0.0F, -1.0F, 2, 12, 2, 0.0F);
			this.setRotateAngle(lleg1, -0.5235987755982988F, -0.3141592653589793F, 1.0471975511965976F);
			this.gaster = new RendererModel(this, 22, 30);
			this.gaster.setRotationPoint(0.0F, 0.0F, 0.0F);
			this.gaster.addBox(-4.0F, -4.0F, 5.0F, 8, 8, 10, 0.0F);
			this.head = new RendererModel(this, 0, 0);
			this.head.setRotationPoint(-1.582067810090848E-15F, 15.49999999999996F, -5.0000000000000115F);
			this.head.addBox(-4.0F, -3.5F, -8.0F, 8, 7, 8, 0.0F);
			this.rleg1 = new RendererModel(this, 0, 34);
			this.rleg1.setRotationPoint(3.0000000000000107F, 15.99999999999996F, -3.000000000000007F);
			this.rleg1.addBox(-1.0F, 0.0F, -1.0F, 2, 12, 2, 0.0F);
			this.setRotateAngle(rleg1, -0.5235987755982988F, 0.3141592653589793F, -1.0471975511965976F);
			this.body.addChild(this.spikes);
			this.head.addChild(this.mouth);
			this.body.addChild(this.gaster);
		}

		@Override
		public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
			this.rleg2.render(f5);
			this.lleg2.render(f5);
			this.body.render(f5);
			this.lleg1.render(f5);
			this.head.render(f5);
			this.rleg1.render(f5);
		}

		/**
		 * This is a helper function from Tabula to set the rotation of model parts
		 */
		public void setRotateAngle(RendererModel modelRenderer, float x, float y, float z) {
			modelRenderer.rotateAngleX = x;
			modelRenderer.rotateAngleY = y;
			modelRenderer.rotateAngleZ = z;
		}

		public void setRotationAngles(Entity e, float f, float f1, float f2, float f3, float f4, float f5) {
			super.setRotationAngles(e, f, f1, f2, f3, f4, f5);
			this.head.rotateAngleY = f3 / (180F / (float) Math.PI);
			this.head.rotateAngleX = f4 / (180F / (float) Math.PI);
			this.lleg1.rotateAngleX = MathHelper.cos(f * 1.0F) * -1.0F * f1;
			this.rleg1.rotateAngleX = MathHelper.cos(f * 1.0F) * 1.0F * f1;
			this.rleg2.rotateAngleX = MathHelper.cos(f * 0.6662F + (float) Math.PI) * 2.0F * f1 * 0.5F;
			this.lleg2.rotateAngleX = MathHelper.cos(f * 0.6662F) * 2.0F * f1 * 0.5F;
		}
	}
}
