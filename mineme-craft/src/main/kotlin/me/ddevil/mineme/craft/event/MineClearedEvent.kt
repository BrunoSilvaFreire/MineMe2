package me.ddevil.mineme.craft.event

import me.ddevil.mineme.craft.api.mine.Mine

class MineClearedEvent(mine: Mine) : CancellableMineEvent(mine)