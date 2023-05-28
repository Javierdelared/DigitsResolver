package operations;

public class Mul implements Operator {

    public static final Mul INSTANCE = new Mul();
    public static final String OPERATOR = "*";
    private Mul() {}
    public static Mul getInstance() {
        return INSTANCE;
    }
    @Override
    public Integer operate(Integer num1, Integer num2) {
        return num1 * num2;
    }

    @Override
    public String getOperator() {
        return OPERATOR;
    }
}
