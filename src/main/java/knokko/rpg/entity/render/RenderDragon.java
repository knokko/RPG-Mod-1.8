package knokko.rpg.entity.render;

import knokko.rpg.main.s;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class RenderDragon extends RenderLiving{

	public RenderDragon(RenderManager manager, ModelBase model, float shadow) {
		super(manager, model, shadow);
	}

	@Override
	public ResourceLocation getEntityTexture(Entity entity) {
		return new ResourceLocation(s.t + "textures/entities/firedragon.png");
	}

}
