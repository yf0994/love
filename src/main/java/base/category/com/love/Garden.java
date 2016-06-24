package base.category.com.love;

import android.graphics.Canvas;
import android.graphics.Color;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fengyin on 16-6-23.
 */
public class Garden {


    private Canvas canvas;
    private List<Bloom> blooms;

    public Garden(Canvas canvas){
        this.canvas = canvas;
        this.blooms = new ArrayList<>();
    }


    public Canvas getCanvas() {
        return canvas;
    }

    public void render(){
        for(int i = 0; i < blooms.size(); i++){
            blooms.get(i).draw();
        }
    }

    public void addBloom(Bloom b){
        blooms.add(b);
    }

    public void removeBloom(Bloom b){
        Bloom bloom;
        for(int i = 0; i < blooms.size(); i++){
            bloom = this.blooms.get(i);
            if(bloom == b){
                blooms.remove(i);
                return;
            }
        }
    }

    public Bloom createRandomBloom(int x, int y){
        return createBloom(x, y, randomInt(options.bloomRadius_min, options.bloomRadius_max),
                Garden.randomrgba(Garden.options.color_rmin, Garden.options.color_rmax,
                        Garden.options.color_gmin, Garden.options.color_gmax,
                        Garden.options.color_bmin, Garden.options.color_bmax,
                        Garden.options.opacity),
                Garden.randomInt(Garden.options.petalCount_min, Garden.options.petalCount_max));
    }

    public Bloom createBloom(int x, int y, int r, int c, int pc){
        return new Bloom(new Vector(x, y), r, c, pc, this);
    }

    public static class options{
        public static int petalCount_min = 8;
        public static int petalCount_max = 15;

        public static float petalStretch_min = 2f;
        public static float petalStretch_max = 3.5f;

        public static float growFactor_min = 1f;
        public static float growFactor_max = 1.1f;

        public static int bloomRadius_min = 8;
        public static int bloomRadius_max = 10;

        public static int color_rmin = 128;
        public static int color_rmax = 255;

        public static int color_gmin = 0;
        public static int color_gmax = 128;

        public static int color_bmin = 0;
        public static int color_bmax = 128;

        public static int opacity = 50; //0.1f
    }

    public static float circle = (float)(2 * Math.PI);

    public static float random(float min, float max){
        return (float)(Math.random() * (max - min) + min);
    }

    public static int randomInt(int min, int max){
        return (int)Math.floor(Math.random()* (max - min + 1)) + min;
    }

    public static float degrad(float angle){
        return circle / 360 * angle;
    }


    public static int rgba(int r, int g, int b, int a) {
        return Color.argb(a, r, g, b);
    }

    public static int randomrgba(int rmin, int rmax, int gmin, int gmax, int bmin, int bmax, int a){
        int r = Math.round(random(rmin, rmax));
        int g = Math.round(random(gmin, gmax));
        int b = Math.round(random(bmin, bmax));
        int limit = 5;

        if (Math.abs(r - g) <= limit && Math.abs(g - b) <= limit && Math.abs(b - r) <= limit) {
            return rgba(rmin, rmax, gmin, gmax);
        } else {
            return rgba(r, g, b, a);
        }
    }

}
