package de.bulldog98.event.mixin;

import de.bulldog98.event.api.KilledByCallback;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.ActionResult;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(LivingEntity.class)
public class MixinLivingEntity {
    @Inject(method = "onKilledBy(Lnet/minecraft/entity/LivingEntity;)V", at = @At(value = "HEAD", target =
            "Lnet/minecraft/entity/LivingEntity;onKilledBy(Lnet/minecraft/entity/LivingEntity;)V",
            shift = At.Shift.AFTER), locals = LocalCapture.CAPTURE_FAILEXCEPTION, cancellable = true)
    public void onInteractKilledBy(@Nullable LivingEntity adversary, CallbackInfo info) {
        LivingEntity killedEntity = (LivingEntity) (Object) this;
        ActionResult result = KilledByCallback.EVENT.invoker().interact(killedEntity, adversary);

        if (result != ActionResult.PASS) {
            info.cancel();
        }
    }
}
