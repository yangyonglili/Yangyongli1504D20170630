package yangyongli.bawei.com.yangyongli1504d20170630;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.bumptech.glide.Glide;

import uk.co.senab.photoview.PhotoView;

/**
*
* 类描述：ImageMainActivity  图片展示类
* 创建人：yangyongli
* 创建时间：2017/6/30
*
*/
public class ImageMainActivity extends AppCompatActivity {
    /**
     * potoview控件
     */
    private PhotoView photoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_main);
        //获取控件id
        photoView= (PhotoView) findViewById(R.id.photoview);
        //得到传值url
        Intent intent = getIntent();
        String imageurl = intent.getStringExtra("imageurl");
        //设置图片
        Glide.with(this).load(imageurl).into(photoView);

    }
}
