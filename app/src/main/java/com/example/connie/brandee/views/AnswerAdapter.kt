package com.example.connie.brandee.views

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView

import com.example.connie.brandee.R
import com.example.connie.brandee.models.QuestionsModel

import java.util.ArrayList

class AnswerAdapter(private var context: Context, private var questionsModel: QuestionsModel,
                    private var answerClickListener: AnswerClickListener,
                    private var answerFilledListener: AnswerFilledListener) : RecyclerView.Adapter<AnswerAdapter.AnswerViewHolder>() {
    private var answer: String = questionsModel.name.toUpperCase()
    private var inputs = ArrayList<Char>()

    init {
        for (i in answer.indices) {
            inputs.add('#')
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): AnswerViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.each_answer_layout, viewGroup, false)
        return AnswerViewHolder(view)
    }

    override fun onBindViewHolder(answerViewHolder: AnswerViewHolder, i: Int) {
        val text = inputs[i]
        if (text == '#') {
//            answerViewHolder.answerRelLayout.background = context.resources.getDrawable(R.drawable.black_bg)
            answerViewHolder.answerTextView.text = ""
        } else {
//            answerViewHolder.answerRelLayout.background = context.resources.getDrawable(R.drawable.green_bg)
            answerViewHolder.answerTextView.text = text.toString()
            answerViewHolder.itemView.setOnClickListener {
                inputs.removeAt(i)
                inputs.add(i, '#')
                notifyDataSetChanged()
                answerClickListener.onAnswerClicked(text)
            }
        }

    }

    override fun getItemCount(): Int {
        return answer.length
    }

    inner class AnswerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        internal var answerRelLayout: LinearLayout = itemView.findViewById(R.id.answer_rel_view)
        internal var answerTextView: TextView = itemView.findViewById(R.id.answer_text_view)
    }

    // adding new character to the answer board
    fun addNewCharacter(cr: Char) {
        val index = inputs.indexOf('#')
        if (index != -1) {
            inputs.removeAt(index)
            inputs.add(index, cr)
            notifyDataSetChanged()

            if (inputs.indexOf('#') == -1) {
                val ans = StringBuilder()
                for (c in inputs) {
                    ans.append(c)
                }
                val isCorrect = answer == ans.toString()
                answerFilledListener.onAnswerFilled(isCorrect, questionsModel)
            }
        }
    }

    interface AnswerClickListener {
        fun onAnswerClicked(cr: Char)
    }

    interface AnswerFilledListener {
        fun onAnswerFilled(isCorrect: Boolean, questionsModel: QuestionsModel)
    }
}
