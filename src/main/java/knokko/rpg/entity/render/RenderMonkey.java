package knokko.rpg.entity.render;

import knokko.rpg.main.s;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class RenderMonkey extends RenderBiped{
	
	public static final ResourceLocation texture = new ResourceLocation(s.t + "textures/entities/aapjem.png");
	
	public RenderMonkey(RenderManager manager, ModelBiped model, float f) {
		super(manager, model, f);
	}
	
	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return texture;
	}

}
