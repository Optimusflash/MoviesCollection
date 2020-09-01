package com.optimus.moviescollection.presentation.details

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.optimus.moviescollection.data.model.credits.Cast
import com.optimus.moviescollection.databinding.CastRvCellBinding
import com.optimus.moviescollection.extensions.loadRoundImageWide185

/**
 * Created by Dmitriy Chebotar on 01.09.2020.
 */
class CreditViewHolder(private val binding: CastRvCellBinding) : RecyclerView.ViewHolder(binding.root) {

    companion object{
        fun create(parent: ViewGroup): CreditViewHolder{
            val binding =
                CastRvCellBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return CreditViewHolder(binding)
        }
    }

    fun bind(cast: Cast){
        binding.ivAvatar.loadRoundImageWide185(cast.imageUrl, cast.gender)
        binding.personName.text = cast.name
        binding.personCharacter.text = cast.character
    }
}