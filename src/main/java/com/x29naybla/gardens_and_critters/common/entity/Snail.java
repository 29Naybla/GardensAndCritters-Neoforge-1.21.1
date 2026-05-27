package com.x29naybla.gardens_and_critters.common.entity;

import com.x29naybla.gardens_and_critters.common.registry.GnCEntities;
import com.x29naybla.gardens_and_critters.common.tag.GnCTags;
import net.minecraft.Util;
import net.minecraft.core.Holder;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.ai.navigation.WallClimberNavigation;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.gameevent.GameEvent;
import net.neoforged.neoforge.event.entity.living.BabyEntitySpawnEvent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.function.DoubleSupplier;

import static net.neoforged.neoforge.common.NeoForge.EVENT_BUS;

public class Snail extends Animal {
    private static final EntityDataAccessor<Byte> DATA_FLAGS_ID = SynchedEntityData.defineId(Snail.class, EntityDataSerializers.BYTE);
    private static final EntityDataAccessor<Integer> VARIANT = SynchedEntityData.defineId(Snail.class, EntityDataSerializers.INT);

    public Snail(EntityType<? extends Animal> entityType, Level level) {
        super(entityType, level);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Animal.createLivingAttributes()
                .add(Attributes.MAX_HEALTH, 8d)
                .add(Attributes.MOVEMENT_SPEED, 0.1d)
                .add(Attributes.FOLLOW_RANGE, 24d)
                .add(Attributes.JUMP_STRENGTH, 0d);
    }

    protected static double generateSpeed(DoubleSupplier supplier) {
        return (0.1d + supplier.getAsDouble()*(0.02-(-0.02))+(-0.02));
    }

    protected void randomizeAttributes(RandomSource random) {
        this.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(generateSpeed(random::nextDouble));
    }
    @Override
    public boolean canBeCollidedWith() {
        return this.onGround();
    }

    @Override
    public boolean isPushable() {
        return true;
    }

    public @NotNull SpawnGroupData finalizeSpawn(ServerLevelAccessor level, @NotNull DifficultyInstance difficulty, @NotNull MobSpawnType spawnType, @Nullable SpawnGroupData spawnGroupData) {
        this.randomizeAttributes(level.getRandom());
        SnailVariant[] snailVariant = Arrays.stream(SnailVariant.values()).filter(SnailVariant::isCommon).toArray(SnailVariant[]::new);
        SnailVariant randomVariant = Util.getRandom(snailVariant, this.random);
        Holder<Biome> biome = level.getBiome(getOnPos());

        this.setVariant(SnailVariant.CREAM);
        if(biome.is(GnCTags.Biomes.SNAILS_ARE_CREAM_VARIANT) && biome.is(GnCTags.Biomes.SNAILS_ARE_CREAM_VARIANT)) {
            if(getRandom().nextInt(10) <= 4){
                this.setVariant(SnailVariant.LEMON);
            }
        } else if(biome.is(GnCTags.Biomes.SNAILS_ARE_LEMON_VARIANT) && !biome.is(GnCTags.Biomes.SNAILS_ARE_CREAM_VARIANT)) {
            this.setVariant(SnailVariant.LEMON);
        } else if(biome.is(GnCTags.Biomes.SNAILS_ARE_NAUTILUS_VARIANT)) {
            this.setVariant(SnailVariant.NAUTILUS);
        } else if(biome.is(GnCTags.Biomes.SNAILS_ARE_GREEN_VARIANT)) {
            this.setVariant(SnailVariant.GREEN);
        } else if(biome.is(GnCTags.Biomes.SNAILS_ARE_BLACK_VARIANT)) {
            this.setVariant(SnailVariant.BLACK);
        } else if(biome.is(GnCTags.Biomes.SNAILS_ARE_LIME_VARIANT)) {
            this.setVariant(SnailVariant.LIME);
        } else if(biome.is(GnCTags.Biomes.SNAILS_ARE_PUMPKIN_VARIANT)) {
            this.setVariant(SnailVariant.PUMPKIN);
        } else if(biome.is(GnCTags.Biomes.SNAILS_ARE_MUSHROOM_VARIANT)) {
            this.setVariant(SnailVariant.MUSHROOM);
        } else
            this.setVariant(randomVariant);

        return super.finalizeSpawn(level, difficulty, spawnType, spawnGroupData);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));

        this.goalSelector.addGoal(1, new PanicGoal(this, 2));
        this.goalSelector.addGoal(2, new BreedGoal(this, 1));
        this.goalSelector.addGoal(3, new TemptGoal(this, 1.25, stack -> stack.is(GnCTags.Items.SNAIL_FOOD), false));

        this.goalSelector.addGoal(4, new WaterAvoidingRandomStrollGoal(this, 1));
        this.goalSelector.addGoal(5, new LookAtPlayerGoal(this, Player.class, 6));
        this.goalSelector.addGoal(6, new RandomLookAroundGoal(this));
    }

    @Override
    protected @NotNull PathNavigation createNavigation(@NotNull Level level) {
        return new WallClimberNavigation(this, level);
    }

    @Override
    public @NotNull InteractionResult mobInteract(Player player, InteractionHand hand) {
        ItemStack itemstack = player.getItemInHand(hand);
        if(!this.level().isClientSide) {
            if(itemstack.is(Items.BONE_MEAL)){
                if(this.getHealth() < this.getMaxHealth()){
                    this.heal(1);
                    itemstack.consume(1, player);
                    this.gameEvent(GameEvent.EAT);
                    return InteractionResult.sidedSuccess(this.level().isClientSide());
                } else
                    return super.mobInteract(player, hand);
            }
        }

        return super.mobInteract(player, hand);
    }

    @Override
    public boolean isFood(ItemStack stack) {
        return stack.is(GnCTags.Items.SNAIL_FOOD);
    }

    @Override
    public boolean canMate(@NotNull Animal otherAnimal) {
        if (otherAnimal == this) {
            return false;
        } else if ((this.isLeftHanded() && otherAnimal.isLeftHanded()) || (!this.isLeftHanded() && !otherAnimal.isLeftHanded())) {
            return otherAnimal.getClass() == this.getClass() && this.isInLove() && otherAnimal.isInLove();
        } else return false;
    }

    @Override
    public @Nullable AgeableMob getBreedOffspring(@NotNull ServerLevel level, @NotNull AgeableMob otherParent) {
        Snail baby = GnCEntities.SNAIL.get().create(level);
        if(getRandom().nextInt(10) <= 4){
            baby.setVariant(this.getVariant());
        } else
            baby.setVariant(((Snail) otherParent).getVariant());

        this.setOffspringAttributes(otherParent, baby);

        return baby;
    }

    protected void setOffspringAttributes(AgeableMob parent, Snail child) {
        this.setOffspringAttribute(parent, child, Attributes.MOVEMENT_SPEED, 0.08, 0.12);
    }

    private void setOffspringAttribute(AgeableMob parent, Snail child, Holder<Attribute> attribute, double min, double max) {
        double d0 = createOffspringAttribute(
                this.getAttributeBaseValue(attribute), parent.getAttributeBaseValue(attribute), min, max, this.random
        );
        child.getAttribute(attribute).setBaseValue(d0);
    }

    static double createOffspringAttribute(double value1, double value2, double min, double max, RandomSource random) {
        if (max <= min) {
            throw new IllegalArgumentException("Incorrect range for an attribute");
        } else {
            value1 = Mth.clamp(value1, min, max);
            value2 = Mth.clamp(value2, min, max);
            double d0 = 0.15 * (max - min);
            double d1 = Math.abs(value1 - value2) + d0 * 2.0;
            double d2 = (value1 + value2) / 2.0;
            double d3 = (random.nextDouble() + random.nextDouble() + random.nextDouble()) / 3.0 - 0.5;
            double d4 = d2 + d1 * d3;
            if (d4 > max) {
                double d6 = d4 - max;
                return max - d6;
            } else if (d4 < min) {
                double d5 = min - d4;
                return min + d5;
            } else {
                return d4;
            }
        }
    }

    @Override
    public void spawnChildFromBreeding(ServerLevel level, Animal mate) {
        Snail snail1 = (Snail) this.getBreedOffspring(level, mate);
        Snail snail2 = (Snail) this.getBreedOffspring(level, mate);
        final BabyEntitySpawnEvent event1 = new BabyEntitySpawnEvent(this, mate, snail1);
        final BabyEntitySpawnEvent event2 = new BabyEntitySpawnEvent(this, mate, snail2);
        final boolean cancelled = EVENT_BUS.post(event1).isCanceled() && EVENT_BUS.post(event2).isCanceled();
        if (cancelled) {
            this.setAge(6000);
            mate.setAge(6000);
            this.resetLove();
            mate.resetLove();
            return;
        }
        if (snail1 != null) {
            snail1.setBaby(true);
            snail1.moveTo(this.getX()+0.125, this.getY(), this.getZ()+0.125, 0.0F, 0.0F);
            this.finalizeSpawnChildFromBreeding(level, mate, snail1);
            level.addFreshEntityWithPassengers(snail1);
        }
        if (snail2 != null) {
            snail2.setBaby(true);
            snail2.moveTo(this.getX()-0.125, this.getY(), this.getZ()-0.125, 0.0F, 0.0F);
            this.finalizeSpawnChildFromBreeding(level, mate, snail2);
            level.addFreshEntityWithPassengers(snail2);
        }
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.@NotNull Builder builder) {
        super.defineSynchedData(builder);
        builder.define(DATA_FLAGS_ID, (byte)0);
        builder.define(VARIANT, 0);
    }

    @Override
    public void addAdditionalSaveData(@NotNull CompoundTag compound) {
        super.addAdditionalSaveData(compound);
        compound.putInt("Variant", this.getTypeVariant());
    }

    @Override
    public void readAdditionalSaveData(@NotNull CompoundTag compound) {
        super.readAdditionalSaveData(compound);
        this.entityData.set(VARIANT, compound.getInt("Variant"));
    }

    @Override
    public void tick() {
        super.tick();

        if (!this.level().isClientSide) {
            this.setClimbing(this.horizontalCollision);
        }
    }

    @Override
    public boolean onClimbable() {
        return this.isClimbing();
    }

    public boolean isClimbing() {
        return (this.entityData.get(DATA_FLAGS_ID) & 1) != 0;
    }

    public void setClimbing(boolean climbing) {
        byte b0 = this.entityData.get(DATA_FLAGS_ID);
        if (climbing) {
            b0 = (byte)(b0 | 1);
        } else {
            b0 = (byte)(b0 & -2);
        }

        this.entityData.set(DATA_FLAGS_ID, b0);
    }

    private int getTypeVariant(){
        return this.entityData.get(VARIANT);
    }

    public SnailVariant getVariant(){
        return SnailVariant.byId(this.getTypeVariant() & 255);
    }

    private void setVariant(SnailVariant variant){
        this.entityData.set(VARIANT, variant.getId() & 255);
    }
}
