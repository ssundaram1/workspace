public final class TestSingleton {

  private static final TestSingleton INSTANCE = new TestSingleton();

  private TestSingleton() {
    // to avoid instanation using reflection
    if (INSTANCE != null) {
      throw new IllegalStateException("Already instantiated");
    }
  }

  public static TestSingleton getInstance() {
    return INSTANCE;
  }

  // Correct lazy initialization in Java
  class Foo {
    private static class HelperHolder {
      public static final Helper helper = new Helper();
    }

    public static Helper getHelper() {
      return HelperHolder.helper;
    }
  }

  public class Singleton {
    private volatile static Singleton instance;

    private Singleton() {
    }

    public static Singleton getInstance() {
      if (instance == null) {
        synchronized (Singleton.class) {
          if (instance == null) {
            instance = new Singleton();
          }
        }
      }
      return instance;
    }
  }

  // improved version

  public class SingletonImproved {
    private volatile static SingletonImproved instance;

    private SingletonImproved() {
    }

    public static SingletonImproved getInstance() {
      SingletonImproved result = instance;
      if (result == null) {
        synchronized (Singleton.class) {
          result = instance;
          if (result == null) {
            instance = result = new SingletonImproved();
          }
        }
      }
      return result;
    }
  }

}
