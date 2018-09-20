package knokko.rpg.entity.render;

import knokko.rpg.entity.creature.EntityLifeEye;
import knokko.rpg.entity.monster.EntityTroll;
import knokko.rpg.main.s;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelIronGolem;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.ResourceLocation;

public class RenderTroll extends RenderBiped{
	
	private static final ResourceLocation texture = new ResourceLocation("knokkorpg:textures/entities/troll.png");

	public RenderTroll(RenderManager manager, ModelBiped model, float f) {
		super(manager, model, f);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return texture;
	}
}
