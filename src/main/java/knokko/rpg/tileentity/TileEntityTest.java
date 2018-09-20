package knokko.rpg.tileentity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.gui.IUpdatePlayerListBox;
import net.minecraft.tileentity.TileEntity;

public class TileEntityTest extends TileEntity implements IUpdatePlayerListBox{
	
	protected ItemStack[] inventory = new ItemStack[5];
	
	@Override
	public void update(){
		if(inventory != null){
			if(inventory.length > 0){
				System.out.println(inventory[0]);
			}
		}
	}
	
	public void activated(EntityPlayer player){
		inventory[0] = player.getCurrentEquippedItem();
	}
	
	public void writeToNBT(NBTTagCompound nbt){
		super.writeToNBT(nbt);
		nbt.setInteger("inventoryLength", inventory.length);
		int i = 0;
		while(i < inventory.length && inventory[i] != null){
			NBTTagCompound nbt2 = new NBTTagCompound();
			inventory[i].writeToNBT(nbt2);
			System.out.println("[writeToNBT] nbt2 = " + nbt2);
			nbt.setTag("stack " + i, nbt2);
			++i;
		}
	}
	public void readFromNBT(NBTTagCompound nbt){
		super.readFromNBT(nbt);
		inventory = new ItemStack[nbt.getInteger("inventoryLength")];
		int i = 0;
		while(i < inventory.length){
			System.out.println("[readFromNBT] stack " + i + " = " + nbt.getTag("stack " + i));
			inventory[i] = ItemStack.loadItemStackFromNBT(nbt.getCompoundTag("stack " + i));
			++i;
		}
	}
}
