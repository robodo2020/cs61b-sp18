public class NBody{
    public static double readRadius(String path){ // 使用static的時機 為啥這邊就一定要用？
        In in = new In(path);
        double firstItemInFile = in.readInt();
        double secondItemInFile = in.readDouble();
        // System.out.println(secondItemInFile);
        return secondItemInFile;
    }
    public static Planet[] readPlanets(String path){
        In in = new In(path);
        int N = in.readInt();
        double R = in.readDouble();
        Planet[] AllPlanets = new Planet[5];
        for (int i = 0; i < 5; i++){
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
        String starfield = "images/starfield.jpg";
        StdDraw.enableDoubleBuffering();

        StdDraw.setScale(-Radius, Radius);
        StdDraw.clear();
        StdDraw.picture(0, 0, starfield);

        for(int i = 0; i < AllPlanets.length; i++){
            AllPlanets[i].draw();
        }
        StdDraw.show(10);
		
        for (double time = 0; time < T; time += dt){
            double[] xForces = new double[5];
            double[] yForces = new double[5];
            for(int j = 0; j < 5; j++){
                xForces[j] = AllPlanets[j].calcNetForceExertedByX(AllPlanets);
                yForces[j] = AllPlanets[j].calcNetForceExertedByY(AllPlanets);
            }
            for(int j = 0; j < 5; j++){
                AllPlanets[j].update(dt, xForces[j], yForces[j]);
            }
            StdDraw.picture(0,0,starfield);
            for (int i = 0; i < 5; i++){
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