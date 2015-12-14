package com.example.user.junyeoljo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class Day6_JJY extends ActionBarActivity implements AdapterView.OnItemClickListener {

    String Curday,CurYear, CurMonth = "";

    int CurSetday =0 ;
    /**
     * 월별 캘린더 뷰 객체
     */
    CalendarMonthView monthView;

    /**
     * 월별 캘린더 어댑터
     */
    CalendarMonthAdapter monthViewAdapter;

    /**
     * 월을 표시하는 텍스트뷰
     */
    TextView monthText;

    /**
     * 현재 연도
     */
    int curYear;
    /**
     * 현재 월
     */
    int curMonth;

    final int REQUEST_CODE = 1002;
    ListView listView1;
    IconTextListAdapter listViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day6);

        // 캘린더 리스트뷰 부분
        listView1 = (ListView) findViewById(R.id.listView);
        listViewAdapter = new IconTextListAdapter(this);
        listViewAdapter.addItem(new IconTextItem("2015-10-02", "내생일", "10"));
        listViewAdapter.addItem(new IconTextItem("2015-11-11", "빼빼로데이", "11"));
        listViewAdapter.addItem(new IconTextItem("2015-12-25", "크리스마스", "12"));

        listView1.setAdapter(listViewAdapter);
        // listView1 클릭시 이벤스 설정.
        listView1.setOnItemClickListener(this);

        listView1.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("position",Integer.toString(position-1));
                listViewAdapter.removeItem(position);
                for(int i=0;i<listViewAdapter.getCount();i++) {
                    String[] result1 = listViewAdapter.getItemDay(i).toString().split("-");
                    if (Integer.parseInt(result1[1]) == (curMonth + 1)) {
                        monthViewAdapter.setdayPosition(Integer.parseInt(result1[2]));
                    }
                }
                monthViewAdapter.notifyDataSetChanged();
                return true;
            }
        });
        // 월별 캘린더 뷰 객체 참조
        monthView = (CalendarMonthView) findViewById(R.id.monthView);
        monthViewAdapter = new CalendarMonthAdapter(this);
        monthView.setAdapter(monthViewAdapter);

        // 리스너 설정
        monthView.setOnDataSelectionListener(new OnDataSelectionListener() {
            public void onDataSelected(AdapterView parent, View v, int position, long id) {
                // 현재 선택한 일자 정보 표시
                MonthItem curItem = (MonthItem) monthViewAdapter.getItem(position);

                CurYear = Integer.toString(monthViewAdapter.getCurYear());
                CurMonth = Integer.toString(monthViewAdapter.getCurMonth()+1);
                Curday = Integer.toString(curItem.getDay());
                //Toast.makeText(getApplicationContext(),CurYear+"-"+CurMonth+"-"+Curday, Toast.LENGTH_SHORT).show();

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
                setDayTextColor();
            }
        });

        // 다음 월로 넘어가는 이벤트 처리
        Button monthNext = (Button) findViewById(R.id.monthNext);
        monthNext.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                monthViewAdapter.setNextMonth();
                monthViewAdapter.notifyDataSetChanged();

                setMonthText();
                setDayTextColor();
            }
        });

    }


    /**
     * 월 표시 텍스트 설정
     */
    private void setMonthText() {
        curYear = monthViewAdapter.getCurYear();
        curMonth = monthViewAdapter.getCurMonth();

        monthText.setText(curYear + "년 " + (curMonth + 1) + "월");

    }

    private  void setDayTextColor() {
        for(int i=0;i<listViewAdapter.getCount();i++) {
            String[] result1 = listViewAdapter.getItemDay(i).toString().split("-");
            if (Integer.parseInt(result1[0]) == (curYear) && Integer.parseInt(result1[1]) == (curMonth + 1)) {
                monthViewAdapter.setdayPosition(Integer.parseInt(result1[2]));
            }
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_sub, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        if (id == R.id.action_add) {
            //return true;
            if(Curday == null || Curday.equals("")){
                Toast.makeText(getApplicationContext(),"선택된 날짜가 없습니다.", Toast.LENGTH_SHORT).show();
            }
            else {

                //Toast.makeText(getApplicationContext(),CurYear+"-"+CurMonth+"-"+Curday, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Day6_JJY.this,Day6_addschedule.class);
                intent.putExtra("value",CurYear+"-"+CurMonth+"-"+Curday);
                startActivityForResult(intent,REQUEST_CODE);

            }
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQUEST_CODE:
                if(resultCode == RESULT_OK){

                    if(CurMonth.length()==1) {
                        CurMonth = "0"+CurMonth;
                    }
                        listViewAdapter.addItem(new IconTextItem(CurYear + "-" + CurMonth + "-" + Curday, data.getStringExtra("result"),CurMonth));
                        listView1.setAdapter(listViewAdapter);
                }
                break;
        }
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        //Toast.makeText(Day6_JJY.this, listViewAdapter.getItem(position).toString(), Toast.LENGTH_SHORT).show();

        String[] result = listViewAdapter.getItemDay(position).toString().split("-");
        String selyear = result[0];
        String selmon = result[1];
        String selday = result[2];
        int selmonth = Integer.parseInt(selmon);
        //selmonth = 12, curMonth+1 = 8
        //Toast.makeText(getApplicationContext(), Integer.toString(selmonth) +  "-" +Integer.toString(curMonth+1), Toast.LENGTH_SHORT).show();
        if(((curMonth+1) - selmonth)<0) {

            for(int i=0;i<(selmonth - (curMonth+1));i++){
                monthViewAdapter.setNextMonth();
                monthViewAdapter.notifyDataSetChanged();
                monthText.setText(selyear + "년 " + (selmon) + "월");
            }


        } else if(((curMonth+1) - selmonth)>0) {

            for(int i=0;i<((curMonth+1) - selmonth);i++){
                monthViewAdapter.setPreviousMonth();
                monthViewAdapter.notifyDataSetChanged();
                monthText.setText(selyear + "년 " + (selmon) + "월");
            }

        }
        curYear=Integer.parseInt(selyear);
        curMonth=Integer.parseInt(selmon)-1;
        CurSetday = Integer.parseInt(selday);
        CurYear = selyear;
        CurMonth = Integer.toString(curMonth);
        Curday = selday;

        //monthViewAdapter.setdayPosition(CurSetday);
        setDayTextColor();
        monthViewAdapter.setSelectedPosition(monthViewAdapter.firstDay + CurSetday - 1);
        monthViewAdapter.notifyDataSetChanged();


    }

}
