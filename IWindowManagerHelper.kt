
import android.os.IBinder


object IWindowManagerHelper {

    private val iWindowManager: Any?
    private val iWindowManagerClass: Class<*>

    init {

        val cls = Class.forName("android.view.IWindowManager\$Stub")
        val invoke = Class.forName("android.os.ServiceManager")
                .getMethod("checkService", String::class.java)
                .invoke(null, "window")

        iWindowManager = cls
                .getMethod("asInterface", IBinder::class.java)
                .invoke(null, invoke)

        iWindowManagerClass = Class.forName("android.view.IWindowManager")

    }


    fun setOverscan(display: Int, left: Int, top: Int, right: Int, bottom: Int) {

        val method = iWindowManagerClass.getDeclaredMethod(
                "setOverscan", Integer.TYPE,
                Integer.TYPE, Integer.TYPE,
                Integer.TYPE, Integer.TYPE)

        method.invoke(iWindowManager, display, left, top, right, bottom)

    }

}