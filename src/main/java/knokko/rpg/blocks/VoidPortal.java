package knokko.rpg.blocks;

import java.util.Random;

import knokko.rpg.main.KnokkoRPG;
import knokko.rpg.main.s;
import knokko.rpg.teleporters.VoidTeleporter;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

public class VoidPortal extends Block{

	public VoidPortal() {
		super(Material.portal);
		setCreativeTab(CreativeTabs.tabDecorations);
		setUnlocalizedName("voidportal");
		setBlockUnbreakable();
		setResistance(6000000);
		setLightLevel(0.5F);
	}
	
	@Override
	public boolean isNormalCube(){
		return false;
	}
	
	@Override
	public boolean isBlockSolid(IBlockAccess acces, BlockPos pos, EnumFacing side){
		return false;
	}
	
	@Override
	public void onEntityCollidedWithBlock(World world, BlockPos pos, Entity entity){
		if(!world.isRemote && entity instanceof EntityPlayerMP){
			if(entity.dimension == KnokkoRPG.voidWorldId){
				//entity.travelToDimension(dimensionId);
				//MinecraftServer.getServer().getConfigurationManager().transferPlayerToDimension((EntityPlayerMP) entity, 0, new VoidTeleporter(MinecraftServer.getServer().worldServerForDimension(0)));
				MinecraftServer.getServer().getConfigurationManager().transferEntityToWorld(entity, 0, (WorldServer) world, MinecraftServer.getServer().worldServerForDimension(0), new VoidTeleporter(MinecraftServer.getServer().worldServerForDimension(0)));
			}
			else {
				MinecraftServer.getServer().getConfigurationManager().transferEntityToWorld(entity, KnokkoRPG.voidWorldId, (WorldServer) world, MinecraftServer.getServer().worldServerForDimension(KnokkoRPG.voidWorldId), new VoidTeleporter(MinecraftServer.getServer().worldServerForDimension(KnokkoRPG.voidWorldId)));
				//MinecraftServer.getServer().getConfigurationManager().transferPlayerToDimension((EntityPlayerMP) entity, KnokkoRPG.voidWorldId, new VoidTeleporter(MinecraftServer.getServer().worldServerForDimension(KnokkoRPG.voidWorldId)));
			}
			entity.timeUntilPortal = 100;
		}
	}
	
	
	@Override
	public boolean isCollidable(){
		return true;
	}
	
	@Override
	public boolean isOpaqueCube(){
		return false;
	}
	
	@Override
	public void randomDisplayTick(World world, BlockPos pos, IBlockState state, Random random){
		int times = 0;
		while(times < 50){
			world.spawnParticle(EnumParticleTypes.REDSTONE, pos.getX() - 1 + 2 * random.nextDouble(), pos.getY() - 1 + 2 * random.nextDouble(), pos.getZ() - 1 + 2 * random.nextDouble(), 0.1, 0.1, 0.1);
			++times;
		}
	}
}
