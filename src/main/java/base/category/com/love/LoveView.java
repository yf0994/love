package base.category.com.love;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fengyin on 16-6-23.
 */
public class LoveView extends SurfaceView implements SurfaceHolder.Callback {
    private SurfaceHolder surfaceHolder;
    private Garden garden;
    private Paint bgPaint;

    private Canvas canvas;
    private Bitmap bitmap;

    private int offsetX;
    private int offsetY;

    public LoveView(Context context) {
        super(context);
        init();
    }

    public LoveView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init(){
        surfaceHolder = getHolder();
        surfaceHolder.addCallback(this);
        bgPaint = new Paint();
        bgPaint.setColor(Color.rgb(0xff, 0xff, 0xe0));
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        offsetX = width / 2;
        offsetY = height / 2 - 55;
        bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        canvas = new Canvas(bitmap);
        garden = new Garden(canvas);
        canvas.drawRect(0, 0, width, height, bgPaint);
        onDrawThread();

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }


    private Vector getHeartPoint(float angle){
        float t = (float)(angle / Math.PI);
        float x = (float)(20 * (16 * Math.pow(Math.sin(t), 3)));
        float y = (float)(-19.5 * (13 * Math.cos(t) - 5 * Math.cos(2 * t) - 2 * Math.cos(3 * t) - Math.cos(4 * t)));
        return new Vector(offsetX + (int)x, offsetY + (int)y);
    }

    private void drawHeart(){

        garden.render();
        Canvas c = surfaceHolder.lockCanvas();
        c.drawBitmap(bitmap, 0, 0, null);
        surfaceHolder.unlockCanvasAndPost(c);
    }

    private void onDrawThread(){
        new Thread(){
            @Override
            public void run() {
                super.run();
                float angle = 10;
                int interval = 50;
                final List<Vector> heart = new ArrayList<Vector>();
                while(true){
                    Vector bloom = getHeartPoint(angle);
                    boolean draw = true;
                    for(int i = 0; i < heart.size(); i++){
                        Vector p = heart.get(i);
                        float distance = (float)Math.sqrt(Math.pow(p.getX() - bloom.getX(), 2) + Math.pow(p.getY() - bloom.getY(), 2));
                        if(distance < Garden.options.bloomRadius_max * 1.3){
                            draw = false;
                            break;
                        }
                    }
                    if(draw){
                        heart.add(bloom);
                        garden.createRandomBloom(bloom.getX(), bloom.getY());
                    }

                    if(angle >= 30){
                        break;
                    } else {
                        angle += 0.2;
                    }
                    drawHeart();
                    try {
                        Thread.sleep(interval);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
    }
}
