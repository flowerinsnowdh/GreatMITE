package online.flowerinsnow.greatmite.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.UseAction;
import net.minecraft.world.World;
import online.flowerinsnow.greatmite.util.InventoryUtils;

/**
 * 用碗装的食物类
 */
public class ItemStackableStew extends Item {
    /**
     * 如果此项为true，则该物品的食用方式为喝而不是吃
     */
    public final boolean drink;

    public ItemStackableStew(int hunger, float saturation) {
        this(hunger, saturation, false);
    }

    public ItemStackableStew(int hunger, float saturation, boolean drink) {
        super(new FabricItemSettings()
                .food(new FoodComponent.Builder().hunger(hunger).saturationModifier(saturation).build())
                .maxCount(4).group(ItemGroup.FOOD));
        this.drink = drink;
    }

    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        super.finishUsing(stack, world, user);
        if (user instanceof PlayerEntity player) {
            InventoryUtils.addAnEmptyBowl(player.getInventory());
        }
        return stack;
    }

    @Override
    public UseAction getUseAction(ItemStack stack) {
        return drink ? UseAction.DRINK : UseAction.EAT;
    }
}
