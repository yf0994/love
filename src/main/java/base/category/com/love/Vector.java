package base.category.com.love;

/**
 * Created by fengyin on 16-6-23.
 */
public class Vector {
    private int x;
    private int y;
    public Vector(int x, int y){
        this.x = x;
        this.y = y;
    }

    public Vector rotate(float theta){
        int x = this.x;
        int y = this.y;
        this.x = (int) (Math.cos(theta) * x - Math.sin(theta) * y);
        this.y = (int) (Math.sin(theta) * x + Math.cos(theta) * y);
        return this;
    }

    public Vector mult(float f){
        this.x *= f;
        this.y *= f;
        return this;
    }

    public Vector clone(){
        return new Vector(this.x, this.y);
    }

    public int length(){
        return (int)Math.sqrt(x * x + y * y);
    }

    public Vector subtract(Vector v){
        this.x -= v.x;
        this.y -= v.y;
        return this;
    }

    public Vector set(int x, int y){
        this.x = x;
        this.y = y;
        return this;
    }

    public Vector add(Vector v){
        this.x += v.x;
        this.y += v.y;
        return this;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
