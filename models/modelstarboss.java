/**
 * StarBoss - Undefined Created using Tabula 7.0.1
 */
public static class modelstarboss extends ModelBase {
	public ModelRenderer mainbody;
	public ModelRenderer aurax;
	public ModelRenderer auraz;
	public ModelRenderer fallenstar;
	public ModelRenderer stargemsx;
	public ModelRenderer stargemsz;

	public modelstarboss() {
		this.textureWidth = 128;
		this.textureHeight = 128;
		this.auraz = new ModelRenderer(this, 0, 48);
		this.auraz.setRotationPoint(0.0F, -12.0F, 0.0F);
		this.auraz.addBox(0.0F, -20.0F, -20.0F, 0, 40, 40, 0.0F);
		this.setRotateAngle(auraz, 0.0F, 1.5707963267948966F, 0.0F);
		this.stargemsz = new ModelRenderer(this, 0, 48);
		this.stargemsz.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.stargemsz.addBox(-6.0F, -6.0F, -14.0F, 12, 12, 28, 0.0F);
		this.mainbody = new ModelRenderer(this, 0, 0);
		this.mainbody.setRotationPoint(0.0F, -12.0F, 0.0F);
		this.mainbody.addBox(-12.0F, -12.0F, -12.0F, 24, 24, 24, 0.0F);
		this.stargemsx = new ModelRenderer(this, 0, 64);
		this.stargemsx.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.stargemsx.addBox(-14.0F, -6.0F, -6.0F, 28, 12, 12, 0.0F);
		this.fallenstar = new ModelRenderer(this, 72, 8);
		this.fallenstar.setRotationPoint(0.0F, -36.0F, 0.0F);
		this.fallenstar.addBox(-4.0F, -4.0F, -4.0F, 8, 8, 8, 0.0F);
		this.aurax = new ModelRenderer(this, 0, 48);
		this.aurax.setRotationPoint(0.0F, -12.0F, 0.0F);
		this.aurax.addBox(0.0F, -20.0F, -20.0F, 0, 40, 40, 0.0F);
		this.mainbody.addChild(this.stargemsz);
		this.mainbody.addChild(this.stargemsx);
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3,
			float f4, float f5) {
		this.auraz.render(f5);
		this.mainbody.render(f5);
		this.fallenstar.render(f5);
		this.aurax.render(f5);
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
		this.fallenstar.rotateAngleY = f2 / 20.f;
		this.auraz.rotateAngleZ = f2 / 20.f;
		this.aurax.rotateAngleX = f2 / 20.f;
	}
}
