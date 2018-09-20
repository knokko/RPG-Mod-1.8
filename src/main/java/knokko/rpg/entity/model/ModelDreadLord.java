package knokko.rpg.entity.model;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelDreadLord extends ModelBiped{
	
	public ModelDreadLord(float f1, float f2, int width, int height){
		textureWidth = width;
		textureHeight = height;
		
		this.bipedHead = new ModelRenderer(this, 0, 0);
        this.bipedHead.addBox(-4.0F, -11.0F, -4.0F, 8, 8, 8, f1);
        this.bipedHead.setRotationPoint(0.0F, 0.0F + f2, 0.0F);
        this.bipedHeadwear = new ModelRenderer(this, 34, 4);
        this.bipedHeadwear.addBox(-4.0F, -11.0F, -4.0F, 8, 8, 8, f1 + 0.5F);
        this.bipedHeadwear.setRotationPoint(0.0F, 0.0F + f2, 0.0F);
        this.bipedBody = new ModelRenderer(this, 16, 16);
        this.bipedBody.addBox(-5.0F, -3.0F, -2.0F, 10, 12, 5, f1);
        this.bipedBody.setRotationPoint(0.0F, 0.0F + f2, 0.0F);
        this.bipedRightArm = new ModelRenderer(this, 40, 16);
        this.bipedRightArm.addBox(-5.0F, -5.0F, -2.0F, 5, 12, 5, f1);
        this.bipedRightArm.setRotationPoint(-5.0F, 2.0F + f2, 0.0F);
        this.bipedLeftArm = new ModelRenderer(this, 40, 16);
        this.bipedLeftArm.mirror = true;
        this.bipedLeftArm.addBox(-0.0F, -5.0F, -2.0F, 5, 12, 5, f1);
        this.bipedLeftArm.setRotationPoint(5.0F, 2.0F + f2, 0.0F);
        this.bipedRightLeg = new ModelRenderer(this, 0, 16);
        this.bipedRightLeg.addBox(-3.0F, -3.0F, -2.0F, 5, 15, 5, f1);
        this.bipedRightLeg.setRotationPoint(-1.9F, 15.0F + f2, 0.0F);
        this.bipedLeftLeg = new ModelRenderer(this, 0, 16);
        this.bipedLeftLeg.mirror = true;
        this.bipedLeftLeg.addBox(-1.5F, -3.0F, -2.0F, 5, 15, 5, f1);
        this.bipedLeftLeg.setRotationPoint(1.9F, 15.0F + f2, 0.0F);
	}
	
	 public void render(Entity entity, float p2, float p3, float p4, float p5, float p6, float p7){
	        this.setRotationAngles(p2, p3, p4, p5, p6, p7, entity);
	        this.bipedHead.render(p7);
	        this.bipedBody.render(p7);
	        this.bipedRightArm.render(p7);
	        this.bipedLeftArm.render(p7);
	        this.bipedRightLeg.render(p7);
	        this.bipedLeftLeg.render(p7);
	 }
}
