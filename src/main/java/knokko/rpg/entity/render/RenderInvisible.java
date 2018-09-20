package knokko.rpg.entity.render;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class RenderInvisible extends Render{

	public RenderInvisible(RenderManager renderManager) {
		super(renderManager);
	}

	@Override
	public void doRender(Entity entity, double d1,double d2, double d3, float f1,float f2) {}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return null;
	}
}
