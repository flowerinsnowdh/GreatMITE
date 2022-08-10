package online.flowerinsnow.greatmite;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.player.UseItemCallback;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import online.flowerinsnow.greatmite.item.ItemBowlWater;
import online.flowerinsnow.greatmite.listener.BowlListener;
import online.flowerinsnow.greatmite.util.ReflectMirror;

public class GreatMITE implements ModInitializer {
    public static final ItemBowlWater BOWL_WATER = new ItemBowlWater();
    @Override
    public void onInitialize() {
        initMaxCount();

        registerNewItems();

        UseItemCallback.EVENT.register(new BowlListener());
    }

    /**
     * 修改物品的最大堆叠
     */
    private void initMaxCount() {
        ReflectMirror.Item.setMaxCount(Items.BOWL, 16);
    }

    private void registerNewItems() {
        Registry.register(Registry.ITEM, new Identifier("greatmite", "water_bowl"), BOWL_WATER);
    }
}
