package perception.events;

import graph.CloudResourceType;
import utils.Triplet;

import java.util.Deque;
import java.util.LinkedList;

public abstract class Symptom extends Event {

    //List of resources impacted by the event => Triplet(Cloud resources type, name and score)
    private Deque<Triplet<CloudResourceType, String, Integer>> resources;
    private boolean oneResourceSymptom;
    private int cloudResourcesTotalScore;

    /**
     * Constructor of a symptom
     *
     * @param resources The list of resources that are implied in the symptom
     */
    public Symptom(Deque<Triplet<CloudResourceType, String, Integer>> resources) {
        super(EventType.SYMPTOM);
        this.resources = resources;
        if(resources.size() == 1) {
            oneResourceSymptom = true;
        } else {
            oneResourceSymptom = false;
        }
        this.computeCloudResourcesTotalScore();
    }

    public Symptom(CloudResourceType type, String name, Integer score) {
        super(EventType.SYMPTOM);
        this.resources = new LinkedList<>();
        this.resources.push(new Triplet<>(type, name, score));
        oneResourceSymptom = true;
        this.computeCloudResourcesTotalScore();
    }


    private void computeCloudResourcesTotalScore() {
        this.cloudResourcesTotalScore = 0;
        for(Triplet<CloudResourceType, String, Integer> r : resources) {
            this.cloudResourcesTotalScore += r.getThird();
        }
    }

    public boolean isOneResourceSymptom() {
        return oneResourceSymptom;
    }

    public int getCloudResourcesTotalScore() {
        return cloudResourcesTotalScore;
    }

    public Deque<Triplet<CloudResourceType, String, Integer>> getCloudResources() {
        return resources;
    }

}
