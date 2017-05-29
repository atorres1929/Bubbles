package info.datahelix.bubbles;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

import java.util.ArrayList;
import java.util.Random;

public class RandomCirclesView extends View {

    private Random random;
    private Paint p;
    private ArrayList<Circle> circles;

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
        for (Circle circle: circles){
            p.setColor(circle.color);
            canvas.drawCircle(circle.x, circle.y, circle.r, p);
        }
    }

    public void drawCircle(){
        int x = random.nextInt(getWidth()) + 1;
        int y = random.nextInt(getHeight()) + 1;
        int r = random.nextInt(100) + 25;
        int color = Color.rgb(random.nextInt(256), random.nextInt(256), random.nextInt(256));
        circles.add(new Circle(x, y, r, color));
        invalidate();
    }

    protected class Circle {
        public int r;
        public int x;
        public int y;
        public int color;

        public Circle(int x, int y, int r, int color){
            this.x = x;
            this.y = y;
            this.r = r;
            this.color = color;
        }
    }

}
