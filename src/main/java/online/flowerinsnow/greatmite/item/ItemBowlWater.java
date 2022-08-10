package online.flowerinsnow.greatmite.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.stat.Stats;
import net.minecraft.util.UseAction;
import net.minecraft.world.World;
import online.flowerinsnow.greatmite.util.InventoryUtils;

public class ItemBowlWater extends Item {
    public ItemBowlWater() {
        super(new FabricItemSettings()
                .group(ItemGroup.FOOD).maxCount(4).food(
                        new FoodComponent.Builder().alwaysEdible().saturationModifier(0.0F).hunger(0).build()
                ));
    }

    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        if (user instanceof ServerPlayerEntity player) {
            Criteria.CONSUME_ITEM.trigger(player, stack);
            player.incrementStat(Stats.USED.getOrCreateStat(this));
        }

        if (user instanceof PlayerEntity player && !player.isCreative()) {
            stack.decrement(1);
            if (stack.isEmpty()) {
                return new ItemStack(Items.BOWL);
            } else {
                InventoryUtils.addAnEmptyBowl(player.getInventory());
            }
        }
        return stack;
    }

    @Override
    public UseAction getUseAction(ItemStack stack) {
        return UseAction.DRINK;
    }
}
