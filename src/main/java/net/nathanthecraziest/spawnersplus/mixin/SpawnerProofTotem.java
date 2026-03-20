package net.nathanthecraziest.spawnersplus.mixin;

import net.minecraft.block.spawner.MobSpawnerLogic;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import eu.pb4.polymer.core.api.item.PolymerItemUtils;

@Mixin(MobSpawnerLogic.class)
public class SpawnerProofTotem {

    @Shadow
    private int requiredPlayerRange;

    @Inject(method = "isPlayerInRange", at = @At("HEAD"), cancellable = true)
    private void disableSpawnerWithTotem(World world, BlockPos pos, CallbackInfoReturnable<Boolean> cir) {
        double x = pos.getX() + 0.5;
        double y = pos.getY() + 0.5;
        double z = pos.getZ() + 0.5;

        boolean playerFound = false;

        for (PlayerEntity player : world.getPlayers()) {
            if (player.squaredDistanceTo(x, y, z) <= requiredPlayerRange * requiredPlayerRange) {
                playerFound = true;

                ItemStack mainhandStack = PolymerItemUtils.getRealItemStack(player.getMainHandStack(),
                        world.getRegistryManager());

                ItemStack offhandStack = PolymerItemUtils.getRealItemStack(player.getOffHandStack(),
                        world.getRegistryManager());

                if (isSilencer(mainhandStack, world) || isSilencer(offhandStack, world)) {
                    cir.setReturnValue(false); // force spawner off
                    return;
                }
            }
        }

        cir.setReturnValue(playerFound);
    }

    private boolean isSilencer(ItemStack stack, World world) {
        if (stack.isEmpty())
            return false;

        // Retrieve real item id
        ItemStack realStack = PolymerItemUtils.getRealItemStack(stack, world.getRegistryManager());
        Identifier id = Registries.ITEM.getId(realStack.getItem());

        // Actual check
        return id.toString().equals("spawnersplus:spawner_silencer");
    }
}
