package knokko.rpg.entity.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelShark extends ModelBase{
	
	public ModelRenderer body;
	public ModelRenderer leftFin;
	public ModelRenderer rightFin;
	public ModelRenderer horn;
	
	public ModelShark(){
		super();
		float f1 = -10;
		float f2 = -35;
		textureWidth = 256;
		textureHeight = 128;
		body = new ModelRenderer(this, 0, 0);
		body.addBox(f1, 0, f2, 25, 25, 80);
		leftFin = new ModelRenderer(this, 0, 0);
		leftFin.addBox(25 + f1, 0, 20 + f2, 25, 15, 15);
		rightFin = new ModelRenderer(this, 0, 0);
		rightFin.addBox(-25 + f1, 0, 20 + f2, 25, 15, 15);
		horn = new ModelRenderer(this, 140, 0);
		horn.addBox(10 + f1, -25, 20 + f2, 5, 30, 15);
	}
	
	public void render(Entity entity, float f1, float f2, float f3, float f4, float f5, float f6){
		body.render(f6);
		leftFin.render(f6);
		rightFin.render(f6);
		horn.render(f6);
	}
}
