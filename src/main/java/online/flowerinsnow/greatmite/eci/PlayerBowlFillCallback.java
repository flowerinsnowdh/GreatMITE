package online.flowerinsnow.greatmite.eci;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

/**
 * 返回FAIL代表取消该事件
 */
public interface PlayerBowlFillCallback {
    Event<PlayerBowlFillCallback> EVENT = EventFactory.createArrayBacked(PlayerBowlFillCallback.class,
            listeners -> ((world, player, hand) -> {
                for (PlayerBowlFillCallback listener : listeners) {
                    ActionResult result = listener.interact(world, player, hand);
                    if (result != ActionResult.PASS) {
                        return result;
                    }
                }
                return ActionResult.PASS;
            }));

    /**
     * 当玩家用碗装水时调用
     *
     * @param world 世界
     * @param player 玩家
     * @param hand 主副手
     * @return 结果，返回FAIL时取消事件
     */
    ActionResult interact(World world, PlayerEntity player, Hand hand);
}
