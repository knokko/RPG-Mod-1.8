package knokko.rpg.entity.render;

import knokko.rpg.entity.monster.EntityEmpire;
import knokko.rpg.main.s;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.layers.LayerBipedArmor;
import net.minecraft.entity.Entity;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class RenderEmpire extends RenderBiped{
	
	public RenderEmpire(RenderManager manager, ModelBiped biped, float f) {
		super(manager, biped, f);
		addLayer(new LayerBipedArmor(this));
	}
	
	public static final ResourceLocation texture = new ResourceLocation(s.t + "textures/entities/empire.png");
	
	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return texture;
	}
}
