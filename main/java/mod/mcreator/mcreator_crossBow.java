package mod.mcreator;//based on master condiguration

import cpw.mods.fml.client.*;
import cpw.mods.fml.client.registry.*;
import cpw.mods.fml.common.*;
import cpw.mods.fml.common.asm.*;
import cpw.mods.fml.common.asm.transformers.*;
import cpw.mods.fml.common.discovery.*;
import cpw.mods.fml.common.discovery.asm.*;
import cpw.mods.fml.common.event.*;
import cpw.mods.fml.common.functions.*;
import cpw.mods.fml.common.network.*;
import cpw.mods.fml.common.registry.*;
import cpw.mods.fml.common.toposort.*;
import cpw.mods.fml.common.versioning.*;
import cpw.mods.fml.relauncher.*;
import cpw.mods.fml.server.*;
import net.minecraft.block.*;
import net.minecraft.block.material.*;
import net.minecraft.client.*;
import net.minecraft.client.audio.*;
import net.minecraft.client.entity.*;
import net.minecraft.client.gui.*;
import net.minecraft.client.gui.achievement.*;
import net.minecraft.client.gui.inventory.*;
import net.minecraft.client.model.*;
import net.minecraft.client.multiplayer.*;
import net.minecraft.client.particle.*;
import net.minecraft.client.renderer.*;
import net.minecraft.client.renderer.culling.*;
import net.minecraft.client.renderer.entity.*;
import net.minecraft.client.renderer.tileentity.*;
import net.minecraft.client.settings.*;
import net.minecraft.command.*;
import net.minecraft.crash.*;
import net.minecraft.creativetab.*;
import net.minecraft.dispenser.*;
import net.minecraft.enchantment.*;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.boss.*;
import net.minecraft.entity.effect.*;
import net.minecraft.entity.item.*;
import net.minecraft.entity.monster.*;
import net.minecraft.entity.passive.*;
import net.minecraft.entity.player.*;
import net.minecraft.entity.projectile.*;
import net.minecraft.inventory.*;
import net.minecraft.item.*;
import net.minecraft.item.crafting.*;
import net.minecraft.nbt.*;
import net.minecraft.network.*;
import net.minecraft.network.rcon.*;
import net.minecraft.pathfinding.*;
import net.minecraft.potion.*;
import net.minecraft.profiler.*;
import net.minecraft.server.*;
import net.minecraft.server.dedicated.*;
import net.minecraft.server.gui.*;
import net.minecraft.server.integrated.*;
import net.minecraft.server.management.*;
import net.minecraft.src.*;
import net.minecraft.stats.*;
import net.minecraft.tileentity.*;
import net.minecraft.util.*;
import net.minecraft.village.*;
import net.minecraft.world.*;
import net.minecraft.world.biome.*;
import net.minecraft.world.chunk.*;
import net.minecraft.world.chunk.storage.*;
import net.minecraft.world.demo.*;
import net.minecraft.world.gen.*;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.layer.*;
import net.minecraft.world.gen.structure.*;
import net.minecraft.world.storage.*;
import net.minecraftforge.classloading.*;
import net.minecraftforge.client.*;
import net.minecraftforge.client.event.*;
import net.minecraftforge.client.event.sound.*;
import net.minecraftforge.common.*;
import net.minecraftforge.event.*;
import net.minecraftforge.event.entity.*;
import net.minecraftforge.event.entity.item.*;
import net.minecraftforge.event.entity.living.*;
import net.minecraftforge.event.entity.minecart.*;
import net.minecraftforge.event.entity.player.*;
import net.minecraftforge.event.terraingen.*;
import net.minecraftforge.event.world.*;
import net.minecraftforge.oredict.*;
import net.minecraftforge.transformers.*;
import net.minecraft.init.*;
import java.util.*;

public class mcreator_crossBow {

public mcreator_crossBow(){}

public static Item block;
public static Object instance;

public void load(){

GameRegistry.addRecipe(new ItemStack(block, 1), new Object[]{
	"012", "345", "678", Character.valueOf('0'), new ItemStack(Blocks.planks, 1), Character.valueOf('1'), new ItemStack(Items.iron_ingot, 1), Character.valueOf('2'), new ItemStack(Blocks.planks, 1), Character.valueOf('3'), new ItemStack(Blocks.planks, 1), Character.valueOf('4'), new ItemStack(Blocks.planks, 1), Character.valueOf('5'), new ItemStack(Blocks.planks, 1), Character.valueOf('6'), new ItemStack(Items.string, 1), Character.valueOf('7'), new ItemStack(Blocks.cobblestone, 1), Character.valueOf('8'), new ItemStack(Items.string, 1), 
});
}

public void registerRenderers(){}
public void generateNether(World world, Random random, int chunkX, int chunkZ){}
public void generateSurface(World world, Random random, int chunkX, int chunkZ){}
public int addFuel(ItemStack fuel){
	return 0;
}
public void serverLoad(FMLServerStartingEvent event){}
public void preInit(FMLPreInitializationEvent event){}

static{
block = (new ItemgGUN());
Item.itemRegistry.addObject(498, "crossBow", block);
}

static class ItemgGUN extends Item{

public ItemgGUN(){
super();
setMaxDamage(1000);
maxStackSize = 1;
setFull3D();
setUnlocalizedName("crossBow");
setTextureName("MUIU9aX");
setCreativeTab(CreativeTabs.tabCombat);
}

public int getMaxItemUseDuration(ItemStack par1ItemStack)
    {
        return 72000;
    }

   public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, final EntityPlayer par3EntityPlayer){

        boolean flag = par3EntityPlayer.capabilities.isCreativeMode || EnchantmentHelper.getEnchantmentLevel(Enchantment.infinity.effectId, par1ItemStack) > 0;

        if (flag || par3EntityPlayer.inventory.hasItem(Items.arrow))
        {
            float f = 1.0F;

            EntityArrow entityarrow = new EntityArrow(par2World, par3EntityPlayer, f * 2.0F)
            {

				public void onCollideWithPlayer(EntityPlayer entity){
					super.onCollideWithPlayer(entity);
					int i = MathHelper.floor_double(this.boundingBox.minX + 0.001D);
					int j = MathHelper.floor_double(this.boundingBox.minY + 0.001D);
					int k = MathHelper.floor_double(this.boundingBox.minZ + 0.001D);
					World world = this.worldObj;
					
				}

            	public void onUpdate(){
				     super.onUpdate();
				     int i = MathHelper.floor_double(this.boundingBox.minX + 0.001D);
				     int j = MathHelper.floor_double(this.boundingBox.minY + 0.001D);
				     int k = MathHelper.floor_double(this.boundingBox.minZ + 0.001D);
				     World world = this.worldObj;
				     Entity entity = (Entity)par3EntityPlayer;
				     
				     if (this.worldObj.getBlock(i, j, k) != Blocks.air || this.worldObj.getBlock(i, j-1, k) != Blocks.air ||
				     this.worldObj.getBlock(i, j+1, k) != Blocks.air ||
				     this.worldObj.getBlock(i+1, j, k) != Blocks.air || this.worldObj.getBlock(i-1, j, k) != Blocks.air
				     || this.worldObj.getBlock(i, j, k+1) != Blocks.air || this.worldObj.getBlock(i, j, k-1) != Blocks.air){
				       	
				       	this.kill();
				     }

            	}

            	};

                entityarrow.setIsCritical(true);
                entityarrow.setDamage(5.0);
                entityarrow.setKnockbackStrength(0);

                if(false){
                	entityarrow.setFire(100);
				}

            	par1ItemStack.damageItem(1, par3EntityPlayer);
            	par2World.playSoundAtEntity(par3EntityPlayer, "random.bow", 1.0F, 1.0F / (itemRand.nextFloat() * 0.4F + 1.2F) + f * 0.5F);

            if (flag)
            {
                entityarrow.canBePickedUp = 2;
            }
            else
            {
                par3EntityPlayer.inventory.consumeInventoryItem(Items.arrow);
            }

            if (!par2World.isRemote)
            {
                par2World.spawnEntityInWorld(entityarrow);
            }
            World world = par2World;
            EntityPlayer entity = par3EntityPlayer;
			int i = (int)entity.posX;
			int j = (int)entity.posY;
			int k = (int)entity.posZ;
            
        }

        return par1ItemStack;
    }



    /**
     * returns the action that specifies what animation to play when the items is being used
     */
    public EnumAction getItemUseAction(ItemStack par1ItemStack)
    {
        return EnumAction.bow;
    }


}}
