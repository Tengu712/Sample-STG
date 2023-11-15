package stg.app.entity.move;

import java.util.*;

import stg.app.entity.move.control.*;

public class ProgramableMover implements Mover {
    private VelocityMover mover;
    private List<Controller> controllers;

    public ProgramableMover(double x, double y, double rad, double spd) {
        this.mover = new VelocityMover(x, y, rad, spd);
        this.controllers = new ArrayList<>();
    }

    public boolean update() {
        this.mover.update();
        for (Controller n : this.controllers) {
            if (n == null) continue;
            n.update(this.mover);
        }
        return this.mover.isAlive();
    }

    public double getX() { return this.mover.getX(); }
    public double getY() { return this.mover.getY(); }

    public ProgramableMover attach(Controller controller) {
        this.controllers.add(controller);
        return this;
    }
}
