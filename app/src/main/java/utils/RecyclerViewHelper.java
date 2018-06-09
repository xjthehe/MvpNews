package utils;

import android.content.Context;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * Created by lenovo on 2018/6/9.
 */

public class RecyclerViewHelper {
    private RecyclerViewHelper() {
        throw new RuntimeException("RecyclerViewHelper cannot be initialized!");
    }


    /**
     * 配置垂直列表RecyclerView
     * @param view
     */
    public static void initRecyclerViewV(Context context, RecyclerView view, boolean isDivided,
                                         RecyclerView.Adapter adapter) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
//        view.setHasFixedSize(true);
        view.setLayoutManager(layoutManager);
        view.setItemAnimator(new DefaultItemAnimator());
        if (isDivided) {
            view.addItemDecoration(new DividerItemDecoration(context, DividerItemDecoration.VERTICAL));
        }
        view.setAdapter(adapter);
    }





}
