package knokko.rpg.entity.render;

import knokko.rpg.main.s;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class RenderFireLander extends RenderBiped {

	public RenderFireLander(RenderManager manager, ModelBiped model, float shadow) {
		super(manager, model, shadow);
	}
	
	@Override
	public ResourceLocation getEntityTexture(Entity entity){
		return new ResourceLocation(s.t + "textures/entities/firelander.png");
	}

}
