package net.brndnwlsh.pathofthedragon.block.custom;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.intprovider.IntProvider;

public class InfiniteOreBlock extends Block {
    public static final MapCodec<InfiniteOreBlock> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(IntProvider
                    .createValidatingCodec(0, 10).fieldOf("experience").forGetter(block -> block.experienceDropped),
                    createSettingsCodec()).apply(instance, InfiniteOreBlock::new)
    );
    public static final BooleanProperty FULL = BooleanProperty.of("full");
    private final IntProvider experienceDropped;

    @Override
    public MapCodec<? extends InfiniteOreBlock> getCodec() {
        return CODEC;
    }

    public InfiniteOreBlock(IntProvider experienceDropped,  Settings settings) {
        super(settings);
        this.setDefaultState(this.getDefaultState().with(FULL, Boolean.TRUE));
        this.experienceDropped = experienceDropped;
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FULL);
    }

    @Override
    protected void onStacksDropped(BlockState state, ServerWorld world, BlockPos pos, ItemStack tool, boolean dropExperience) {
        super.onStacksDropped(state, world, pos, tool, dropExperience);
        if (dropExperience && state.get(FULL)) {
            this.dropExperienceWhenMined(world, pos, tool, this.experienceDropped);
        }
    }
}
