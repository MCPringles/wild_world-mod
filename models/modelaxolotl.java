//Made with Blockbench
//Paste this code into your mod.

public static class modelaxolotl extends ModelBase {
	private final ModelRenderer bodybone;
	private final ModelRenderer head;
	private final ModelRenderer gillsbone;
	private final ModelRenderer gillsbone2;
	private final ModelRenderer Leg1;
	private final ModelRenderer Leg2;
	private final ModelRenderer Leg3;
	private final ModelRenderer Leg4;

	public modelaxolotl() {
		textureWidth = 32;
		textureHeight = 32;

		bodybone = new ModelRenderer(this);
		bodybone.setRotationPoint(0.0F, 24.0F, 0.0F);
		bodybone.cubeList.add(new ModelBox(bodybone, 16, 0, -1.5F, -4.0F, -3.0F, 3, 3, 5, 0.0F, false));
		bodybone.cubeList.add(new ModelBox(bodybone, 11, 0, -1.0F, -4.0F, 2.0F, 2, 2, 3, 0.0F, false));
		bodybone.cubeList.add(new ModelBox(bodybone, 18, 13, -0.5F, -4.0F, 5.0F, 1, 1, 2, 0.0F, false));
		bodybone.cubeList.add(new ModelBox(bodybone, 0, 7, 0.0F, -5.0F, -2.0F, 0, 4, 12, 0.0F, false));

		head = new ModelRenderer(this);
		head.setRotationPoint(0.0F, 21.0F, -3.0F);
		setRotationAngle(head, 0.2618F, 0.0F, 0.0F);
		head.cubeList.add(new ModelBox(head, 0, 9, -2.0F, -2.0F, -4.0F, 4, 2, 5, 0.0F, false));

		gillsbone = new ModelRenderer(this);
		gillsbone.setRotationPoint(-2.0F, -1.0F, 1.0F);
		setRotationAngle(gillsbone, 0.0F, 0.5236F, 0.0F);
		head.addChild(gillsbone);
		gillsbone.cubeList.add(new ModelBox(gillsbone, 0, 5, -4.0F, -3.0F, 0.0F, 4, 4, 0, 0.0F, false));

		gillsbone2 = new ModelRenderer(this);
		gillsbone2.setRotationPoint(2.0F, -1.0F, 1.0F);
		setRotationAngle(gillsbone2, 0.0F, 2.618F, 0.0F);
		head.addChild(gillsbone2);
		gillsbone2.cubeList.add(new ModelBox(gillsbone2, 0, 5, -4.0F, -3.0F, 0.0F, 4, 4, 0, 0.0F, false));

		Leg1 = new ModelRenderer(this);
		Leg1.setRotationPoint(-1.0F, 21.0F, 0.0F);
		Leg1.cubeList.add(new ModelBox(Leg1, 0, 23, -3.0F, 0.0F, -4.0F, 3, 3, 3, 0.0F, false));

		Leg2 = new ModelRenderer(this);
		Leg2.setRotationPoint(1.0F, 21.0F, 0.0F);
		Leg2.cubeList.add(new ModelBox(Leg2, 0, 23, 0.0F, 0.0F, -4.0F, 3, 3, 3, 0.0F, true));

		Leg3 = new ModelRenderer(this);
		Leg3.setRotationPoint(-1.0F, 21.0F, 0.0F);
		Leg3.cubeList.add(new ModelBox(Leg3, 0, 23, -3.0F, 0.0F, 1.0F, 3, 3, 3, 0.0F, false));

		Leg4 = new ModelRenderer(this);
		Leg4.setRotationPoint(1.0F, 21.0F, 0.0F);
		Leg4.cubeList.add(new ModelBox(Leg4, 0, 23, 0.0F, 0.0F, 1.0F, 3, 3, 3, 0.0F, true));
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		bodybone.render(f5);
		head.render(f5);
		Leg1.render(f5);
		Leg2.render(f5);
		Leg3.render(f5);
		Leg4.render(f5);
	}
	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}

	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity e) {
		super.setRotationAngles(f, f1, f2, f3, f4, f5, e);
		this.head.rotateAngleY = f3 / (180F / (float) Math.PI);
		this.head.rotateAngleX = f4 / (180F / (float) Math.PI);
		this.Leg2.rotateAngleX = MathHelper.cos(f * 1.0F) * -1.0F * f1;
		this.Leg3.rotateAngleX = MathHelper.cos(f * 1.0F) * 1.0F * f1;
		this.Leg4.rotateAngleX = MathHelper.cos(f * 1.0F) * -1.0F * f1;
		this.Leg1.rotateAngleX = MathHelper.cos(f * 1.0F) * 1.0F * f1;
	}
}