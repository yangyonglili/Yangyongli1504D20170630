package yangyongli.bawei.com.yangyongli1504d20170630;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import me.maxwin.view.XListView;

/**
 * 类描述：ViewPager 中的Fragment
 * 创建人：yangyongli
 * 创建时间：2017/6/30
 */
public class BigFragment extends Fragment implements XListView.IXListViewListener {

    //适配器
    private MyXlistAdapter adapter;
    //集合
    private static List<TitleBean.DataBean> mlist = new ArrayList<>();
    /**
     * 控件xlistview
     */
    private XListView xlistview;
    private List<String> imvlist = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getActivity(), R.layout.fragment1, null);
        xlistview = (XListView) view.findViewById(R.id.xlistview);
        //继承implements XListView.IXListViewListener
        //设置允许刷新和加载   以及实现监听为当前对象
        xlistview.setPullRefreshEnable(true);
        xlistview.setPullLoadEnable(true);
        xlistview.setXListViewListener(this);
        mlist = MainActivity.getList();
        forinsertImag();
        //实例化适配器
        adapter = new MyXlistAdapter();
        //点击监听跳转
        xlistview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //设置跳转页面
                Intent it = new Intent(getActivity(), ImageMainActivity.class);
                it.putExtra("imageurl",imvlist.get(position-1));
                //跳转
                startActivity(it);
            }
        });
        xlistview.setAdapter(adapter);
        return view;
    }

    private void forinsertImag() {
        for (int i = 0; i < mlist.size(); i++) {
            imvlist.add("http://img4.imgtn.bdimg.com/it/u=3152147067,915716173&fm=26&gp=0.jpg");
            imvlist.add("http://img1.3lian.com/2015/w7/97/d/86.jpg");
            imvlist.add("http://img2.3lian.com/2014/f2/91/d/113.jpg");
            imvlist.add("http://img05.tooopen.com/images/20141119/sy_75326911564.jpg");
            imvlist.add("http://img3.redocn.com/20120215/Redocn_2012021310262342.jpg");
            imvlist.add("http://img1.3lian.com/2015/a1/101/d/109.jpg");
            imvlist.add("http://img1.3lian.com/2015/a1/127/d/120.jpg");
            imvlist.add("http://img1.3lian.com/2015/a1/127/d/119.jpg");
            imvlist.add("http://img3.3lian.com/2013/s1/77/d/54.jpg");
            imvlist.add("http://img15.3lian.com/2015/h1/57/d/61.jpg");
            imvlist.add("http://img3.3lian.com/2014/c2/94/d/25.jpg");
            imvlist.add("http://img1.3lian.com/2015/a1/101/d/109.jpg");
        }
    }

    @Override
    public void onRefresh() {
        stopPrerssBar();
    }

    @Override
    public void onLoadMore() {
        stopPrerssBar();
    }

    //停止加载条和刷新条 的 方法
    public void stopPrerssBar() {
        //停止加载条和刷新条
        xlistview.stopLoadMore();
        xlistview.stopRefresh();

        SimpleDateFormat sdf = new SimpleDateFormat("HH-mm-ss");
        Date data = new Date();
        String s = sdf.format(data);
        //设置刷新时间
        xlistview.setRefreshTime(s);
        //刷新适配器
        adapter.notifyDataSetChanged();
    }

    /**
     * xlistview适配器
     */
    class MyXlistAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return mlist.size();
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            //优化
            ViewHolder holder;
            //判断当前视图是否为空
            if (convertView == null) {
                //实例化 holder 对象
                holder = new ViewHolder();
                //设置视图
                convertView = View.inflate(getActivity(), R.layout.listview_messahe, null);
                //为holder类中的属性设置id
                holder.imv = (ImageView) convertView.findViewById(R.id.imv);
                holder.text = (TextView) convertView.findViewById(R.id.text);
                //设置Tag
                convertView.setTag(holder);
            } else {
                //获取Tag并给holder设置
                holder = (ViewHolder) convertView.getTag();
            }
            //开始赋值
            Glide.with(getActivity()).load(imvlist.get(position)).into(holder.imv);
            holder.text.setText(mlist.get(position).getUmeng_event()+mlist.get(position).getName()+mlist.get(position).getUrl());
            return convertView;
        }

        class ViewHolder {
            ImageView imv;
            TextView text;
        }
    }
}
