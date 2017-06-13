package me.ddevil.mineme.craft.event

import me.ddevil.mineme.craft.api.mine.Mine
import me.ddevil.shiroi.craft.event.ShiroiEvent

class MineDisabledEvent(val mine: Mine) : ShiroiEvent()