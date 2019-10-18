package com.lazynessmind.horsemodifiers.items;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public class FlashCarrot extends Carrot {

    public FlashCarrot() {
        super("flashcarrot");
    }


    @Override
    public void addInformation(ItemStack stack, @Nullable World world, List<String> list, ITooltipFlag tooltipFlag) {
        super.addInformation(stack, world, list, tooltipFlag);
        list.add(TextFormatting.GOLD + "Changes the speed attribute!");
        list.add(TextFormatting.WHITE + "When the limit is reached you need to lock the horse in a cell!");
    }

    @Override
    public boolean itemInteractionForEntity(ItemStack stack, EntityPlayer player, EntityLivingBase entity, EnumHand hand) {
        return super.itemInteractionForEntity(stack, player, entity, hand);
    }

    @Override
    public boolean changeAttributes(EntityLivingBase entity, EntityPlayer playerEntity) {
        //Add 0.5D to the speed maybe?
        //The game generates a random number to the speed starting at 0.45D
        //Limit the speed to 1.0D because the server starts shaking in higher values
        //But for the love of goods a config will be added.
        double currentSpeed = entity.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getAttributeValue();
        if (currentSpeed < 1.0D) {
            entity.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(currentSpeed + 0.1D);
            //entity.playSound(SoundEvents.ENTITY_EXPERIENCE_ORB_PICKUP, SoundType.CROP.getVolume() * 0.6F, SoundType.CROP.getPitch());
            System.out.println("Current speed: " + currentSpeed);
            return true;
        } else {
            playerEntity.sendMessage(new TextComponentString(TextFormatting.BLUE + "The limit has been reached!" + TextFormatting.RED + " Don't go to fast!"));
            return false;
        }
    }
}
