package happiepath.coreshine.nl.happiepath;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import java.util.Random;
import java.util.Vector;

/**
 * Created by opalraava on 24/09/17.
 */

public class DrawView extends View {
    public boolean isRunning = true;
    public int max_mode = 6;
    public int mode = 0;

    Paint paint = new Paint();

    private void init() {
        paint.setColor(Color.WHITE);
    }

    public DrawView(Context context) {
        super(context);
        init();
    }

    public DrawView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public DrawView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    @Override
    public void onDraw(Canvas canvas) {
        if (isRunning == true)
            switch (mode) {
                case 0:
                    for (int i = 0; i < 100; i++) drawRandomLine(canvas);
                    break;
                case 1:
                    for (int i = 0; i < 1000; i++) drawRandomLine(canvas);
                    break;
                case 2:
                    for (int i = 0; i < 42; i++) drawRandomLine(canvas);
                    break;
                case 3:
                    drawMatrixRandomColors(canvas);
                    break;
                case 4:
                    drawGray(canvas);
                    break;
                case 5:
                    drawMatrixGradient(canvas);
                    break;
            }
        else
            drawGray(canvas);
    }

    private class rgb_t { public int r, g, b; }
    int is_initialized = 0;
    Vector<Vector<rgb_t>> matrix;
    private void drawMatrixGradient(Canvas canvas) {
        Random rnd = new Random();
        int matrix_rows = 100;
        int matrix_cols = 100;

        if (is_initialized == 0) {
            is_initialized = 1;
            // initialize...
            matrix = new Vector<Vector<rgb_t>>();
            for (int i = 0; i < matrix_rows; i++) {
                Vector<rgb_t> row = new Vector<rgb_t>();
                for (int j = 0; j < matrix_cols; j++){
                    int r = rnd.nextInt(255), g = rnd.nextInt(255), b = rnd.nextInt(255);
                    rgb_t pix = new rgb_t(); pix.r = r; pix.g = g; pix.b = b;
                    row.add(pix);
                }
                matrix.add(row);
            }

        }

        // per frame action...
        for (int i = 0; i < matrix_rows; i++) {
            Vector<rgb_t> row = matrix.get(i);
            for (int j = 0; j < matrix_cols; j++){
                rgb_t pix = row.get(j);
            }
        }
    }

    private void drawGray(Canvas canvas) {
        canvas.drawRGB(127,127,127);
    }

    private Paint randomColor(){
        Random rnd = new Random();
        Paint p = new Paint();
        int a = 255, r = rnd.nextInt(255), g = rnd.nextInt(255), b = rnd.nextInt(255);
        p.setARGB(a,r,g,b);
        return p;
    }

    private void drawMatrixRandomColors(Canvas canvas) {
        // canvas dimensions...
        int width = canvas.getWidth();
        int height = canvas.getHeight();

        // the actual parameters to this drawing algorithm
        int num_rows = width/50;
        int num_cols = height/50;
        float point_radius = 7.0f;

        // offset to draw the pixel in the middle...
        int x_offs = width / num_cols / 2;
        int y_offs = height / num_rows / 2;
        int grid_spacing = 0;
        if (width < height)
            grid_spacing = width/num_cols;
        else
            grid_spacing = height/num_rows;

        for (int x = x_offs; x < width - grid_spacing; x += grid_spacing)
            for (int y = y_offs; y < height - grid_spacing; y += grid_spacing)
            {
                Paint p = randomColor();
                canvas.drawCircle(x,y,point_radius,p);
            }
    }

    private void drawRandomLine(Canvas canvas) {
        Random rnd = new Random();

        Paint p = new Paint();
        int a = 255, r = rnd.nextInt(255), g = rnd.nextInt(255), b = rnd.nextInt(255);
        p.setARGB(a,r,g,b);
        p.setStrokeWidth(10.0f);

        int x1 = rnd.nextInt(canvas.getWidth());
        int y1 = rnd.nextInt(canvas.getHeight());
        int x2 = rnd.nextInt(canvas.getWidth());
        int y2 = rnd.nextInt(canvas.getHeight());

        switch (mode)
        {
            case 0:
                canvas.drawLine(x1,y1,x2,y2,p);
                break;
            case 1:
                canvas.drawCircle(x1,y1,10.0f,p);
                break;
            case 2:
                canvas.drawCircle(x1,y1, x2/10,p);
                break;
        }
        //

    }

}
