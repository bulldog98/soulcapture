package de.bulldog98.event.api;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.ActionResult;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface KilledByCallback {
    Event<KilledByCallback> EVENT = EventFactory.createArrayBacked(KilledByCallback.class,
            (listeners) -> (killedEntity, adversary) -> {
                for (KilledByCallback event : listeners) {
                    ActionResult result = event.interact(killedEntity, adversary);

                    if (result != ActionResult.PASS) {
                        return result;
                    }
                }

                return ActionResult.PASS;
            }
    );

    ActionResult interact(@NotNull LivingEntity killedEntity, @Nullable LivingEntity adversary);
}
