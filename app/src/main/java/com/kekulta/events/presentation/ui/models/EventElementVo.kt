package com.kekulta.events.presentation.ui.models

data class EventElementVo(
    val id: String,
    val name: String,
    val avatar: String?,
    /* TODO: This is VO representation. This data shouldn't be stored like that,
         it should be stored in appropriate classes with regards to locale(i.e. timezone and
          language). As well as we shouldn't pass these classes to the View layer. All formatting
          must be done in ViewModel via specialised formatters */
    val date: String,
    val note: String?,
    val place: String,
    val tags: List<String>,
)

