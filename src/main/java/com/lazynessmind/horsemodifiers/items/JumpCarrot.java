package com.lazynessmind.horsemodifiers.items;

import com.lazynessmind.horsemodifiers.configs.Configs;
import net.minecraft.block.SoundType;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
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
        ITextComponent tooltipOne = new TextComponentTranslation("translation.jumpcarrot.tooltip.one");
        tooltipOne.getStyle().setColor(TextFormatting.GOLD);
        list.add(tooltipOne.getFormattedText());
        list.add(new TextComponentTranslation("translation.jumpcarrot.tooltip.two").getFormattedText());
    }

    @Override
    public boolean itemInteractionForEntity(ItemStack stack, EntityPlayer player, EntityLivingBase entity, EnumHand hand) {
        return super.itemInteractionForEntity(stack, player, entity, hand);
    }

    @Override
    public boolean changeAttributes(EntityLivingBase entity, EntityPlayer playerEntity) {
        //One carrot gives 0.1D
        //Max could be 2.0D but, after 1.0D the horse starts to losing health
        //1.0D = 5.25 blocks
        double currentJump = entity.getAttributeMap().getAttributeInstanceByName("horse.jumpStrength").getAttributeValue();
        if (currentJump != 0 && currentJump < Configs.jumpCarrotLimit) {
            if (currentJump + Configs.jumpAddValue < Configs.jumpCarrotLimit) {
                entity.getAttributeMap().getAttributeInstanceByName("horse.jumpStrength").setBaseValue(currentJump + Configs.jumpAddValue);
            } else {
                entity.getAttributeMap().getAttributeInstanceByName("horse.jumpStrength").setBaseValue(Configs.jumpCarrotLimit);
            }
            entity.playSound(SoundEvents.ENTITY_EXPERIENCE_ORB_PICKUP, SoundType.ANVIL.getVolume() * 0.6F, SoundType.ANVIL.getPitch());
            return true;
        } else {
            ITextComponent tooltipOne = new TextComponentTranslation("translation.jumpcarrot.message");
            tooltipOne.getStyle().setColor(TextFormatting.BLUE);
            playerEntity.sendMessage(tooltipOne);
            return false;
        }

    }
}
