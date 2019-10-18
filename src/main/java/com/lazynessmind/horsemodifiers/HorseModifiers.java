package com.lazynessmind.horsemodifiers;

import com.lazynessmind.horsemodifiers.proxy.CommonProxy;
import com.lazynessmind.horsemodifiers.tab.Tabs;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = Const.MOD_ID, name = Const.NAME, version = Const.VERSION, updateJSON = Const.UPDATE_JSON)
public class HorseModifiers {

    @Mod.Instance
    public static HorseModifiers instance;

    @SidedProxy(clientSide = Const.CLIENT_PROXY, serverSide = Const.COMMON_PROXY)
    public static CommonProxy common;

    public static Tabs tabs = new Tabs();

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {

    }

    @EventHandler
    public void init(FMLInitializationEvent event) {

    }
}
