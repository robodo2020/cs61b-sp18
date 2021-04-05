public class NBody{
    public static double readRadius(String path){ 
        // what is the timing to use "static", why we must use this here?
        In in = new In(path);
        double firstItemInFile = in.readInt();
        double secondItemInFile = in.readDouble();
        return secondItemInFile;
    }
    public static Planet[] readPlanets(String path){
        In in = new In(path);
        int N = in.readInt();
        double R = in.readDouble();
        Planet[] AllPlanets = new Planet[N];
        for (int i = 0; i < N; i++){
            double xxPos = in.readDouble();
            double yyPos = in.readDouble();
            double xxVel = in.readDouble();
            double yyVel = in.readDouble();
            double mass = in.readDouble();
            String imgFileName = in.readString();
            AllPlanets[i] = new Planet(xxPos, yyPos, xxVel, yyVel, mass, imgFileName);
        }
        return AllPlanets;
    }
    public static void main(String[] args){
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        double Radius = readRadius(filename);
        Planet[] AllPlanets = readPlanets(filename);
        int N = AllPlanets.length;
        String starfield = "images/starfield.jpg";
        StdDraw.enableDoubleBuffering();

        StdDraw.setScale(-Radius, Radius);
        StdDraw.clear();
        StdDraw.picture(0, 0, starfield);

        for(int i = 0; i < N; i++){
            AllPlanets[i].draw();
        }
        StdDraw.show(10);
		
        for (double time = 0; time < T; time += dt){
            double[] xForces = new double[N];
            double[] yForces = new double[N];
            for(int j = 0; j < N; j++){
                xForces[j] = AllPlanets[j].calcNetForceExertedByX(AllPlanets);
                yForces[j] = AllPlanets[j].calcNetForceExertedByY(AllPlanets);
            }
            for(int j = 0; j < N; j++){
                AllPlanets[j].update(dt, xForces[j], yForces[j]);
            }
            StdDraw.picture(0,0,starfield);
            for (int i = 0; i < N; i++){
                AllPlanets[i].draw();
            }
            StdDraw.show();
            StdDraw.pause(10);
        }
        StdOut.printf("%d\n", AllPlanets.length);
        StdOut.printf("%.2e\n", Radius);
        for (int i = 0; i < AllPlanets.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                        AllPlanets[i].xxPos, AllPlanets[i].yyPos, AllPlanets[i].xxVel,
                        AllPlanets[i].yyVel, AllPlanets[i].mass, AllPlanets[i].imgFileName);   
        }
    }
}