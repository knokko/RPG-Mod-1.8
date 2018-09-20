package knokko.rpg.entity.render;

import knokko.rpg.entity.creature.EntityFireEye;
import knokko.rpg.entity.creature.EntityIceEye;
import knokko.rpg.entity.creature.EntityLifeEye;
import knokko.rpg.main.s;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class RenderEye extends RenderLiving{

	public RenderEye(RenderManager manager, ModelBase model, float f) {
		super(manager, model, f);
	}

	@Override
	public ResourceLocation getEntityTexture(Entity entity) {
		if(entity instanceof EntityLifeEye){
			return new ResourceLocation(s.t + "textures/entities/lifeeye.png");
		}
		if(entity instanceof EntityIceEye){
			return new ResourceLocation(s.t + "textures/entities/iceeye.png");
		}
		if(entity instanceof EntityFireEye){
			return new ResourceLocation(s.t + "textures/entities/fireeye.png");
		}
		return null;
	}

}
