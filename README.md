# Monsters-vs-Kingsmen

Monsters-vs-Kingmen is a roleplaying multiplayer game. The game is simple, during the set up phase players are assigned classes and resources to prepare for the fight. On the third night, a random player is selected to spawn in as a dragon. Once becoming the dragon, your job is to kill as many other players as possible before you are slain. After the dragon is slain, the dead players will spawn in as monsters of various types. For the rest of the game the monsters try to exterminate the players still alive. If they are successful before the time runs out, the monsters win, otherwise the kingsmen win.

## Using the Plugin

 * Grab the jar file included in the home directory of the project and place it in the plugins folder of your spigot enabled server
 * Spawn into any world as a server lobby
 * Run the command `/buildworld` as an op and the server will generate a new world for play (this part is usually quite laggy)
 * Once the server announces that world generation is complete, run the command `/startgame` to spawn in class eggs
 * Each player will be given a spawn egg, click on the egg in your inventory to spawn in as that class
 * From this point onwards, the game runs by itself and will auto spawn in a dragon and a timer once the dragon is dead
 * When finished playing the game, run the command `/destroyworld` to reset
 * If youd like, run `/buildworld` and then `/startgame` to play again in a newly generated world

PS running the command `/rules` will provide any player unfamiliar with the game a rulebook.

## Currently Known Bugs

 * Sometimes running `/destroyworld` will not properly delete the world file (This should be ran asynchronously - lag is the reason this breaks sometimes) If this happens, running `/buildworld` will not work properly and will finish instantly without actually generating anything. To fix: run the `/destroyworld` command 2 times one after the other and then build the new world.
