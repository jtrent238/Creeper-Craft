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

import net.minecraftforge.common.util.*;
import net.minecraft.client.renderer.texture.*;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;
public class mcreator_creeprStone {

public mcreator_creeprStone(){}

public static BlockCreeprStone block;

public static Object instance;public int addFuel(ItemStack fuel){return 0;}
public void serverLoad(FMLServerStartingEvent event){}
public void preInit(FMLPreInitializationEvent event){

GameRegistry.registerBlock(block, "CreeprStone");
}
public void registerRenderers(){}
public void load(){
}


static{

block = (BlockCreeprStone)(new BlockCreeprStone().setHardness(2.0F)
.setResistance(10.0F)
.setLightLevel(0.0F)
.setBlockName("CreeprStone")
.setBlockTextureName("CreeperStone")
.setLightOpacity(0)
.setStepSound(Block.soundTypeStone)
.setCreativeTab(CreativeTabs.tabBlock)
);block.setBlockBounds(0.0F,0.0F,0.0F,1.0F,1.0F,1.0F);
Block.blockRegistry.addObject(186, "CreeprStone", block);
block.setHarvestLevel("pickaxe", 0);
}

public void generateSurface(World world, Random random, int chunkX, int chunkZ){}
public void generateNether(World world, Random random, int chunkX, int chunkZ){}
static class BlockCreeprStone extends Block
{

int a1 = 0,a2 = 0,a3 = 0,a4 = 0,a5 = 0,a6 = 0;

IIcon gor = null, dol = null, st1 = null, st2 = null, st3 = null, st4 = null;

boolean red = false;





protected BlockCreeprStone()
{
        super(Material.ground);

}


public void onBlockAdded(World world, int i, int j, int k){
EntityPlayer entity = Minecraft.getMinecraft().thePlayer;
if(entity!=null&&world!=null){
int le = MathHelper.floor_double((double)(entity.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
world.setBlockMetadataWithNotify(i, j, k, le, 2);
}

world.scheduleBlockUpdate(i, j, k, this, this.tickRate(world));

}
public int isProvidingStrongPower(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5){
return red?1:0;
}
@SideOnly(Side.CLIENT)
@Override
public IIcon getIcon(int i, int par2){

if (i == 0)
return gor;

else if (i == 1)
return dol;

else if (i == 2)
return st1;

else if (i == 3)
return st2;

else if (i == 4)
return st4;

else if (i == 5)
return st3;

else
return gor;

}

@SideOnly(Side.CLIENT)
@Override
public void registerBlockIcons(IIconRegister reg){
this.gor = reg.registerIcon("CreeperStone");
this.dol = reg.registerIcon("CreeperStone");
this.st1 = reg.registerIcon("CreeperStone");
this.st2 = reg.registerIcon("CreeperStone");
this.st3 = reg.registerIcon("CreeperStone");
this.st4 = reg.registerIcon("CreeperStone");
}
public int getRenderType(){
return 0;
}
@Override
public int tickRate(World world)
{
    return 10;
}

public int quantityDropped(Random par1Random){
return 1;
}

}
}
