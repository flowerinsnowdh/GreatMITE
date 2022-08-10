package online.flowerinsnow.greatmite;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.player.UseItemCallback;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
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
    public static final ItemStackableStew SALAD = new ItemStackableStew(1, 0.05F);
    public static final ItemStackableStew BEEF_STEW = new ItemStackableStew(16, 0.8F);
    public static final ItemStackableStew BOWL_MILK = new ItemStackableStew(1, 0.0F, true);
    public static final ItemStackableStew CEREAL = new ItemStackableStew(2, 0.2F);
    public static final ItemStackableStew CHICKEN_SOUP = new ItemStackableStew(10, 0.5F, true);
    public static final ItemStackableStew CREAM_OF_MUSHROOM_SOUP = new ItemStackableStew(5, 0.15F, true);
    public static final ItemStackableStew CREAM_OF_VEGETABLE_SOUP = new ItemStackableStew(7, 0.35F, true);
    public static final ItemStackableStew ICE_CREAM = new ItemStackableStew(7, 0.35F);
    public static final ItemStackableStew MASHED_POTATO = new ItemStackableStew(8, 0.6F);
    public static final ItemStackableStew PORRIDGE = new ItemStackableStew(2, 0.2F);
    public static final ItemStackableStew PUMPKIN_SOUP = new ItemStackableStew(2, 0.05F);
    public static final ItemStackableStew SORBET = new ItemStackableStew(2, 0.2F);
    public static final ItemStackableStew VEGETABLE_SOUP = new ItemStackableStew(6, 0.3F, true);
    public static final Item BANANA = new Item(new FabricItemSettings().group(ItemGroup.FOOD).maxCount(16)
            .food(new FoodComponent.Builder().hunger(1).saturationModifier(0.1F).build()));
    public static final Item BLUEBERRIES = new Item(new FabricItemSettings().group(ItemGroup.FOOD).maxCount(16)
            .food(new FoodComponent.Builder().hunger(1).saturationModifier(0.05F).build()));
    public static final Item CHEESE = new Item(new FabricItemSettings().group(ItemGroup.FOOD).maxCount(16)
            .food(new FoodComponent.Builder().hunger(3).saturationModifier(0.15F).build()));
    public static final Item CHOCOLATE = new Item(new FabricItemSettings().group(ItemGroup.FOOD).maxCount(16)
            .food(new FoodComponent.Builder().hunger(3).saturationModifier(0.15F).build()));
    public static final Item DOUGH = new Item(new FabricItemSettings().group(ItemGroup.FOOD).maxCount(16)
            .food(new FoodComponent.Builder().hunger(2).saturationModifier(0.3F).build()));
    public static final Item FLOUR = new Item(new FabricItemSettings().group(ItemGroup.FOOD).maxCount(16));
    public static final Item LEMON = new Item(new FabricItemSettings().group(ItemGroup.FOOD).maxCount(16));
    public static final Item ONION = new Item(new FabricItemSettings().group(ItemGroup.FOOD).maxCount(16)
            .food(new FoodComponent.Builder().hunger(1).saturationModifier(0.05F).build()));
    public static final Item ORANGE = new Item(new FabricItemSettings().group(ItemGroup.FOOD).maxCount(16)
            .food(new FoodComponent.Builder().hunger(1).saturationModifier(0.1F).build()));

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
        ReflectMirror.Item.setFoodcomponent(Items.WHEAT_SEEDS, new FoodComponent.Builder().alwaysEdible().hunger(0).saturationModifier(0.05F).build());
        ReflectMirror.Item.setFoodcomponent(Items.PUMPKIN_SEEDS, new FoodComponent.Builder().alwaysEdible().hunger(2).saturationModifier(0.05F).build());
        ReflectMirror.Item.setFoodcomponent(Items.MELON_SEEDS, new FoodComponent.Builder().alwaysEdible().hunger(1).saturationModifier(0.05F).build());
        ReflectMirror.Item.setFoodcomponent(Items.BEETROOT_SEEDS, new FoodComponent.Builder().alwaysEdible().hunger(0).saturationModifier(0.05F).build());
        ReflectMirror.Item.setFoodcomponent(Items.SUGAR, new FoodComponent.Builder().alwaysEdible().hunger(0).saturationModifier(0.05F).build());
        ReflectMirror.Item.setFoodcomponent(Items.NETHER_WART, new FoodComponent.Builder().alwaysEdible().hunger(0).saturationModifier(0.05F).build());
        ReflectMirror.Item.setFoodcomponent(Items.COOKED_BEEF, new FoodComponent.Builder().hunger(10).saturationModifier(0.5F).meat().build());
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
        Registry.register(Registry.ITEM, new Identifier(MODID, "banana"), BANANA);
        Registry.register(Registry.ITEM, new Identifier(MODID, "blueberries"), BLUEBERRIES);
        Registry.register(Registry.ITEM, new Identifier(MODID, "cheese"), CHEESE);
        Registry.register(Registry.ITEM, new Identifier(MODID, "chocolate"), CHOCOLATE);
        Registry.register(Registry.ITEM, new Identifier(MODID, "dough"), DOUGH);
        Registry.register(Registry.ITEM, new Identifier(MODID, "flour"), FLOUR);
        Registry.register(Registry.ITEM, new Identifier(MODID, "lemon"), LEMON);
        Registry.register(Registry.ITEM, new Identifier(MODID, "onion"), ONION);
        Registry.register(Registry.ITEM, new Identifier(MODID, "orange"), ORANGE);
    }
}
