
/**
 * NetherCrawler - Undefined Created using Tabula 7.0.1
 */
public static class modelnethercrawler extends ModelBase {
	public ModelRenderer head;
	public ModelRenderer lleg1;
	public ModelRenderer rleg2;
	public ModelRenderer rleg1;
	public ModelRenderer lleg2;
	public ModelRenderer body;
	public ModelRenderer mouth;
	public ModelRenderer gaster;
	public ModelRenderer spikes;

	public modelnethercrawler() {
		this.textureWidth = 80;
		this.textureHeight = 48;
		this.spikes = new ModelRenderer(this, 25, -14);
		this.spikes.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.spikes.addBox(0.0F, -6.0F, -5.0F, 0, 3, 14, 0.0F);
		this.rleg2 = new ModelRenderer(this, 0, 34);
		this.rleg2.setRotationPoint(3.0000000000000107F, 15.99999999999996F,
				3.0000000000000013F);
		this.rleg2.addBox(-1.0F, 0.0F, -1.0F, 2, 12, 2, 0.0F);
		this.setRotateAngle(rleg2, 0.5235987755982988F, -0.3141592653589793F,
				-1.0471975511965976F);
		this.lleg2 = new ModelRenderer(this, 0, 34);
		this.lleg2.setRotationPoint(-3.0000000000000013F, 15.99999999999996F,
				3.0000000000000013F);
		this.lleg2.addBox(-1.0F, 0.0F, -1.0F, 2, 12, 2, 0.0F);
		this.setRotateAngle(lleg2, 0.5235987755982988F, 0.3141592653589793F,
				1.0471975511965976F);
		this.mouth = new ModelRenderer(this, 24, 5);
		this.mouth.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.mouth.addBox(-2.0F, 2.5F, -10.0F, 4, 1, 2, 0.0F);
		this.body = new ModelRenderer(this, 0, 16);
		this.body.setRotationPoint(-1.582067810090848E-15F, 15.99999999999996F,
				1.5265566588595902E-15F);
		this.body.addBox(-3.0F, -3.0F, -5.0F, 6, 6, 10, 0.0F);
		this.lleg1 = new ModelRenderer(this, 0, 34);
		this.lleg1.setRotationPoint(-3.0000000000000013F, 15.99999999999996F,
				-3.000000000000007F);
		this.lleg1.addBox(-1.0F, 0.0F, -1.0F, 2, 12, 2, 0.0F);
		this.setRotateAngle(lleg1, -0.5235987755982988F, -0.3141592653589793F,
				1.0471975511965976F);
		this.gaster = new ModelRenderer(this, 22, 30);
		this.gaster.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.gaster.addBox(-4.0F, -4.0F, 5.0F, 8, 8, 10, 0.0F);
		this.head = new ModelRenderer(this, 0, 0);
		this.head.setRotationPoint(-1.582067810090848E-15F, 15.49999999999996F,
				-5.0000000000000115F);
		this.head.addBox(-4.0F, -3.5F, -8.0F, 8, 7, 8, 0.0F);
		this.rleg1 = new ModelRenderer(this, 0, 34);
		this.rleg1.setRotationPoint(3.0000000000000107F, 15.99999999999996F,
				-3.000000000000007F);
		this.rleg1.addBox(-1.0F, 0.0F, -1.0F, 2, 12, 2, 0.0F);
		this.setRotateAngle(rleg1, -0.5235987755982988F, 0.3141592653589793F,
				-1.0471975511965976F);
		this.body.addChild(this.spikes);
		this.head.addChild(this.mouth);
		this.body.addChild(this.gaster);
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3,
			float f4, float f5) {
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
	public void setRotateAngle(ModelRenderer modelRenderer, float x, float y,
			float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}

	public void setRotationAngles(float f, float f1, float f2, float f3,
			float f4, float f5, Entity e) {
		super.setRotationAngles(f, f1, f2, f3, f4, f5, e);
		this.head.rotateAngleY = f3 / (180F / (float) Math.PI);
		this.head.rotateAngleX = f4 / (180F / (float) Math.PI);
		this.lleg1.rotateAngleX = MathHelper.cos(f * 1.0F) * -1.0F * f1;
		this.rleg1.rotateAngleX = MathHelper.cos(f * 1.0F) * 1.0F * f1;
		this.rleg2.rotateAngleX = MathHelper.cos(f * 0.6662F + (float) Math.PI)
				* 2.0F * f1 * 0.5F;
		this.lleg2.rotateAngleX = MathHelper.cos(f * 0.6662F) * 2.0F * f1
				* 0.5F;
	}
}
