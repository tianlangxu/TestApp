package com.materialdesign.drawer.activity;

import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.testapp.R;

public class DrawerActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mShowToast;
    private Button mShowSnackBar;
    private TextInputLayout mTextInputLayout;
    private Toolbar mToolbar;
    private ImageView mIvMeinv;
    private TextView mTvInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        mShowToast = (Button) findViewById(R.id.showToast);
        mShowSnackBar = (Button) findViewById(R.id.showSnackBar);
        mTextInputLayout = (TextInputLayout) findViewById(R.id.textInputLayout);
        mIvMeinv = (ImageView) findViewById(R.id.iv_meinv);
        mTvInfo = (TextView) findViewById(R.id.tv_info);

        mShowToast.setOnClickListener(this);
        mShowSnackBar.setOnClickListener(this);

        initTextInputLayout();
        initImage();
    }

    private void initImage() {
        BitmapDrawable drawable = (BitmapDrawable) mIvMeinv.getDrawable();
        // 获取bitmap中的色彩信息
//        Palette mPalette = Palette.generate(drawable.getBitmap());

//        Palette mPalette = new Palette.Builder(drawable.getBitmap()).generate();

        // 暗、柔和的颜色
//        int darkMutedColor = mPalette.getDarkMutedColor(Color.RED);


        Palette.from(drawable.getBitmap()).generate(new Palette.PaletteAsyncListener() {
            @Override
            public void onGenerated(Palette palette) {
                int newColor = palette.getLightMutedColor(Color.RED);
                Log.i("fff", "new Color ---> " + newColor);
                Log.i("fff", "getTranslucentColor(0.8f, newColor) ---> " + getTranslucentColor(0.8f, newColor));

                mTvInfo.setBackgroundColor(getTranslucentColor(0.8f, newColor));
            }
        });

    }

    /**
     * 设置半透明颜色
     *
     * @param rgb
     * @return
     */
    private int getTranslucentColor(float percent, int rgb) {
        int blue = rgb & 0xff;
        int green = rgb >> 8 & 0xff;
        int red = rgb >> 16 & 0xff;
        int alpha = rgb >>> 24;

        alpha = Math.round(alpha * percent);

        return Color.argb(alpha, red, green, blue);
    }

    private void initTextInputLayout() {
        mTextInputLayout.setCounterEnabled(true);
        mTextInputLayout.setCounterMaxLength(10);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.showToast:
                showToast();
                break;

            case R.id.showSnackBar:
                showSnackbar(v);
                break;
        }
    }

    private void showSnackbar(View v) {
        Snackbar snackbar = Snackbar.make(v, "是否打开GPS", Snackbar.LENGTH_SHORT);
        snackbar.setAction("确定", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(DrawerActivity.this, "显示Toast", Toast.LENGTH_SHORT).show();
            }
        });

        snackbar.setCallback(new Snackbar.Callback() {
            @Override
            public void onDismissed(Snackbar snackbar, int event) {
                Toast.makeText(DrawerActivity.this, "onDismissed", Toast.LENGTH_SHORT).show();
                super.onDismissed(snackbar, event);
            }

            @Override
            public void onShown(Snackbar snackbar) {
                Toast.makeText(DrawerActivity.this, "onShown", Toast.LENGTH_SHORT).show();
                super.onShown(snackbar);
            }
        });
        snackbar.show();

    }

    private void showToast() {
        Toast.makeText(DrawerActivity.this, "显示Toast", Toast.LENGTH_LONG).show();
    }
}
