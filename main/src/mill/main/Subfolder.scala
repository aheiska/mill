package mill.main;
import mill.*
import mill.define.{Caller, Ctx, Discover, EnclosingClass, Segments}

object SubfolderModule {
  class Info(val millSourcePath0: os.Path, val segments: Seq[String]) {
    implicit val subFolderInfo: Info = this
  }
}

abstract class SubfolderModule()(implicit
    millModuleLine0: sourcecode.Line,
    millFile0: sourcecode.File,
    subFolderInfo: SubfolderModule.Info
) extends mill.define.Module.BaseClass()(
      Ctx.make(
        millModuleEnclosing0 = subFolderInfo.segments.mkString("."),
        millModuleLine0 = millModuleLine0,
        millModuleBasePath0 = Ctx.BasePath(subFolderInfo.millSourcePath0 / os.up),
        segments0 = Segments.labels(subFolderInfo.segments.init: _*),
        external0 = Ctx.External(false),
        foreign0 = Ctx.Foreign(None),
        fileName = millFile0,
        enclosing = Caller(null),
        enclosingClass = EnclosingClass(null),
        discover = null
      )
    ) with Module {
  def millDiscover: Discover = sys.error("RootModule#millDiscover must be overridden")
  override implicit lazy val implicitMillDiscover: Discover = millDiscover
}
