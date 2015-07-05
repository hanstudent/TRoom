package com.example.ettie.teachersroom;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       /* To hide action bar/title bar
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);*/
        setTitle(R.string.tr);
        setContentView(R.layout.activity_main);

    }

    public void showLogin(View v) {
        Intent intent = new Intent(this, ReturningUserLoginActivity.class);
        startActivity(intent);
    }

    public void showSignUp(View v) {
        Intent intent = new Intent(this, NewUserLoginActivity.class);
        startActivity(intent);
    }

    public void showTestingScreen(View v) {
        Intent intent = new Intent(MainActivity.this, ListOfPosts.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.about:
                Toast.makeText(this, R.string.BSD, Toast.LENGTH_SHORT).show();
                return true;
            case R.id.contact:
                Toast.makeText(this, R.string.contactInfo, Toast.LENGTH_LONG).show();
                return true;
        }
        return false;
    }
}
