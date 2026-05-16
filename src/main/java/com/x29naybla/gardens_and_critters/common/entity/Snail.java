package com.x29naybla.gardens_and_critters.common.entity;

import com.x29naybla.gardens_and_critters.common.registry.GnCEntities;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.ai.navigation.WallClimberNavigation;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.event.entity.living.BabyEntitySpawnEvent;
import org.jetbrains.annotations.Nullable;

import static net.neoforged.neoforge.common.NeoForge.EVENT_BUS;

public class Snail extends Animal {
    private static final EntityDataAccessor<Byte> DATA_FLAGS_ID = SynchedEntityData.defineId(Snail.class, EntityDataSerializers.BYTE);
    public final AnimationState idleAnimationState = new AnimationState();

    public Snail(EntityType<? extends Animal> entityType, Level level) {
        super(entityType, level);
    }


    public static AttributeSupplier.Builder createAttributes() {
        return Animal.createLivingAttributes()
                .add(Attributes.MAX_HEALTH, 8d)
                .add(Attributes.MOVEMENT_SPEED, 0.1d)
                .add(Attributes.FOLLOW_RANGE, 24d);
    }

    private void setupAnimationStates() {}

    //Goals and AI
    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));

        this.goalSelector.addGoal(1, new PanicGoal(this, 2));
        this.goalSelector.addGoal(2, new BreedGoal(this, 1));
        this.goalSelector.addGoal(3, new TemptGoal(this, 1.25, stack -> stack.is(Tags.Items.MUSHROOMS), false));

        this.goalSelector.addGoal(4, new WaterAvoidingRandomStrollGoal(this, 1));
        this.goalSelector.addGoal(5, new LookAtPlayerGoal(this, Player.class, 6));
        this.goalSelector.addGoal(6, new RandomLookAroundGoal(this));
    }

    @Override
    protected PathNavigation createNavigation(Level level) {
        return new WallClimberNavigation(this, level);
    }

    @Override
    public boolean isFood(ItemStack stack) {
        return stack.is(Tags.Items.MUSHROOMS);
    }

    @Override
    public @Nullable AgeableMob getBreedOffspring(ServerLevel level, AgeableMob otherParent) {
        return GnCEntities.SNAIL.get().create(level);
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

    //Data
    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(DATA_FLAGS_ID, (byte)0);
    }

    @Override
    public void tick() {
        super.tick();

        if(this.level().isClientSide()) {
            this.setupAnimationStates();
        }
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
}
