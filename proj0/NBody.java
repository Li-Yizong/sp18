public class NBody {

    public static double readRadius (String fileName) {
        In in = new In(fileName);

        in.readInt();

        return in.readDouble();
    }

    public static Planet[] readPlanets (String fileName) {
        In in = new In(fileName);
        int N = in.readInt();
        Planet[] planets = new Planet[N];

        in.readDouble();

        for (int i = 0; i < N; i++) {
            double xp = in.readDouble();
            double yp = in.readDouble();
            double xv = in.readDouble();
            double yv = in.readDouble();
            double m = in.readDouble();
            String img = in.readString();
            planets[i] = new Planet(xp, yp, xv, yv, m, img);
        }

        return planets;
    }

    public static void main(String[] args) {
        /* Collection of all needed input. */
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];

        double radius = readRadius(filename);
        Planet[] planets = readPlanets(filename);

        /* Drawing the background. */
        StdDraw.setScale(-radius, radius);
        StdDraw.picture(0, 0, "images/starfield.jpg");

        for(Planet planet:planets){
            planet.draw();
        }

        StdDraw.enableDoubleBuffering();

        int N = planets.length;

        for (double t = 0.0; t <= T; t += dt) {
            double[] xForces = new double[N];
            double[] yForces = new double[N];

            for (int i = 0; i < N; i++) {
                
                xForces[i] = planets[i].calcNetForceExertedByX(planets);
                yForces[i] = planets[i].calcNetForceExertedByY(planets);
            }

            for (int i = 0; i < N; i++) {
                planets[i].update(dt, xForces[i], yForces[i]);
            }

            StdDraw.picture(0, 0, "images/starfield.jpg");

            for (Planet planet : planets) {
                planet.draw();
            }

            StdDraw.show();

            StdDraw.pause(10);
        }

        StdOut.printf("%d\n", planets.length);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < planets.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
            planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
            planets[i].yyVel, planets[i].mass, planets[i].imgFileName);   
        }

    }
}