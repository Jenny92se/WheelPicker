package com.aigestudio.wheelpicker.demo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.aigestudio.wheelpicker.WheelPicker;

/**
 * @author AigeStudio 2015-12-06
 * @author AigeStudio 2016-07-08
 */
public class PreviewActivity extends Activity implements WheelPicker.OnItemSelectedListener {

    private String getItem;
    private WheelPicker wheelRight;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_preview);

     //   WheelPicker wheelLeft = (WheelPicker) findViewById(R.id.main_wheel_left);
       // wheelLeft.setOnItemSelectedListener(this);
    //    WheelPicker wheelCenter = (WheelPicker) findViewById(R.id.main_wheel_center);
     //   wheelCenter.setOnItemSelectedListener(this);
        wheelRight = (WheelPicker) findViewById(R.id.main_wheel_right);
        wheelRight.setOnItemSelectedListener(this);
  //      wheelRight.setClickable(true);
        wheelRight.setOnTouchListener(touchListener);
      //  wheelRight.setOnClickListener(clickListener);
        int initialPosition=wheelRight.getCurrentItemPosition();
        Log.d("initial position","pos: "+initialPosition);
        getItem=wheelRight.getData().get(initialPosition).toString();
    }

    @Override
    public void onItemSelected(WheelPicker picker, Object data, int position) {
        String text = "";
        switch (picker.getId()) {
       /*     case R.id.main_wheel_left:
                text = "Left:";
                break;
            case R.id.main_wheel_center:
                text = "Center:";
                break;*/
            case R.id.main_wheel_right:
                text = "Right:";
                break;
        }
        getItem=String.valueOf(data);

    }

    // interface를 하나밖에 implement 하지 못하여 리스너 객체를 생성하여 사용
    private View.OnTouchListener touchListener = new View.OnTouchListener(){

        public boolean onTouch(View v, MotionEvent event)
        {
            switch (event.getAction()){
                // if pressed
                case MotionEvent.ACTION_DOWN:{
                    break;
                }
                case MotionEvent.ACTION_CANCEL:
                case MotionEvent.ACTION_UP:{
                        if(wheelRight.getIsClick()) { //아이템 선택 후, 클릭일 경우만 인탠트발생.
                            Intent intent = new Intent(getApplicationContext(), ContentActivity.class);
                            intent.putExtra("item", getItem);
                            startActivity(intent);
                        }
                    break;
                }
                default:{
                    break;
                }
            }
            return false;
            // false이 onTouchListener 이후에도 다른 Listener들이 동작하게 함.
        }
    };

    private View.OnClickListener clickListener = new View.OnClickListener(){
        @Override
        public void onClick(View v) {
            Log.d("click","true");
            Intent intent = new Intent(getApplicationContext(), ContentActivity.class);
            intent.putExtra("item", getItem);
            startActivity(intent);
        }
    };

}