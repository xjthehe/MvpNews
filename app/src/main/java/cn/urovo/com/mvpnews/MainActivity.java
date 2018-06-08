package cn.urovo.com.mvpnews;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import inject.DaggerComponets;
import inject.MainModel;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.tv_main)
    Button textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        DaggerComponets.builder()
                .mainModel(new MainModel(this))
                .build()
                .injectMain(this);
    }

    @OnClick(R.id.tv_main)
    public void clicktV(){
        textView.setText("xxxxx");
    }
}
