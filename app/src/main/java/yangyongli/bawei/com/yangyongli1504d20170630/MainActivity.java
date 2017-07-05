package yangyongli.bawei.com.yangyongli1504d20170630;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;

import com.google.gson.Gson;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
*
* 类描述： Activity 类
* 创建人：yangyongli
* 创建时间：2017/6/30
*
*/
@ContentView(R.layout.activity_main)
public class MainActivity extends FragmentActivity{

    /**
     * //集合
     */
    private List<Fragment> flist=new ArrayList<>();
    private static List<TitleBean.DataBean> datelist=new ArrayList<>();
    private List<String> titlelist=new ArrayList<>();
   //适配器
    private MyFragmentAdapter adapter;
    private static  String TAG="--------Activity---------";
    //私有化Hander
    private Handler mhander=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            //接收信息并赋值
            String obj = (String) msg.obj;
            Gson gson=new Gson();
            TitleBean titleBean = gson.fromJson(obj, TitleBean.class);
            Log.e(TAG, "titleBean: "+titleBean.toString());
            datelist.addAll(titleBean.getData());
            //遍历对象集合 得到title
            for (int i = 0; i <datelist.size() ; i++) {
                titlelist.add(datelist.get(i).getName());
                Log.e(TAG, "datelist: "+datelist.get(i).getName());
            }
            adapter.notifyDataSetChanged();
        }
    };

    /**
     * 控件TabLayout
     */
    @ViewInject(R.id.tab)
    private TabLayout tab;

    /**
     *控件 ViewPager
     */
    @ViewInject(R.id.vp)
    private ViewPager vp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置试图
        x.view().inject(this);
        //网络请求数据
        initHttp();
        //记得在该Activity中加android:theme="@style/Base.Theme.AppCompat"权限
        //绑定
        new Thread(){
            @Override
            public void run() {
                //得到资源
                String url = Utilsss.getUrlConnect("http://lf.snssdk.com/neihan/service/tabs/?essence=1&iid=3216590132&device_id=32613520945&ac=wifi&channel=360&aid=7&app_name=joke_essay&version_code=612&version_name=6.1.2&device_platform=android&ssmix=a&device_type=sansung&device_brand=xiaomi&os_api=28&os_version=6.10.1&uuid=326135942187625&openudid=3dg6s95rhg2a3dg5&manifest_version_code=612&resolution=1450*2800&dpi=620&update_version_code=6120");
                if(url!=null){
                    //准备发送消息
                    Message obtain = Message.obtain();
                    //赋值
                    obtain.obj=url;
                    //发送
                    mhander.sendMessage(obtain);
                }
            }
        }.start();
        tab.setupWithViewPager(vp);
        //字体颜色
        tab.setTabTextColors(getResources().getColor(R.color.hui), getResources().getColor(R.color.green));
        //指示器颜色
        tab.setSelectedTabIndicatorColor(getResources().getColor(R.color.bule));
        //模式
        tab.setTabMode(TabLayout.MODE_SCROLLABLE);
        for (int i = 0; i <10 ; i++) {
            flist.add(new BigFragment());
        }

         adapter=new MyFragmentAdapter(getSupportFragmentManager());
        vp.setAdapter(adapter);

    }

    public void initHttp(){
//        RequestParams params=new RequestParams("http://lf.snssdk.com/neihan/service/tabs/?essence=1&iid=3216590132&device_id=32613520945&ac=wifi&channel=360&aid=7&app_name=joke_essay&version_code=612&version_name=6.1.2&device_platform=android&ssmix=a&device_type=sansung&device_brand=xiaomi&os_api=28&os_version=6.10.1&uuid=326135942187625&openudid=3dg6s95rhg2a3dg5&manifest_version_code=612&resolution=1450*2800&dpi=620&update_version_code=6120");
//        x.http().get(params, new Callback.CommonCallback<String>() {
//            @Override
//            public void onSuccess(String result) {
//                Gson gson=new Gson();
//                TitleBean titleBean = gson.fromJson(result, TitleBean.class);
//                Log.e(TAG, "onSuccess: "+titleBean.toString());
//                datelist.addAll(titleBean.getData());
//                //遍历对象集合 得到title
//                for (int i = 0; i <datelist.size() ; i++) {
//                    titlelist.add(datelist.get(i).getName());
//                    Log.e(TAG, "onSuccess: "+datelist.get(i).getName());
//
//                }
//            }
//
//            @Override
//            public void onError(Throwable ex, boolean isOnCallback) {
//
//            }
//
//            @Override
//            public void onCancelled(CancelledException cex) {
//
//            }
//
//            @Override
//            public void onFinished() {
//
//            }
//        });

//开启一个线程

    }
    class MyFragmentAdapter extends FragmentPagerAdapter{

        @Override
        public CharSequence getPageTitle(int position) {
            return titlelist.get(position);
        }

        public MyFragmentAdapter(FragmentManager fm) {
            super(fm);
        }

        //返回Fragment  item
        @Override
        public Fragment getItem(int position) {
            return flist.get(position);
        }

        @Override
        public int getCount() {
            return titlelist.size();
        }
    }
    public static List<TitleBean.DataBean> getList(){
        return datelist;
    }
}
