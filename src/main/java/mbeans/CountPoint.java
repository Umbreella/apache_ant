package mbeans;

import beans.PointAttempt;

import javax.management.Notification;
import javax.management.NotificationBroadcasterSupport;

public class CountPoint extends NotificationBroadcasterSupport implements CountPointMBean {
    private int countPoints = 0;
    private int countHitPoints = 0;
    private int countMissPoints = 0;

    @Override
    public void addPointAttempt(PointAttempt pointAttempt) {
        countPoints++;
        if (pointAttempt.isSuccess()) {
            countHitPoints++;
        } else {
            countMissPoints++;
        }

        if (countPoints % 10 == 0) {
            String type = "count_points";
            Object source = this;
            long sequenceNumber = 2;
            String message = "Count points is multiple of ten.";

            Notification notification = new Notification(type, source,
                    sequenceNumber, message);
            sendNotification(notification);
        }
    }

    @Override
    public int getCountPoints() {
        return countPoints;
    }

    @Override
    public int getCountHitPoints() {
        return countHitPoints;
    }

    @Override
    public int getCountMissPoints() {
        return countMissPoints;
    }

    @Override
    public float getPercentMiss() {
        return (float) countMissPoints / (float) countPoints;
    }
}
