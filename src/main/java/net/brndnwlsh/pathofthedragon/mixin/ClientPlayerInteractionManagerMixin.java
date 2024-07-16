package net.brndnwlsh.pathofthedragon.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.brndnwlsh.pathofthedragon.PathOfTheDragon;
import net.brndnwlsh.pathofthedragon.block.custom.InfiniteOreBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerInteractionManager;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(ClientPlayerInteractionManager.class)
public class ClientPlayerInteractionManagerMixin {

	@Shadow @Final private MinecraftClient client;

	@WrapOperation(at = @At(value = "INVOKE",
			target = "Lnet/minecraft/world/World;setBlockState(Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/block/BlockState;I)Z"),
			method = "breakBlock")
	public boolean setBlockState (World instance, BlockPos pos, BlockState state, int flags, Operation<Boolean> original) {

		PathOfTheDragon.LOGGER.info("client removed block");
		BlockState blockState = instance.getBlockState(pos);
		Block block = blockState.getBlock();
		if (block instanceof InfiniteOreBlock && blockState.get(InfiniteOreBlock.FULL) && !this.client.player.isCreative()) {
			return instance.setBlockState(pos, blockState.with(InfiniteOreBlock.FULL, false), flags);
		} else {
			return original.call(instance, pos, state, flags);
    }
	}
}