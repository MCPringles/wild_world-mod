//Made with Blockbench
//Paste this code into your mod.

public static class Modelaerodus extends ModelBase {
	private final ModelRenderer aerodus;
	private final ModelRenderer head;
	private final ModelRenderer mouth;
	private final ModelRenderer ears;
	private final ModelRenderer body;
	private final ModelRenderer triangle;
	private final ModelRenderer neck;
	private final ModelRenderer leg1;
	private final ModelRenderer leg2;
	private final ModelRenderer leg3;
	private final ModelRenderer leg4;
	private final ModelRenderer tail;
	private final ModelRenderer tailend;

	public Modelaerodus() {
		textureWidth = 128;
		textureHeight = 128;

		aerodus = new ModelRenderer(this);
		aerodus.setRotationPoint(0.0F, 11.0F, -7.0F);

		head = new ModelRenderer(this);
		head.setRotationPoint(0.0F, -13.0F, -7.0F);
		setRotationAngle(head, 0.5236F, 0.0F, 0.0F);
		aerodus.addChild(head);
		head.cubeList.add(new ModelBox(head, 0, 60, -5.0F, -4.0F, -9.0F, 10, 10, 10, 0.0F, false));
		head.cubeList.add(new ModelBox(head, 30, 60, -4.0F, -4.0F, -13.0F, 8, 6, 4, 0.0F, false));
		head.cubeList.add(new ModelBox(head, 24, 80, -4.01F, 0.0F, -13.01F, 2, 6, 2, 0.0F, false));
		head.cubeList.add(new ModelBox(head, 24, 80, 2.01F, 0.0F, -13.01F, 2, 6, 2, 0.0F, false));

		mouth = new ModelRenderer(this);
		mouth.setRotationPoint(0.0F, -2.0F, -5.0F);
		head.addChild(mouth);
		mouth.cubeList.add(new ModelBox(mouth, 0, 80, -4.0F, 2.0F, -8.0F, 8, 4, 8, -0.1F, false));

		ears = new ModelRenderer(this);
		ears.setRotationPoint(0.0F, -2.0F, -3.0F);
		setRotationAngle(ears, 0.5236F, 0.0F, 0.0F);
		head.addChild(ears);
		ears.cubeList.add(new ModelBox(ears, 0, 80, 5.01F, -1.0F, 2.0F, 0, 4, 4, 0.0F, false));
		ears.cubeList.add(new ModelBox(ears, 0, 80, -5.01F, -1.0F, 2.0F, 0, 4, 4, 0.0F, true));

		body = new ModelRenderer(this);
		body.setRotationPoint(0.0F, -7.0F, 7.0F);
		aerodus.addChild(body);
		body.cubeList.add(new ModelBox(body, 0, 0, -5.0F, -4.0F, -13.0F, 10, 10, 28, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 76, 12, -7.0F, -5.0F, -15.0F, 14, 14, 12, 0.0F, false));

		triangle = new ModelRenderer(this);
		triangle.setRotationPoint(0.0F, 6.0F, -7.0F);
		setRotationAngle(triangle, 0.0873F, 0.0F, 0.0F);
		body.addChild(triangle);
		triangle.cubeList.add(new ModelBox(triangle, 0, 38, -4.0F, 0.0F, 1.0F, 8, 2, 20, 0.0F, false));

		neck = new ModelRenderer(this);
		neck.setRotationPoint(0.0F, -4.0F, -12.0F);
		setRotationAngle(neck, 1.0472F, 0.0F, 0.0F);
		body.addChild(neck);
		neck.cubeList.add(new ModelBox(neck, 36, 38, -4.0F, -4.0F, -5.0F, 8, 12, 8, 0.0F, false));

		leg1 = new ModelRenderer(this);
		leg1.setRotationPoint(-6.0F, -7.0F, -1.0F);
		aerodus.addChild(leg1);
		leg1.cubeList.add(new ModelBox(leg1, 0, 0, -2.0F, -2.0F, -3.0F, 4, 20, 4, 0.0F, false));
		leg1.cubeList.add(new ModelBox(leg1, 48, 20, -2.0F, 18.0F, -5.0F, 4, 2, 6, 0.0F, false));

		leg2 = new ModelRenderer(this);
		leg2.setRotationPoint(6.0F, -7.0F, -1.0F);
		aerodus.addChild(leg2);
		leg2.cubeList.add(new ModelBox(leg2, 0, 0, -2.0F, -2.0F, -3.0F, 4, 20, 4, 0.0F, false));
		leg2.cubeList.add(new ModelBox(leg2, 48, 20, -2.0F, 18.0F, -5.0F, 4, 2, 6, 0.0F, true));

		leg3 = new ModelRenderer(this);
		leg3.setRotationPoint(6.0F, -7.0F, 19.0F);
		aerodus.addChild(leg3);
		leg3.cubeList.add(new ModelBox(leg3, 0, 0, -2.0F, -2.0F, -3.0F, 4, 20, 4, 0.0F, false));
		leg3.cubeList.add(new ModelBox(leg3, 48, 20, -2.0F, 18.0F, -5.0F, 4, 2, 6, 0.0F, true));

		leg4 = new ModelRenderer(this);
		leg4.setRotationPoint(-6.0F, -7.0F, 19.0F);
		aerodus.addChild(leg4);
		leg4.cubeList.add(new ModelBox(leg4, 0, 0, -2.0F, -2.0F, -3.0F, 4, 20, 4, 0.0F, false));
		leg4.cubeList.add(new ModelBox(leg4, 48, 20, -2.0F, 18.0F, -5.0F, 4, 2, 6, 0.0F, false));

		tail = new ModelRenderer(this);
		tail.setRotationPoint(0.0F, -11.0F, 22.0F);
		setRotationAngle(tail, -1.0472F, 0.0F, 0.0F);
		aerodus.addChild(tail);
		tail.cubeList.add(new ModelBox(tail, 78, 38, -1.0F, 0.0F, 0.0F, 2, 2, 16, 0.0F, false));

		tailend = new ModelRenderer(this);
		tailend.setRotationPoint(0.0F, 2.0F, 16.0F);
		setRotationAngle(tailend, 1.0472F, 0.0F, 0.0F);
		tail.addChild(tailend);
		tailend.cubeList.add(new ModelBox(tailend, 98, 48, -1.0F, -2.0F, 0.0F, 2, 2, 4, 0.0F, false));
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		aerodus.render(f5);
	}
	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}

	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity e) {
		super.setRotationAngles(f, f1, f2, f3, f4, f5, e);
		this.head.rotateAngleY = f3 / (180F / (float) Math.PI);
		this.head.rotateAngleX = 0.5236F + f4 / (180F / (float) Math.PI);
		this.leg1.rotateAngleX = MathHelper.cos(f * 1.0F) * 1.0F * f1;
		this.leg4.rotateAngleX = MathHelper.cos(f * 1.0F) * 1.0F * f1;
		this.leg2.rotateAngleX = MathHelper.cos(f * 1.0F) * -1.0F * f1;
		this.leg3.rotateAngleX = MathHelper.cos(f * 1.0F) * -1.0F * f1;
	}
}