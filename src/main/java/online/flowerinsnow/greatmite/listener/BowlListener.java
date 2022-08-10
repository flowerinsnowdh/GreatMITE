package online.flowerinsnow.greatmite.listener;

import net.fabricmc.fabric.api.event.player.UseItemCallback;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.stat.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.world.RaycastContext;
import net.minecraft.world.World;
import online.flowerinsnow.greatmite.GreatMITE;
import online.flowerinsnow.greatmite.eci.PlayerBowlFillCallback;
import online.flowerinsnow.greatmite.util.InventoryUtils;
import online.flowerinsnow.greatmite.util.ReflectMirror;

public class BowlListener implements UseItemCallback {
    @Override
    public TypedActionResult<ItemStack> interact(PlayerEntity player, World world, Hand hand) {
        if (player.isSpectator()) {
            return TypedActionResult.pass(ItemStack.EMPTY);
        }
        ItemStack itemStack = player.getStackInHand(hand);
        if (itemStack.getItem() != Items.BOWL) {
            return TypedActionResult.pass(itemStack);
        }
        BlockHitResult blockHitResult = ReflectMirror.Item.raycast(world, player, RaycastContext.FluidHandling.ANY);
        if (blockHitResult.getType() == HitResult.Type.MISS) {
            return TypedActionResult.pass(itemStack);
        }
        if (blockHitResult.getType() == HitResult.Type.BLOCK && world.getBlockState(blockHitResult.getBlockPos()).getBlock() == Blocks.WATER) {
            ActionResult result = PlayerBowlFillCallback.EVENT.invoker().interact(world, player, hand);
            if (result != ActionResult.FAIL) {
                player.incrementStat(Stats.USED.getOrCreateStat(Items.BOWL));
                if (!player.isCreative()) {
                    itemStack.decrement(1);
                }
                InventoryUtils.addAnItem(player.getInventory(), GreatMITE.BOWL_WATER);
            }
        }
        return TypedActionResult.pass(itemStack);
    }
}
