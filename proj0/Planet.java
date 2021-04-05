public class Planet{
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;
    public Planet(double xP, double yP, double xV, double yV, double m, String img){
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }
    public Planet(Planet p) {
        this.xxPos = p.xxPos;
        this.yyPos = p.yyPos;
        this.xxVel = p.xxVel;
        this.yyVel = p.yyVel;
        this.mass = p.mass;
        this.imgFileName = p.imgFileName;
    }
    public double calcDistance(Planet ComparePlanet){
        double rx = (this.xxPos - ComparePlanet.xxPos);
        double ry = (this.yyPos - ComparePlanet.yyPos);
        double r = Math.sqrt(Math.pow(rx, 2) + Math.pow(ry, 2));
        return r;
    }
    public double calcForceExertedBy(Planet ComparePlanet){
        final double G = 6.67e-11; // use final 
        double m1 = this.mass;
        double m2 = ComparePlanet.mass;
        double F = (G * m1 * m2)/ Math.pow(this.calcDistance(ComparePlanet), 2);
        return F;
    }
    public double calcForceExertedByX(Planet ComparePlanet){
        double dx = ComparePlanet.xxPos - this.xxPos;
        double F = this.calcForceExertedBy(ComparePlanet);
        double Fx = F * dx / this.calcDistance(ComparePlanet);
        return Fx;
        // double dy = ComparePlanet.yyPos - this.yyPos;
    }
    public double calcForceExertedByY(Planet ComparePlanet){
        double dy = ComparePlanet.yyPos - this.yyPos;
        double F = this.calcForceExertedBy(ComparePlanet);
        double Fy = F * dy / this.calcDistance(ComparePlanet);
        return Fy;
    }
    public double calcNetForceExertedByX(Planet[] AllPlanets){
        double TotalNetFx = 0;
        for (int i = 0; i < AllPlanets.length; i++){
            if (this.equals(AllPlanets[i]) != true){
                TotalNetFx += this.calcForceExertedByX(AllPlanets[i]);
            }
        }
        return TotalNetFx;
    }
    public double calcNetForceExertedByY(Planet[] AllPlanets){
        double TotalNetFy = 0;
        for (int i = 0; i < AllPlanets.length; i++){
            if (this.equals(AllPlanets[i]) != true){
                TotalNetFy += this.calcForceExertedByY(AllPlanets[i]);
            }
        }
        return TotalNetFy;
    }
    public void update(double dt, double Fx, double Fy){
        double ax = Fx / this.mass; 
        double ay = Fy / this.mass;
        this.xxVel = this.xxVel + dt * ax;
        this.yyVel = this.yyVel + dt * ay;
        this.xxPos = this.xxPos + dt * this.xxVel;
        this.yyPos = this.yyPos + dt * this.yyVel;
    }
    public void draw(){
		StdDraw.picture(this.xxPos, this.yyPos, this.imgFileName);
    }
    // this 的使用時機 為什麼這份感覺好像是建立在一個要實體化的instance上面
    // 什麼時候要用this 什麼時候要用parameter引入？
}

 