package online.flowerinsnow.greatmite.util;

import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.MappingResolver;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.world.RaycastContext;
import net.minecraft.world.World;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public final class ReflectMirror {
    public static final String INTERMEDIARY = "intermediary";
    public static class Item {
        public static final Class<net.minecraft.item.Item> CLASS = net.minecraft.item.Item.class;

        public static final Field F_MAXCOUNT = getField(CLASS, "field_8013", "maxCount");
        public static final Method M_RAYCAST = getMethod(CLASS, new Class[]{World.class, PlayerEntity.class, RaycastContext.FluidHandling.class},"method_7872", "raycast");

        public static BlockHitResult raycast(World world, PlayerEntity player, RaycastContext.FluidHandling fluidHandling) {
            try {
                return (BlockHitResult) M_RAYCAST.invoke(null, world, player, fluidHandling);
            } catch (IllegalAccessException | InvocationTargetException e) {
                throw new UtilException(e);
            }
        }

        public static void setMaxCount(net.minecraft.item.Item item, int maxCount) {
            try {
                F_MAXCOUNT.set(item, maxCount);
            } catch (IllegalAccessException e) {
                throw new UtilException(e);
            }
        }
    }

    public static Class<?> getClass(String midName) {
        MappingResolver resolver = FabricLoader.getInstance().getMappingResolver();
        try {
            return Class.forName(resolver.mapClassName(INTERMEDIARY, midName)); // 通过中间名映射获取类
        } catch (ClassNotFoundException e) {
            throw new UtilException(e);
        }
    }

    public static Method getMethod(Class<?> cls, Class<?>[] paramTypes, String... possibleNames) {
        Method method = null;
        for (String name : possibleNames) {
            try {
                method = cls.getDeclaredMethod(name, paramTypes);
                break;
            } catch (NoSuchMethodException ignored) {
            }
        }

        if (method != null) {
            method.setAccessible(true);
        }

        return method;
    }

    public static Field getField(Class<?> cls, String... possibleNames) {
        Field field = null;
        for (String name : possibleNames) {
            try {
                field = cls.getDeclaredField(name);
                break;
            } catch (NoSuchFieldException ignored) {
            }
        }

        if (field != null) {
            field.setAccessible(true);
        }

        return field;
    }

    private ReflectMirror() {
    }
}
