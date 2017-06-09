package perception.services;

import perception.core.PerceptionRunContext;

/**
 * Interface that provides a way to declare a class as a perception run resource.
 * With the methods that provide this interface, flink monitor process will be re-created before
 * each run and destroy after each run
 *
 * /!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\
 * Warning : A perception run resource MUST be serializable in order to used by Apache flink.
 * /!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\
 *
 * /!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\
 * Warning : DURING APACHE FLINK EXECUTION, ALL MEMBERS OF A PERCEPTION RUN RESOURCE CAN BE
 * MODIFIED, BUT THIS WILL NOT AFFECTING PERCEPTION MONITORING PROCESS. IN ORDER TO UPDATE MEMBERS
 * FROM A PERCEPTION RUN RESOURCE, YOU MUST SHUTDOWN PERCEPTION EXECUTION, DO YOUR CHANGES AND
 * RE-EXECUTE PERCEPTION.
 * (In fact, during the execution, apache flink will take the control of the PerceptionRunResource
 * that we created by serializing them, after this step, apache flink will used the same "cloned"
 * PerceptionRunResource and will get rid of our "real" instance of PerceptionRunResource.)
 * /!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\
 */
public interface PerceptionRunResource {

    /**
     * Method call before flink env execution
     * @param ctx perCEPtion run context
     * @return true if all things are setup correctly, a false value disable the flink env launch
     */
    boolean beforeRun(PerceptionRunContext ctx);

    /**
     * Method call after each flink env execution end
     */
    void endRun();

}
