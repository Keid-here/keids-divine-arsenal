package com.keid.divinearsenal.blocks;

import com.keid.divinearsenal.data.tags.ModTags;
import com.keid.divinearsenal.items.ItemInit;
import com.keid.divinearsenal.screen.DivineAnvilScreenHandler;
import io.wispforest.owo.util.ImplementedInventory;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

import static net.minecraft.core.registries.BuiltInRegistries.ITEM;

public class DivineanvilBlockEntity extends BlockEntity implements ExtendedScreenHandlerFactory, ImplementedInventory {
    private final NonNullList<ItemStack> inventory = NonNullList.withSize(2, ItemStack.EMPTY);

    private static final int INPUT_SLOT = 0;
    private static final int OUTPUT_SLOT = 1;

    protected final ContainerData propertyDelegate;
    private int progress = 0;
    private int maxProgress = 72;

    public DivineanvilBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.DIVINEANVIL_BLOCK_ENTITY, pos, state);
        this.propertyDelegate = new ContainerData() {

            @Override
            public int get(int index) {
                return switch (index) {
                    case 0 -> DivineanvilBlockEntity.this.progress;
                    case 1 -> DivineanvilBlockEntity.this.maxProgress;
                    default -> 0;
                };
            }

            @Override
            public void set(int index, int value) {
                switch (index) {
                    case 0 -> DivineanvilBlockEntity.this.progress = value;
                    case 1 -> DivineanvilBlockEntity.this.maxProgress = value;
                }
            }

            @Override
            public int getCount() {
                return 2;
            }
        };
    }

    @Override
    protected void saveAdditional(CompoundTag nbt) {
        super.saveAdditional(nbt);
        ContainerHelper.saveAllItems(nbt, inventory);
        nbt.putInt("divineanvil.progress", progress);
    }

    @Override
    public void load(CompoundTag nbt) {
        super.load(nbt);
        ContainerHelper.loadAllItems(nbt, inventory);
        progress = nbt.getInt("divineanvil.progress");
    }

    @Override
    public void writeScreenOpeningData(ServerPlayer player, FriendlyByteBuf buf) {
        buf.writeBlockPos(this.worldPosition);
    }

    @Override
    public Component getDisplayName() {
        return Component.literal("Divine Anvil");
    }

    @Override
    public @Nullable AbstractContainerMenu createMenu(int syncId, Inventory playerInventory, Player player) {
        return new DivineAnvilScreenHandler(syncId, playerInventory, this, this.propertyDelegate);
    }

    @Override
    public NonNullList<ItemStack> getItems() {
        return inventory;
    }

    public void tick(Level world, BlockPos pos, BlockState state) {
        if(this.level.isClientSide()) {
            return;
        }

        if(isOutputSlotEmptyOrReceivable()) {
            if(this.hasRecipe()) {
                this.increaseCraftProgress();
                setChanged(this.level, pos, state);

                if(hasCraftingFinished()) {
                    this.craftItem();
                    this.resetProgress();
                }
            } else {
                this.resetProgress();
            }
        } else {
            this.resetProgress();
            setChanged(this.level, pos, state);
        }
    }

    private void resetProgress() {
        this.progress = 0;
    }

    private void craftItem() {
        this.removeItem(INPUT_SLOT, 1);

        int rank = getItem(OUTPUT_SLOT).getOrCreateTag().getInt("rank");
        if (rank < 1) { rank = 1;}
        int newRank = rank + 1;

        this.getItem(OUTPUT_SLOT).getOrCreateTag().putInt("rank", newRank);
        //this.setStack(OUTPUT_SLOT, new ItemStack(result.getItem(), getStack(OUTPUT_SLOT).getCount() + result.getCount()));
    }

    private boolean hasCraftingFinished() {
        return progress >= maxProgress;
    }

    private void increaseCraftProgress() {
        progress++;
    }

    private boolean hasRecipe() {
        ItemStack input = getItem(INPUT_SLOT);
        ItemStack output = getItem(OUTPUT_SLOT);

        int rankInput = input.getOrCreateTag().getInt("rank");
        int rankOutput = output.getOrCreateTag().getInt("rank");

        if (rankInput < 1) { rankInput = 1;}
        if (rankOutput < 1) { rankOutput = 1;}

        boolean hasInput = getItem(INPUT_SLOT).is(ModTags.Items.UPGRADEABLE_ITEMS);

        return rankInput == rankOutput && output.getItem() == input.getItem() && hasInput;
        //return hasInput && canInsertAmountIntoOutputSlot(result) && canInsertItemIntoOutputSlot(result.getItem());
    }

    private boolean canInsertItemIntoOutputSlot(Item item) {
        return this.getItem(OUTPUT_SLOT).getItem() == item || this.getItem(OUTPUT_SLOT).isEmpty();
    }

    private boolean canInsertAmountIntoOutputSlot(ItemStack result) {
        return this.getItem(OUTPUT_SLOT).getCount() + result.getCount() <= getItem(OUTPUT_SLOT).getMaxStackSize();
    }

    private boolean isOutputSlotEmptyOrReceivable() {
        return !this.getItem(OUTPUT_SLOT).isEmpty();
    }
}
