package com.example.recep.sayac;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Vibrator;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;

public class MainActivity extends AppCompatActivity implements SharedPreferences.OnSharedPreferenceChangeListener {

    private int a;
    private Button btn1;
    private SharedPreferences preferences,secenekler;
    private RelativeLayout rl;
    private Boolean titresim_durumu;
    private AdView adView;

    @Override
    protected void onPause() {
        SharedPreferences.Editor editor=preferences.edit();
        editor.putInt("Sayıcı",a);
        editor.commit();
        super.onPause();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn1 = (Button) findViewById(R.id.btn1);
        rl = (RelativeLayout) findViewById(R.id.rl);

        preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        secenekler = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        final MediaPlayer ses0=MediaPlayer.create(getApplicationContext(),R.raw.ses0);
        final MediaPlayer ses1=MediaPlayer.create(getApplicationContext(),R.raw.ses1);
        final MediaPlayer ses2=MediaPlayer.create(getApplicationContext(),R.raw.ses2);
        final MediaPlayer ses3=MediaPlayer.create(getApplicationContext(),R.raw.ses3);
        final MediaPlayer ses4=MediaPlayer.create(getApplicationContext(),R.raw.ses4);
        final Vibrator titresim= (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        ReklamYukle();
        AyarlariYükle();
        a=preferences.getInt("Sayıcı",0);
        btn1.setText(""+a);



        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String ses_pos=secenekler.getString("ses1","3");

                switch (Integer.valueOf(ses_pos)){

                    case 0:
                        ses0.start();
                        break;
                    case 1:
                        ses1.start();
                        break;
                    case 2:
                        ses2.start();
                        break;
                    case 3:
                        ses3.start();
                        break;
                    case 4:
                        ses4.start();
                        break;
                    case 5:

                        break;


                }

                if(titresim_durumu){titresim.vibrate(250);}
                a++;
                btn1.setText(""+a);
            }
        });


    }

    private void ReklamYukle() {
        adView = new AdView(this);
        adView.setAdSize(AdSize.BANNER);
        adView.setAdUnitId(getString(R.string.reklam_kimligi));

        LinearLayout ly = (LinearLayout) findViewById(R.id.ly);
        ly.addView(adView);

        AdRequest adRequest = new AdRequest.Builder().addTestDevice(AdRequest.DEVICE_ID_EMULATOR).build();

        adView.loadAd(adRequest);


    }

    public void AyarlariYükle() {
        String pos=secenekler.getString("arkaplan","3");

        switch (Integer.valueOf(pos)){

            case 0:
                rl.setBackgroundResource(R.color.r0);
                break;
            case 1:
                rl.setBackgroundResource(R.color.r1);
                break;
            case 2:
                rl.setBackgroundResource(R.color.r2);
                break;
            case 3:
                rl.setBackgroundResource(R.color.r3);
                break;
            case 4:
                rl.setBackgroundResource(R.color.r4);
                break;
            case 5:
                rl.setBackgroundResource(R.drawable.bac1);
                break;
            case 6:
                rl.setBackgroundResource(R.drawable.bac2);
                break;
            case 7:
                rl.setBackgroundResource(R.drawable.bac3);
                break;
            case 8:
                rl.setBackgroundResource(R.drawable.bac4);
                break;
            case 9:
                rl.setBackgroundResource(R.drawable.bac5);
                break;
            case 10:
                rl.setBackgroundResource(R.drawable.bac6);
                break;

        }






        titresim_durumu=secenekler.getBoolean("titresim",false);
        secenekler.registerOnSharedPreferenceChangeListener(MainActivity.this);

    }

    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_ana_sayfa, menu);
        return true;

    }

    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if(id==R.id.set1){
            Intent intent = new Intent(getApplicationContext(),Ayarlar.class);
            startActivity(intent);
            return true;
        }

        if(id==R.id.set2){
            a=0;
            btn1.setText(""+a);

            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String s) {
        AyarlariYükle();
    }

}
