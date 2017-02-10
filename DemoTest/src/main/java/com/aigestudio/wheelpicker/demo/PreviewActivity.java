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
    private boolean isSelected=false;
    private boolean isDown=false;
    private boolean isUp=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_preview);

     //   WheelPicker wheelLeft = (WheelPicker) findViewById(R.id.main_wheel_left);
       // wheelLeft.setOnItemSelectedListener(this);
    //    WheelPicker wheelCenter = (WheelPicker) findViewById(R.id.main_wheel_center);
     //   wheelCenter.setOnItemSelectedListener(this);
        WheelPicker wheelRight = (WheelPicker) findViewById(R.id.main_wheel_right);
        wheelRight.setOnItemSelectedListener(this);
        wheelRight.setOnTouchListener(touchListener);
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
    //    Intent intent = new Intent(getApplicationContext(), ContentActivity.class);
 //       intent.putExtra("item", String.valueOf(data));
    //    startActivity(intent);
        getItem=String.valueOf(data);
        if(isUp&&isDown) {
            isSelected = true;
            Log.d("selected","true");
        }
        else {
            isSelected=false;
            Log.d("selected","false");
        }
        isDown=false;
        isUp=false;

    }

    private View.OnTouchListener touchListener = new View.OnTouchListener(){

        public boolean onTouch(View v, MotionEvent event)
        {
            switch (event.getAction()){

                // if pressed
                case MotionEvent.ACTION_DOWN:{
                    if(isSelected) {
                        isSelected=false;
                        Log.d("selected","false");
                        Intent intent = new Intent(getApplicationContext(), ContentActivity.class);
                        intent.putExtra("item", getItem);
                        startActivity(intent);

                    }
                    isDown=true;
                    break;
                }

                case MotionEvent.ACTION_CANCEL:
                case MotionEvent.ACTION_UP:{

                    isUp=true;
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
}