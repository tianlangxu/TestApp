package com.materialdesign.drawer.activity;

import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.administrator.testapp.R;

public class DrawerActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mShowToast;
    private Button mShowSnackBar;
    private TextInputLayout mTextInputLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer);

        mShowToast = (Button) findViewById(R.id.showToast);
        mShowSnackBar = (Button) findViewById(R.id.showSnackBar);
        mTextInputLayout = (TextInputLayout) findViewById(R.id.textInputLayout);

        mShowToast.setOnClickListener(this);
        mShowSnackBar.setOnClickListener(this);

        initTextInputLayout();
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
