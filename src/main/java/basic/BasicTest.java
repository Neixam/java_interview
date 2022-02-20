package basic;

import io.vavr.control.Option;

/**
 * For this basic test, you should not use any library. e.g. you should not use Math.pow for power method
 */
public class BasicTest {

  /**
   * return i ^ n for positive Integer, None otherwise
   * else return None in case of errors
   */
  public static Option<Integer> power(Integer i, Integer n) {
    if (n < 0) {
      return Option.none();
    }
    var ret = Option.of(1);
    for (var j = 0; j < n; j++) {
      if (ret.get() * i < 0) {
        return Option.none();
      }
      ret = Option.some(ret.get() * i);
    }
    return ret;
  }
}
