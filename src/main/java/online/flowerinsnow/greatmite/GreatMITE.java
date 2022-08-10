package online.flowerinsnow.greatmite;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.player.UseItemCallback;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import online.flowerinsnow.greatmite.item.ItemBowlWater;
import online.flowerinsnow.greatmite.listener.BowlListener;

public class GreatMITE implements ModInitializer {
    public static final ItemBowlWater BOWL_WATER = new ItemBowlWater();
    @Override
    public void onInitialize() {
        Registry.register(Registry.ITEM, new Identifier("greatmite", "water_bowl"), BOWL_WATER);

        UseItemCallback.EVENT.register(new BowlListener());
    }
}
