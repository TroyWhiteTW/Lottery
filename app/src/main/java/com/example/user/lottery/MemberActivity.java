package com.example.user.lottery;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MemberActivity extends AppCompatActivity {
    private Button btn_history, btn_member, btn_game, btn_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member);

        setFnBtn();
    }

    public void setFnBtn() {
        btn_list = (Button) findViewById(R.id.btn_list_member);
        btn_game = (Button) findViewById(R.id.btn_game_member);
        btn_member = (Button) findViewById(R.id.btn_member_member);
        btn_history = (Button) findViewById(R.id.btn_history_member);

        btn_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(MemberActivity.this, ListActivity.class);
                startActivity(it);
                finish();
            }
        });
        btn_game.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(MemberActivity.this, GameActivity.class);
                startActivity(it);
                finish();
            }
        });
        btn_member.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        btn_history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(MemberActivity.this, HistoryActivity.class);
                startActivity(it);
                finish();
            }
        });
    }
}
