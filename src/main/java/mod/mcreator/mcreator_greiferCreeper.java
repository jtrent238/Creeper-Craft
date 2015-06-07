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

@SuppressWarnings("unchecked")
public class mcreator_greiferCreeper {

	public static int mobid = 0;
	public Object instance;

    public void load(){}

    public void generateNether(World world, Random random, int chunkX, int chunkZ){}
	public void generateSurface(World world, Random random, int chunkX, int chunkZ){}
	public int addFuel(ItemStack fuel){
		return 0;
	}
	@SideOnly(Side.CLIENT)
	public void registerRenderers(){
		RenderingRegistry.registerEntityRenderingHandler(mcreator_greiferCreeper.EntitygreiferCreeper.class, new RenderLiving(new ModelCreeper(), 0){protected ResourceLocation getEntityTexture(Entity par1Entity){return new ResourceLocation("66820_skin_201208312236165881.png");}

public void doRender(EntityLiving par1EntityLiving, double par2, double par4, double par6, float par8, float par9){
super.doRender(par1EntityLiving, par2, par4, par6, par8, par9);
BossStatus.setBossStatus((EntitygreiferCreeper)par1EntityLiving, false);
}

});
	}
	public void serverLoad(FMLServerStartingEvent event){}
	public void preInit(FMLPreInitializationEvent event){
		int entityID = EntityRegistry.findGlobalUniqueEntityId();
		mobid = entityID;
		EntityRegistry.registerGlobalEntityID(mcreator_greiferCreeper.EntitygreiferCreeper.class, "greiferCreeper", entityID);
		EntityRegistry.registerModEntity(mcreator_greiferCreeper.EntitygreiferCreeper.class, "greiferCreeper", entityID, instance, 64, 1, true);
		EntityList.entityEggs.put(Integer.valueOf(entityID), new EntityList.EntityEggInfo(entityID, (0 << 16) + (0 << 8) + 0, (0 << 16) + (102 << 8) + 0));
		EntityRegistry.addSpawn(mcreator_greiferCreeper.EntitygreiferCreeper.class, 1, 1, 1, EnumCreatureType.monster ,mcreator_creeperBiome.biome);

        DungeonHooks.addDungeonMob("greiferCreeper", 180);
	}

    /*public Entity spawnEntity(int var1, World var2, double var3, double var5, double var7)
    {
        if(var1==mobid)
                return new mcreator_greiferCreeper.EntitygreiferCreeper(var2);
        else
                return null;
    }*/


   public static class EntitygreiferCreeper extends EntityPigZombie implements IBossDisplayData
	{
		World world = null;
	    public EntitygreiferCreeper(World var1)
	    {
	        super(var1);
	        world = var1;
	        experienceValue = 500;
	        this.isImmuneToFire = true;
	        addRandomArmor();
        	
this.targetTasks.addTask(1, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 2, true));
this.targetTasks.addTask(2, new EntityAIHurtByTarget(this, false));
	    }

	    

	    

	    
protected void addRandomArmor(){

this.setCurrentItemOrArmor(0, new ItemStack(mcreator_creeperTools.block));
this.setCurrentItemOrArmor(4, new ItemStack(mcreator_creeperGemArmor.helmet));
this.setCurrentItemOrArmor(3, new ItemStack(mcreator_creeperGemArmor.body));
this.setCurrentItemOrArmor(2, new ItemStack(mcreator_creeperGemArmor.legs));
this.setCurrentItemOrArmor(1, new ItemStack(mcreator_creeperGemArmor.boots));
}

protected void dropRareDrop(int par1){
this.dropItem(Items.nether_star, 1);
}


    	public boolean isAIEnabled()
		{
			   return true;
    	}

	    /**
	     * Drop 0-2 items of this living's type
	     */
	    protected void dropFewItems(boolean var1, int var2)
	    {
	        this.entityDropItem(new ItemStack(mcreator_creeperTools.block), 0.0F);
	    }

	    /**
	     * Returns the sound this mob makes while it's alive.
	     */
	    protected String getLivingSound()
	    {
	        return "mob.enderdragon.growl";
	    }

	    /**
	     * Returns the sound this mob makes when it is hurt.
	     */
	    protected String getHurtSound()
	    {
	        return "mob.enderdragon.hit";
	    }

	    /**
	     * Returns the sound this mob makes on death.
	     */
	    protected String getDeathSound()
	    {
	        return "mob.enderdragon.end";
	    }

	    public void onStruckByLightning(EntityLightningBolt entityLightningBolt){
			int i = (int)this.posX;
			int j = (int)this.posY;
			int k = (int)this.posZ;
			Entity entity = this;
			
		}

		protected void fall(float l){
			int i = (int)this.posX;
			int j = (int)this.posY;
			int k = (int)this.posZ;
			super.fall(l);
			Entity entity = this;
			
		}

    	public void onCriticalHit(Entity entity) {
			int i = (int)this.posX;
			int j = (int)this.posY;
			int k = (int)this.posZ;
			
		}

		public void onKillEntity(EntityLiving entityLiving){
			int i = (int)this.posX;
			int j = (int)this.posY;
			int k = (int)this.posZ;
			Entity entity = this;
			
		}

		public boolean interact(EntityPlayer entity){
			int i = (int)this.posX;
			int j = (int)this.posY;
			int k = (int)this.posZ;
			
			return true;
		}

		public String getEntityName(){
			return "greiferCreeper";
		}

	}

	

}
