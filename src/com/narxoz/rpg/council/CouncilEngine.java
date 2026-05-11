package com.narxoz.rpg.council;

import com.narxoz.rpg.combatant.Hero;
import com.narxoz.rpg.guild.GuildMediator;
import com.narxoz.rpg.quest.QuestLog;
import java.util.List;
import com.narxoz.rpg.quest.QuestIterator;
import com.narxoz.rpg.quest.Quest;

/**
 * Orchestrates a planning session that uses both Iterator and Mediator.
 */
public class CouncilEngine {

    public CouncilRunResult runCouncil(List<Hero> party, QuestLog questLog, GuildMediator hall) {
        // TODO: walk questLog with at least 2 different iterators,
        //       dispatch coordinating messages through hall for each quest,
        //       and return counters (questsTraversed, messagesRouted, membersNotified).
        int questsTraversed = 0;
        int messagesRouted = 0;
        int membersNotified = 0;

        QuestIterator forward = questLog.ordered();
        while (forward.hasNext()) {
            Quest quest = forward.next();
            questsTraversed++;

            hall.dispatch("COUNCIL", party.get(0), "Council reviewing quest: " + quest.getTitle());
            messagesRouted += party.size();
            membersNotified += party.size();
        }
        QuestIterator reverse = questLog.reverse();
        while (reverse.hasNext()) {
            Quest quest = reverse.next();
            questsTraversed++;

            hall.dispatch("COUNCIL", party.get(0), "Re-evaluating quest: " + quest.getTitle());
            messagesRouted += party.size();
            membersNotified += party.size();
        }
        return new CouncilRunResult(questsTraversed, messagesRouted, membersNotified);
    }
}
