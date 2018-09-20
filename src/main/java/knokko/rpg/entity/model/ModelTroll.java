package knokko.rpg.entity.model;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class ModelTroll extends ModelBiped{
	
	public ModelTroll(float f1, float f2, int width, int height){
		textureWidth = width;
		textureHeight = height;
		
		this.bipedHead = new ModelRenderer(this, 72, 0);
        this.bipedHead.addBox(-6.0F, -24.0F, -4.0F, 12, 12, 12, f1);
        this.bipedHead.setRotationPoint(0.0F, 0.0F + f2, 0.0F);
        this.bipedHeadwear = new ModelRenderer(this, 72, 0);
        this.bipedHeadwear.addBox(-6.0F, -24.0F, -4.0F, 12, 12, 12, f1 + 0.5F);
        this.bipedHeadwear.setRotationPoint(0.0F, 0.0F + f2, 0.0F);
        this.bipedBody = new ModelRenderer(this, 0, 0);
        this.bipedBody.addBox(-7.5F, -12.0F, -2.0F, 15, 18, 8, f1);
        this.bipedBody.setRotationPoint(0.0F, 0.0F + f2, 0.0F);
        this.bipedRightArm = new ModelRenderer(this, 0, 0);
        this.bipedRightArm.addBox(-8.8F, -14.0F, -0.0F, 6, 18, 6, f1);
        this.bipedRightArm.setRotationPoint(-5.0F, 2.0F + f2, 0.0F);
        this.bipedLeftArm = new ModelRenderer(this, 0, 0);
        this.bipedLeftArm.mirror = true;
        this.bipedLeftArm.addBox(3.0F, -14.0F, -0.0F, 6, 18, 6, f1);
        this.bipedLeftArm.setRotationPoint(5.0F, 2.0F + f2, 0.0F);
        this.bipedRightLeg = new ModelRenderer(this, 0, 0);
        this.bipedRightLeg.addBox(-6.0F, -6.0F, -2.0F, 8, 18, 8, f1);
        this.bipedRightLeg.setRotationPoint(-1.9F, 20.0F + f2, 0.0F);
        this.bipedLeftLeg = new ModelRenderer(this, 0, 0);
        this.bipedLeftLeg.mirror = true;
        this.bipedLeftLeg.addBox(-1.0F, -6.0F, -2.0F, 8, 18, 8, f1);
        this.bipedLeftLeg.setRotationPoint(1.9F, 20.0F + f2, 0.0F);
	}
	
	 public void setRotationAngles(float f1, float f2, float f3, float f4, float f5, float f9, Entity entity)
	    {
	        this.bipedHead.rotateAngleY = f4 / (180F / (float)Math.PI);
	        this.bipedHead.rotateAngleX = f5 / (180F / (float)Math.PI);
	        this.bipedHeadwear.rotateAngleY = this.bipedHead.rotateAngleY;
	        this.bipedHeadwear.rotateAngleX = this.bipedHead.rotateAngleX;
	        this.bipedRightArm.rotateAngleX = MathHelper.cos(f1 * 0.6662F + (float)Math.PI) * 2.0F * f2 * 0.5F;
	        this.bipedLeftArm.rotateAngleX = MathHelper.cos(f1 * 0.6662F) * 2.0F * f2 * 0.5F;
	        this.bipedRightArm.rotateAngleZ = 0.0F;
	        this.bipedLeftArm.rotateAngleZ = 0.0F;
	        this.bipedRightLeg.rotateAngleX = MathHelper.cos(f1 * 0.6662F) * 1.4F * f2;
	        this.bipedLeftLeg.rotateAngleX = MathHelper.cos(f1 * 0.6662F + (float)Math.PI) * 1.4F * f2;
	        this.bipedRightLeg.rotateAngleY = -6.0F;
	        this.bipedLeftLeg.rotateAngleY = -6.0F;

	        if (this.isRiding)
	        {
	            this.bipedRightArm.rotateAngleX += -((float)Math.PI / 5F);
	            this.bipedLeftArm.rotateAngleX += -((float)Math.PI / 5F);
	            this.bipedRightLeg.rotateAngleX = -((float)Math.PI * 2F / 5F);
	            this.bipedLeftLeg.rotateAngleX = -((float)Math.PI * 2F / 5F);
	            this.bipedRightLeg.rotateAngleY = ((float)Math.PI / 10F);
	            this.bipedLeftLeg.rotateAngleY = -((float)Math.PI / 10F);
	        }

	        if (this.heldItemLeft != 0)
	        {
	            this.bipedLeftArm.rotateAngleX = this.bipedLeftArm.rotateAngleX * 0.5F - ((float)Math.PI / 10F) * (float)this.heldItemLeft;
	        }

	        if (this.heldItemRight != 0)
	        {
	            this.bipedRightArm.rotateAngleX = this.bipedRightArm.rotateAngleX * 0.5F - ((float)Math.PI / 10F) * (float)this.heldItemRight;
	        }

	        this.bipedRightArm.rotateAngleY = 0.0F;
	        this.bipedLeftArm.rotateAngleY = 0.0F;
	        float f6;
	        float f7;

	        if (this.swingProgress > -9990.0F)
	        {
	            f6 = this.swingProgress;
	            this.bipedBody.rotateAngleY = MathHelper.sin(MathHelper.sqrt_float(f6) * (float)Math.PI * 2.0F) * 0.2F;
	            this.bipedRightArm.rotationPointZ = MathHelper.sin(this.bipedBody.rotateAngleY) * 5.0F;
	            this.bipedRightArm.rotationPointX = -MathHelper.cos(this.bipedBody.rotateAngleY) * 5.0F;
	            this.bipedLeftArm.rotationPointZ = -MathHelper.sin(this.bipedBody.rotateAngleY) * 5.0F;
	            this.bipedLeftArm.rotationPointX = MathHelper.cos(this.bipedBody.rotateAngleY) * 5.0F;
	            this.bipedRightArm.rotateAngleY += this.bipedBody.rotateAngleY;
	            this.bipedLeftArm.rotateAngleY += this.bipedBody.rotateAngleY;
	            this.bipedLeftArm.rotateAngleX += this.bipedBody.rotateAngleY;
	            f6 = 1.0F - this.swingProgress;
	            f6 *= f6;
	            f6 *= f6;
	            f6 = 1.0F - f6;
	            f7 = MathHelper.sin(f6 * (float)Math.PI);
	            float f8 = MathHelper.sin(this.swingProgress * (float)Math.PI) * -(this.bipedHead.rotateAngleX - 0.7F) * 0.75F;
	            this.bipedRightArm.rotateAngleX = (float)((double)this.bipedRightArm.rotateAngleX - ((double)f7 * 1.2D + (double)f8));
	            this.bipedRightArm.rotateAngleY += this.bipedBody.rotateAngleY * 2.0F;
	            this.bipedRightArm.rotateAngleZ = MathHelper.sin(this.swingProgress * (float)Math.PI) * -0.4F;
	        }

	        if (this.isSneak)
	        {
	            this.bipedBody.rotateAngleX = 0.5F;
	            this.bipedRightArm.rotateAngleX += 0.4F;
	            this.bipedLeftArm.rotateAngleX += 0.4F;
	            this.bipedRightLeg.rotationPointZ = 4.0F;
	            this.bipedLeftLeg.rotationPointZ = 4.0F;
	            this.bipedRightLeg.rotationPointY = 9.0F;
	            this.bipedLeftLeg.rotationPointY = 9.0F;
	            this.bipedHead.rotationPointY = 1.0F;
	            this.bipedHeadwear.rotationPointY = 1.0F;
	        }
	        else
	        {
	            this.bipedBody.rotateAngleX = 0.0F;
	            this.bipedRightLeg.rotationPointZ = 0.1F;
	            this.bipedLeftLeg.rotationPointZ = 0.1F;
	            this.bipedRightLeg.rotationPointY = 12.0F;
	            this.bipedLeftLeg.rotationPointY = 12.0F;
	            this.bipedHead.rotationPointY = 0.0F;
	            this.bipedHeadwear.rotationPointY = 0.0F;
	        }

	        this.bipedRightArm.rotateAngleZ += MathHelper.cos(f3 * 0.09F) * 0.05F + 0.05F;
	        this.bipedLeftArm.rotateAngleZ -= MathHelper.cos(f3 * 0.09F) * 0.05F + 0.05F;
	        this.bipedRightArm.rotateAngleX += MathHelper.sin(f3 * 0.067F) * 0.05F;
	        this.bipedLeftArm.rotateAngleX -= MathHelper.sin(f3 * 0.067F) * 0.05F;

	        if (this.aimedBow)
	        {
	            f6 = 0.0F;
	            f7 = 0.0F;
	            this.bipedRightArm.rotateAngleZ = 0.0F;
	            this.bipedLeftArm.rotateAngleZ = 0.0F;
	            this.bipedRightArm.rotateAngleY = -(0.1F - f6 * 0.6F) + this.bipedHead.rotateAngleY;
	            this.bipedLeftArm.rotateAngleY = 0.1F - f6 * 0.6F + this.bipedHead.rotateAngleY + 0.4F;
	            this.bipedRightArm.rotateAngleX = -((float)Math.PI / 2F) + this.bipedHead.rotateAngleX;
	            this.bipedLeftArm.rotateAngleX = -((float)Math.PI / 2F) + this.bipedHead.rotateAngleX;
	            this.bipedRightArm.rotateAngleX -= f6 * 1.2F - f7 * 0.4F;
	            this.bipedLeftArm.rotateAngleX -= f6 * 1.2F - f7 * 0.4F;
	            this.bipedRightArm.rotateAngleZ += MathHelper.cos(f3 * 0.09F) * 0.05F + 0.05F;
	            this.bipedLeftArm.rotateAngleZ -= MathHelper.cos(f3 * 0.09F) * 0.05F + 0.05F;
	            this.bipedRightArm.rotateAngleX += MathHelper.sin(f3 * 0.067F) * 0.05F;
	            this.bipedLeftArm.rotateAngleX -= MathHelper.sin(f3 * 0.067F) * 0.05F;
	        }
	    }
}
