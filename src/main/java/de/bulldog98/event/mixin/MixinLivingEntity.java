package de.bulldog98.event.mixin;

import de.bulldog98.event.api.KilledByCallback;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.ActionResult;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public class MixinLivingEntity {
    @Inject(method = "onKilledBy", at = @At("HEAD"), cancellable = true)
    public void onInteractKilledBy(@Nullable LivingEntity adversary, CallbackInfo info) {
        LivingEntity killedEntity = (LivingEntity) (Object) this;
        ActionResult result = KilledByCallback.EVENT.invoker().interact(killedEntity, adversary);

        if (result != ActionResult.PASS) {
            info.cancel();
        }
    }
}
