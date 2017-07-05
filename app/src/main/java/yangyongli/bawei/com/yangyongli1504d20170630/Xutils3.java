package yangyongli.bawei.com.yangyongli1504d20170630;

import android.app.Application;

import org.xutils.x;

/**
*
* 类描述：工具类
* 创建人：yangyongli
* 创建时间：2017/6/30
*
*/
public class Xutils3 extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        //设置当前
        x.Ext.init(this);
        //设置可以debug
        x.Ext.setDebug(BuildConfig.DEBUG);
    }
}
