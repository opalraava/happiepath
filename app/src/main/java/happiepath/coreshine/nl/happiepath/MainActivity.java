package happiepath.coreshine.nl.happiepath;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    DrawView drawView;
    final Handler myHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "TODO: (re)start animation now", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();

//                drawView.mode++;
//                if (drawView.mode >= drawView.max_mode )
//                    drawView.mode = 0;

                drawView.isRunning = !drawView.isRunning;

                if (drawView.isRunning)
                    fab.setImageResource(android.R.drawable.ic_media_pause);
                else
                    fab.setImageResource(android.R.drawable.ic_media_play);
            }
        });

        drawView = (DrawView) findViewById(R.id.drawview);
        drawView.mode = 0;

        // Redraw once a second...
        Timer myTimer = new Timer();
        myTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                myHandler.post(myRunnable);
            }
        }, 0, 1);

    }

    // update the drawView
    final Runnable myRunnable = new Runnable() {
        public void run() {
            // GUI calls allowed...
            drawView.invalidate();
        }
    };



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.go_happie) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
