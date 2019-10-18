package com.lazynessmind.horsemodifiers.registry;

import com.lazynessmind.horsemodifiers.interfaces.IHasModel;
import com.lazynessmind.horsemodifiers.items.HMItem;
import com.lazynessmind.horsemodifiers.items.HMItems;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.common.ForgeVersion;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber
public class RegistryEvents {

    @SubscribeEvent
    public static void onItemsRegistry(final RegistryEvent.Register<Item> event) {
        event.getRegistry().registerAll(HMItems.ITEMS.toArray(new Item[0]));
    }

    @SubscribeEvent
    public static void onModelRegister(ModelRegistryEvent event) {
        for (Item item : HMItems.ITEMS) {
            if (item instanceof IHasModel) {
                ((IHasModel) item).registerModels();
                ((IHasModel) item).registerModels(item.getMetadata(item.getDefaultInstance()));
            }
        }
    }

    //Maybe still broken at the moment
    @SubscribeEvent
    public static void onWorldLoaded(EntityJoinWorldEvent joinWorldEvent) {
        System.out.println("Update");
        if (!joinWorldEvent.getWorld().isRemote) {
            if (joinWorldEvent.getEntity() instanceof EntityPlayer) {
                EntityPlayer player = (EntityPlayer) joinWorldEvent.getEntity();
                if (ForgeVersion.getStatus() == ForgeVersion.Status.OUTDATED) {
                    System.out.println("Update");
                    player.sendMessage(new TextComponentString(TextFormatting.BOLD + "[Farming Tools]" + TextFormatting.RED + " Current version is outdated! " + TextFormatting.WHITE + "Check the mod page to update. :)"));
                }
            }
        }
    }
}
