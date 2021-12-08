import java.lang.Math;

public class Planet {

   static final double gConst = 6.67e-11;

   /* Current x position*/
   public double xxPos;

   /* Current y position*/
   public double yyPos;

   /* Current velocity in the x direction*/
   public double xxVel;

   /* Current velocity in the y direction*/
   public double yyVel;

   /* Mass*/
   public double mass;

   /** 
    * The name of the file that corresponds to the image that depicts the planet.
    * For example jupiter.gif
    */
    public String imgFileName;

    /*Constructors */
    public Planet(double xP, double yP, double xV, double yV, double m, String img) {
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }

    public Planet(Planet p){
        xxPos = p.xxPos;
        yyPos = p.yyPos;
        xxVel = p.xxVel;
        yyVel = p.yyVel;
        mass = p.mass;
        imgFileName = p.imgFileName;
    }

    /* Calculation of distance between two planets. */
    public double calcDistance(Planet obj) {
        return Math.sqrt((obj.xxPos-this.xxPos)*(obj.xxPos-this.xxPos)+(obj.yyPos-this.yyPos)*(obj.yyPos-this.yyPos));
    }

    /* Calculation of force exerted by another planet. */
    public double calcForceExertedBy (Planet obj) {
        return gConst*this.mass*obj.mass/(this.calcDistance(obj)*this.calcDistance(obj));
    }

    /**
     * The next two methods calculate the force exerted in the X and Y directions respectively.
     */
    public double calcForceExertedByX (Planet obj) {
        return this.calcForceExertedBy(obj)*(obj.xxPos-this.xxPos)/this.calcDistance(obj);
    }

    public double calcForceExertedByY (Planet obj) {
        return this.calcForceExertedBy(obj)*(obj.yyPos-this.yyPos)/this.calcDistance(obj);
    }
    /**
     * To compare two planets.
     * If two planets are equal, this method will return true.
     */
    public boolean equals (Planet obj) {
        if (this == obj){
            return true;
        }

        return false;
    }
    /**
     * The next two methods calculate the net X and net Y force
     */
    public double calcNetForceExertedByX (Planet[] allobjs) {
        double netForceX = 0;
        for (int i = 0; i < allobjs.length; i++){
            if (this.equals(allobjs[i])) {
                continue;
            }
            netForceX = netForceX + this.calcForceExertedByX(allobjs[i]);
        }

        return netForceX;
    }

    public double calcNetForceExertedByY (Planet[] allobjs) {
        double netForceY = 0;
        for (int i = 0; i < allobjs.length; i++){
            if (this.equals(allobjs[i])) {
              continue;  
            }
            netForceY = netForceY + this.calcForceExertedByY(allobjs[i]);
        }

        return netForceY;
    }

    /**
     * The update method is used to update the acceleration, velocity, and position
     * @param period
     * @param forceX
     * @param forceY
     */
    public void update (double period, double forceX, double forceY) {
        double xxAce, yyAce;

        xxAce = forceX/this.mass;
        yyAce = forceY/this.mass;

        this.xxVel = this.xxVel + period*xxAce;
        this.yyVel = this.yyVel + period*yyAce;

        this.xxPos = this.xxPos + period*this.xxVel;
        this.yyPos = this.yyPos + period*this.yyVel;
    }

    public void draw() {
        StdDraw.picture(xxPos, yyPos, "images/" + imgFileName);
    }
}