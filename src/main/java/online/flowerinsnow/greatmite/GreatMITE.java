package online.flowerinsnow.greatmite;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.player.UseItemCallback;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import online.flowerinsnow.greatmite.item.ItemBowlWater;
import online.flowerinsnow.greatmite.item.ItemStackableStew;
import online.flowerinsnow.greatmite.listener.BowlListener;
import online.flowerinsnow.greatmite.util.ReflectMirror;

public class GreatMITE implements ModInitializer {
    public static final String MODID = "greatmite";
    public static final ItemBowlWater BOWL_WATER = new ItemBowlWater();
    public static final ItemStackableStew SALAD = new ItemStackableStew(1, 1.0F);
    public static final ItemStackableStew BEEF_STEW = new ItemStackableStew(16, 16.0F);
    public static final ItemStackableStew BOWL_MILK = new ItemStackableStew(1, 0.0F, true);
    public static final ItemStackableStew CEREAL = new ItemStackableStew(2, 4.0F);
    public static final ItemStackableStew CHICKEN_SOUP = new ItemStackableStew(10, 10.0F, true);
    public static final ItemStackableStew CREAM_OF_MUSHROOM_SOUP = new ItemStackableStew(5, 3.0F, true);
    public static final ItemStackableStew CREAM_OF_VEGETABLE_SOUP = new ItemStackableStew(7, 7.0F, true);
    public static final ItemStackableStew ICE_CREAM = new ItemStackableStew(7, 7.0F);
    public static final ItemStackableStew MASHED_POTATO = new ItemStackableStew(8, 12.0F);
    public static final ItemStackableStew PORRIDGE = new ItemStackableStew(2, 4.0F);
    public static final ItemStackableStew PUMPKIN_SOUP = new ItemStackableStew(2, 1.0F);
    public static final ItemStackableStew SORBET = new ItemStackableStew(2, 4.0F);
    public static final ItemStackableStew VEGETABLE_SOUP = new ItemStackableStew(6, 6.0F, true);

    @Override
    public void onInitialize() {
        initMaxCount();
        modifyFood();

        registerNewItems();

        UseItemCallback.EVENT.register(new BowlListener());
    }

    /**
     * 修改物品的最大堆叠
     */
    private void initMaxCount() {
        ReflectMirror.Item.setMaxCount(Items.BOWL, 16);
        ReflectMirror.Item.setMaxCount(Items.SUGAR, 16);
        // TODO 更多物品堆叠上限待修改
    }

    /**
     * 将原版不能吃的东西变得能吃，或修改植物营养值
     */
    private void modifyFood() {
        ReflectMirror.Item.setFoodcomponent(Items.WHEAT_SEEDS, new FoodComponent.Builder().alwaysEdible().hunger(0).saturationModifier(1.0F).build());
        ReflectMirror.Item.setFoodcomponent(Items.PUMPKIN_SEEDS, new FoodComponent.Builder().alwaysEdible().hunger(2).saturationModifier(1.0F).build());
        ReflectMirror.Item.setFoodcomponent(Items.MELON_SEEDS, new FoodComponent.Builder().alwaysEdible().hunger(1).saturationModifier(1.0F).build());
        ReflectMirror.Item.setFoodcomponent(Items.BEETROOT_SEEDS, new FoodComponent.Builder().alwaysEdible().hunger(0).saturationModifier(1.0F).build());
        ReflectMirror.Item.setFoodcomponent(Items.SUGAR, new FoodComponent.Builder().alwaysEdible().hunger(0).saturationModifier(1.0F).build());
        ReflectMirror.Item.setFoodcomponent(Items.NETHER_WART, new FoodComponent.Builder().alwaysEdible().hunger(0).saturationModifier(1.0F).build());
    }

    private void registerNewItems() {
        Registry.register(Registry.ITEM, new Identifier(MODID, "water_bowl"), BOWL_WATER);
        Registry.register(Registry.ITEM, new Identifier(MODID, "salad"), SALAD);
        Registry.register(Registry.ITEM, new Identifier(MODID, "beef_stew"), BEEF_STEW);
        Registry.register(Registry.ITEM, new Identifier(MODID, "bowl_milk"), BOWL_MILK);
        Registry.register(Registry.ITEM, new Identifier(MODID, "cereal"), CEREAL);
        Registry.register(Registry.ITEM, new Identifier(MODID, "chicken_soup"), CHICKEN_SOUP);
        Registry.register(Registry.ITEM, new Identifier(MODID, "cream_of_mushroom_soup"), CREAM_OF_MUSHROOM_SOUP);
        Registry.register(Registry.ITEM, new Identifier(MODID, "cream_of_vegetable_soup"), CREAM_OF_VEGETABLE_SOUP);
        Registry.register(Registry.ITEM, new Identifier(MODID, "ice_cream"), ICE_CREAM);
        Registry.register(Registry.ITEM, new Identifier(MODID, "mashed_potato"), MASHED_POTATO);
        Registry.register(Registry.ITEM, new Identifier(MODID, "porridge"), PORRIDGE);
        Registry.register(Registry.ITEM, new Identifier(MODID, "pumpkin_soup"), PUMPKIN_SOUP);
        Registry.register(Registry.ITEM, new Identifier(MODID, "sorbet"), SORBET);
        Registry.register(Registry.ITEM, new Identifier(MODID, "vegetable_soup"), VEGETABLE_SOUP);
    }
}
