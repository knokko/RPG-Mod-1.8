package knokko.rpg.entity.render;

import knokko.rpg.main.s;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class RenderSoar extends RenderBiped{

	public RenderSoar(RenderManager manager, ModelBiped model, float f) {
		super(manager, model, f);
	}
	
	public ResourceLocation getEntityTexture(Entity entity){
		return new ResourceLocation(s.t + "textures/entities/soar.png");
	}
}
