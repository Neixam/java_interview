package async;

import io.vavr.collection.List;
import io.vavr.*;
import io.vavr.control.Option;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * You should complete the function in this class
 */
class AsyncTest {

  private static List<Enterprise> enterprises = List.of(
      new Enterprise("ent_1", "Google", "ceo_2"),
      new Enterprise("ent_2", "Facebook", "ceo_1")
  );

  private static List<Ceo> ceos = List.of(
      new Ceo("ceo_1", "Mark"),
      new Ceo("ceo_2", "Sundar"),
      new Ceo("ceo_3", "Bill")
  );

  public static CompletableFuture<Option<Ceo>> getCeoById(String ceo_id) {
    var executor = Executors.newCachedThreadPool();
    try {
      return CompletableFuture.supplyAsync(() -> ceos.find(ceo -> ceo.id.equals(ceo_id)), executor);
    } finally {
      executor.shutdown();
    }
  }

  public static CompletableFuture<Option<Enterprise>> getEnterpriseByCeoId(String ceo_id) {
    var executor = Executors.newCachedThreadPool();
    try {
      return CompletableFuture.supplyAsync(() -> enterprises.find(enterprise -> enterprise.ceo_id.equals(ceo_id))
            , executor);
    } finally {
      executor.shutdown();
    }
  }

  public static CompletableFuture<Tuple2<Option<Ceo>, Option<Enterprise>>> getCEOAndEnterprise(String ceo_id) {
    var executor = Executors.newCachedThreadPool();
    try {
      return CompletableFuture.supplyAsync(
              () -> new Tuple2<>(getCeoById(ceo_id).join(), getEnterpriseByCeoId(ceo_id).join()),
              executor);
    } finally {
      executor.shutdown();
    }
  }

}
