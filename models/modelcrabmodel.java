//Made with Blockbench
//Paste this code into your mod.

public static class modelcrabmodel extends ModelBase {
	private final ModelRenderer crab;
	private final ModelRenderer body;
	private final ModelRenderer legl1;
	private final ModelRenderer legr2;
	private final ModelRenderer legl3;
	private final ModelRenderer legr4;
	private final ModelRenderer legl5;
	private final ModelRenderer legr6;
	private final ModelRenderer eyel;
	private final ModelRenderer eyer;

	public modelcrabmodel() {
		textureWidth = 32;
		textureHeight = 32;

		crab = new ModelRenderer(this);
		crab.setRotationPoint(0.0F, 24.0F, 0.0F);
		setRotationAngle(crab, 0.0F, -1.5708F, 0.0F);

		body = new ModelRenderer(this);
		body.setRotationPoint(0.0F, 0.0F, 0.0F);
		crab.addChild(body);
		body.cubeList.add(new ModelBox(body, 6, 0, -2.0F, -2.5F, -2.5F, 4, 2, 5, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 6, 8, -3.0F, -2.0F, 0.75F, 1, 2, 3, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 14, 8, -3.0F, -2.0F, -3.75F, 1, 2, 3, 0.0F, true));

		legl1 = new ModelRenderer(this);
		legl1.setRotationPoint(0.0F, -1.0F, 2.0F);
		crab.addChild(legl1);
		legl1.cubeList.add(new ModelBox(legl1, 0, 0, 0.0F, -1.0F, 0.0F, 0, 2, 3, 0.0F, false));

		legr2 = new ModelRenderer(this);
		legr2.setRotationPoint(0.0F, -1.0F, -2.0F);
		crab.addChild(legr2);
		legr2.cubeList.add(new ModelBox(legr2, 0, 2, 0.0F, -1.0F, -3.0F, 0, 2, 3, 0.0F, false));

		legl3 = new ModelRenderer(this);
		legl3.setRotationPoint(1.0F, -1.0F, 2.0F);
		setRotationAngle(legl3, 0.0F, 0.2618F, 0.0F);
		crab.addChild(legl3);
		legl3.cubeList.add(new ModelBox(legl3, 0, 0, 0.0F, -1.0F, 0.0F, 0, 2, 3, 0.0F, false));

		legr4 = new ModelRenderer(this);
		legr4.setRotationPoint(1.0F, -1.0F, -2.0F);
		setRotationAngle(legr4, 0.0F, -0.2618F, 0.0F);
		crab.addChild(legr4);
		legr4.cubeList.add(new ModelBox(legr4, 0, 2, 0.0F, -1.0F, -3.0F, 0, 2, 3, 0.0F, true));

		legl5 = new ModelRenderer(this);
		legl5.setRotationPoint(-1.0F, -1.0F, 2.0F);
		setRotationAngle(legl5, 0.0F, -0.2618F, 0.0F);
		crab.addChild(legl5);
		legl5.cubeList.add(new ModelBox(legl5, 0, 0, 0.0F, -1.0F, 0.0F, 0, 2, 3, 0.0F, true));

		legr6 = new ModelRenderer(this);
		legr6.setRotationPoint(-1.0F, -1.0F, -2.0F);
		setRotationAngle(legr6, 0.0F, 0.2618F, 0.0F);
		crab.addChild(legr6);
		legr6.cubeList.add(new ModelBox(legr6, 0, 2, 0.0F, -1.0F, -3.0F, 0, 2, 3, 0.0F, false));

		eyel = new ModelRenderer(this);
		eyel.setRotationPoint(-0.75F, -2.0F, 1.0F);
		crab.addChild(eyel);
		eyel.cubeList.add(new ModelBox(eyel, 0, 10, -0.5F, -2.25F, -0.5F, 1, 2, 1, 0.0F, false));

		eyer = new ModelRenderer(this);
		eyer.setRotationPoint(-0.75F, -2.0F, -1.0F);
		crab.addChild(eyer);
		eyer.cubeList.add(new ModelBox(eyer, 0, 10, -0.5F, -2.25F, -0.5F, 1, 2, 1, 0.0F, false));
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		crab.render(f5);
	}
	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}

	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity e) {
		super.setRotationAngles(f, f1, f2, f3, f4, f5, e);
		this.legr4.rotateAngleY = MathHelper.cos(f * 1.0F) * 1.0F * f1;
		this.legl1.rotateAngleY = MathHelper.cos(f * 1.0F) * -1.0F * f1;
		this.legr6.rotateAngleY = MathHelper.cos(f * 1.0F) * 1.0F * f1;
		this.legl3.rotateAngleY = MathHelper.cos(f * 1.0F) * -1.0F * f1;
		this.eyel.rotateAngleY = f3 / (180F / (float) Math.PI);
		this.eyel.rotateAngleX = f4 / (180F / (float) Math.PI);
		this.legl5.rotateAngleY = MathHelper.cos(f * 1.0F) * -1.0F * f1;
		this.eyer.rotateAngleY = f3 / (180F / (float) Math.PI);
		this.eyer.rotateAngleX = f4 / (180F / (float) Math.PI);
		this.legr2.rotateAngleY = MathHelper.cos(f * 1.0F) * 1.0F * f1;
	}
}