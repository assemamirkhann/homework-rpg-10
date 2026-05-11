package com.narxoz.rpg;

import com.narxoz.rpg.combatant.Hero;
import com.narxoz.rpg.council.CouncilEngine;
import com.narxoz.rpg.council.CouncilRunResult;
import com.narxoz.rpg.guild.*;
import com.narxoz.rpg.quest.*;

import java.util.Arrays;
import java.util.List;
/**
 * Entry point for Homework 10 — The Adventurers' Guild: Iterator + Mediator.
 *
 * The scaffold prints the banner only; students fill in the guild demo.
 */
public class Main {

    public static void main(String[] args) {
        System.out.println("=== Homework 10 Demo: Iterator + Mediator ===");

        // 1. Create at least 2 heroes.
        // 2. Build a QuestLog with at least 5 quests of mixed priority.
        // 3. Register at least 4 GuildMembers (Quartermaster, Scout, Healer, Captain) on the GuildHall.
        // 4. Iterate the quest log with at least 2 different QuestIterator implementations.
        // 5. Dispatch coordinating messages through the mediator during quest planning.
        // 6. Run the CouncilEngine and print a final CouncilRunResult.
        Hero h1 = new Hero("Arthur", 120, 30, 15);
        Hero h2 = new Hero("Merlin", 80, 100, 10);

        List<Hero> party = Arrays.asList(h1, h2);

        QuestLog questLog = new QuestLog();
        questLog.add(new Quest("Goblin Hunt", QuestPriority.LOW, 100, false));
        questLog.add(new Quest("Dragon Slay", QuestPriority.HIGH, 1000, true));
        questLog.add(new Quest("Escort Caravan", QuestPriority.MEDIUM, 300, false));
        questLog.add(new Quest("Ancient Ruins", QuestPriority.HIGH, 700, true));
        questLog.add(new Quest("Bandit Camp", QuestPriority.LOW, 200, false));

        GuildMediator hall = new GuildHall();

        GuildMember captain = new Captain("Alaric", hall);
        GuildMember healer = new Healer("Luna", hall);
        GuildMember scout = new Scout("Rex", hall);
        GuildMember quartermaster = new Quartermaster("Borin", hall);

        hall.register(captain);
        hall.register(healer);
        hall.register(scout);
        hall.register(quartermaster);

        System.out.println("\n--- Ordered Quests ---");
        QuestIterator ordered = questLog.ordered();
        while (ordered.hasNext()) {
            System.out.println(ordered.next());
        }

        System.out.println("\n--- High Priority Quests ---");
        QuestIterator priority = questLog.priorityAtLeast(QuestPriority.HIGH);
        while (priority.hasNext()) {
            System.out.println(priority.next());
        }
        captain.issueOrder("COUNCIL", "Prepare for expedition planning.");
        scout.reportRoute("RECON", "Forest path is clear.");
        healer.prepareAid("COUNCIL", "Stock healing potions.");
        quartermaster.requestSupplies("SUPPLIES", "Requesting 10 rations.");

        CouncilEngine engine = new CouncilEngine();
        CouncilRunResult result = engine.runCouncil(party, questLog, hall);

        System.out.println("\n=== Council Result ===");
        System.out.println(result);
    }
}
