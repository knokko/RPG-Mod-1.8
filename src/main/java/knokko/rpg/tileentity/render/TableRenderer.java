package knokko.rpg.tileentity.render;

import org.lwjgl.opengl.GL11;

import knokko.rpg.blocks.model.ModelTable;
import knokko.rpg.entity.model.ModelEye;
import knokko.rpg.main.s;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class TableRenderer extends TileEntitySpecialRenderer{
	public final ModelTable model;
	
	public TableRenderer(){
		model = new ModelTable();
	}
	@Override
	public void renderTileEntityAt(TileEntity tileEntity, double x, double y, double z, float scale, int i) {
        GL11.glPushMatrix();
        GL11.glTranslatef((float) x + 0.5F, (float) y + 1.5F, (float) z + 0.5F);
        ResourceLocation textures = (new ResourceLocation("textures/blocks/planks_oak.png")); 
        Minecraft.getMinecraft().renderEngine.bindTexture(textures);                     
        GL11.glPushMatrix();
        GL11.glRotatef(180F, 0.0F, 0.0F, 1.0F);
        this.model.render((Entity)null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
        GL11.glPopMatrix();
        GL11.glPopMatrix();
	}
	 private void adjustRotatePivotViaMeta(World world, int x, int y, int z) {
         GL11.glPushMatrix();
         GL11.glPopMatrix();
 }
}
