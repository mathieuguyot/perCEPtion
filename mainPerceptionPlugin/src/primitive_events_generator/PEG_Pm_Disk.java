package primitive_events_generator;

import events.primitive_events.PE_Disk;
import graph.CloudResource;
import graph.CloudResourceType;
import graph.PM;
import perception.events.PrimitiveEvent;
import perception.primitive_events_generator.PrimitiveEventGenerator;

import java.util.Optional;

public class PEG_Pm_Disk extends PrimitiveEventGenerator {

    public PEG_Pm_Disk(String name, long msRefreshingRate) {
        super(name, msRefreshingRate);
    }

    public PEG_Pm_Disk(long msRefreshingRate) {
        super("PEG_Pm_Disk", msRefreshingRate);
    }


    @Override
    protected Optional<PrimitiveEvent> processResource(CloudResource cr) {
        if(cr.getType() == CloudResourceType.PM) {
            PE_Disk pe_Disk = new PE_Disk(cr.getName(), cr.getType(), cr.getScore(), ((PM)cr).getDiskConsumption());
            return Optional.of(pe_Disk);
        }
        return Optional.empty();
    }

}
