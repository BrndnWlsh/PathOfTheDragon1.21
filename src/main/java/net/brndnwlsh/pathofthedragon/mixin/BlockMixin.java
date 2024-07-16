package net.brndnwlsh.pathofthedragon.mixin;

import net.brndnwlsh.pathofthedragon.PathOfTheDragon;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import static net.minecraft.block.Block.getDroppedStacks;

@Mixin(Block.class)
public class BlockMixin {

	@Redirect(at = @At(value = "INVOKE",
			target = "Lnet/minecraft/block/Block;dropStacks(Lnet/minecraft/block/BlockState;Lnet/minecraft/world/World;" +
					"Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/block/entity/BlockEntity;Lnet/minecraft/entity/Entity;" +
					"Lnet/minecraft/item/ItemStack;)V"),
			method = "afterBreak")
	public void dropStacks (BlockState state, World world, BlockPos pos, BlockEntity blockEntity, Entity entity, ItemStack tool) {
		if (world instanceof ServerWorld serverWorld && entity instanceof PlayerEntity player) {
			getDroppedStacks(state, serverWorld, pos, blockEntity, entity, tool).forEach(stack ->
			{if (!player.getInventory().insertStack(stack)) {
				serverWorld.spawnEntity(new ItemEntity(world, entity.getX(), entity.getEyeY() - 0.3F,
						entity.getZ(), stack, 0, 0, 0));
			}});
			state.onStacksDropped(serverWorld, pos, tool, true);
		}
		PathOfTheDragon.LOGGER.info("dropped a stack");
		}
}