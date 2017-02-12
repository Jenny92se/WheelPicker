package com.aigestudio.wheelpicker.demo;

import android.app.Activity;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by ppoya on 2017-02-05.
 */
public class ContentActivity extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);

        Intent intent = getIntent();
        String item = intent.getExtras().getString("item");

        TextView selectedItem=(TextView)findViewById(R.id.selected_item);
        selectedItem.setText(item);

        Button playButton=(Button)findViewById(R.id.btn_play);
        playButton.setClickable(true);
        playButton.setOnClickListener(new Button.OnClickListener() {

            @Override
            public void onClick(View v) {

                try {
                    AssetFileDescriptor afd = getAssets().openFd("audioSpeech/recorded.mp3");
                    MediaPlayer player = new MediaPlayer();
                    player.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
                    player.prepare();
                    player.start();
                }catch (Exception ex)
                {
                    Log.d("playException","occured");
                }
            }
        });
    }
}
