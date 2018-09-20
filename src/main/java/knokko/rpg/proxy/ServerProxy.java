package knokko.rpg.proxy;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;

public class ServerProxy {
	
	public void registerRenderThings(){}
	public void registerBlocksAndItems(){}
	public void spawnParticle(World world, double x, double y, double z, double red,double green, double blue) {}
	public void spawnParticle(World world, double x, double y, double z, double red,double green, double blue, int renderDistance) {}
	public void setPlayerWalkSpeed(EntityPlayer player, float speed){}
	
	public Side getSide(){
		return Side.SERVER;
	}
}
