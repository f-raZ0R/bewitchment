/*
 * All Rights Reserved (c) 2022 MoriyaShiine
 */

package moriyashiine.bewitchment.mixin.poppet;

import moriyashiine.bewitchment.common.registry.BWComponents;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.fluid.Fluid;
import net.minecraft.tag.FluidTags;
import net.minecraft.tag.Tag;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@SuppressWarnings("ConstantConditions")
@Mixin(Entity.class)
public abstract class EntityMixin {
	@Inject(method = "isSubmergedIn", at = @At("RETURN"), cancellable = true)
	private void isSubmergedIn(Tag<Fluid> tag, CallbackInfoReturnable<Boolean> callbackInfo) {
		if (!callbackInfo.getReturnValue() && (Object) this instanceof LivingEntity living && tag == FluidTags.WATER && BWComponents.ADDITIONAL_WATER_DATA_COMPONENT.get(living).isSubmerged()) {
			callbackInfo.setReturnValue(true);
		}
	}
}
