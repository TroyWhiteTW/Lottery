package com.example.user.lottery;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HistoryActivity extends AppCompatActivity {
    Button btn_history, btn_member, btn_game, btn_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        btn_list = (Button) findViewById(R.id.btn_list_history);
        btn_game = (Button) findViewById(R.id.btn_game_history);
        btn_member = (Button) findViewById(R.id.btn_member_history);
        btn_history = (Button) findViewById(R.id.btn_history_history);

        btn_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(HistoryActivity.this, ListActivity.class);
                startActivity(it);
                finish();
            }
        });
        btn_game.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(HistoryActivity.this, GameActivity.class);
                startActivity(it);
                finish();
            }
        });
        btn_member.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(HistoryActivity.this, MemberActivity.class);
                startActivity(it);
                finish();
            }
        });
        btn_history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}
