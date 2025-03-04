package punchmade.dav.mixin; //dis guy so cool frfr

import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.Item;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Item.class)
public abstract class ItemMixin {

    @Inject(method = "isFood", at = @At("HEAD"), cancellable = true)
    private void makeMossEdible(CallbackInfoReturnable<Boolean> cir) {
        Item self = (Item) (Object) this;

        if (self instanceof BlockItem blockItem &&
                (blockItem.getBlock() == Blocks.MOSS_BLOCK || blockItem.getBlock() == Blocks.MOSS_CARPET)) {
            cir.setReturnValue(true);
        }
    }

    @Inject(method = "getFoodComponent", at = @At("HEAD"), cancellable = true)
    private void addMossFoodComponent(CallbackInfoReturnable<FoodComponent> cir) {
        Item self = (Item) (Object) this;

        if (self instanceof BlockItem blockItem &&
                (blockItem.getBlock() == Blocks.MOSS_BLOCK || blockItem.getBlock() == Blocks.MOSS_CARPET)) {
            cir.setReturnValue(new FoodComponent.Builder().hunger(1).saturationModifier(0.1f).build());
        }
    }
}