package knokko.rpg.effect;

import java.util.Random;

import knokko.util.Position;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.World;

public class EnergyBall {
	public static void makeTinyBall(World world, double x, double y, double z, double color1, double color2, double color3){
		world.spawnParticle(EnumParticleTypes.REDSTONE, x, y, z, color1, color2, color3);
	}
	/**
	 * This method will create a star with the given colors.
	 * @param world The world where the star will get created.
	 * @param x The x position of the star.
	 * @param y The y position of the star.
	 * @param z The z position of the star.
	 * @param red How red the particles have to be.
	 * @param green How green the particles has to be.
	 * @param purple How purple the particles has to be.
	 * @param maxRange The max range that particles can spawn from the base.
	 */
	public static void makeStar(World world, double x, double y, double z, double red, double green, double purple, double maxRange){
		double range = 0;
		while(range < maxRange){
			range += 0.1;
			world.spawnParticle(EnumParticleTypes.REDSTONE, x + 0, y + 0, z + 0, red, green, purple);
			world.spawnParticle(EnumParticleTypes.REDSTONE, x + range, y + range, z + range, red, green, purple);
			world.spawnParticle(EnumParticleTypes.REDSTONE, x + range, y + range, z + 0, red, green, purple);
			world.spawnParticle(EnumParticleTypes.REDSTONE, x + range, y + range, z - range, red, green, purple);
			world.spawnParticle(EnumParticleTypes.REDSTONE, x + range, y + 0, z + range, red, green, purple);
			world.spawnParticle(EnumParticleTypes.REDSTONE, x + range, y + 0, z + 0, red, green, purple);
			world.spawnParticle(EnumParticleTypes.REDSTONE, x + range, y + 0, z - range, red, green, purple);
			world.spawnParticle(EnumParticleTypes.REDSTONE, x + range, y - range, z + range, red, green, purple);
			world.spawnParticle(EnumParticleTypes.REDSTONE, x + range, y - range, z + 0, red, green, purple);
			world.spawnParticle(EnumParticleTypes.REDSTONE, x + range, y - range, z - range, red, green, purple);
			
			world.spawnParticle(EnumParticleTypes.REDSTONE, x + 0, y + 0, z + 0, red, green, purple);
			world.spawnParticle(EnumParticleTypes.REDSTONE, x + 0, y + range, z + range, red, green, purple);
			world.spawnParticle(EnumParticleTypes.REDSTONE, x + 0, y + range, z + 0, red, green, purple);
			world.spawnParticle(EnumParticleTypes.REDSTONE, x + 0, y + range, z - range, red, green, purple);
			world.spawnParticle(EnumParticleTypes.REDSTONE, x + 0, y + 0, z + range, red, green, purple);
			world.spawnParticle(EnumParticleTypes.REDSTONE, x + 0, y + 0, z + 0, red, green, purple);
			world.spawnParticle(EnumParticleTypes.REDSTONE, x + 0, y + 0, z - range, red, green, purple);
			world.spawnParticle(EnumParticleTypes.REDSTONE, x + 0, y - range, z + range, red, green, purple);
			world.spawnParticle(EnumParticleTypes.REDSTONE, x + 0, y - range, z + 0, red, green, purple);
			world.spawnParticle(EnumParticleTypes.REDSTONE, x + 0, y - range, z - range, red, green, purple);
			
			world.spawnParticle(EnumParticleTypes.REDSTONE, x + 0, y + 0, z + 0, red, green, purple);
			world.spawnParticle(EnumParticleTypes.REDSTONE, x - range, y + range, z + range, red, green, purple);
			world.spawnParticle(EnumParticleTypes.REDSTONE, x - range, y + range, z + 0, red, green, purple);
			world.spawnParticle(EnumParticleTypes.REDSTONE, x - range, y + range, z - range, red, green, purple);
			world.spawnParticle(EnumParticleTypes.REDSTONE, x - range, y + 0, z + range, red, green, purple);
			world.spawnParticle(EnumParticleTypes.REDSTONE, x - range, y + 0, z + 0, red, green, purple);
			world.spawnParticle(EnumParticleTypes.REDSTONE, x - range, y + 0, z - range, red, green, purple);
			world.spawnParticle(EnumParticleTypes.REDSTONE, x - range, y - range, z + range, red, green, purple);
			world.spawnParticle(EnumParticleTypes.REDSTONE, x - range, y - range, z + 0, red, green, purple);
			world.spawnParticle(EnumParticleTypes.REDSTONE, x - range, y - range, z - range, red, green, purple);
		}
	}
	/**
	 * This method creates a colorful ball of particles that are just made for effects.
	 * The ball doesn't hurt anything, it is my replacement for fireworks.
	 * @param world The world to create the ball.
	 * @param x The x position of the ball.
	 * @param y The y position of the ball.
	 * @param z The z position of the ball.
	 * @param maxRange The max squared distance the particles can spawn from the base.
	 * @param red How red the ball has to be.
	 * @param green How green the ball has to be.
	 * @param purple How purple the ball has to be.
	 * @param maxTimes The amount of particles that will spawn.
	 */
	public static void makeBall(World world, double x, double y, double z, double maxRange, double red, double green, double purple, int maxTimes){
		if(!world.isRemote)
			return;
		Random random = new Random();
		Position pos = new Position(x, y, z);
		Position pos2;
		int times = 0;
		while(times < maxTimes){
			pos2 = new Position(x - maxRange + random.nextDouble() * maxRange * 2, y - maxRange + random.nextDouble() * maxRange * 2, z - maxRange + random.nextDouble() * maxRange * 2);
			if(pos.getDistance(pos2) <= maxRange){
				pos2.spawnParticle(world, red, green, purple);
			}
			++times;
		}
	}
}
