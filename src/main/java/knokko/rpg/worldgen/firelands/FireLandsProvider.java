package knokko.rpg.worldgen.firelands;

import java.util.Random;

import knokko.rpg.entity.creature.EntityFireEye;
import knokko.rpg.entity.monster.FireDragon;
import knokko.rpg.main.s;
import net.minecraft.entity.Entity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class FireLandsProvider extends WorldProvider{

	@Override
	public String getDimensionName() {
		return "Fire Lands";
	}
	
	public IChunkProvider createChunkGenerator(){
		return new FireLandsChunkProvider(worldObj);
	}
	
	@SideOnly(Side.CLIENT)
	public Vec3 getSkyColor(Entity entity, float f){
		return new Vec3(1, 0, 0);
	}
	
	 @SideOnly(Side.CLIENT)
	 public Vec3 getFogColor(float f1, float f2){
	    return new Vec3(1, 0, 0);
	 }
	 
	 @SideOnly(Side.CLIENT)
	 public boolean doesXZShowFog(int x, int z){
	    return true;
	 }
	 
	 @SideOnly(Side.CLIENT)
	 public Vec3 drawClouds(float partticks){
		 return new Vec3(0, 0, 0);
	 }
	 
	 public long getWorldTime(){
		 return 6000;
	 }
	 
	 public boolean canDoRainSnowIce(Chunk chunk){
		 return false;
	 }
	 
	 public static Entity getRandomSpawnAbleEntity(World world, Random random){
		 int r = random.nextInt(100);
		 if(r == 3){
			 return new FireDragon(world);
		 }
		 else {
			 return new EntityFireEye(world);
		 }
	 }

	@Override
	public String getInternalNameSuffix() {
		return s.t + "firelands";
	}
	
	@Override
	public BiomeGenBase getBiomeGenForCoords(BlockPos pos){
		return BiomeGenBase.hell;
	}
}
