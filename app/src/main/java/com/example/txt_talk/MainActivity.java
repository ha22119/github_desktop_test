// 야호야호 주석이당

package com.example.txt_talk;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    LinearLayout myLayout,friend1,friend2,friend3;
    TextView myName, mySangMe;
    View dialogView; //대화상자에 특정한 xml UI를 이식할때
    EditText inputArea;
    int selectFriends;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        myLayout = (LinearLayout)findViewById(R.id.myLayout);
        friend1 = (LinearLayout)findViewById(R.id.friend1);
        friend2 = (LinearLayout)findViewById(R.id.friend2);
        friend3 = (LinearLayout)findViewById(R.id.friend3);

        Log.d("okay","됨");
        //ContextMenu 사용하기 위해 해야할 것
        //ContextMenu 등록하기
        registerForContextMenu(myLayout);
        registerForContextMenu(friend1);
        registerForContextMenu(friend2);
        registerForContextMenu(friend3);
    }

    //옵션메뉴 사용을 위한 onCreateOption...
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuIn = getMenuInflater();
        menuIn.inflate(R.menu.option_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.optionMenu_deleteAllFriends:
                AlertDialog.Builder dlg = new AlertDialog.Builder(this);
                dlg.setMessage("모두 삭제하시겠습니까?");
                dlg.setPositiveButton("네", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        friend1.setVisibility(View.GONE);
                        friend2.setVisibility(View.GONE);
                        friend3.setVisibility(View.GONE);
                    }
                });
                dlg.setNegativeButton("아니오", null);
                dlg.show();
                return true;

            case R.id.optionMenu_restoreAllFriends:
                friend1.setVisibility(View.VISIBLE);
                friend2.setVisibility(View.VISIBLE);
                friend3.setVisibility(View.VISIBLE);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }


    //메뉴 등록하기
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        if(v == myLayout){
            MenuInflater menuIn = getMenuInflater();
            menuIn.inflate(R.menu.my_context_menu,menu);
        }
        if(v == friend1){
            selectFriends = 1;
            MenuInflater menuIn = getMenuInflater();
            menuIn.inflate(R.menu.friends_context_menu,menu);
        }
        if(v == friend2){
            selectFriends = 2;

            MenuInflater menuIn = getMenuInflater();
            menuIn.inflate(R.menu.friends_context_menu,menu);
        }
        if(v == friend3){
            selectFriends = 3;

            MenuInflater menuIn = getMenuInflater();
            menuIn.inflate(R.menu.friends_context_menu,menu);
        }
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.myContextMenu_changeName:
                dialogView = (View)View.inflate
                        (this, R.layout.input_dialog,null);
                AlertDialog.Builder changeNameDlg = new AlertDialog.Builder(this);

                changeNameDlg.setTitle("이름을 입력하세요");
                changeNameDlg.setView(dialogView);
                changeNameDlg.setNegativeButton("안 바꿀래요",null);
                changeNameDlg.setPositiveButton("개명하겠습니다", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        inputArea = (EditText)dialogView.findViewById(R.id.inputArea);
                        myName = (TextView) findViewById(R.id.myName);
                        myName.setText(inputArea.getText().toString());
                    }
                });
                changeNameDlg.show();
                return true;

            case R.id.myContextMenu_changeSangMe:
                dialogView = (View)View.inflate(this, R.layout.input_dialog,null);
                AlertDialog.Builder changeSangMeDlg = new AlertDialog.Builder(MainActivity.this);

                changeSangMeDlg.setTitle("상태메세지 입력하세요");
                changeSangMeDlg.setView(dialogView);
                changeSangMeDlg.setNegativeButton("안 바꿀래요",null);
                changeSangMeDlg.setPositiveButton("바꾸기", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        inputArea = (EditText)dialogView.findViewById(R.id.inputArea);
                        mySangMe = (TextView)findViewById(R.id.mySangMe);
                        mySangMe.setText(inputArea.getText().toString());
                    }
                });
                changeSangMeDlg.show();

                return true;

            case R.id.friendsContextMenu_delFriend:
                if(selectFriends == 1)
                    friend1.setVisibility(View.GONE);
                if(selectFriends == 2)
                    friend2.setVisibility(View.GONE);
                if(selectFriends == 3)
                    friend3.setVisibility(View.GONE);
                return true;

            default:
                return super.onContextItemSelected(item);

        }
    }
}
