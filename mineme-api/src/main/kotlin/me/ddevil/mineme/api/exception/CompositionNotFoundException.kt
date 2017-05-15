package me.ddevil.mineme.api.exception

class CompositionNotFoundException(val compId: String) : Exception("Couldn't find a composition with the id $compId")