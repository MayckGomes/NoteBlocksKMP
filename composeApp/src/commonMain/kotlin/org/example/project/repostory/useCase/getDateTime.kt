package org.example.project.repostory.useCase

import kotlinx.datetime.Clock

fun getDataTime(): String {

    var date = Clock.System.now().toString()

    date = date.split("T")[0]

    return "${ date.split("-")[2] }/${ date.split("-")[1] }/${ date.split("-")[0] }"

}
