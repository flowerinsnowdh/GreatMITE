package online.flowerinsnow.greatmite.util;

import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

public final class InventoryUtils {
    private InventoryUtils() {
    }

    public static boolean addAnEmptyBowl(PlayerInventory inventory) {
        return addAnItem(inventory, Items.BOWL);
    }

    public static boolean addAnItem(PlayerInventory inventory, Item item) {
        for (int i = 0; i < inventory.size(); i++) {
            ItemStack stack = inventory.getStack(i);
            if (stack.getItem() == item && stack.getCount() < item.getMaxCount()) {
                stack.increment(1);
                return true;
            }
        }

        for (int i = 0; i < inventory.size(); i++) {
            ItemStack stack = inventory.getStack(i);
            if (stack.isEmpty()) {
                inventory.setStack(i, new ItemStack(item));
                return true;
            }
        }

        inventory.player.dropItem(new ItemStack(item), false);
        return false;
    }
}
