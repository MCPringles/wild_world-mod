//Made with Blockbench
//Paste this code into your mod.

public static class modelcustom_model extends ModelBase {
	private final ModelRenderer head;
	private final ModelRenderer rods;
	private final ModelRenderer rods1;
	private final ModelRenderer rods2;

	public modelcustom_model() {
		textureWidth = 32;
		textureHeight = 32;

		head = new ModelRenderer(this);
		head.setRotationPoint(-3.0F, 17.0F, -4.0F);
		head.cubeList.add(new ModelBox(head, 0, 0, -1.0F, -3.0F, 0.0F, 8, 8, 8,
				0.0F, false));
		head.cubeList.add(new ModelBox(head, 9, 10, 0.0F, 4.0F, -0.05F, 2, 1,
				0, 0.0F, false));
		head.cubeList.add(new ModelBox(head, 9, 10, 4.0F, 4.0F, -0.05F, 2, 1,
				0, 0.0F, true));

		rods = new ModelRenderer(this);
		rods.setRotationPoint(0.0F, 24.0F, 0.0F);

		rods1 = new ModelRenderer(this);
		rods1.setRotationPoint(0.0F, 0.0F, 0.0F);
		rods.addChild(rods1);
		rods1.cubeList.add(new ModelBox(rods1, 0, 0, -9.0F, -9.0F, -1.0F, 2, 6,
				2, 0.0F, false));
		rods1.cubeList.add(new ModelBox(rods1, 0, 0, 7.0F, -9.0F, -1.0F, 2, 6,
				2, 0.0F, false));
		rods1.cubeList.add(new ModelBox(rods1, 0, 0, -1.0F, -9.0F, -9.0F, 2, 6,
				2, 0.0F, false));
		rods1.cubeList.add(new ModelBox(rods1, 0, 0, -1.0F, -9.0F, 7.0F, 2, 6,
				2, 0.0F, false));

		rods2 = new ModelRenderer(this);
		rods2.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotationAngle(rods2, 0.0F, -0.7854F, 0.0F);
		rods.addChild(rods2);
		rods2.cubeList.add(new ModelBox(rods2, 0, 0, -9.0F, -9.0F, -1.0F, 2, 6,
				2, 0.0F, false));
		rods2.cubeList.add(new ModelBox(rods2, 0, 0, 7.0F, -9.0F, -1.0F, 2, 6,
				2, 0.0F, false));
		rods2.cubeList.add(new ModelBox(rods2, 0, 0, -1.0F, -9.0F, -9.0F, 2, 6,
				2, 0.0F, false));
		rods2.cubeList.add(new ModelBox(rods2, 0, 0, -1.0F, -9.0F, 7.0F, 2, 6,
				2, 0.0F, false));
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3,
			float f4, float f5) {
		head.render(f5);
		rods.render(f5);
	}
	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y,
			float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}

	public void setRotationAngles(float f, float f1, float f2, float f3,
			float f4, float f5, Entity e) {
		super.setRotationAngles(f, f1, f2, f3, f4, f5, e);
		this.rods.rotateAngleY = f2 / 50.f;
	}
}