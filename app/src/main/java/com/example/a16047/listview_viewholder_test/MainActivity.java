package com.example.a16047.listview_viewholder_test;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<String> mData;
    private ListView mListView;
    private ViewHolderAdapter mAdapter;
    private Button mButton,mButton1;
    private int mTouchSlop;
    private Animator mAnimator;
    private View header;
    private boolean mShow=true;
    private int direction;
    private float mFirst;
    private float mCurrent;
    private Toolbar mToolbar;
    View.OnTouchListener myOnTouchListener= new View.OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            switch (motionEvent.getAction()){
                case MotionEvent.ACTION_DOWN:
                    mFirst=motionEvent.getY();
                    break;
                case MotionEvent.ACTION_MOVE:
                    mCurrent=motionEvent.getY();
                    if(mCurrent-mFirst>mTouchSlop){
                        direction=0;//down
                    }else if(mFirst-mCurrent>mTouchSlop){
                        direction=1;//up
                    }
                    System.out.println("onTouch: direction="+direction);
                    System.out.println("onTouch: mShow="+mShow);
                    if(direction==1){
                        if(!mShow){
                            toolbarAnim(1);//show
                            mShow=!mShow;
                            }
                    }else if(direction==0){
                        if(mShow){
                            toolbarAnim(0);//hide
                            mShow=!mShow;
                           }
                    }
                    break;
                case MotionEvent.ACTION_UP:
                    break;
            }
            return false;
        }
    };
    private ListView mListView1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scroll_hide_test);
        mData=new ArrayList<String>();
        /*
        ListView+ViewHolder
        加了空列表提示
        动态修改
        测试成功
         */

        /*
        mListView= (ListView) findViewById(R.id.listView);
        mListView.setEmptyView(findViewById(R.id.empty_view));
        mAdapter=new ViewHolderAdapter(this,mData);
        mListView.setAdapter(mAdapter);
        for(int i=0;i<mListView.getChildCount();i++){
            //遍历所有的Item
            View view=mListView.getChildAt(i);

        }
        mButton= (Button) findViewById(R.id.button);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mData.add(""+mData.size()+1);
                mAdapter.notifyDataSetChanged();
                mListView.setSelection(mData.size()-1);
            }
        });
        mButton1= (Button) findViewById(R.id.button1);
        mButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mData.add(""+mData.size()+1);
                mAdapter.notifyDataSetChanged();
                mListView.setSelection(mData.size()-1);
            }
        });
        */

        /*
        自定义列表-测试
        具有弹性的ListView
        测试成功
         */

        /*
       for(int i=0;i<20;i++){
          mData.add(""+i);
       }
        mListView=(ListView)findViewById(R.id.mListView);
        mAdapter=new ViewHolderAdapter(this,mData);
        mListView.setAdapter(mAdapter);
        */
        /*
        自动显示、隐藏布局-测试
         */

        for(int i=0;i<20;i++){
            mData.add(""+i);
        }
        mListView1=(ListView)findViewById(R.id.mListView1);
        mToolbar= (Toolbar) findViewById(R.id.toolBar2);
        setSupportActionBar(mToolbar);
       /* header=new View(this);
        header.setLayoutParams(new AbsListView.LayoutParams(
               AbsListView.LayoutParams.MATCH_PARENT,
                (int)getResources().getDimension(
                        R.dimen.abc_action_bar_default_height_material)
        ));
        mListView1.addHeaderView(header);
        */
        mTouchSlop= ViewConfiguration.get(this).getScaledTouchSlop();
        mAdapter=new ViewHolderAdapter(this,mData);
        mListView1.setAdapter(mAdapter);
        mListView1.setOnTouchListener(myOnTouchListener);
    }

    private void toolbarAnim(int i) {
        if(mAnimator!=null&&mAnimator.isRunning()){
            mAnimator.cancel();
        }

        if(i==0){
            mAnimator= ObjectAnimator.ofFloat(mToolbar,"translationY",
                    mToolbar.getTranslationY(),0);
        }else{
            mAnimator=ObjectAnimator.ofFloat(mToolbar,
                    "translationY",mToolbar.getTranslationY(),-mToolbar.getHeight());
        }
        System.out.println("onTouch: mToolbar.getTranslationY()="+mToolbar.getTranslationY());
        System.out.println("onTouch: -mToolbar.getHeight()="+mToolbar.getHeight());
        mAnimator.start();
    }



}
