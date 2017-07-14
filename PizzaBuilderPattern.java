public class PizzaBuilderPattern {
  private final int size;
  private final boolean cheese;
  private final boolean pepperoni;
  private final boolean bacon;

  public static class Builder {
    // required
    private final int size;

    // optional
    private boolean cheese = false;
    private boolean pepperoni = false;
    private boolean bacon = false;

    public Builder(int size) {
      this.size = size;
    }

    public Builder cheese(boolean value) {
      cheese = value;
      return this;
    }

    public Builder pepperoni(boolean value) {
      pepperoni = value;
      return this;
    }

    public Builder bacon(boolean value) {
      bacon = value;
      return this;
    }

    public PizzaBuilderPattern build() {
      return new PizzaBuilderPattern(this);
    }
  }

  private PizzaBuilderPattern(Builder builder) {
    size = builder.size;
    cheese = builder.cheese;
    pepperoni = builder.pepperoni;
    bacon = builder.bacon;
  }

  public static void main(String args[]) {

    PizzaBuilderPattern pizza = new PizzaBuilderPattern.Builder(12).cheese(true).pepperoni(true).bacon(true).build();

  }
}
