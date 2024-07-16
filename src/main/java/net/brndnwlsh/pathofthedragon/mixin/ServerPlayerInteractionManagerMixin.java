package net.brndnwlsh.pathofthedragon.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.brndnwlsh.pathofthedragon.PathOfTheDragon;
import net.brndnwlsh.pathofthedragon.block.ModBlocks;
import net.brndnwlsh.pathofthedragon.block.custom.InfiniteOreBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.network.ServerPlayerInteractionManager;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(ServerPlayerInteractionManager.class)
public class ServerPlayerInteractionManagerMixin {
	@Shadow protected ServerWorld world;

	@Shadow @Final protected ServerPlayerEntity player;

	@WrapOperation(at = @At(value = "INVOKE", target = "Lnet/minecraft/server/world/ServerWorld;removeBlock(Lnet/minecraft/util/math/BlockPos;Z)Z"),
			method = "tryBreakBlock")
	public boolean removeBlock (ServerWorld instance, BlockPos pos, boolean move, Operation<Boolean> original) {

		PathOfTheDragon.LOGGER.info("server removed block");
		BlockState state = this.world.getBlockState(pos);
		Block block = state.getBlock();
		if (block instanceof InfiniteOreBlock && state.get(InfiniteOreBlock.FULL) && !this.player.isCreative()) {
			return this.world.setBlockState(pos, state.with(InfiniteOreBlock.FULL, false), Block.NOTIFY_ALL);
		} else {
			return original.call(instance, pos, move);
	}
	}
}

