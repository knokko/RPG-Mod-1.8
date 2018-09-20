package knokko.rpg.blocks;

import java.util.Random;

import knokko.rpg.main.KnokkoRPG;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class FirePortal extends Block {

	public FirePortal() {
		super(Material.portal);
		setBlockUnbreakable();
		setUnlocalizedName("fireportal");
		setResistance(6000000);
		setLightLevel(0.5F);
		setCreativeTab(CreativeTabs.tabDecorations);
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
		if(!world.isRemote){
			if(entity.dimension == KnokkoRPG.fireLandsId){
				entity.travelToDimension(0);
			}
			else {
				entity.travelToDimension(KnokkoRPG.fireLandsId);
			}
		}
		if(!world.isRemote){
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
			world.spawnParticle(EnumParticleTypes.REDSTONE, pos.getX() - 1 + 2 * random.nextDouble(), pos.getY() - 1 + 2 * random.nextDouble(), pos.getZ() - 1 + 2 * random.nextDouble(), 1, 0.1, 0.1);
			++times;
		}
	}
}
