package me.ddevil.mineme.api.request

interface RequestListener<in R> : (R) -> (Unit)