package operations;

public class Sub implements Operator {

    public static final Sub INSTANCE = new Sub();
    public static final String OPERATOR = "-";
    private Sub() {}
    public static Sub getInstance() {
        return INSTANCE;
    }
    @Override
    public Integer operate(Integer num1, Integer num2) {
        if (num1 < num2) {
            return num2 - num1;
        }
        return num1 - num2;
    }

    @Override
    public String getOperator() {
        return OPERATOR;
    }
}
