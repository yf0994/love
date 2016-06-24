package base.category.com.love;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fengyin on 16-6-23.
 */
public class Bloom {
    private Vector p;
    private int r; // radius
    private int c; // color
    private int pc; // petalcount
    private List<Petal> petals;
    private Garden garden;


    public Bloom(Vector p, int r, int c, int pc, Garden garden){
        this.p = p;
        this.r = r;
        this.c = c;
        this.pc = pc;
        petals = new ArrayList<>();
        this.garden = garden;
        init();
        garden.addBloom(this);
    }

    public void draw(){
        Petal p;
        boolean isfinished = true;
        this.getGarden().getCanvas().save();
        this.getGarden().getCanvas().translate(this.getP().getX(), this.getP().getY());
        for(int i = 0; i < this.petals.size(); i++){
            p = this.petals.get(i);
            p.render();
            isfinished = p.isfinished();
        }
        this.getGarden().getCanvas().restore();
        if(isfinished == true){
            this.getGarden().removeBloom(this);
        }

    }

    public void init(){
        float angle = 360 / this.pc;
        int startAngle = Garden.randomInt(0, 90);
        for(int i = 0; i < pc; i++){
            this.petals.add(new Petal(Garden.random(Garden.options.petalStretch_min, Garden.options.petalStretch_max),
                    Garden.random(Garden.options.petalStretch_min, Garden.options.petalStretch_max),
                    startAngle + i * angle, angle, Garden.random(Garden.options.growFactor_min,
                    Garden.options.growFactor_max), this));
        }
    }

    public Garden getGarden(){
        return garden;
    }

    public int getC() {
        return c;
    }

    public int getPc() {
        return pc;
    }

    public Vector getP() {
        return p;
    }

    public List<Petal> getPetals() {
        return petals;
    }

    public int getR() {
        return r;
    }
}
