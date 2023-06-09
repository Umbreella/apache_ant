package beans;

import mbeans.CountPoint;
import util.Connector;

import jakarta.enterprise.context.ApplicationScoped;

import jakarta.inject.Named;

import java.time.ZoneId;
import java.time.ZonedDateTime;

import java.util.ArrayList;
import java.util.Collections;
import java.util.TimeZone;

import java.lang.management.ManagementFactory;

import java.io.Serializable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.management.*;

@Named
@ApplicationScoped
@NoArgsConstructor
public class Model implements Serializable {

    private ArrayList<PointAttempt> data = new ArrayList<>();
    private ZoneId zoneId = ZonedDateTime.now().getZone();

    private static CountPoint countPoint;
    @Getter
    @Setter
    String timezoneOffset;

    static {
        try {
            MBeanServer server = ManagementFactory.getPlatformMBeanServer();

            countPoint = new CountPoint();
            server.registerMBean(countPoint, new ObjectName("mbeans:type=CountPoint"));

        } catch (MalformedObjectNameException | NotCompliantMBeanException |
                 InstanceAlreadyExistsException |
                 MBeanRegistrationException e) {
            throw new RuntimeException(e);
        }
    }

    public void add(PointAttempt attempt) {
        data.add(attempt);
        countPoint.addPointAttempt(attempt);
        Connector.getInstance().makeBigAdd(attempt);
    }

    public ArrayList<PointAttempt> get() {
        return new ArrayList<>(data);
    }

    public void timezoneChangedListener() {
        String strFromJavaScript = getTimezoneOffset();
        TimeZone tz = TimeZone.getTimeZone("GMT+" + strFromJavaScript);
        zoneId = tz.toZoneId();
    }

    public ArrayList<PointAttempt> getReversed() {
        ArrayList<PointAttempt> toReturn = new ArrayList<>(data);
        Collections.reverse(toReturn);
        toReturn.forEach(attempt -> attempt.setZoneId(zoneId));
        return toReturn;
    }

    @Override
    public String toString() {
        return "Model{" +
                "data=" + data +
                '}';
    }

}
