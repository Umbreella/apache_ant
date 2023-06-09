package mbeans;

import beans.PointAttempt;

public interface CountPointMBean {
    public void addPointAttempt(PointAttempt pointAttempt);
    public int getCountPoints();
    public int getCountHitPoints();
    public int getCountMissPoints();
    public float getPercentMiss();
}
