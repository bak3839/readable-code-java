package cleancode.studycafe.mytobe.model;

public enum StudyCafePassType implements PassTypeProvidable {

    HOURLY("시간 단위 이용권") {
        @Override
        public String display(int duration, int price) {
            return String.format("%s시간권 - %d원", duration, price);
        }
    },
    WEEKLY("주 단위 이용권") {
        @Override
        public String display(int duration, int price) {
            return String.format("%s주권 - %d원", duration, price);
        }
    },
    FIXED("1인 고정석") {
        @Override
        public String display(int duration, int price) {
            return String.format("%s주권 - %d원", duration, price);
        }
    };

    private final String description;

    StudyCafePassType(String description) {
        this.description = description;
    }

    public boolean isSamePassType(StudyCafePassType passType) {
        return this == passType;
    }
}
