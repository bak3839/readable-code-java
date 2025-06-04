package cleancode.studycafe.mytobe.pass;

public class StudyCafeSeatPass {

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

    public String getPassInfo() {
        return passType.display(duration, price);
    }

    public boolean isSamePassType(StudyCafePassType passType) {
        return this.passType.equals(passType);
    }

    public boolean isSameDuration(int lockerDuration) {
        return this.duration == lockerDuration;
    }

    public int calculateDiscountPrice() {
        return (int) (price * discountRate);
    }

    public int calculateTotalPrice() {
        return price - calculateDiscountPrice();
    }
}
