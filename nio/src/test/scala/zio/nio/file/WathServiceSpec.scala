package zio.nio.file

import zio.nio.BaseSpec
import zio.test.Assertion._
import zio.test._
import java.nio.file.StandardWatchEventKinds.ENTRY_CREATE

object WathServiceSpec extends BaseSpec {

  override def spec =
    suite("WatchServiceSpec")(
      testM("Watch Service register")(
        FileSystem.default.newWatchService.use { watchService =>
          for {
            watchKey <- Path("nio/src/test/resources").register(watchService, ENTRY_CREATE)
            watchable = watchKey.watchable
          } yield assert(watchable)(equalTo(Path("nio/src/test/resources")))
        }
      )
    )
}