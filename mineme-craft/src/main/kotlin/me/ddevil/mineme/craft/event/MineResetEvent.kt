package me.ddevil.mineme.craft.event

import me.ddevil.mineme.craft.api.mine.Mine

class MineResetEvent(mine: Mine) : CancellableMineEvent(mine)