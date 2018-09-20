package knokko.rpg.entity.main;

import knokko.rpg.entity.creature.*;
import knokko.rpg.entity.data.*;
import knokko.rpg.entity.effect.*;
import knokko.rpg.entity.minion.*;
import knokko.rpg.entity.monster.*;
import knokko.rpg.entity.projectile.*;

public class RPGentities {
	public static void registerEntities(){
		EntityHandler.registerEntity(EntityBlood.class, "Blood");
		EntityHandler.registerEntity(EntityEnergyBall.class, "EnergyBall");
		EntityHandler.registerEntity(EntityEyeGroupData.class, "EyeGroupData");
		EntityHandler.registerEntity(EntityMonkeyGroupData.class, "MonkeyGroupData");
		EntityHandler.registerEntity(UndeadTeam.class, "UndeadTeam");
		EntityHandler.registerEntity(EntityMeteor.class, "Meteor");
		EntityHandler.registerCreatures(EntityLifeEye.class, "LifeEye");
		EntityHandler.registerSnowCreature(EntityIceEye.class, "IceEye");
		EntityHandler.registerMountainMonster(EntityTroll.class, "Troll");
		EntityHandler.registerEntity(EntityIceSpell.class, "IceSpell");
		EntityHandler.registerEntity(EntityFireSpell.class, "FireSpell");
		EntityHandler.registerEntity(EntityDarkPulse.class, "DarkPulse");
		EntityHandler.registerJungleCreature(EntityMonkey.class, "Monkey");
		EntityHandler.registerUnspawnableMob(EntityEmpire.class, "Empire");
		EntityHandler.registerUnspawnableMob(EntityChow.class, "Chow");
		EntityHandler.registerUnspawnableMob(EntityDreadLord.class, "DreadLord");
		EntityHandler.registerUnspawnableMob(EntitySoar.class, "Soar");
		EntityHandler.registerUnspawnableMob(FireDragon.class, "FireDragon");
		EntityHandler.registerEntity(EntityExplosiveSpell.class, "ExplosiveSpell");
		EntityHandler.registerEntity(EntityLaser.class, "Laser");
		EntityHandler.registerNetherMob(EntityFireEye.class, "FireEye");
		EntityHandler.registerNetherMob(EntityLavaShark.class, "LavaShark");
		EntityHandler.registerUnspawnableMob(EntityFireLander.class, "FireLander");
		EntityHandler.registerUnspawnableMob(EntityPowerBlaze.class, "PowerBlaze");
	}
}
