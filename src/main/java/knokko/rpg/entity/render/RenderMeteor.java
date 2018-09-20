package knokko.rpg.entity.render;

import knokko.rpg.entity.projectile.EntityMeteor;
import knokko.rpg.main.s;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.Entity;
import net.minecraft.entity.projectile.EntityFireball;
import net.minecraft.init.Items;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

@SideOnly(Side.CLIENT)
public class RenderMeteor extends Render
{
    private float scale;
    private static final String __OBFID = "CL_00000995";

    public RenderMeteor(RenderManager p_i46176_1_, float p_i46176_2_)
    {
        super(p_i46176_1_);
        this.scale = p_i46176_2_;
    }

    public void doRender(Entity entity, double x, double y, double z, float p_76986_8_, float partialTicks)
    {
        GlStateManager.pushMatrix();
        this.bindEntityTexture(entity);
        GlStateManager.translate((float)x, (float)y, (float)z);
        GlStateManager.enableRescaleNormal();
        float f2 = this.scale;
        GlStateManager.scale(f2 / 1.0F, f2 / 1.0F, f2 / 1.0F);
        Tessellator tessellator = Tessellator.getInstance();
        WorldRenderer worldrenderer = tessellator.getWorldRenderer();
        float f7 = 1.0F;
        float f8 = 0.5F;
        float f9 = 0.25F;
        GlStateManager.rotate(180.0F - this.renderManager.playerViewY, 0.0F, 1.0F, 0.0F);
        GlStateManager.rotate(-this.renderManager.playerViewX, 1.0F, 0.0F, 0.0F);
        worldrenderer.startDrawingQuads();
        worldrenderer.setNormal(0.0F, 1.0F, 0.0F);
        worldrenderer.addVertexWithUV((double)(0.0F - f8), (double)(0.0F - f9), 0.0D, 0, 1);
        worldrenderer.addVertexWithUV((double)(f7 - f8), (double)(0.0F - f9), 0.0D, 1, 1);
        worldrenderer.addVertexWithUV((double)(f7 - f8), (double)(1.0F - f9), 0.0D, 1, 0);
        worldrenderer.addVertexWithUV((double)(0.0F - f8), (double)(1.0F - f9), 0.0D, 0, 0);
        tessellator.draw();
        GlStateManager.disableRescaleNormal();
        GlStateManager.popMatrix();
        super.doRender(entity, x, y, z, p_76986_8_, partialTicks);
    }

    public ResourceLocation getEntityTexture(Entity entity){
		return new ResourceLocation(s.t + "textures/entities/meteor.png");
	}
}
