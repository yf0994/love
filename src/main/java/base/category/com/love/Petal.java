package base.category.com.love;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;

/**
 * Created by fengyin on 16-6-23.
 */
public class Petal {
    private float stretchA;
    private float stretchB;
    private float startAngle;
    private float angle;
    private float growFactor;
    private Bloom bloom;
    private int r;
    private boolean isfinished = false;
    private Paint paint;

    public Petal(float stretchA, float stretchB, float startAngle, float angle, float growFactor, Bloom bloom){
        this.stretchA = stretchA;
        this.stretchB = stretchB;
        this.startAngle = startAngle;
        this.angle = angle;
        this.bloom = bloom;
        this.growFactor = growFactor;
        this.r = 2;
        this.isfinished = false;
        paint = new Paint();
        paint.setColor(bloom.getC());
    }

    public void draw(){
        Canvas canvas = this.bloom.getGarden().getCanvas();
        Vector v1, v2, v3, v4;
        Vector v = new Vector(0, this.r).rotate(Garden.degrad(this.startAngle));
        v1 = new Vector(0, 3).rotate(Garden.degrad(this.startAngle));
        v2 = v.clone().rotate(Garden.degrad(this.angle));
        v3 = v.clone().mult(this.stretchA);
        v4 = v2.clone().mult(this.stretchB);

        Path path = new Path();
        path.moveTo(v1.getX(), v1.getY());
        path.cubicTo(v3.getX(), v3.getY(), v4.getX(), v4.getY(), v2.getX(), v2.getY());
        canvas.drawPath(path, paint);
    }

    public void render(){
        if(this.r <= this.bloom.getR()){
            this.r += this.growFactor;
            this.draw();
        } else {
            this.isfinished = true;
        }
    }

    public boolean isfinished() {
        return isfinished;
    }
}
