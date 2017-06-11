package info.datahelix.bubbles;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.Random;

public class RandomCirclesView extends View {

    public static String TAG = "RandomCirclesView";

    private Random random;
    private Paint p;
    private ArrayList<Circle> circles;
    private int width;
    private int height;

    public RandomCirclesView(Context context) {
        super(context);
        random = new Random();
        circles = new ArrayList<>();
        p = new Paint(Paint.ANTI_ALIAS_FLAG);
        p.setAntiAlias(true);
        p.setStyle(Paint.Style.FILL);
    }


    @Override
    public void onDraw(Canvas canvas) {
        for (Circle circle : circles) {
            p.setColor(circle.color);
            canvas.drawCircle(circle.x, circle.y, circle.r, p);
        }
    }

    public void addCircles(int[] xParams, int[] yParams, int[] rParams, int[] cParams) {
        int count = 0;
        while (count < xParams.length) {
            int x = xParams[count];
            int y = yParams[count];
            int r = rParams[count];
            int c = cParams[count];
            circles.add(new Circle(x, y, r, c));
            count++;
        }
    }

    private void addCircleToArray() {
        try {
            int x = random.nextInt(width) + 1;
            int y = random.nextInt(height) + 1;
            int r = random.nextInt(100) + 25;
            int color = Color.rgb(random.nextInt(256), random.nextInt(256), random.nextInt(256));
            circles.add(new Circle(x, y, r, color));
        } catch (IllegalArgumentException ex){
            Log.e(TAG, "Forgot to #setDimensions in #onCreate!", ex);
        }
    }

    public void removeCircles(int amount) {
        for (int i = 0; i < amount; i++){
            circles.remove(0);
        }
        invalidate();
    }

    public void addCircles(int num) {
        int count = 0;
        while (count < num) {
            addCircleToArray();
            count++;
        }
        invalidate();
    }

    public int getNumCircles(){return circles.size();}

    public void setDimensions(int width, int height) {
        this.width = width;
        this.height = height;
    }

    protected class Circle {
        public int r;
        public int x;
        public int y;
        public int color;

        public Circle(int x, int y, int r, int color) {
            this.x = x;
            this.y = y;
            this.r = r;
            this.color = color;
        }
    }

}
