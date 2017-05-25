package perception.services;

import perception.core.PerceptionRunContext;

/**
 * Interface that provides a way to declare a class as a perception run resource.
 * With the methods that provide this interface, flink monitor process will be re-created before
 * each run and destroy after each run
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
