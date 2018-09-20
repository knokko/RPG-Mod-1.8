package knokko.rpg.worldgen.voidworld;

import java.util.Random;

import knokko.rpg.main.s;
import knokko.util.BlockUtils;
import knokko.util.ChunkUtils;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.util.Vec3;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class VoidWorldProvider extends WorldProvider {

	@Override
	public String getDimensionName() {
		return "Void World";
	}
	
	@Override
	public boolean canRespawnHere(){
		return false;
	}
	
	@Override
	public IChunkProvider createChunkGenerator(){
		ChunkPrimer primer = new ChunkPrimer();
		return new VoidChunkProvider(worldObj, primer);
	}
	
	@Override
	public void registerWorldChunkManager(){
		super.registerWorldChunkManager();
		hasNoSky = true;
	}
	
	public boolean isSurfaceWorld(){
		return false;
	}
	
	@Override
	public String getInternalNameSuffix() {
		return s.t + "VoidWorldGenerator";
	}
	
	@Override
	public BiomeGenBase getBiomeGenForCoords(BlockPos pos){
		return BiomeGenBase.sky;
	}
	
	@Override
	public long getWorldTime(){
		 return 18000;
	}
	
	@SideOnly(Side.CLIENT)
	public Vec3 getSkyColor(Entity entity, float f){
		return new Vec3(0, 0, 0);
	}
	
	@Override
	public boolean canDoRainSnowIce(Chunk chunk){
		 return false;
	 }
}
