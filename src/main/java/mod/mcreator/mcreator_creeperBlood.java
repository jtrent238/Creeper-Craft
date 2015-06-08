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
import java.util.Random;

public class mcreator_creeperBlood {

public static Object instance;

public mcreator_creeperBlood(){}

public static Block block2 = new creeperBloodStill().setBlockName("Creeper Blood2");
public static Block block = new creeperBloodFlowing().setBlockName("Creeper Blood");

public void preInit(FMLPreInitializationEvent event){}

public void load(){
	GameRegistry.registerBlock(block2, "Creeper Blood2");
	GameRegistry.registerBlock(block, "Creeper Blood");
    
GameRegistry.addRecipe(new ItemStack(block, 1), new Object[]{
	"X1X", "345", "X7X", Character.valueOf('1'), new ItemStack(mcreator_creeperMeatRaw.block, 1), Character.valueOf('3'), new ItemStack(mcreator_creeperMeatRaw.block, 1), Character.valueOf('4'), new ItemStack(mcreator_creeperGem.block, 1), Character.valueOf('5'), new ItemStack(mcreator_creeperMeatRaw.block, 1), Character.valueOf('7'), new ItemStack(mcreator_creeperMeatRaw.block, 1), 
});

}

static{
	Block.blockRegistry.addObject(193, "Creeper Blood", block);
	Block.blockRegistry.addObject(194, "Creeper Blood2", block2);
}

public void generateNether(World world, Random random, int chunkX, int chunkZ){}
public void generateSurface(World world, Random random, int chunkX, int chunkZ){}
public int addFuel(ItemStack fuel){
	return 0;
}
public void registerRenderers(){}
public void serverLoad(FMLServerStartingEvent event){}

static class creeperBloodFlowing extends BlockDynamicLiquid {

        public creeperBloodFlowing() {
                super(Material.water);
                this.blockHardness = 100.0F;
                this.setLightOpacity(3);
        }

	@SideOnly(Side.CLIENT)
    public int colorMultiplier(IBlockAccess par1IBlockAccess, int par2, int par3, int par4){
    	return 0x006600;
    }

}

static class creeperBloodStill extends BlockStaticLiquid {

        public creeperBloodStill() {
                super(Material.water);
                this.blockHardness = 100.0F;
                this.setLightOpacity(3);
                this.disableStats();
        }

        	@SideOnly(Side.CLIENT)
		    public int colorMultiplier(IBlockAccess par1IBlockAccess, int par2, int par3, int par4){
		    	return 0x006600;
			}

}

}
