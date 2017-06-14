# Welcome to MineMe 2.0
MineMe 2 is the a complete remake of the original plugin, MineMe.  
Not event a single line of code was used from version 1.0, this 
is a completely new plugin.  
If you are a user from the original plugin and wants to know 
what's new, I recommend you to read the Differences from 1.0 page.  
If you're here for a different reason, well, I can't help you 
right now :c this page is under development. But come back later and I 
promise I'll fill it with love and useful information :D
## Differences from 1.0
### <a name="differences"></a>Mines and compositions are now 2 different things.
In version 1.0, each mine had it's own composition section 
where you would define which materials are used to reset a mine.  
Now **mines and compositions are two different things**.  
A mine basically controls it's geometry, reset time, how to 
execute resets, holograms, etc.  
A compositions controls specifically the materials that will 
be used in a mine.  
Basically, we've extract the materials list from the mine and 
delegated it to another object called "Composition".  
But why? Because now you can set 2 different mines to use the 
same composition and any changes you make to this composition
will affect both mines.  
There is a bunch of other reasons, but it's not worth mentioning it now.
### Mine Reset Executors and Repopulators
When a mine was reset in version 1.0, there wasn't much that could be customized, but now
there is.  
MineMe 2 introduced **Mine Reset Executors** and **Repopulators**, but what do they do?  
A Mine Reset Repopulator selects which material will be in each block when resetting a mine.  
A Mine Reset Executor selects how this material will be apply to the block.  
The reason for this is that, Mine Reset Executors made async mine resets 
possible, and Repopulators can be used to make unique resets, which will be used in a 
future feature called events.

### Holograms Formations
When creating holograms for mines, each mine type had it's unique unmodifiable way to place
holograms, and you couldn't customize them in any way.  
Now holograms formations allow you to select how holograms gets positioned in your mines.  
Each formation has it's meta options, check the wiki page for them.