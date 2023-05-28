package operations;

public class Sub implements Operator {

    public static final Sub INSTANCE = new Sub();
    private Sub() {}
    public static Sub getInstance() {
        return INSTANCE;
    }
    @Override
    public Integer operate(Integer num1, Integer num2) {
        if (num1 < num2) {
            // Swap
            num1 = num1 ^ num2 ^ (num2 = num1);
        }
        return num1 - num2;
    }

    @Override
    public String getOperator() {
        return "-";
    }
}
