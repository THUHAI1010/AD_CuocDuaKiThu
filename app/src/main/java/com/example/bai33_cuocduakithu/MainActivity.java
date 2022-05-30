package com.example.bai33_cuocduakithu;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button btnPlay;
    TextView txtDiemSo;
    SeekBar sbOne, sbTwo, sbThree;
    CheckBox cbOne, cbTwo, cbThree;
    int Diem = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AnhXa();
        DisableCheckSeebar();
        //CountDownTimer Để các con vật chạy và lặp lại
        //60000 là 60s thời gian đém ngược đuến khi kết thúc
        //300, sau 0.3s con vật nhích 1 lần
        CountDownTimer count = new CountDownTimer(60000, 300) {
            @Override
            public void onTick(long l) {
                DisableCheck();
                Random rad = new Random();
                int One = rad.nextInt(5);
                int Two = rad.nextInt(5);
                int Three = rad.nextInt(5);
                sbOne.setProgress(sbOne.getProgress() + One);
                sbTwo.setProgress(sbTwo.getProgress() + Two);
                sbThree.setProgress(sbThree.getProgress() + Three);

                //FROG WIN!
                if (sbOne.getProgress() >= sbOne.getMax()) {
                    this.cancel(); //CountDownTimer dừng lại
                    Toast.makeText(MainActivity.this, "FROG WIN!", Toast.LENGTH_SHORT).show();

                    if (cbOne.isChecked()) { //nếu bạn check đúng
                        Toast.makeText(MainActivity.this, "Bạn đã đoán ĐÚNG!", Toast.LENGTH_SHORT).show();
                        Diem += 10;
                        txtDiemSo.setText(String.valueOf(Diem));
                    } else {
                        Toast.makeText(MainActivity.this, "Bạn đã đoán SAI!", Toast.LENGTH_SHORT).show();
                        Diem -= 5;
                        txtDiemSo.setText(String.valueOf(Diem));
                    }
                    EnableCheck();
                    btnPlay.setVisibility(View.VISIBLE);
                }
                //PIG WIN!
                if (sbTwo.getProgress() >= sbTwo.getMax()) {
                    this.cancel(); //CountDownTimer dừng lại
                    Toast.makeText(MainActivity.this, "PIG WIN!", Toast.LENGTH_SHORT).show();

                    if (cbTwo.isChecked()) { //nếu bạn check đúng
                        Toast.makeText(MainActivity.this, "Bạn đã đoán ĐÚNG!", Toast.LENGTH_SHORT).show();
                        Diem += 10;
                        txtDiemSo.setText(String.valueOf(Diem));
                    } else {
                        Toast.makeText(MainActivity.this, "Bạn đã đoán SAI!", Toast.LENGTH_SHORT).show();
                        Diem -= 5;
                        txtDiemSo.setText(String.valueOf(Diem));
                    }
                    EnableCheck();
                    btnPlay.setVisibility(View.VISIBLE);
                }
                //DOG WIN!
                if (sbThree.getProgress() >= sbThree.getMax()) {
                    this.cancel(); //CountDownTimer dừng lại
                    Toast.makeText(MainActivity.this, "DOG WIN!", Toast.LENGTH_SHORT).show();

                    if (cbThree.isChecked()) { //nếu bạn check đúng
                        Toast.makeText(MainActivity.this, "Bạn đã đoán ĐÚNG!", Toast.LENGTH_SHORT).show();
                        Diem += 10;
                        txtDiemSo.setText(String.valueOf(Diem));
                    } else {
                        Toast.makeText(MainActivity.this, "Bạn đã đoán SAI!", Toast.LENGTH_SHORT).show();
                        Diem -= 5;
                        txtDiemSo.setText(String.valueOf(Diem));
                    }
                    EnableCheck();
                    btnPlay.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFinish() {
            }
        };

        cbOne.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    cbTwo.setChecked(false);
                    cbThree.setChecked(false); //khi chon cbOne thì 2 3 ẩn
                }
            }
        });
        
        cbTwo.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    cbOne.setChecked(false);
                    cbThree.setChecked(false); //khi chon cbTwo thì 1 3 ẩn
                }
            }
        });

        cbThree.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    cbTwo.setChecked(false);
                    cbOne.setChecked(false); //khi chon cbThree thì 2 1 ẩn
                }
            }
        });
        
        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                
                if(cbOne.isChecked() || cbTwo.isChecked() || cbThree.isChecked()){
                    sbOne.setProgress(0);
                    sbTwo.setProgress(0);
                    sbThree.setProgress(0);
                    count.start();
                    DisableCheck();
                    btnPlay.setVisibility(View.INVISIBLE);
                }
                else
                    Toast.makeText(MainActivity.this, "Bạn vui lòng đặt cươc trước!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void EnableCheck(){
        cbOne.setEnabled(true);
        cbTwo.setEnabled(true);
        cbThree.setEnabled(true);
    }

    private void DisableCheck(){
        cbOne.setEnabled(false);
        cbTwo.setEnabled(false);
        cbThree.setEnabled(false);
    }

    private void  DisableCheckSeebar(){
        sbOne.setEnabled(false);
        sbTwo.setEnabled(false);
        sbThree.setEnabled(false);
    }

    private void AnhXa(){
        btnPlay = (Button) findViewById(R.id.buttonPlay);
        txtDiemSo = (TextView) findViewById(R.id.textViewDiemSo);
        sbOne = (SeekBar) findViewById(R.id.seekBarOne);
        sbTwo = (SeekBar) findViewById(R.id.seekBarTwo);
        sbThree = (SeekBar) findViewById(R.id.seekBarThree);
        cbOne = (CheckBox) findViewById(R.id.checkboxOne);
        cbTwo = (CheckBox) findViewById(R.id.checkboxTwo);
        cbThree = (CheckBox) findViewById(R.id.checkboxThree);
    }
}