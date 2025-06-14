package cleancode.studycafe.tobe.model.pass;

import cleancode.studycafe.tobe.model.pass.locker.StudyCafeLockerPass;

import java.util.Set;

public class StudyCafeSeatPass implements StudyCafePass {

    private final StudyCafePassType passType;
    private final int duration;
    private final int price;
    private final double discountRate;

    private StudyCafeSeatPass(StudyCafePassType passType, int duration, int price, double discountRate) {
        this.passType = passType;
        this.duration = duration;
        this.price = price;
        this.discountRate = discountRate;
    }

    public static StudyCafeSeatPass of(StudyCafePassType passType, int duration, int price, double discountRate) {
        return new StudyCafeSeatPass(passType, duration, price, discountRate);
    }

    public boolean cannotUseLocker() {
        return this.passType.isNotLockerType();
    }

    public boolean isSamePassType(StudyCafePassType studyCafePassType) {
        return this.passType == studyCafePassType;
    }

    public boolean isSameDurationType(StudyCafeLockerPass lockerPass) {
        return lockerPass.isSamePassType(passType)
                && lockerPass.isSameDuration(duration);
    }

    @Override
    public StudyCafePassType getPassType() {
        return passType;
    }

    @Override
    public int getDuration() {
        return duration;
    }

    @Override
    public int getPrice() {
        return price;
    }

    private static final Set<StudyCafePassType> LOCKER_TYPES = Set.of(StudyCafePassType.FIXED);

    public int getDiscountPrice() {
        return (int) (this.price * this.discountRate);
    }
}
