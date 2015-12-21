package com.example.user.junyeoljo;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.InputStream;
import java.net.URL;
import java.util.Calendar;
import java.util.HashMap;

public class Day7_JJY extends ActionBarActivity {

    static HashMap<String, String> hashmap = new HashMap<String, String>();

    int month_temp = 0;    //월정보
    int day_temp = 0;        //일정보
    int year_temp = 0;    //년정보

    String[] sWfKor;       //날씨정보
    String[] sDay;         //예보날짜(0~2까지 3일치 가져온다)
    String sTm;            //발표시각(YYYYMMDDHHmm형식이다)

    int data = 0;    //이건 파싱해서 array로 넣을때 번지
    boolean bDay;    //이건 예보날짜에 대한 flag
    boolean bTm;    //이건 발표시각에 대한 flag
    boolean bWfKor;    //이건 날씨에 대한 flag
    boolean tTm;    //이건 발표날짜에 대한 flag 텍스트에 넣는 용도
    boolean tItem;    //이건 파싱끝에 대한 flag 텍스트에 넣는용도
    Handler handler;    //핸들러
    // 월별 캘린더 뷰 객체
    CalendarMonthView_Day7 monthView;
    // 월별 캘린더 어댑터
    CalendarMonthAdapter_Day7 monthViewAdapter;
    MonthItem_Day7 monthitem;
    // 월을 표시하는 텍스트뷰
    TextView monthText;
    // 현재 연도
    int curYear;
    // 현재 월
    int curMonth;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day7);

        handler = new Handler();
        bWfKor = tItem = tTm = bTm = bDay = false;        // false로 초기화해주자
        sWfKor = new String[31];    // 날씨
        sDay = new String[31];    // 예보날짜

        // 월별 캘린더 뷰 객체 참조
        monthView = (CalendarMonthView_Day7) findViewById(R.id.monthView);
        monthViewAdapter = new CalendarMonthAdapter_Day7(this);
        monthView.setAdapter(monthViewAdapter);

        // 오늘 날씨를 받아 오기위한 고정값
        year_temp = Calendar.getInstance().get(Calendar.YEAR);
        month_temp = Calendar.getInstance().get(Calendar.MONTH) +1 ;
        day_temp = Calendar.getInstance().get(Calendar.DATE);

        // 리스너 설정
        monthView.setOnDataSelectionListener(new OnDataSelectionListener() {
            public void onDataSelected(AdapterView parent, View v, int position, long id) {
                // 현재 선택한 일자 정보 표시
                MonthItem_Day7 curItem = (MonthItem_Day7) monthViewAdapter.getItem(position);
                int day = curItem.getDay();

                year_temp = curYear;        //사실 변수를 만들필요는 없지만;;
                month_temp = curMonth + 1;    //월은 0부터 시작이므로 +1
                day_temp = day;            //일 값

                if (day_temp == 0) {        //날짜가 선택안될 때

                } else {                    //날짜가 선택되면
                    //현재 선택된 날짜를 String으로 바꾸어준다(YYYYMMDD형식으로)
                    String temp_date = converterDate(year_temp, month_temp, day_temp);

                }
            }
        });

        monthText = (TextView) findViewById(R.id.monthText);
        setMonthText();


        // 이전 월로 넘어가는 이벤트 처리
        Button monthPrevious = (Button) findViewById(R.id.monthPrevious);
        monthPrevious.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                monthViewAdapter.setPreviousMonth();
                monthViewAdapter.notifyDataSetChanged();

                setMonthText();
            }
        });

        // 다음 월로 넘어가는 이벤트 처리
        Button monthNext = (Button) findViewById(R.id.monthNext);
        monthNext.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                monthViewAdapter.setNextMonth();
                monthViewAdapter.notifyDataSetChanged();

                setMonthText();
            }
        });

        network_thread thread = new network_thread();
        thread.start();
    }

    /**
     * 기상청을 연결하여 정보받고 뿌려주는 스레드
     */
    class network_thread extends Thread {    //기상청 연결을 위한 스레드
        /**
         * 기상청을 연결하는 스레드
         */
        public void run() {

            try {

                XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
                factory.setNamespaceAware(true);
                XmlPullParser xpp = factory.newPullParser();

                String weatherUrl = "http://www.kma.go.kr/wid/queryDFSRSS.jsp?zone=1165059000";    //이곳이 기상청URL
                URL url = new URL(weatherUrl);        //URL객체생성
                InputStream is = url.openStream();    //연결할 url을 inputstream에 넣어 연결을 하게된다.
                xpp.setInput(is, "UTF-8");            //이렇게 하면 연결이 된다. 포맷형식은 utf-8로

                int eventType = xpp.getEventType();    //풀파서에서 태그정보를 가져온다.

                while (eventType != XmlPullParser.END_DOCUMENT) {    //문서의 끝이 아닐때

                    switch (eventType) {
                        case XmlPullParser.START_TAG:    //'<'시작태그를 만났을때

                            if (xpp.getName().equals("wfKor")) {    //날씨정보(맑음, 구름조금, 구름많음, 흐림, 비, 눈/비, 눈)
                                bWfKor = true;
                            }
                            if (xpp.getName().equals("day")) {    //예보날짜정보
                                bDay = true;
                            }
                            if (xpp.getName().equals("tm")) {    //발표시각정보
                                bTm = true;
                            }
                            break;

                        case XmlPullParser.TEXT:    //텍스트를 만났을때
                            //앞서 시작태그에서 얻을정보를 만나면 플래그를 true로 했는데 여기서 플래그를 보고
                            if (bWfKor) {                //날씨
                                sWfKor[data] = xpp.getText();
                                bWfKor = false;
                            }
                            if (bDay) {                //예보날짜
                                sDay[data] = xpp.getText();
                                bDay = false;
                            }
                            if (bTm) {                //발표시각
                                sTm = xpp.getText();
                                bTm = false;
                            }
                            break;

                        case XmlPullParser.END_TAG:        //'>' 엔드태그를 만나면 (이부분이 중요)

                            if (xpp.getName().equals("item")) {    //태그가 끝나느 시점의 태그이름이 item이면(이건 거의 문서의 끝
                                tItem = true;                        //따라서 이때 모든 정보를 화면에 뿌려주면 된다.
                                view_text();                         //뿌려주는 곳~
                            }
                            if (xpp.getName().equals("data")) {    //data태그는 예보시각기준 예보정보가 하나씩이다.
                                data++;                            //즉 data태그 == 예보 개수 그러므로 이때 array를 증가해주자
                            }
                            break;
                    }
                    eventType = xpp.next();    //이건 다음 이벤트로~
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        /**
         * 이 부분이 뿌려주는곳
         * 뿌리는건 핸들러가~
         */
        private void view_text() {

            handler.post(new Runnable() {    //기본 핸들러니깐 handler.post하면됨

                @Override
                public void run() {

                    if (tItem) {        //문서를 다 읽었다

                        String cur_date, cur_date1;

                        tItem = false;

                        //선택된 날짜를 스트링으로 바꾸자
                        cur_date = converterDate(year_temp, month_temp, day_temp);
                        //파싱한 데이터를 for문으로 저장
                        for (int i = 0; i < 3; i++) {
                            //발표시각에서 시간은 날려주고 예보날짜(0~2)를 더해주면 예보날짜가 나온다, 비교를 위해 스트링으로~
                            String get_date = String.valueOf((Long.parseLong(sTm) / 10000) + Integer.parseInt(sDay[i]));

                            cur_date1 = converterDate(year_temp, month_temp, day_temp+i);

//                            hashmap.put(cur_date, sWfKor[i]);
                            //선택한 날짜와 xml파싱한 날짜가 같으면 해쉬맵에 넣고 빠져나온다.(무조건 첫예보만 가져온다;;)
                           //if (get_date.toString().equals(cur_date)) {
                                hashmap.put(cur_date1, sWfKor[i]);
                           //     break;
                           // }
                        }
                        //날씨정보를 가지고있으면 toast로
                        if (hashmap.get(cur_date) != null) {
                            //Toast.makeText(getApplicationContext(), hashmap.get(cur_date), Toast.LENGTH_SHORT).show();
                        } else {//없을때
                            Toast.makeText(getApplicationContext(), "날씨정보가 없습니다", Toast.LENGTH_SHORT).show();
                        }

                        monthViewAdapter.notifyDataSetInvalidated();    //data가 업댓됐으니~
                        monthViewAdapter.resetDayNumbers();            //날짜를 리셋해주면 같은 레이어에 있는 날씨아이콘과 텍스트 뿌려짐
                        data = 0;    //다음에 또 써야하니 0으로
                    }
                }
            });
        }
    }

    /**
     * 받아온 날짜값(int->string) 변환
     *
     * @param year
     * @param month
     * @param day
     */
    public String converterDate(int year, int month, int day) {

        String cur_date;

        if ((month < 10) && (day < 10)) {    //int형이라 10이하면 1자리다 우린 두자리로 된게 필요하니깐
            cur_date = String.valueOf(year) + "0" + String.valueOf(month) + "0" + String.valueOf(day);
        } else if ((month < 10)) {
            cur_date = String.valueOf(year) + "0" + String.valueOf(month) + String.valueOf(day);
        } else if ((day < 10)) {
            cur_date = String.valueOf(year) + String.valueOf(month) + "0" + String.valueOf(day);
        } else {
            cur_date = String.valueOf(year) + String.valueOf(month) + String.valueOf(day);
        }
        return cur_date;
    }

    /**
     * 월 표시 텍스트 설정
     */
    private void setMonthText() {

        curYear = monthViewAdapter.getCurYear();
        curMonth = monthViewAdapter.getCurMonth();

        monthText.setText(curYear + "년 " + (curMonth + 1) + "월");
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_sub, menu);
        return true;
    }

    // 상단바의 버튼 클릭시 이벤트
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        if (id == R.id.action_add) {
            // 날짜정보 가져오는 부분.
//            if ((day_temp != 0) && (month_temp != 0)) {        //일과 월이 제대로 선택되면
//                network_thread thread = new network_thread(); //네트웍작업이라 스레드를 하나 만들자
//                thread.start();    //스레드 시작
//            } else {    //월과 일이 제대로 선택이 안되면
//                Toast.makeText(getApplicationContext(), "날짜를 선택해 주세요", Toast.LENGTH_LONG).show();
//            }
        }
        return super.onOptionsItemSelected(item);
    }
}