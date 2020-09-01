package com.optimus.moviescollection.extensions

import android.content.Context
import androidx.core.view.children
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.optimus.moviescollection.data.model.Genre
import java.util.*

/**
 * Created by Dmitriy Chebotar on 01.09.2020.
 */

fun ChipGroup.addChips(context: Context, items: List<Genre>, isChecked: Boolean = false, checkable: Boolean = true, clickListener: ((id: Int)-> Unit)?){
    for (i in items.indices){
        val chip = Chip(context).apply {
            id = items[i].id
            text = items[i].name.capitalize(Locale.getDefault())
            this.isChecked = isChecked
            isCheckable = checkable
        }
        this.addView(chip)
    }
    this.setOnCheckedChangeListener { _, checkedId ->
        clickListener?.invoke(checkedId)
    }
}