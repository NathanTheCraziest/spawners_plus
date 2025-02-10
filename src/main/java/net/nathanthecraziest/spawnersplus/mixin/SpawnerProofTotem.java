package net.nathanthecraziest.spawnersplus.mixin;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.MobSpawnerLogic;
import net.minecraft.world.World;
import net.nathanthecraziest.spawnersplus.items.ModItems;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(MobSpawnerLogic.class)
public class SpawnerProofTotem {

    @Shadow private int requiredPlayerRange;

    @Inject(method = "isPlayerInRange", at = @At("HEAD"), cancellable = true)
    private void disableSpawnerWithTotem(World world, BlockPos pos, CallbackInfoReturnable<Boolean> cir){
        cir.cancel();
        if(world.isPlayerInRange((double)pos.getX() + 0.5, (double)pos.getY() + 0.5, (double)pos.getZ() + 0.5, (double)this.requiredPlayerRange))
        {
            boolean isPlayerHoldingTotem = false;
            for (PlayerEntity player: world.getPlayers()) {
                if(world.getClosestPlayer(player, requiredPlayerRange) != null){

                    PlayerEntity closestPlayer = world.getClosestPlayer(player, requiredPlayerRange);

                    if(closestPlayer.isHolding(ModItems.SPAWNER_SILENCER) && player.getEntityWorld() == world){

                        isPlayerHoldingTotem = true;

                    } else cir.setReturnValue(false);

                } else cir.setReturnValue(false);
            }
            cir.setReturnValue(!isPlayerHoldingTotem);
        }
        else{
            cir.setReturnValue(false);
        }
    }
}
