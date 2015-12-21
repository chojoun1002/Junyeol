package com.example.user.junyeoljo;

import android.content.Context;
import android.graphics.Color;
import android.text.format.Time;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;

import java.util.Calendar;

/**
 * 어댑터 객체 정의
 */
public class CalendarMonthAdapter_Day7 extends BaseAdapter {

	public static final String TAG = "CalendarMonthAdapter_Day7";

	Day7_JJY main;
	Context mContext;

	public static int oddColor = Color.rgb(225, 225, 225);
	public static int headColor = Color.rgb(12, 32, 158);

	private int selectedPosition = -1;
	private int dayPosition = -1;

	private MonthItem_Day7[] items;

	private int countColumn = 7;

	int mStartDay;
	int startDay;
	int curYear;
	int curMonth;

	int firstDay;
	int lastDay;

	Calendar mCalendar;
	boolean recreateItems = false;

	public CalendarMonthAdapter_Day7(Context context) {
		super();
		mContext = context;
		init();
	}

	public CalendarMonthAdapter_Day7(Context context, AttributeSet attrs) {
		super();
		mContext = context;
		init();
	}

	private void init() {
		items = new MonthItem_Day7[7 * 6];

		mCalendar = Calendar.getInstance();
		recalculate();
		resetDayNumbers();

	}

	public void recalculate() {
		mCalendar.set(Calendar.DAY_OF_MONTH, 1);

		// get week day
		int dayOfWeek = mCalendar.get(Calendar.DAY_OF_WEEK);
		firstDay = getFirstDay(dayOfWeek);
		Log.d(TAG, "firstDay : " + firstDay);

		mStartDay = mCalendar.getFirstDayOfWeek();
		curYear = mCalendar.get(Calendar.YEAR);
		curMonth = mCalendar.get(Calendar.MONTH);
		lastDay = getMonthLastDay(curYear, curMonth);

		Log.d(TAG, "curYear : " + curYear + ", curMonth : " + curMonth + ", lastDay : " + lastDay);

		int diff = mStartDay - Calendar.SUNDAY - 1;
		startDay = getFirstDayOfWeek();
		Log.d(TAG, "mStartDay : " + mStartDay + ", startDay : " + startDay);

	}

	public void setPreviousMonth() {
		mCalendar.add(Calendar.MONTH, -1);
		recalculate();

		resetDayNumbers();
		selectedPosition = -1;
	}

	public void setNextMonth() {
		mCalendar.add(Calendar.MONTH, 1);
		recalculate();

		resetDayNumbers();
		selectedPosition = -1;
	}

	public void resetDayNumbers() {	//여기서 그리드뷰안의 레이어를 뿌려준다

		String temp_weather;		//먼저 변수하나 만들고

		for (int i = 0; i < 42; i++) {
			// calculate day number
			int dayNumber = (i+1) - firstDay;
			if (dayNumber < 1 || dayNumber > lastDay) {
				dayNumber = 0;
			}
			//귀찮지만 날짜를 스트링으로 바꾸자~그래야 해쉬맵을 쓸테니
			String temp_date=converterDate(curYear, curMonth+1, dayNumber);
			//바뀐날짜를 키로 해쉬맵에 넣어서 날씨를 가져오자
			temp_weather=main.hashmap.get(temp_date);
			//MonthItem_Day7에 값을 넣어주자
			items[i] = new MonthItem_Day7(dayNumber,temp_weather);

		}
	}

	public String converterDate(int year,int month,int day){

		String cur_date;

		if((month<10)&&(day<10)){
			cur_date=String.valueOf(year)+"0"+String.valueOf(month)+"0"+String.valueOf(day);
		}else if((month<10)){
			cur_date=String.valueOf(year)+"0"+String.valueOf(month)+String.valueOf(day);
		}else if((day<10)){
			cur_date=String.valueOf(year)+String.valueOf(month)+"0"+String.valueOf(day);
		}else{
			cur_date=String.valueOf(year)+String.valueOf(month)+String.valueOf(day);
		}
		return cur_date;
	}

	private int getFirstDay(int dayOfWeek) {
		int result = 0;
		if (dayOfWeek == Calendar.SUNDAY) {
			result = 0;
		} else if (dayOfWeek == Calendar.MONDAY) {
			result = 1;
		} else if (dayOfWeek == Calendar.TUESDAY) {
			result = 2;
		} else if (dayOfWeek == Calendar.WEDNESDAY) {
			result = 3;
		} else if (dayOfWeek == Calendar.THURSDAY) {
			result = 4;
		} else if (dayOfWeek == Calendar.FRIDAY) {
			result = 5;
		} else if (dayOfWeek == Calendar.SATURDAY) {
			result = 6;
		}
		return result;
	}


	public int getCurYear() {
		return curYear;
	}

	public int getCurMonth() {
		return curMonth;
	}

	public int getNumColumns() {
		return 7;
	}

	public int getCount() {
		return 7 * 6;
	}

	public Object getItem(int position) {
		return items[position];
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		Log.d(TAG, "getView(" + position + ") called.");

		MonthItemView_Day7 itemView;

		if (convertView == null) {
			itemView = new MonthItemView_Day7(mContext);
		} else {
			itemView = (MonthItemView_Day7) convertView;
		}

		// create a params
		GridView.LayoutParams params = new GridView.LayoutParams(
				GridView.LayoutParams.MATCH_PARENT,
				120);

		// calculate row and column
		int rowIndex = position / countColumn;
		int columnIndex = position % countColumn;

		Log.d(TAG, "Index : " + rowIndex + ", " + columnIndex);

		// set item data and properties
		itemView.setItem(items[position]);
		itemView.setLayoutParams(params);
		itemView.setPadding(2, 2, 2, 2);

		// set properties
		itemView.setGravity(Gravity.LEFT);

		if (columnIndex == 0) {
			itemView.setTextColor(Color.RED);
		} else if (columnIndex == 6) {
			itemView.setTextColor(Color.BLUE);
		} else {
			itemView.setTextColor(Color.BLACK);
		}

		// set background color
		if (position == getSelectedPosition()) {
			itemView.setBackgroundColor(Color.YELLOW);
		} else {
			itemView.setBackgroundColor(Color.WHITE);
		}

		return itemView;
	}

	public static int getFirstDayOfWeek() {
		int startDay = Calendar.getInstance().getFirstDayOfWeek();
		if (startDay == Calendar.SATURDAY) {
			return Time.SATURDAY;
		} else if (startDay == Calendar.MONDAY) {
			return Time.MONDAY;
		} else {
			return Time.SUNDAY;
		}
	}


	/**
	 * get day count for each month
	 */
	private int getMonthLastDay(int year, int month){
		switch (month) {
			case 0:
			case 2:
			case 4:
			case 6:
			case 7:
			case 9:
			case 11:
				return (31);

			case 3:
			case 5:
			case 8:
			case 10:
				return (30);

			default:
				if(((year%4==0)&&(year%100!=0)) || (year%400==0) ) {
					return (29);   // 2월 윤년계산
				} else {
					return (28);
				}
		}
	}








	/**
	 * set selected row
	 *
	 * @param selectedPosition
	 */
	public void setSelectedPosition(int selectedPosition) {
		this.selectedPosition = selectedPosition;
	}

	/**
	 * get selected row
	 *
	 * @return
	 */
	public int getSelectedPosition() {
		return selectedPosition;
	}


	/**
	 * set selected row
	 *
	 * @param dayPosition
	 */
	public void setdayPosition(int dayPosition) {
		this.dayPosition = dayPosition;
	}

	/**
	 * get selected row
	 *
	 * @return
	 */
	public int getdayPosition() {
		return dayPosition;
	}

}