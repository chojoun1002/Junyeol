package com.example.user.junyeoljo;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.sothree.slidinguppanel.SlidingUpPanelLayout;

public class Day5_JJY extends ActionBarActivity {

    ViewPager pager;
    ViewPagerAdapter adapter;
    Button button01;
    EditText editText01;
    Day5_viewPage page;
    SlidingUpPanelLayout slidingPanelLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day5);

        // 뷰페이저 객체를 참조하고 어댑터를 설정합니다.
        pager = (ViewPager) findViewById(R.id.pager);
        adapter = new ViewPagerAdapter(this);
        pager.setAdapter(adapter);

        button01 = (Button) findViewById(R.id.button01);
        editText01 = (EditText) findViewById(R.id.editText01);
        adapter.notifyDataSetChanged();
//        slidingPanelLayout =  (SlidingUpPanelLayout)findViewById(R.id.sliding_layout);
//
//        button01.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(getApplicationContext(),pager.getCurrentItem()+ page.webView.getUrl() + "에서 \n" +editText01.getText().toString()+ "으로 이동", Toast.LENGTH_SHORT).show();
//                pager.getCurrentItem();
//                page..;
//                page.webView.loadUrl(editText01.getText().toString());
//            }
//        });

    }

    /**
     * 뷰페이저를 위한 어댑터 정의
     */
    public class ViewPagerAdapter extends PagerAdapter {
        // sample names
        private String[] names = { "다음", "네이버", "한샘_로그인" };
        // sample image resource ids
        private String[] resIds = {"http://m.daum.net", "http://m.naver.com", "http://login.hanssem.com/"};

        /**
         * Context 객체
         */
        private Context mContext;

        /**
         * 초기화
         *
         * @param context
         */
        public ViewPagerAdapter( Context context ) {
            mContext = context;
        }

        /**
         * 페이지 갯수
         */
        public int getCount() {
            return names.length;
        }

        /**
         * 뷰페이저가 만들어졌을 때 호출됨
         */
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            // create a instance of the page and set data
            page = new Day5_viewPage(mContext);
            //page.setNameText(names[position]);
            page.setWebView(resIds[position]);

            // 컨테이너에 추가
            container.addView(page, position);

            return page;
        }

        /**
         * Called to remove the page
         */
        public void destroyItem(ViewGroup container, int position, Object view) {
            container.removeView((View)view);
        }

        public boolean isViewFromObject(View view, Object object) {

            return view.equals(object);
        }

    }

}
