package knokko.rpg.blocks.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelTable extends ModelBase{
	public ModelRenderer leg;
	public ModelRenderer top;
	
	public ModelTable(float f1, float f2, int i1, int i2){
		this.textureWidth = i1;
        this.textureHeight = i2;
        leg = new ModelRenderer(this, 0, 0);
        leg.addBox(-1.5F, 9, -1.5F, 3, 15, 3);
        top = new ModelRenderer(this, 0, 0);
        top.addBox(-8, 8, -8, 16, 2, 16);
	}
	
	public void render(Entity entity, float f1, float f2, float f3, float f4, float f5, float f7){
		leg.render(f7);
		top.render(f7);
	}
	
	public ModelTable()
    {
        this(0.0F);
    }

    public ModelTable(float f)
    {
        this(f, 0.0F, 64, 32);
    }
}
