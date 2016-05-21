package de.goldmann.comercio.client;

public interface AppJob
{
    void execute();

    long getInterval();

    String getName();

    long getStartAfter();

    String getTriggerName();
}
