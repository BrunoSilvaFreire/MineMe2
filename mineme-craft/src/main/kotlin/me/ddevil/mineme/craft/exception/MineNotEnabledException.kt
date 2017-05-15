package me.ddevil.mineme.craft.exception

import me.ddevil.mineme.craft.api.mine.Mine

class MineNotEnabledException(val mine: Mine) : IllegalStateException("Mine ${mine.name} is not enabled!")