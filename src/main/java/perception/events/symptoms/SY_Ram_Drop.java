package perception.events.symptoms;

import graph.CloudResourceType;
import perception.events.Symptom;

public class SY_Ram_Drop extends Symptom {

    final int ramInitValue;
    final int ramDropValue;

    public SY_Ram_Drop(String cloudResourceName,
                       CloudResourceType cloudResourceType,
                       int score,
                       int ramInitValue,
                       int ramDropValue) {
        super(cloudResourceType, cloudResourceName, score);
        this.ramInitValue = ramInitValue;
        this.ramDropValue = ramDropValue;
    }

    public int getRamInitValue() {
        return ramInitValue;
    }

    public int getRamDropValue() {
        return ramDropValue;
    }

    @Override
    public String toString() {
        return "SY_Cpu_Drop{" +
                "ramInitValue=" + ramInitValue +
                ", ramDropValue=" + ramDropValue +
                '}';
    }
}
