package perception.core;

import perception.events.Symptom;

import java.io.Serializable;
import java.util.Deque;
import java.util.LinkedList;

public class SymptomQueue implements Serializable {

    private Deque<Symptom> queue;

    public SymptomQueue() {
        queue = new LinkedList<>();
    }

    public Deque<Symptom> getQueue() {
        return queue;
    }

    public void pushSymptom(Symptom symptom) {
        queue.add(symptom);
    }

}
