package knokko.rpg.entity.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.boss.EntityDragon;

public class ModelDragon extends ModelBase{
	
	public ModelRenderer body;
	public ModelRenderer lWing;
	public ModelRenderer rWing;
	public ModelRenderer tail;
	public ModelRenderer lbLegp1;
	public ModelRenderer lbLegp2;
	public ModelRenderer lFoot;
	public ModelRenderer rbLegp1;
	public ModelRenderer rbLegp2;
	public ModelRenderer rFoot;
	public ModelRenderer lfLegp1;
	public ModelRenderer lfLegp2;
	public ModelRenderer lClaw;
	public ModelRenderer rfLegp1;
	public ModelRenderer rfLegp2;
	public ModelRenderer rClaw;
	public ModelRenderer backHorns;
	
	
	public ModelDragon(){
		textureHeight = 512;
		textureWidth = 1024;
		body = new ModelRenderer(this, 0, 0).addBox(-50, -25, -150, 100, 50, 300);
		lWing = new ModelRenderer(this, 500, 0).addBox(50.01F, -20, -100, 150, 5, 100);
		rWing = new ModelRenderer(this, 500, 0).addBox(-200.01F, -20, -100, 150, 5, 100);
		rWing.mirror = true;
		tail = new ModelRenderer(this, 0, 205).addBox(-40, -20, 150.001F, 80, 40, 50);
		tail.addBox(-30, -15, 200.002F, 60, 30, 50);
		tail.addBox(-20, -10, 250.003F, 40, 20, 50);
		tail.addBox(-10, -5, 300.004F, 20, 10, 50);
		lbLegp1 = new ModelRenderer(this, 0, 0).addBox(25, 25.001F, 100, 25, 50, 25);
		lbLegp2 = new ModelRenderer(this, 50, 50).addBox(25, 60, 125, 25, 25, 50);
		lFoot = new ModelRenderer(this, 0, 125).addBox(15, 55, 170, 40, 50, 30);
		rbLegp1 = new ModelRenderer(this, 0, 0).addBox(-50, 25.001F, 100, 25, 50, 25);
		rbLegp2 = new ModelRenderer(this, 50, 50).addBox(-50, 60, 125, 25, 25, 50);
		rFoot = new ModelRenderer(this, 0, 125).addBox(-60, 55, 170, 40, 50, 30);
		lfLegp1 = new ModelRenderer(this, 0, 350).addBox(-50, 25.001F, -120, 20, 40, 20);
		lfLegp2 = new ModelRenderer(this, 80, 350).addBox(-50, 55, -160.001F, 20, 20, 40);
		lClaw = new ModelRenderer(this, 200, 350).addBox(-60, 45, -180.002F, 40, 40, 20);
		rfLegp1 = new ModelRenderer(this, 0, 350).addBox(30, 25.001F, -120, 20, 40, 20);
		rfLegp2 = new ModelRenderer(this, 80, 350).addBox(30, 55, -160.001F, 20, 20, 40);
		rClaw = new ModelRenderer(this, 200, 350).addBox(20, 45, -180.002F, 40, 40, 20);
		backHorns = new ModelRenderer(this, 170, 0);
		backHorns.addBox(-10, -35.001F, -150, 20, 10, 40);
		backHorns.addBox(-10, -35.001F, -70, 20, 10, 40);
		backHorns.addBox(-10, -35.001F, 10, 20, 10, 40);
		backHorns.addBox(-10, -35.001F, 90, 20, 10, 40);
		backHorns.addBox(-10, -30.001F, 150, 20, 10, 40);
		backHorns.addBox(-10, -25.001F, 200, 20, 10, 40);
		backHorns.addBox(-10, -20.001F, 250, 20, 10, 40);
		backHorns.addBox(-10, -15.001F, 300, 20, 10, 40);
	}
	
	public void render(Entity entity, float f1, float f2, float f3, float f4, float f5, float f6){
		body.render(f6);
		lWing.render(f6);
		rWing.render(f6);
		tail.render(f6);
		lbLegp1.render(f6);
		lbLegp2.render(f6);
		lFoot.render(f6);
		rbLegp1.render(f6);
		rbLegp2.render(f6);
		rFoot.render(f6);
		lfLegp1.render(f6);
		lfLegp2.render(f6);
		lClaw.render(f6);
		rfLegp1.render(f6);
		rfLegp2.render(f6);
		rClaw.render(f6);
		backHorns.render(f6);
	}
}
