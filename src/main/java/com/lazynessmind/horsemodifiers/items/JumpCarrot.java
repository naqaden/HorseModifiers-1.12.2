package com.lazynessmind.horsemodifiers.items;

import com.lazynessmind.horsemodifiers.configs.Configs;
import net.minecraft.block.SoundType;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public class JumpCarrot extends Carrot {

    public JumpCarrot() {
        super("jumpcarrot");
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World world, List<String> list, ITooltipFlag tooltipFlag) {
        super.addInformation(stack, world, list, tooltipFlag);
        list.add(TextFormatting.GOLD + "Changes the jump attribute!");
        list.add(TextFormatting.WHITE + "When the limit is reached the affected horse can jump 7 blocks height!");
    }

    @Override
    public boolean itemInteractionForEntity(ItemStack stack, EntityPlayer player, EntityLivingBase entity, EnumHand hand) {
        return super.itemInteractionForEntity(stack, player, entity, hand);
    }

    @Override
    public boolean changeAttributes(EntityLivingBase entity, EntityPlayer playerEntity) {
        //One carrot gives 0.1D
        //Max could be 2.0D but, after 1.0D the horse starts to losing health
        //1.0D = 7 blocks
        //The only value that will be configurable is the amount that the carrot modifies the attribute
        double currentJump = entity.getAttributeMap().getAttributeInstanceByName("horse.jumpStrength").getAttributeValue();
        if (currentJump != 0 && currentJump < 1.0D) {
            entity.getAttributeMap().getAttributeInstanceByName("horse.jumpStrength").setBaseValue(currentJump + Configs.jumpAddValue);
            entity.playSound(SoundEvents.ENTITY_EXPERIENCE_ORB_PICKUP, SoundType.ANVIL.getVolume() * 0.6F, SoundType.ANVIL.getPitch());
            return true;
        } else {
            playerEntity.sendMessage(new TextComponentString(TextFormatting.BLUE + "The limit has been reached!" + TextFormatting.GOLD + " The moon can't be reachable!"));
            return false;
        }

    }
}
