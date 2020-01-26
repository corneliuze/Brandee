package com.example.connie.brandee.views

import android.content.Context
import android.media.MediaPlayer
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView

import com.example.connie.brandee.R

import java.util.ArrayList
import java.util.Collections

class InputAdapter(private val context: Context, private val input: ArrayList<Char>,
                   private val inputClickListener: InputClickListener,
                   private val lengthOfAnswer: Int) : RecyclerView.Adapter<InputAdapter.InputViewHolder>() {


    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): InputViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.each_input_layout, viewGroup, false)
        return InputViewHolder(view)
    }

    override fun onBindViewHolder(inputViewHolder: InputViewHolder, i: Int) {
        val text = input[i]
        inputViewHolder.inputTextView.text = text.toString()
        if (text == '#') {
            inputViewHolder.itemView.visibility = View.INVISIBLE
        } else {
            inputViewHolder.itemView.visibility = View.VISIBLE
            inputViewHolder.itemView.setOnClickListener {
                if (Collections.frequency(input, '#') != lengthOfAnswer) {
                    inputViewHolder.itemView.visibility = View.INVISIBLE
                    input.removeAt(i)
                    input.add(i, '#')
                    inputClickListener.onInputClick(i, text)
                    notifyDataSetChanged()

                }
            }
        }

    }

    override fun getItemCount(): Int {
        return input.size
    }


    //to return the returned character from the answer board
    fun onReturnCharacter(cr: Char) {
        val index = input.indexOf('#')
        input.removeAt(index)
        input.add(index, cr)
        notifyDataSetChanged()
    }

    interface InputClickListener {
        fun onInputClick(position: Int, ch: Char)
    }

    inner class InputViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var inputRelLayout: RelativeLayout = itemView.findViewById(R.id.input_rel_view)
        var inputTextView: TextView = itemView.findViewById(R.id.input_text_view)
    }

}
