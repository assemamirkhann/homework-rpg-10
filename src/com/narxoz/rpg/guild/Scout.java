package com.narxoz.rpg.guild;

/**
 * Guild officer responsible for route reports and reconnaissance.
 */
public class Scout extends GuildMember {

    public Scout(String name, GuildMediator mediator) {
        super(name, mediator);
    }

    public void reportRoute(String topic, String payload) {
        // TODO: send a scouting message through the mediator.
        getMediator().dispatch(topic, this, payload);
    }

    @Override
    public void receive(String topic, GuildMember from, String payload) {
        // TODO: react to a guild-hall message without calling another colleague directly
        System.out.println("[Scout " + getName() + "] received via " + topic
                + " from " + from.getName()
                + ": " + payload);
        if ("COUNCIL".equals(topic)) {
            System.out.println("[Scout " + getName() + "] analyzing routes for upcoming missions.");
        } else if ("RECON".equals(topic)) {
            System.out.println("[Scout " + getName() + "] updating map with new reconnaissance data.");
        }
    }
}