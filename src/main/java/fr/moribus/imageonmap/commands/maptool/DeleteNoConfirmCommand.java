/*
 * Copyright (C) 2013 Moribus
 * Copyright (C) 2015 ProkopyL <prokopylmc@gmail.com>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package fr.moribus.imageonmap.commands.maptool;

import fr.moribus.imageonmap.PluginLogger;
import fr.moribus.imageonmap.commands.*;
import fr.moribus.imageonmap.map.ImageMap;
import fr.moribus.imageonmap.map.MapManager;
import fr.moribus.imageonmap.map.MapManagerException;
import java.util.List;
import org.bukkit.entity.Player;


@CommandInfo(name =  "delete-noconfirm", usageParameters = "[map name]")
public class DeleteNoConfirmCommand extends Command
{

    public DeleteNoConfirmCommand(Commands commandGroup) {
        super(commandGroup);
    }

    @Override
    protected void run() throws CommandException 
    {
        Player player = playerSender();
        ImageMap map = getMapFromArgs();
        MapManager.clear(player.getInventory(), map);
        try
        {
            MapManager.deleteMap(map);
            info("Map successfully deleted.");
        }
        catch (MapManagerException ex)
        {
            PluginLogger.warning("A non-existent map was requested to be deleted", ex);
            warning("This map does not exist.");
        }
    }
    
    @Override
    protected List<String> complete() throws CommandException
    {
        if(args.length == 1) 
            return getMatchingMapNames(playerSender(), args[0]);
        return null;
    }
}

